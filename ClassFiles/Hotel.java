package ClassFiles;

import java.util.ArrayList;
import javax.swing.JDialog;
import DialogFiles.*;
import ExceptionFiles.*;


/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a hotel.
 */
public class Hotel {
    /**
     *  These are private attributes of the class Hotel.
     * 
     *  HotelName represents the name of the Hotel.
     *  HotelRooms is an array list of Room which stores the rooms
     *      of the hotel (1 to 50).
     *  EstimatedEarning is the totality of reservation price for the month.
     *  BasePrice is the base price of rooms.
     *  HotelReservations is an array list of Reservation which stores the
     *      reservations of the hotel.
     */
    private String HotelName;
    private ArrayList<Room> HotelRooms;
    private double EstimatedEarning;
    private float BasePrice;
    private ArrayList<Reservation> HotelReservations;

    /**
     * VoucherList is the list of available vouchers in the hotel
     * dpmList is the list of date price modifier for the hotel
     */
    private ArrayList<Voucher> VoucherList;
    private ArrayList<DPM> dpmList;



    // ----- CONSTRUCTORS ----- //
    
    /**
     *  Constructs a new Hotel with the provide hotel name.
     * 
     *  @param hotelName The provided hotel name, usually a user input.
     */
    public Hotel(String hotelName) {
        this.HotelName = hotelName;
        this.HotelRooms = new ArrayList<Room>();
        this.HotelReservations = new ArrayList<Reservation>();
        this.EstimatedEarning = 0;
        this.BasePrice = 1299;

        // EXTENSIONS:
        populateVoucherList();
        initDPM();
    }



    // ----- PUBLIC METHODS ----- //

    /**
     *  [DONE] Adds a room depending on the room count provided.
     * 
     *  @param stndrdNum The number of standard rooms to be added.
     *  @param delNum The number of deluxe rooms to be added.
     *  @param execNum The number of executive rooms to be added.
     */
    public void addRoom(int stndrdNum, int delNum, int execNum) {
        addStandardRoom(stndrdNum);
        addDeluxeRoom(delNum);
        addExecutiveRoom(execNum);
    }

    /**
     *  [DONE] Shows the information of the hotel according to the date
     *      in a form of JDialog.
     * 
     *  @param date The date to be searched.
     */
    public void searchByDate(int date) {
        @SuppressWarnings("unused")
        HotelInfo sbDate = new HotelInfo(this, date);
    }

    /**
     *  [DONE] Shows hotel information and the room information in a
     *      form of JDialog.
     * 
     *  @param name The room to be searched.
     */
    public boolean searchByRoom(String name) {
        try {
            String roomName = name.substring(0, 3);
            Room currRoom = findRoom(roomName);

            @SuppressWarnings("unused")
            HotelInfo sbRoom = new HotelInfo(this, currRoom);

            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Seacrhes the reservation and shows the information in the
     *      form of JDialog.
     * 
     *  @param inputs The inputs needed for searching reservation.
     *  @param cIn The check in date
     *  @param cOut The check out date
     *  @return true: reservation found, false otherwise
     */
    public boolean searchByReserve(String[] inputs, int cIn, int cOut) {
        Reservation currReserv = LinearSearch_Reservation(HotelReservations, inputs[2], 
                    inputs[3], cIn, cOut, inputs[1].substring(0, 3));

        if (currReserv != null) {
            @SuppressWarnings("unused")
            HotelInfo sbReserv = new HotelInfo(this, currReserv);
            return true;
        }

        return false;
    }

    /**
     *  [DONE] Removes room depending on the room indices provided.
     * 
     *  @param roomF The index of first room to be removed.
     *  @param roomL The index of last room to be removed.
     */
    public void removeRoom(int roomF, int roomL) {
        if (roomF == roomL) {   // for only one room
            HotelRooms.remove(roomF);
        }
        if (roomF != roomL) {  // for more than one room
            for (int i = roomF; i <= roomL; i++)
                HotelRooms.remove(roomF);
        }
    }

    /**
     *  [DONE] Allows modification of price.
     * 
     *  @param base The new base price.
     */
    public void changeRoomPrice(float base) {
        BasePrice = base;
        for (int i = 0; i < HotelRooms.size(); i++)
            HotelRooms.get(i).setPrice(BasePrice);
    }

    /**
     *  [DONE] Finds the reservation based on the provided parameters.
     * 
     *  @param fName First name of the guest.
     *  @param lName Last name of the guest.
     *  @param cIn The check-in date.
     *  @param cOut The check-out date.
     *  @param room The name of the room reserved.
     * 
     *  @return true - reservation found;
     *          false - reservation not found
     */
    public boolean checkReservation(String fName, String lName, int cIn, 
    int cOut, String room) 
    {
        Reservation find = LinearSearch_Reservation(HotelReservations, fName, 
                                                        lName, cIn, cOut, room);
        if (find != null)
            return true;
        
        return false;
    }

    /**
     *  [DONE] Removes the reservation based on the provided parameters.
     * 
     *  @param fName First name of the guest.
     *  @param lName Last name of the guest.
     *  @param cIn The check-in date.
     *  @param cOut The check-out date.
     *  @param room The name of the room reserved.
     */
    public void removeReservation(String fName, String lName, int cIn, 
    int cOut, String room) 
    {
        Reservation find = LinearSearch_Reservation(HotelReservations, fName, 
                                                    lName, cIn, cOut, room);
        HotelReservations.remove(find);
    }

    /**
     *  [DONE] Updates the DPM with the provided date and rate
     * 
     *  @param date The date chosen
     *  @param rate The new rate for that day
     */
    public void updateDPM(int date, float rate) {
        
        this.dpmList.get(date - 1).setRate(rate);
        
        for (int i = 0; i < HotelRooms.size(); i++)
            passDPM(HotelRooms.get(i));
    }

    /**
     * [DONE] Creates a new discount voucher
     * 
     * @param code The code of the voucher
     * @param rate The discount rate 
     * 
     * @return true if creation success; false otherwise
     */
    public boolean createVoucherType1(String code, float rate) {
        if (searchVoucher(code) ==  null) {
            DiscountVoucher newVoucher = new DiscountVoucher(code, rate);
            VoucherList.add(newVoucher);
            return true;
        }

        return false;
    }

    /**
     * [DONE] Creates a new date voucher
     * 
     * @param code The code of the voucher
     * @param rate The discount rate 
     * @param date1 The first date to check
     * @param date2 The second date to check
     * 
     * @return true if creation success; false otherwise
     */
    public boolean createVoucherType2(String code, float rate, int date1, int date2) {
        if (searchVoucher(code) ==  null) {
            DateVoucher newVoucher = new DateVoucher(code, rate, date1, date2);
            VoucherList.add(newVoucher);
            return true;
        }

        return false;
    }

    /**
     * [DONE] Creates a new stay voucher
     * 
     * @param code The code of the voucher
     * @param rate The discount rate 
     * @param length The length of stay to check
     * @param day The day where the discount is applied
     * 
     * @return true if creation success; false otherwise
     */
    public boolean createVoucherType3(String code, float rate, int length, int day) {
        if (searchVoucher(code) ==  null) {
            StayVoucher newVoucher = new StayVoucher(code, rate, length, day);
            VoucherList.add(newVoucher);
            return true;
        }

        return false;
    }

    /**
     *  [DONE] Creates a reservation with the provided inputs and dates.
     * 
     *  @param inputs The String array of inputs consisting of first name,
     *      last name, and room name.
     *  @param cIn The check in date.
     *  @param cOut The check out date.
     * 
     *  @return  1 - creation successful
     *           2 - invalid voucher code || voucher code can't be applied
     *           3 - invalid days
     *           4 - an error happened
     */
    public int createReservation(String[] inputs, int cIn, int cOut) {
        try {
            boolean flag = true;
            Room currRoom = findRoom(inputs[1].substring(0, 3));

            if (currRoom.isFreeDays(cIn, cOut)) {
                Reservation newReserv = new Reservation(currRoom, inputs[2], 
                                                            inputs[3], cIn, cOut);
                if (!inputs[6].isEmpty()) {
                    try {
                        // Searches and checks the voucher
                        Voucher voucher = searchVoucher(inputs[6]);
                        voucherExists(voucher);
                        voucherApplicable(voucher, newReserv);

                        // Adds the voucher
                        newReserv.addVoucher(voucher);
                    } catch (VoucherException e) {
                        // Voids the reservation (a.k.a cancels it)
                        newReserv.voidReservation();
                        currRoom.updateDays(cIn, cOut, "Free");
                        return 2;
                    }
                }

                if (flag) {
                    HotelReservations.add(newReserv);
                    HotelInfo details = new HotelInfo(this, newReserv);
                    details.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    return 1;
                }
            }

            return 3;
        } catch (NullPointerException e) {
            return 4;
        }
    }

    /**
     * [DONE] Removes the voucher selected 
     * 
     * @return  1 - removal success;
     *          2 - voucher not found;
     *          3 - voucher cannot be deleted
     */
    public int removeVoucher(String code) {
        Voucher vouch = searchVoucher(code);

        if (vouch == null)
            return 2;
        if (vouch == VoucherList.get(0) || 
            vouch == VoucherList.get(1) || 
            vouch == VoucherList.get(2))
            return 3;

        VoucherList.remove(vouch);
        return 1;
    }



    // ----- GETTERS ----- //

    /**
     *  Gets the free rooms on the provided date.
     * 
     *  @param date The date to be checked.
     * 
     *  @return An array list of rooms (free/unbooked rooms).
     */
    public ArrayList<Room> getFreeRooms(int date) {
        ArrayList<Room> freeRooms = new ArrayList<Room>();

        for (int i = 0; i < HotelRooms.size(); i++) {
            if (HotelRooms.get(i).isFreeDate(date))
                freeRooms.add(HotelRooms.get(i));
        }
        return freeRooms;
    }

    /**
     *  Gets the booked rooms on the provided date.
     * 
     *  @param date The date to be checked.
     * 
     *  @return An array list of rooms (booked rooms).
     */
    public ArrayList<Room> getBookedRooms(int date) {
        ArrayList<Room> bookedRooms = new ArrayList<Room>();

        for (int i = 0; i < HotelRooms.size(); i++) {
            if (!HotelRooms.get(i).isFreeDate(date))
                bookedRooms.add(HotelRooms.get(i));
        }
        return bookedRooms;
    }

    /**
     * Gets the name of the hotel.
     * 
     * @return The hotel name.
     */
    public String getHotelName() {
        return HotelName;
    }

    /**
     * Gets the list of all rooms in the hotel.
     * 
     * @return An array list of rooms.
     */
    public ArrayList<Room> getRoomList() {
        return HotelRooms;
    }

    /**
     * Gets the estimated earning of the hotel.
     * 
     * @return The estimated earning.
     */
    public double getEstimatedEarning() {
        return EstimatedEarning;
    }

    /**
     * Gets the list of all reservations in the hotel.
     * 
     * @return An array list of reservations.
     */
    public ArrayList<Reservation> getReservationList() {
        return HotelReservations;
    }

    /**
     * Gets the price of a room based on its type.
     * 
     * @param type The type of room (1 for standard, 2 for deluxe, 3 for executive).
     * @return The price of the room.
     */
    public float getRoomPrice(int type) {
        for (int i = 0; i < HotelRooms.size(); i++) {
            switch (type) {
                case 2 -> {
                    if (HotelRooms.get(i) instanceof DeluxeRoom)
                        return HotelRooms.get(i).getRoomPrice();
                }
                case 3 -> {
                    if (HotelRooms.get(i) instanceof ExecutiveRoom)
                        return HotelRooms.get(i).getRoomPrice();
                }
                default -> {
                    if (!(HotelRooms.get(i) instanceof ExecutiveRoom) &&
                        !(HotelRooms.get(i) instanceof DeluxeRoom))
                        return HotelRooms.get(i).getRoomPrice();
                }
            }
        }

        return 0;
    }

    /**
     * Gets the voucher list
     * 
     * @return the voucher list of the hotel
     */
    public ArrayList<Voucher> getVoucherList() {
        return VoucherList;
    }

    /**
     * Gets the DPM list
     * 
     * @return the DPM list
     */
    public ArrayList<DPM> getDpmList() {
        return dpmList;
    }



    // ----- SETTERS ----- //

    /**
     * Sets the hotel name with the new name.
     * 
     * @param newName The new name of the hotel.
     */
    public void setHotelName(String newName) {
        HotelName = newName;
    }



    // ----- CHECKERS ----- //

    /**
     * Checks if the total room count is within the allowed range.
     * 
     * @param stndrdNum  the number of standard rooms
     * @param delNum     the number of deluxe rooms
     * @param execNum    the number of executive rooms
     * @return true if the total room count is between 1 and 50, false otherwise
     */
    public boolean checkRoomCount(int stndrdNum, int delNum, int execNum) {
        int check = stndrdNum + delNum + execNum + HotelRooms.size();

        if (check >= 1 && check <= 50)
            return true;
        else
            return false; 
    }

    /**
     * Checks if removing a range of rooms will leave at least one room.
     * 
     * @param room1  the starting room number
     * @param room2  the ending room number
     * @return true if removing the rooms will leave at least one room, false otherwise
     */
    public boolean checkRoomCountRemove(int room1, int room2) {
        int check = room2 - room1 + 1;

        if (HotelRooms.size() - check >= 1)
            return true;
        else
            return false; 
    }

    /**
     *  Checks the status of the rooms - if it is booked or no.
     * 
     *  @param roomF The first room to chekc.
     *  @param roomL The last room to check.
     * 
     *  @return true - room/s are free
     *          false - room/s are booked
     */
    public boolean checkRoomStatus(int roomF, int roomL) {
        int i;
        Room currRoom;

        // checking if the rooms are all free for any day
        i = roomF;
        while (i <= roomL) {
            currRoom = HotelRooms.get(i);

            if (!currRoom.isRoomFree())
                return false;

            i++;
        }

        return true;
    }



    // ----- PRIVATE METHODS ----- //

    /**
     *  Generates room name in the format: <floor #><room #>. 
     *  Example: 101, 102, ..., 110
     * 
     *  @param roomCount The number of rooms where the room name is also based.
     * 
     *  @return An integer that represents the roomNumber.
     */
    private int generateRoomNumber(int roomCount) {
        int roomFloor, roomNumber;

        roomCount += 1;

        if (roomCount % 10 == 0) {
            roomFloor = roomCount / 10 * 100;
            roomNumber = roomFloor + ((roomCount + 1) % 10) * 10;
        }
        else {
            roomFloor = ((roomCount / 10) + 1) * 100;
            roomNumber = roomFloor + (roomCount % 10);
        }

        return roomNumber;
    }

    /**
     * Finds a room by its name.
     * 
     * @param name The name of the room to find.
     * @return The room object if found; null if not found.
     */
    private Room findRoom(String name) {
        String currName;

        for (int i = 0; i < HotelRooms.size(); i++) {
            currName = HotelRooms.get(i).getRoomName();
            if (currName.equals(name))
                return HotelRooms.get(i);
        }

        return null;
    }

    /**
     * Adds a standard room to the hotel.
     * 
     * @param num The number of standard rooms to add.
     */
    private void addStandardRoom(int num) {
        String roomName;
        int count, roomNumber;
        Room newRoom;

        for (int i = 1; i <= num; i++) {
            count = checkRoomNumber();

            if (count == -1) {
                roomNumber = generateRoomNumber(HotelRooms.size());
                roomName = Integer.toString(roomNumber);

                newRoom = new Room(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(newRoom);
            }
            else {
                roomNumber = generateRoomNumber(count);
                roomName = Integer.toString(roomNumber);

                newRoom = new Room(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(count, newRoom);
            }
        }
    }

    /**
     * Adds a deluxe room to the hotel.
     * 
     * @param num The number of deluxe rooms to add.
     */
    private void addDeluxeRoom(int num) {
        String roomName;
        int count, roomNumber;
        DeluxeRoom newRoom;

        for (int i = 1; i <= num; i++) {
            count = checkRoomNumber();

            if (count == -1) {
                roomNumber = generateRoomNumber(HotelRooms.size());
                roomName = Integer.toString(roomNumber);

                newRoom = new DeluxeRoom(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(newRoom);
            }
            else {
                roomNumber = generateRoomNumber(count);
                roomName = Integer.toString(roomNumber);

                newRoom = new DeluxeRoom(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(count, newRoom);
            }
        }
    }

    /**
     * Adds an executive room to the hotel.
     * 
     * @param num The number of executive rooms to add.
     */
    private void addExecutiveRoom(int num) {
        String roomName;
        int count, roomNumber;
        ExecutiveRoom newRoom;

        for (int i = 1; i <= num; i++) {
            count = checkRoomNumber();

            if (count == -1) {
                roomNumber = generateRoomNumber(HotelRooms.size());
                roomName = Integer.toString(roomNumber);

                newRoom = new ExecutiveRoom(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(newRoom);
            }
            else {
                roomNumber = generateRoomNumber(count);
                roomName = Integer.toString(roomNumber);
                
                newRoom = new ExecutiveRoom(roomName, BasePrice);
                passDPM(newRoom);
                HotelRooms.add(count, newRoom);
            }
        }
    }
    
    /**
     *  Checks the room numbers if they are in chronological order and follows 
     *      the naming convention (10 per floor).
     * 
     *  @return The index where the missing room is supposed to be; -1 if the rooms
     *      are in order.
     */
    private int checkRoomNumber() {
        int curr, ctr;

        ctr = 101;
        for (int i = 0; i < HotelRooms.size(); i++) {
            curr = Integer.parseInt(HotelRooms.get(i).getRoomName());

            if (ctr != curr)
                return i;

            if (ctr % 10 == 0)
                ctr += 91;
            else
                ctr++;
        }

        return -1;
    }

    /**
     * Initializes the voucher list and adds the first three vouchers.
     */
    private void populateVoucherList() {
        VoucherList = new ArrayList<Voucher>();

        VoucherList.add(new DiscountVoucher("I_WORK_HERE", 0.1f));
        VoucherList.add(new DateVoucher("PAYDAY", 0.07f, 15, 30));
        VoucherList.add(new StayVoucher("STAY4_GET1", 1, 4, 1));
    }

    /**
     * Searches the voucher list with the provided code.
     * 
     * @param code The voucher code to search.
     * @return The voucher found.
     */
    private Voucher searchVoucher(String code) {
        for (int i = 0; i < VoucherList.size(); i++)
            if (code.equals(VoucherList.get(i).getDiscountCode()))
                return VoucherList.get(i);

        return null;
    }

    /**
     *  Searches the reservation array list with the provided first name, 
     *      last name, check in/out dates.
     * 
     *  @param list The list to be searched.
     *  @param fname The first name of the guest.
     *  @param lname The last name of the guest.
     *  @param cIn The check in date.
     *  @param cOut The check out date.
     * 
     *  @return The index of the Object in the array if found;
     *      -1 if not found.
     */
    private Reservation LinearSearch_Reservation(ArrayList<Reservation> list, String fname, 
    String lname, int cIn, int cOut, String room) 
    {
        int i;
        boolean found;
        boolean checkpoint1 = false;
        boolean checkpoint2 = false;
        boolean checkpoint3 = false;

        i = 0;
        found = false;
        while (i < list.size() && !found) 
        {
            checkpoint1 =   fname.equals(list.get(i).getReserveFName()) && 
                            lname.equals(list.get(i).getReserveLName());
            checkpoint2 =   cIn == list.get(i).getCheckIn() && 
                            cOut == list.get(i).getCheckOut();
            checkpoint3 =   room.equals(list.get(i).getRoom().getRoomName());

            if (checkpoint1 && checkpoint2 && checkpoint3)
                found = true;
            else
                i++;
        }

        if (found)
            return list.get(i);
        else
            return null;
    }

    /**
     * Initializes the DPM to default (100%)
     */
    private void initDPM() {
        int i;

        this.dpmList = new ArrayList<DPM>();
 
        for (i = 0; i < 31; i++)
            this.dpmList.add(new DPM(100));
    }

    /**
     * Passes the hotel dpm to the dpm of the room
     * 
     * @param room The room where dpm is passed
     */
    private void passDPM(Room room) {
        float dpmRate;
        
        for (int i = 0; i < 31; i++) {
            dpmRate = dpmList.get(i).getRate();
            room.getDay(i + 1).setDayDPM(dpmRate);
        }
    }



    // ----- EXCEPTIONS ----- //

    /**
     * Throws an exception if the voucher does not exist.
     * 
     * @param voucher The voucher to check.
     * @throws VoucherException If the voucher is null.
     */
    private void voucherExists(Voucher voucher) 
    throws VoucherException 
    {
        if (voucher == null)
            throw new VoucherException();
    } 
    
    /**
     * Throws an exception if the voucher is not applicable to the reservation.
     * 
     * @param voucher The voucher to check.
     * @param reserv The reservation to check against.
     * @throws VoucherException If the voucher is not applicable to the reservation.
     */
    private void voucherApplicable(Voucher voucher, Reservation reserv) 
    throws VoucherException
    {
        if (!voucher.checkApplicable(reserv))
            throw new VoucherException();
    } 
}
