import java.util.ArrayList;

/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a room in a hotel.
 */
public class Room {
    /**
     *  These are private attributes of the class Room.
     * 
     *  RoomName is a String that represents the name of the room.
     *  RoomPrice is a float that represents the price of the room per night.
     *  Status is a String that represents the status of the room (reserved or free).
     *  Reservations is an array list of Reservation that stores the reservations made
     *      on the selected room.
     */
    protected String RoomName;
    protected float RoomPrice;
    protected ArrayList<Day> Days;
    protected float RoomRate;


     // CONSTRUCTOR
    /**
     *  Constructs a room with the provided name.
     * 
     *  @param name The name of the room.
     */
     public Room(String name) {
         this.RoomName = name;
         this.RoomPrice = 1299;
         Days = new ArrayList<Day>();
 
         // Populate Days (31 days with free status)
         populateDays();
     }



     // PUBLIC METHODS
    /**
     *  Displays the room information.
     */
    public void displayRoomInfo() {
        System.out.println(">> [PRINTING] Room Information...\n");
        System.out.println("     ROOM                :  " + this.RoomName);
        System.out.println("     PRICE/NIGHT         :  " + this.RoomPrice);
        System.out.println("     AVAILABILITY        :\n");
        displayDays(Days);
    }

    /**
     *  isFreeDays() checks if the room is free on the given range of date.
     * 
     *  @param dayIn The check in date.
     *  @param dayOut The check out date
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
     *  @param date The date to check
     * 
     *  @return true - the specific date is free; 
     *     false - the specific day is booked.
     */
     public boolean isFreeDate(int date) {
        String checkDay = Days.get(date - 1).getStatus();

        if (!checkDay.equals("Booked"))
            return true;
        
        return false;
    }

    /**
     *  isRoomFree() checks if the room is free for the whole month, i.e. no
     *      reservations.
     * 
     *  @return true - room is free; false - room is not free.
     */
    public boolean isRoomFree() {
        ArrayList<Day> days = getFreeDays();

        if (days.size() != 31)
            return false;
        
        return true;
    }


 
     // GETTERS
     /**
      *  Gets the price of the room.
      * 
      *  @return A float that represents the room price.
      */
     public float getRoomPrice() {
         return RoomPrice;
     }
 
     /**
      *  Gets the name of the room.
      * 
      *  @return A String representation of the room name.
      */
     public String getRoomName() {
         return RoomName;
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


 
     // SETTERS
     /**
      *  Sets and allows updating the price.
      * 
      *  @param price The new price.
      */
     public void setPrice(float price) {
         this.RoomPrice = price;
     }

     public void setRoomRate(float newRate) {
        this.RoomRate = newRate;
    }

     /**
      *  Updates the day with the String newStatus on the given range of date
      *      (cIn to cOut).
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


 
     // PRIVATE
     /**
      *  populateDays() populate the araay list of Day with dates (1 to 31).     
      */
     protected void populateDays() {
         for (int i = 1; i <= 31; i++) 
             Days.add(new Day(i));
     }
 
     /**
      *  Displays the days in the provided array list of days.
      * 
      *  @param list The array list of days.
      */
     protected void displayDays(ArrayList<Day> list) {
         int ctr = 0;
         int num = list.size();
 
         for (int row = 1; row <= 11 && ctr < num; row++) {
             System.out.print("       | ");
             for (int col = 1; col <= 3 && ctr < num; col++) {
                 System.out.print("June " + list.get(ctr).getDate() + " [" + 
                             list.get(ctr).getStatus() + "] | ");
                 ctr++;
             }
             System.out.println("\n");
         }
     }
 }
 