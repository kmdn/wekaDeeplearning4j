
package weka.dl4j.text.tokenization.tokenizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

/**
 * A DeepLearning4j's Tokenizer interface to Weka Tokenizer.
 *
 * @author Felipe Bravo-Marquez
 */
public class WekaTokenizer implements Tokenizer, Serializable {

  private static final long serialVersionUID = 4026687132223588081L;
  /**
   * The TokenPreProcess Object
   */
  private TokenPreProcess tokenPreProcess;

  /**
   * The Weka Tokenizer Object
   */
  private weka.core.tokenizers.Tokenizer wekaTokenizer;

  /**
   * The number of tokens
   */
  private int numTokens = 0;

  /**
   * initializes the Object
   *
   * @param content the String to tokenize
   * @param wekaTokenizer the WekaTokenizer Object
   */
  public WekaTokenizer(String content, weka.core.tokenizers.Tokenizer wekaTokenizer) {
    this.wekaTokenizer = wekaTokenizer;
    this.wekaTokenizer.tokenize(content);
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.tokenization.tokenizer.Tokenizer#countTokens()
   */
  @Override
  public int countTokens() {
    return this.numTokens;
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.tokenization.tokenizer.Tokenizer#getTokens()
   */
  @Override
  public List<String> getTokens() {
    List<String> tokens = new ArrayList<String>();
    while (hasMoreTokens()) {
      tokens.add(nextToken());
    }
    return tokens;
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.tokenization.tokenizer.Tokenizer#setTokenPreProcessor(org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess)
   */
  @Override
  public void setTokenPreProcessor(TokenPreProcess tokenPreProcessor) {
    this.tokenPreProcess = tokenPreProcessor;
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.tokenization.tokenizer.Tokenizer#hasMoreTokens()
   */
  @Override
  public boolean hasMoreTokens() {
    return this.wekaTokenizer.hasMoreElements();
  }

  /* (non-Javadoc)
   * @see org.deeplearning4j.text.tokenization.tokenizer.Tokenizer#nextToken()
   */
  @Override
  public String nextToken() {

    this.numTokens++;

    String base = this.wekaTokenizer.nextElement();
    if (tokenPreProcess != null) {
      base = tokenPreProcess.preProcess(base);
    }
    return base;
  }
}
