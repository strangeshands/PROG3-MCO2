package MVCFiles;


/**
 * Calendar model for selection of dates in the calendar.
 * 
 * @author TOLENTINO, HEPHZI
 * @author SANTOS, FRANCINE
 */
public class CalendarModel {
    /**
     * The check in and out dates.
     */
    private int date1st;
    private int date2nd;



    /**
     * Constructs a new calendar model.
     */
    public CalendarModel() {
        date1st = 0;
        date2nd = 0;
    }



    /**
     * Gets the 1st date
     * 
     * @return The first date
     */
    public int getStartDay() {
        return date1st;
    }

    /**
     * Gets the 2nd date
     * 
     * @return The 2nd date
     */
    public int getEndDay() {
        return date2nd;
    }


    
    /**
     * Sets the 1st date
     * 
     * @param date The date to set
     */
    public void setDate1st(int date) {
        date1st = date;
    }

    /**
     * Sets the 2nd date
     * 
     * @param date The date to set
     */
    public void setDate2nd(int date) {
        date2nd = date;
    }
}
