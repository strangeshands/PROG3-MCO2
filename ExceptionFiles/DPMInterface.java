package ExceptionFiles;


/**
 * Interface for computing DPM (Date Price Modifier)
 */
public interface DPMInterface {
    /**
     * Computes the DPM value based on the given base value.
     * 
     * @param base the base value to compute DPM from
     * @return the computed DPM value
     */
    float computeDPM(float base);
}
