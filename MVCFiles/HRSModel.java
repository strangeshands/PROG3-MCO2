package MVCFiles;

import java.util.ArrayList;
import ClassFiles.*;


/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a Hotel Reservation System (HRS).
 */
public class HRSModel {
    /**
     * The array list of hotels
     */
    private ArrayList<Hotel> HotelList;



    // ----- CONSTRUCTORS ----- //

    /**
     *  Constructs an HRS object.
     */
    public HRSModel() {
        HotelList = new ArrayList<Hotel>();
    }
    


    // ----- PUBLIC METHODS ----- //

    /**
     *  [DONE] Creates a new hotel object with the provided name and room counts
     *      to add.
     * 
     *  @param name The Hotel name (must be unique).
     *  @param stndrdNum The number of standard rooms to add.
     *  @param delNum The number of deluxe rooms to add.
     *  @param execNum The number of executive rooms to add.
     * 
     *  @return 1: Creation of Hotel is successful.
     *          2: Room count is invalid.
     *          3: The Hotel already exists.
     */
    public int createHotel(String name, int stndrdNum, int delNum, int execNum) 
    {
        Hotel newHotel;
        int check = stndrdNum + delNum + execNum;

        if (checkHotel(name)) { 
            newHotel = new Hotel(name);

            if (check >= 1 && check <= 50) {
                newHotel.addRoom(stndrdNum, delNum, execNum);
                HotelList.add(newHotel);

                return 1;
            }

            return 2;
        }

        return 3;
    }

    /**
     *  [DONE] Checks if the hotel exists and searches it by date.
     * 
     *  @param name The name of the hotel.
     *  @param date The date to be searched.
     *  @return true: hotel found, false otherwise
     */
    public boolean searchByDate(String name, int date) {
        try {
            Hotel currHotel = findHotel(name);
            currHotel.searchByDate(date);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Checks if the hotel exists and searches it by room.
     * 
     *  @param hotel The name of the hotel.
     *  @param room The room to be searched.
     *  @return true: hotel found, false otherwise
     */
    public boolean searchByRoom(String hotel, String room) {
        try {
            Hotel currHotel = findHotel(hotel);
            return currHotel.searchByRoom(room);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Checks if the hotel exists and searches it by reservation.
     * 
     *  @param inputs The inputs needed for searching reservation.
     *  @param cIn The check in date
     *  @param cOut The check out date
     *  @return true: hotel and reservation found, false otherwise
     */
    public boolean searchByReserve(String[] inputs, int cIn, int cOut) {
        try {
            Hotel currHotel = findHotel(inputs[0]);
            return currHotel.searchByReserve(inputs, cIn, cOut);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Changes the hotel name; name must be unique.
     * 
     *  @param hotel The name of the hotel selected.
     *  @param newName The new name of the hotel.
     * 
     *  @return true - change success;
     *          false - change failed
     */
    public boolean changeHotelName(String hotel, String newName) {
        try {
            Hotel currHotel = findHotel(hotel);

            if (checkHotel(newName)) {
                currHotel.setHotelName(newName);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Deletes a hotel with the provided hotel name.
     * 
     *  @param hotel The hotel name to be deleted.
     */
    public boolean deleteHotel(String hotel) {
        try {
            Hotel currHotel = findHotel(hotel);
            HotelList.remove(currHotel);

            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Adds a room with the provided hotel name and room counts to add.
     * 
     *  @param name The hotel name.
     *  @param stndrdNum The number of standard rooms to add.
     *  @param delNum The number of deluxe rooms to add.
     *  @param execNum The number of executive rooms to add.
     * 
     *  @return 1 - add room is a success;
     *          2 - maximum count reached
     *          3 - no room added (0 input)
     *          4 - error
     */
    public int addRoom(String name, int stndrdNum, int delNum, int execNum) {
        try {
            Hotel currHotel = findHotel(name);

            if (stndrdNum + delNum + execNum <= 0)
                return 3;
            if (currHotel.checkRoomCount(stndrdNum, delNum, execNum)) {
                currHotel.addRoom(stndrdNum, delNum, execNum);
                return 1;
            }

            return 2;
        } catch (NullPointerException e) {
            return 4;
        }
    }

    /**
     *  [DONE] Removes the selected rooms with the provided hotel name and room index.
     * 
     *  @param hotel The hotel name.
     *  @param room1 The first room to remove (lower bound).
     *  @param room2 The last room to remove (upper bound).
     * 
     *  @return true - removal success
     *          false - removal failed
     */
    public boolean removeRooms(String hotel, int room1, int room2) {
        try {
            Hotel currHotel = findHotel(hotel);

            if (currHotel.checkRoomCountRemove(room1, room2) &&
                currHotel.checkRoomStatus(room1, room2)) 
            {
                currHotel.removeRoom(room1, room2);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Allows modification of price. Checks first if the list of reservation is empty.
     * 
     *  @param hotel The name of the hotel.
     *  @param price The new base price.
     * 
     *  @return true - change price is successful
     *          false - change price failed
     */
    public boolean changeRoomPrice(String hotel, float price) {
        try {
            Hotel currHotel = findHotel(hotel);

            if (currHotel.getReservationList().isEmpty()) {
                currHotel.changeRoomPrice(price);
                return true;
            }

            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Finds the reservation based on the provided parameters.
     * 
     *  @param hotel The hotel name.s
     *  @param fName First name of the guest.
     *  @param lName Last name of the guest.
     *  @param room The name of the room reserved.
     *  @param cIn The check-in date.
     *  @param cOut The check-out date.
     * 
     *  @return true - reservation found
     *          false - reservation not found
     */
    public boolean findReservation(String hotel, String fName, String lName, 
    String room, int cIn, int cOut) 
    {
        try {
            Hotel currHotel = findHotel(hotel);
            return currHotel.checkReservation(fName, lName, cIn, cOut, 
                            room.substring(0, 3));
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *  [DONE] Removes the reservation based on the provided parameters.
     * 
     *  @param hotel The hotel name.
     *  @param fName First name of the guest.
     *  @param lName Last name of the guest.
     *  @param room The name of the room reserved.
     *  @param cIn The check-in date.
     *  @param cOut The check-out date.
     */
    public void removeReservation(String hotel, String fName, String lName, 
    String room, int cIn, int cOut) 
    {
        try {
            Hotel currHotel = findHotel(hotel);
            currHotel.removeReservation(fName, lName, cIn, cOut, 
                        room.substring(0, 3));
        } catch (NullPointerException e) {
        }
    }

    /**
     * [DONE] Updates the DPM and checks if the hotel exists
     * 
     * @param hotel The hotel selected
     * @param date The date selected
     * @param rate The rate to set
     * 
     * @return  1 - update success;
     *          2 - rate is not valid;
     *          3 - error occurred
     */
    public int updateDPM(String hotel, int date, float rate) {
        try {
            if (rate < 50 || rate > 150)
                return 2;

            Hotel currHotel = findHotel(hotel);
            currHotel.updateDPM(date, rate);

            return 1;
        } catch (NullPointerException e) {
            return 3;
        }
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
     *           4 - NullPointerException
     */
    public int createReservation(String[] inputs, int cIn, int cOut) {
        try {
            Hotel currHotel = findHotel(inputs[0]);
            return currHotel.createReservation(inputs, cIn, cOut);
        } catch (NullPointerException e) {
            return 4;
        }
    }
    


    // ----- GETTERS ----- //

    public Hotel getRecentHotel() {
        return HotelList.get(HotelList.size() - 1);
    }

    public String getHotelName(int index) {
        return HotelList.get(index).getHotelName();
    }

    public ArrayList<Hotel> getHotelList() {
        return HotelList;
    }

    public Hotel findHotel(String name) {
        for (int i = 0; i < HotelList.size(); i++) {
            if (name.equals(HotelList.get(i).getHotelName()))
                return HotelList.get(i);
        }

        return null;
    }

    public int getHotelReservNum(String name) {
        Hotel currHotel = findHotel(name);

        return currHotel.getReservationList().size();
    }



    // ----- PRIVATE METHODS ----- //

    /**
     * Checks if the hotel exists
     * 
     * @param name the name of the hotel
     * @return false - found; true - not found
     */
    private boolean checkHotel(String name) {
        for (int i = 0; i < HotelList.size(); i++)
            if (name.equals(HotelList.get(i).getHotelName()))
                return false;
        
        return true;
    }
}
