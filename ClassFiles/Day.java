package ClassFiles;


/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents the days the user booked.
 */
public class Day {
    /**
     *  These are the private attributes under the class Day.
     * 
     *  Date is an integer that represents the numerical day.
     *  Status is a boolean that represents the status of a day ("Free" or "Booked").
     */
    private final int Date;
    private String Status;
    
    /**
     *  DayDPM contains the rate (mark up or mark down) assigned to the room.
     */
    private DPM DayDPM;



    // ----- CONTSTRUCTOR ----- // 
    /**
     *  Constructs an object date with the provided 
     *      numerical day (can be 1 to 31). Default status is "Free."
     * 
     *  @param date An integer that represents the day.
     */
    public Day(int date) {
        this.Date = date;
        this.Status = "Free";
        this.DayDPM = new DPM(100);
    }



    // ----- GETTERS ----- //
    /**
     *  Gets the numerical date. 
     * 
     *  @return Date the represents the day in integer.
     */
    public int getDate() {
        return Date;
    }

    /**
     *  Gets the status of the room. 
     * 
     *  @return Status that represents the room's availability.
     */
    public String getStatus() {
        return Status;
    }

    /**
     *  Gets the DPM assigned to the day.
     * 
     *  @return The date price modifier for the day.
     */
    public DPM getDPM() {
        return DayDPM;
    }

    /**
     * Returns the DPM rate (percentage form)
     * @return The rate value multipled by 100
     */
    public float getDPMRate() {
        return DayDPM.getRate() * 100;
    }



    // ----- SETTERS ----- //
    
    /**
     *  Sets the status with the provided String. 
     *  
     *  @param status The new status.
     */
    public void setStatus(String status) {
        this.Status = status;
    }

    /**
     * Sets the DPM with the provided rate
     * 
     * @param rate The new rate to set
     */
    public void setDayDPM(float rate) {
        DayDPM.setRate(rate);
    }

    /**
     * Sets the DPM with the provided DPM
     * 
     * @param newDPM the new DPM to set
     */
    public void setDPM(DPM newDPM) {
        DayDPM = newDPM;
    }
}