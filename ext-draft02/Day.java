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
    private int Date;
    private String Status;



    // CONTSTRUCTOR
    /**
     *  Constructs an object date with the provided 
     *      numerical day (can be 1 to 31). Default status is "Free."
     * 
     *  @param date An integer that represents the day.
     */
    public Day(int date) {
        this.Date = date;
        this.Status = "Free";
    }



    // GETTERS
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



    // SETTERS
    /**
     *  Sets the status with the provided String. 
     *  
     *  @param status The new status.
     */
    public void setStatus(String status) {
        this.Status = status;
    }
}
