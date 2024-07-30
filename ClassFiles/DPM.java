package ClassFiles;

import ExceptionFiles.*;


/**
 * The class for Date Price Modifier extension.
 * 
 * @author SANTOS, FRANCINE
 * @author TOLENTINO, HEPHZI
 */
public class DPM implements DPMInterface{
    /**
     * The rate (multiplier) for the room price
     */
    private float rate;



    /**
     * The rate assigned to the day.
     * @param rate The day's rate.
     */
    public DPM(int rate) {
        this.rate = (float) rate / 100;
    }



    /**
     * Returns the float representation of the day's rate.
     * @return Rate representing the day's rate.
     */
    public float getRate() {
        return rate;
    }

    /**
     * Sets the rate for the room.
     * Used in rate modification.
     * 
     * @param rate The new rate assigned by the user.
     */
    public void setRate(float rate) {
        this.rate = (float) rate / 100f;
    }

    /**
     * Computes the new price based on the rate of the room.
     * 
     * @param basePrice The room's most basic price.
     * @return A float representation of the price after
     *         applying the new rate.
     */
    public float computeDPM(float basePrice) {
        return basePrice * rate * 100;
    }
}