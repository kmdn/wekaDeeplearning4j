
package weka.dl4j.distribution;

import java.util.Enumeration;
import org.nd4j.shade.jackson.annotation.JsonTypeName;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.OptionMetadata;

/**
 * A version of DeepLearning4j's LogNormalDistribution that implements WEKA option handling.
 *
 * @author Eibe Frank
 * @author Steven Lang
 */
@JsonTypeName("lognormal")
public class LogNormalDistribution extends Distribution<org.deeplearning4j.nn.conf.distribution.LogNormalDistribution>
    implements OptionHandler {

  private static final long serialVersionUID = 1244534661808099971L;

  @OptionMetadata(
    displayName = "mean",
    description = "The mean (default = 1e-3).",
    commandLineParamName = "mean",
    commandLineParamSynopsis = "-mean <double>",
    displayOrder = 1
  )
  public double getMean() {
    return backend.getMean();
  }

  public void setMean(double mean) {
    backend.setMean(mean);
  }

  @OptionMetadata(
    displayName = "standard deviation",
    description = "The standard deviation (default = 1).",
    commandLineParamName = "std",
    commandLineParamSynopsis = "-std <double>",
    displayOrder = 2
  )
  public double getStd() {
    return backend.getStd();
  }

  public void setStd(double std) {
    backend.setStd(std);
  }

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
    // Constructions normal distribution with mean 0 and unit variance
    backend = new org.deeplearning4j.nn.conf.distribution.LogNormalDistribution(1e-3, 1.0);
  }
}
