package ClassFiles;

import java.util.ArrayList;


/**
 * This classs represents a room in a hotel.
 */
public class Room {
    /**
     *  These are private attributes of the class Room.
     * 
     *  RoomName is a String that represents the name of the room.
     *  RoomPrice is a float that represents the price of the room per night
     *  RoomRate is the increase rate of the room.
     */
    protected String RoomName;
    protected float RoomPrice;
    protected float RoomRate;

    /**
     * The days assigned in the room
     */
    private ArrayList<Day> Days;



    // ----- CONSTRUCTORS ----- //

    /**
     *  Constructs a room with the provided name.
     * 
     *  @param name The name of the room.
     */
    public Room(String name, float base) {
        this.RoomName = name;
        this.RoomRate = 1.0f;
        Days = new ArrayList<Day>();

        setPrice(base);
        populateDays();
    }



    // ----- PUBLIC METHODS ----- //

    /**
     *  isFreeDays() checks if the room is free on the given range of date.
     * 
     *  @return true - room is free; false - room is booked.
     */
    public boolean isFreeDays(int dayIn, int dayOut) {
        int i;

        for (i = dayIn; i < dayOut; i++) {
            if (!Days.get(i).getStatus().equals("Free"))
                return false;
        }
        
        return true;
    }

    /**
     *  isFreeDate() checks if the room is free on a given date.
     * 
     *  @return true - the specific date is free; 
     *          false - the specific day is booked.
     */
    public boolean isFreeDate(int date) {
        String checkDay = Days.get(date - 1).getStatus();

        return !checkDay.equals("Booked");
    }

    /**
     *  isRoomFree() checks if the room is free for the whole month, i.e. no
     *      reservations.
     * 
     *  @return true - room is free; false - room is not free.
     */
    public boolean isRoomFree() {
        ArrayList<Day> days = getFreeDays();

        return days.size() == 31;
    }



    // ----- GETTERS ----- //

    /**
     * Returns the name of the room.
     * 
     * @return the room name
     */
    public String getRoomName() {
        return RoomName;
    }

    /**
     * Returns the price of the room.
     * 
     * @return the room price
     */
    public float getRoomPrice() {
        return RoomPrice;
    }

    /**
     * Returns a list of days associated with the room.
     * 
     * @return the list of days
     */
    public ArrayList<Day> getDays() {
        return Days;
    }

    /**
     * Returns a string representing the room name and type.
     * 
     * @return the room name and type (e.g. "101 -- Standard")
     */
    public String getRoomNameType() {
        return getRoomName() + " -- Standard";
    }

    /**
     * Returns the type of the room.
     * 
     * @return the room type (always "Standard")
     */
    public String getRoomType() {
        return "Standard";
    }

    /**
     *  Gets the free days (unbooked) rooms for the whole month.
     * 
     *  @return An array list of free days.
     */
    public ArrayList<Day> getFreeDays() {
        ArrayList<Day> freedays = new ArrayList<Day>();

        for (int i = 0; i < Days.size(); i++)
            if ((Days.get(i).getStatus()).equals("Free"))
                freedays.add(Days.get(i));

        return freedays;
    }

    /**
      *  Gets the day on the provided date (index - 1).
      *
      *  @param date The date to find.
      *  
      *  @return The Day object with the corresponding date.
      */
      public Day getDay(int date) {
        return Days.get(date - 1);
    }
    


    // ----- SETTERS ----- //

    /**
     *  Sets and allows updating the price.
     * 
     *  @param price The new price.
     */
    public void setPrice(float base) {
        this.RoomPrice = base * this.RoomRate;
    }

    /**
     *  Sets and allows updating the room rate.
     * 
     *  @param newRate The new room rate.
     */
    public void setRoomRate(float newRate) {
       this.RoomRate = newRate;
   }

    /**
     * Sets the days of the room
     * 
     * @param days the list of days to be passed to the room
     */
    public void setDays(ArrayList<Day> days) {
        Days = days;
    }

    /**
      *  Updates the day with the String newStatus on the given range of date
      *      (cIn to cOut)
      *
      *  @param cIn The check in date.
      *  @param cOut The check out date.
      *  @param newStatus The new status - "Booked" or "Free."
      */
      public void updateDays(int cIn, int cOut, String newStatus) {
        while (cOut >= cIn) {
            Days.get(cOut - 1).setStatus(newStatus);
            cOut -= 1;
        }
     }



    // ----- PRIVATE METHODS ----- //

    /**
     *  populateDays() populate the array list of Day with dates (1 to 31).     
     */
    private void populateDays() {
        for (int i = 1; i <= 31; i++) 
            Days.add(new Day(i));
    }
}
