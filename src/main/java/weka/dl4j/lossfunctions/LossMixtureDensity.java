
package weka.dl4j.lossfunctions;

import java.util.Enumeration;
import org.nd4j.shade.jackson.annotation.JsonTypeName;
import weka.core.Option;
import weka.core.OptionHandler;

/**
 * A version of DeepLearning4j's LossMixtureDensity that implements WEKA option handling.
 *
 * @author Eibe Frank
 */
@JsonTypeName("MixtureDensity")
public class LossMixtureDensity extends LossFunction<org.nd4j.linalg.lossfunctions.impl.LossMixtureDensity>
    implements OptionHandler {

  private static final long serialVersionUID = 5254528896658149765L;

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {

    return Option.listOptionsForClass(this.getClass()).elements();
  }

  /**
   * Gets the current settings of the Classifier.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {

    return Option.getOptions(this, this.getClass());
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @exception Exception if an option is not supported
   */
  public void setOptions(String[] options) throws Exception {

    Option.setOptions(options, this, this.getClass());
  }

  @Override
  public void initializeBackend() {
    backend = new org.nd4j.linalg.lossfunctions.impl.LossMixtureDensity();
  }
}
