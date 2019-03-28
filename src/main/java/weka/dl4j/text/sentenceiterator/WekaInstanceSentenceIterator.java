
package weka.dl4j.text.sentenceiterator;

import java.util.Arrays;
import java.util.Iterator;
import org.deeplearning4j.text.sentenceiterator.BaseSentenceIterator;
import weka.core.Instance;
import weka.core.Instances;

/**
 * A Deeplearning4j's sentence iterator for Weka Instances. It considers Strings from a given
 * attribute index. If the String attribute has multiple lines, they are splitted.
 *
 * @author Felipe Bravo-Marquez
 */
public class WekaInstanceSentenceIterator extends BaseSentenceIterator {

  /**
   * The Weka Instances object to process
   */
  private Instances instances;

  /**
   * The attribute String index
   */
  private int index;

  /**
   * The Weka Instance iterator
   */
  private Iterator<Instance> iterator;

  /**
   * An internal iterator for string attributes with multiple lines
   */
  private Iterator<String> internalIt;

  /**
   * A flag indicator for string attributs with multiple lines
   */
  private boolean interItOn;

  /**
   * initializes the Object
   *
   * @param instances the Weka Instances object
   * @param index the attribute index
   */
  public WekaInstanceSentenceIterator(Instances instances, int index) {
    this.instances = instances;
    this.index = index;
    this.iterator = this.instances.iterator();
    this.interItOn = false;
  }

  /**
   * Gets the next String from a Weka Instance
   *
   * @return the String value
   */
  public String getNextWekaString() {
    Instance inst = this.iterator.next();
    return inst.stringValue(this.index);
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.sentenceiterator.SentenceIterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    if (this.interItOn) {
      return this.iterator.hasNext() || this.internalIt.hasNext();
    } else {
      return this.iterator.hasNext();
    }
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.sentenceiterator.SentenceIterator#nextSentence()
   */
  @Override
  public String nextSentence() {
    if (!this.interItOn) {
      String line = getNextWekaString();
      String[] intLines = line.split("\n+");
      if (intLines.length > 1) {
        this.internalIt = Arrays.asList(intLines).iterator();
        this.interItOn = true;
        return this.internalIt.next();
      }
      return line;

    } else {
      if (this.internalIt.hasNext()) {
        return this.internalIt.next();
      } else {
        this.interItOn = false;
        String line = getNextWekaString();
        String[] intLines = line.split("\n+");
        if (intLines.length > 1) {
          this.internalIt = Arrays.asList(intLines).iterator();
          this.interItOn = true;
          return this.internalIt.next();
        }
        return line;
      }
    }
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.sentenceiterator.SentenceIterator#reset()
   */
  @Override
  public void reset() {
    this.iterator = this.instances.iterator();
    this.internalIt = null;
    this.interItOn = false;
  }
}
