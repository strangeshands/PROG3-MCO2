package ClassFiles;

import java.util.ArrayList;


/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a reservation made by the user.
 */
public class Reservation {
    /**
     *  These are private attributes under the class Reservation.
     *  
     *  ReserveName is a String that represents the person that reserved a room.
     *  Status is the status of the reservation ("Reserved" or "Cancelled").
     * 
     *  CheckIn is the integer check in date.
     *  CheckOut is the integer check out date.
     * 
     *  discountedPrice is the discounted price after vouchers.
     *  originalPrice is the total price of the reservation without discounts.
     *  
     *  ReserveDays is an array list of Day that stores the span of days in the
     *      reservation.
     * 
     *  ReserveRoom is the room chosen by the guest.
     * 
     *  Voucher is the voucher used for the reservation
     */
    private String ReserveFirstName;
    private String ReserveLastName;

    private String Status;

    private int CheckIn;
    private int CheckOut;

    private float discountedPrice;
    private float originalPrice;

    private ArrayList<Day> ReserveDays;

    private Room ReserveRoom;

    private Voucher Voucher;



    // ----- CONSTRUCTORS ----- //

    /**
     *  Constructs an instance of Reservation with the provided name, check in date,
     *      and check out date. Also calls reserveDays() to populate 
     *      the array list ReserveDays based on cIn and cOut.
     * 
     *  @param room The room of reservation.
     *  @param fName The first name of the person who made the reservation.
     *  @param lName The last name of the person who made the reservation. 
     *  @param cIn The check in date of the reservation.
     *  @param cOut The check out date of the reservation.
     */
    public Reservation(Room room, String fName, String lName, int cIn, int cOut) {
        this.ReserveFirstName = fName;
        this.ReserveLastName = lName;
        this.CheckIn = cIn;
        this.CheckOut = cOut;
        this.Status = "Reserved";
        this.ReserveRoom = room;

        // Populating the ReserveDays
        this.ReserveDays = new ArrayList<Day>();
        reserveDays(cIn, cOut);

        // Assigns the total price
        this.discountedPrice = computeTotalPrice(room.getRoomPrice());
        this.originalPrice = computeTotalPrice(room.getRoomPrice());

        // Sets the voucher to null at first
        this.Voucher = null;
    }



    // ----- PUBLIC METHODS ----- //

    /**
     *  Adds voucher in the reservation.
     * 
     *  @param voucher The voucher to be added
     */
    public void addVoucher(Voucher voucher) {
        Voucher = voucher;
        setDiscountedPrice();
    }

    

    // ----- GETTERS ----- //

    /**
     *  Gets the span of days of the reservation
    * 
    *  @return An array list of Day that represents the reservation days.
    */
    public ArrayList<Day> getReserveDays() {
        return ReserveDays;
    }

    /**
     *  Gets the String reserver first name.
    * 
    *  @return A String that represents the first name of the person who made
    *      the reservation.
    */
    public String getReserveFName() {
        return ReserveFirstName;
    }

    /**
     *  Gets the String reserver last name.
    * 
    *  @return A String that represents the last name of the person who made
    *      the reservation.
    */
    public String getReserveLName() {
        return ReserveLastName;
    }

    /**
     *  Gets the String reserver name.
    * 
    *  @return A String that represents the name of the person who made
    *      the reservation.
    */
    public String getReserveName() {
        return ReserveFirstName + " " + ReserveLastName;
    }

    /**
     *  Gets the room assigned to the reservation.
    * 
    *  @return The Room assigned to the reservation.
    */
    public Room getRoom() {
        return ReserveRoom;
    }

    /**
     *  Gets the check in date of the reservation.
    * 
    *  @return An integer that represents the check in date obtained
    *      from the array list ReserveDays
    */
    public int getCheckIn() {
        return ReserveDays.get(0).getDate();
    }

    /**
     *  Gets the check out date of the reservation.
    * 
    *  @return An integer that represents the check out date obtained
    *      from the array list ReserveDays
    */
    public int getCheckOut() {
        return ReserveDays.get(ReserveDays.size() - 1).getDate();
    }

    /**
     *  Gets the total price of the reservation.
    * 
    *  @return The float total price of the reservation.
    */
    public float getDiscountedPrice() {
        return discountedPrice;
    }

    /**
     *  Gets the status of the reservation.
    * 
    *  @return The String status of the reservation.
    */
    public String getStatus() {
        return Status;
    }

    /**
     * Returns the voucher object.
     * 
     * @return the voucher object
     */
    public Voucher getVoucher() {
        return Voucher;
    }

    /**
     * Returns the original price of the reservation.
     * 
     * @return the original price of the reservation
     */
    public float getOriginalPrice() {
        return originalPrice;
    }

    /**
     * The DPM rate at the selected index
     * 
     * @param index The index of the day to access, usually the n+1th day
     * 
     * @return The rate assigned to that day
     */
    public float getDPMRate(int index) {
        return ReserveDays.get(index).getDPMRate();
    }

    

    // ----- SETTERS ----- //
    
    /**
     *  This updates the total price. Usually used when there are discounts.
     * 
     *  @param newPrice The new price (usually discounted).
     */
    public void setDiscountedPrice() {
        if (!(Voucher instanceof StayVoucher))
            discountedPrice = Voucher.applyDiscount(originalPrice);
        else {
            ((StayVoucher)Voucher).setDiscount(this);
            discountedPrice = Voucher.applyDiscount(originalPrice);
        }

    }

    /**
     *  Voids the reservation if it is not made.
     */
    public void voidReservation() {
        ReserveRoom.updateDays(CheckIn, CheckOut, "Free");
    }



    // ----- PRIVATE METHODS ----- //
    
    /**
     *  reserveDays() populates the array list ReserveDays based on cIn and cOut.
     *  Also updates the array list of Day of the room.
     *  
     *  Set to private since only this class will use it.
     * 
     *  @param cIn check in date
     *  @param cOut check out date
     */
    private void reserveDays(int cIn, int cOut) {
        ReserveRoom.updateDays(cIn, cOut - 1, "Booked");

        Day newDay;
        for (int i = cIn; i <= cOut; i++) {
            newDay = new Day(i);

            /* 
                This sets the check out day as "Free" but still included in the
                    days of reservation.
            */
            if (cIn < cOut)
                newDay.setStatus("Booked");
            else
                newDay.setStatus("Free"); 

            // Create a copy of the DPM object
            newDay.setDayDPM(ReserveRoom.getDay(i).getDPMRate());

            ReserveDays.add(newDay);
        }
    }

    /**
     *  computeTotalPrice() computes the total price provided with the base
     *      price of the room.
     * 
     *  @param base The base price of the room per night.
     *  @return The total price of the room (base * number of nights)
     */
    private float computeTotalPrice(float base) {
        int i;
        int days = CheckOut - CheckIn;
        float newPrice = 0f;

        i = 0;
        while (i < days) {
            newPrice += ReserveDays.get(i).getDPM().computeDPM(base);
            i++;
        }

        return newPrice;
    }
}
