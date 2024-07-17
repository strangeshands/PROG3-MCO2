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
      *  CheckIn is the integer check in date.
      *  CheckOut is the integer check out date.
      *  totalPrice is the total price of the reservation (base price * # of nights).
      *  Status is the status of the reservation ("Reserved" or "Cancelled").
      *  ReserveDays is an array list of Day that stores the span of days in the
      *      reservation.
      *  ReserveRoom is the room chosen by the guest.
      */
     private String ReserveFirstName;
     private String ReserveLastName;

     private String Status;

     private int CheckIn;
     private int CheckOut;

     private float totalPrice;
     private float newRate = 1f;
     private String discountCode = " ";
     private ArrayList<DPM> ratesList;

     private ArrayList<Day> ReserveDays;
     private Room ReserveRoom;



     // CONSTRUCTORS
     /**
      *  Constructs an instance of Reservation with the provided room, name, check in date,
      *      and check out date. Also calls reserveDays() to populate 
      *      the array list ReserveDays based on cIn and cOut.
      * 
      *  @param room The room of reservation.
      *  @param fName The first name of the person who made the reservation.
      *  @param lName The last name of the person who made the reservation.
      *  @param cIn The check in date of the reservation.
      *  @param cOut The check out date of the reservation.
      */
     public Reservation(Room room, String fName, String lName, 
                        int cIn, int cOut) {
         this.ReserveFirstName = fName;
         this.ReserveLastName = lName;
         this.CheckIn = cIn;
         this.CheckOut = cOut;
         this.ratesList = new ArrayList<DPM>();
         this.Status = "Reserved";
         this.ReserveRoom = room;

         // Assigns the total price
         computeTotalPrice(room.getRoomPrice());
 
         // Populating the ReserveDays
         this.ReserveDays = new ArrayList<Day>();
         reserveDays(cIn, cOut);
     }



     // PUBLIC METHODS
     /**
      *  printReserveDetails() prints all the details of the reservation.
      */
     public void printReserveDetails() {
         int day = CheckOut - CheckIn + 1;
         int night = CheckOut - CheckIn;
 
         System.out.println("     REGISTERED NAME: " + getReserveName());
         System.out.println("     STATUS: " + Status);
         System.out.println("     CHECK IN: June " + CheckIn);
         System.out.println("     CHECK OUT: June " + CheckOut);
         System.out.println("          >> " + day + " DAYS, " + night + " NIGHTS");
         System.out.println("     ROOM: " + ReserveRoom.getRoomName());
         
         System.out.print("     ROOM TYPE: ");
         if (!(ReserveRoom instanceof DeluxeRoom) &&
            !(ReserveRoom instanceof ExecutiveRoom))
            System.out.println("STANDARD");
         else if (ReserveRoom instanceof DeluxeRoom)
            System.out.println("DELUXE");
         else
            System.out.println("EXECUTIVE");

         System.out.println("     PRICING: ");
         System.out.println("          BREAKDOWN:   " + ReserveRoom.getRoomPrice() + " x " + night);

         if (newRate != 1f) {
            if (newRate < 1f)
                System.out.println("          DATE MARK DOWN: " + newRate + "%");
            else if (newRate > 1f)
                System.out.println("          DATE MARK UP: " + newRate + "%");
         }

         if (!discountCode.equals("N/A") && 
            discountCode.trim().isEmpty() == false) {
            System.out.print("          DISCOUNT:    ");

            if (discountCode.equals("I_WORK_HERE"))
                System.out.println("10%");
            else if (discountCode.equals("STAY4_GET1"))
                System.out.println("-" + ReserveRoom.getRoomPrice());
            else if (discountCode.equals("PAYDAY"))
                System.out.println("7%");
         }

         System.out.println("                     -------------");
         System.out.println("          TOTAL PRICE: " + getTotalPrice());
     }


 
     // GETTERS
     /**
      *  Gets the span of days of the reservation.
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
      *      from the array list ReserveDays.
      */
     public int getCheckIn() {
         return ReserveDays.get(0).getDate();
     }
 
     /**
      *  Gets the check out date of the reservation.
      * 
      *  @return An integer that represents the check out date obtained
      *      from the array list ReserveDays.
      */
     public int getCheckOut() {
         return ReserveDays.get(ReserveDays.size() - 1).getDate();
     }
 
     /**
      *  Gets the total price of the reservation.
      * 
      *  @return The float total price of the reservation.
      */
     public float getTotalPrice() {
         return totalPrice;
     }
 
     /**
      *  Gets the status of the reservation.
      * 
      *  @return The String status of the reservation.
      */
     public String getStatus() {
         return Status;
     }

     public void setRatesList (ArrayList<DPM> ratesList) {
        this.ratesList = ratesList;
     }

     public DPM findRate(int length) {
        int i;

        for (i = 0; i < ratesList.size(); i++) {
            if (ratesList.get(i).getDaysLength() == length)
                return ratesList.get(i);
        }

        return null;
    }

     public float isCodeValid(String code, int cIn, int cOut) {
        if (code.trim().equals("N/A") ||
            code.trim().isEmpty() == true)
            return 1f;
        else if (!code.equals("I_WORK_HERE") &&
                !code.equals("STAY4_GET1") &&
                !code.equals("PAYDAY"))
            return -1f;
        else {
            if (code.equals("STAY4_GET1") && cOut - cIn <= 5)
                return -1f;
            else if (code.equals("PAYDAY") && 
                (cOut == 15 || cOut == 30))
                return -1f;
            
            this.discountCode = code;
            return 0f;
        }
     }

     public float computeDiscount(String discountCode, int cIn, 
                                int cOut, float basePrice) {
        float newPrice = -1f;

        if (discountCode.equals("I_WORK_HERE")) {
            I_WORK_HERE code = new I_WORK_HERE();
            newPrice = code.applyDiscount(basePrice);
        }
        else if (discountCode.equals("STAY4_GET1")) {
            STAY4_GET1 code = new STAY4_GET1();
            newPrice = code.applyDiscount(basePrice, (cOut-cIn));
        }
        else if (discountCode.equals("PAYDAY")) {
            PAYDAY code = new PAYDAY();
            newPrice = code.applyDiscount(basePrice, cIn, cOut);
        }

        return newPrice;
    }

    
     // PRIVATE METHODS
     /**
      *  reserveDays() populates the array list ReserveDays based on cIn and cOut.
      *  Also updates the array list of Day of the room.
      *  
      *  Set to private since only this class will use it.
      */
     private void reserveDays(int cIn, int cOut) {
        ReserveRoom.updateDays(cIn, cOut, "Booked");
        for (int i = cIn; i <= cOut; i++) {
            ReserveDays.add(ReserveRoom.getDay(i)); 
        }
     }

     /**
      *  computeTotalPrice() computes the total price provided with the base
      *      price of the room.
      * 
            *NOTE:
            computation order
            - base
            - date price modifier
            - discount codes

      *  @param base The base price of the room per night.
      *  @return The total price of the room (base * number of nights).
      */
     public void computeTotalPrice(float basePrice) {
         int nights = CheckOut - CheckIn;
         float newPrice = basePrice * nights;
         DPM newRateDPM = findRate(nights);

        if (newRateDPM != null) {
            this.newRate = newRateDPM.getRate();
            
            if (newRateDPM.getRate() < 1f)
                newPrice = newRateDPM.lessDPM(newPrice);
            else if (newRateDPM.getRate() > 1f)
                newPrice = newRateDPM.moreDPM(newPrice);
        }
        
        if (!discountCode.equals("N/A") &&
            discountCode.trim().isEmpty() == false) {

            if (discountCode.equals("STAY4_GET1"))
                newPrice = computeDiscount(discountCode, CheckIn, CheckOut, ReserveRoom.getRoomPrice());
            else
                newPrice = computeDiscount(discountCode, nights, nights, newPrice);
        }

        this.totalPrice = newPrice;
     }
 }
 