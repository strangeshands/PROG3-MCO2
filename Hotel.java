import java.util.ArrayList;
import java.util.Scanner;

/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a hotel.
 */
public class Hotel extends DatePriceModifier{
    /**
     *  These are private attributes of the class Hotel.
     * 
     *  HotelName represents the name of the Hotel.
     *  HotelRooms is an array list of Room which stores the rooms
     *      of the hotel (1 to 50).
     *  HotelReservations is an array list of Reservation which stores the
     *      reservations of the hotel.
     */
    private String HotelName;
    private ArrayList<Room> HotelRooms;
    private ArrayList<Reservation> HotelReservations;
    private ArrayList<DatePriceModifier> dpModifier;
    private float EstimatedEarning;

    /**
     *  These are passed scanners from HRS to be used for the whole class.
     */
    private Scanner numInput;
    private Scanner stringInput;



    // CONSTRUCTORS
    /**
     *  Constructs a new Hotel with the provide hotel name.
     * 
     *  @param hotelName The provided hotel name, usually a user input.
     *  @param in1 The scanner to be used in this class.
     *  @param in2 The scanner to be used in this class.
     */
    public Hotel(String hotelName, Scanner in1, Scanner in2) {
        this.HotelName = hotelName;
        this.HotelRooms = new ArrayList<Room>();
        this.HotelReservations = new ArrayList<Reservation>();
        this.dpModifier = new ArrayList<DatePriceModifier>();
        this.EstimatedEarning = 0;

        this.numInput = in1;
        this.stringInput = in2;

        // Adding the first room
        int roomNumber = generateRoomNumber(HotelRooms.size());
        HotelRooms.add(new Room(String.valueOf(roomNumber)));
    }

    // NEW METHODS (sort later)

    /**
     * Sets a certain range of rooms a particular room type and
     * sets its price accordingly.
     * 
     * @param roomIndex
     * @param roomCount
     * @param largeHotel
     * @return
     */
    public String initRoomType(int roomIndex, int roomCount, boolean largeHotel) {
        Room Room;
        String roomType;
        int count = 0;
        float price;
        boolean validType;
        
        do {
            System.out.print("Room type: ");
            roomType = EnterString();
            roomType = roomType.toUpperCase();

            if (!roomType.equals("STANDARD") && 
                !roomType.equals("DELUXE") &&
                !roomType.equals("EXECUTIVE")) {
                    validType = false;
                    System.out.println(">> Please enter a valid room type.\n");
            }
            else {
                if (largeHotel == true)
                    roomCount--;

                while (count <= roomCount) {
                    Room = HotelRooms.get(roomIndex);
                    price = Room.getRoomPrice();
                    
                    Room.setRoomType(roomType.toUpperCase());
                    
                    switch (roomType) {
                        case "DELUXE" :
                            price = price + price * 1.2f;
                            break;
                        case "EXECUTIVE" :
                            price = price + price * 1.35f;
                            break;
                    }

                    Room.setPrice(price);

                    //testing
                    //System.out.println(roomIndex + " " + Room.getRoomType() + " " + Room.getRoomPrice());

                    count++;
                    roomIndex++;
                }

                validType = true;
            }
        } while (validType == false);

        return roomType;
    }

    /**
     * Accepts discount codes.
     * 
     * @param code discount code to be checked.
     * @param cIn check in date.
     * @param cOut chaeck out date.
     * @return discount, if the code is valid or 
     *         0, if the code is not valid.
     */
    public float enterDiscount(String code, int cIn, int cOut) {        
        float discount;
        
        switch (code) {
            case "I_WORK_HERE":
                discount = 0.1f;  
                break;  

            case "STAY4_GET1":
                if (cOut - cIn >= 5)
                    discount = 1f;
                    
                else
                    discount = 0f;
                break;  

            case "PAYDAY":
                if ((cIn <= 15 && cOut > 15) || (cIn <= 30 && cOut > 30))
                    discount = 0.07f;
                else
                    discount = 0f;
                break;  

            default:
                discount = 0f;
        }

        return discount;
    }

    public void DPModifier() {
        int i, indexMod = -1;
        int dayStart, dayEnd, rate;
        int modCounter = 0;
        
        dpModifier = dpModifier.initDPModifier();
        ArrayList<DatePriceModifier> differingRates = dpModifier.get(0).listMarkUpDown(dpModifier);
        
        System.out.println("\n--- >> DATE PRICE MODIFIER------");
        System.out.println();
        
        System.out.println("NOTE:");
        System.out.println("By default, all dates in the month have a price rate of 100%.");
        System.out.println("These will not be printed. Only those with a mark up/down");
        System.out.println("will be printed.\n");
        
        if (differingRates.size() == 0) {
            System.out.println("There are currently no rates different from 100%.\n");
        }
        else {
            for (i = 0; i < differingRates.size(); i++) {
                System.out.print("[June " + dpModifier.get(i).getDayStart() + "-" + dpModifier.get(i).getDayEnd() + "]");
                System.out.println(" -> " + dpModifier.get(i).getMarkUpDown() + "%");
            }

            System.out.println();
        }

        do {
            do {
                System.out.println("Starting day: ");
                dayStart = numInput.nextInt();

                System.out.println("Ending day: ");
                dayStart = numInput.nextInt();

                indexMod = findDayModifier(dayStart, dayEnd);

                if (indexMod == -1)
                    System.out.println(">> Please enter a valid date.");
                else if (dayStart == 0 || dayEnd == 0)
                    System.out.println(">> Exiting date-price modifier...");
            } while (indexMod == -1 || dayStart != 0 || dayEnd != 0);

            do {
                System.out.println("New rate: ");
                rate = numInput.nextInt();

                if (rate < 50 || rate > 150)
                    System.out.println(">> Only accepting rates from 50% to 150%");
                else if (rate == 0)
                    System.out.println(">> Exiting date-price modifier...");
            } while (rate < 50 || rate > 150 || rate != 0);

            dpModifier.get(indexMod).setMarkUpDown(rate);
        } while (dayStart != 0 || dayEnd != 0 || rate != 0);

    }


    // PUBLIC METHODS
    /**
     *  Creates room depending on the input count.
     * 
     *  @return true - create room is a success; 
     *          false - rooms are already at maximum count OR cancelled.
     */
    public boolean createRoom() {
        int roomNumber;
        String roomType;
        
        int roomIndex;
        int roomCount;
        int count;
        int ctr = 0;
        boolean largeHotel = false;

        Room newRoom;

        System.out.println("\n--- >> ADD ROOM -----------------");
        System.out.println();

        System.out.println(">> There is/are currently " + HotelRooms.size() + " room/s in this hotel.\n");
        
        if (HotelRooms.size() < 50) {
			// Ask for user input (number of rooms to add)
			// Maxmimum input is 49 since there is always 1 room exisiting 
            roomCount = SelectInt(1, 49, 0, "How many rooms would you like to add?");

            if (roomCount == 0) {   // user cancelled
                System.out.println(">> [Exiting] Create Room...\n");
                return false;
            }
            else if (roomCount == 1 || HotelRooms.size() + 1 == 50) {   // add 1 room, differs in prompt
                count = checkRoomNumber();
                roomIndex = HotelRooms.size() - 1;
                
                if (HotelRooms.size() != 1) {
                    roomIndex += 1;
                    largeHotel = true;
                }

                if (count == -1) {
                    roomNumber = generateRoomNumber(HotelRooms.size());
                    newRoom = new Room(String.valueOf(roomNumber));
                    HotelRooms.add(newRoom);
                }
                else {
                    roomNumber = generateRoomNumber(count);
                    newRoom = new Room(String.valueOf(roomNumber));
                    HotelRooms.add(count, newRoom);
                }

                roomType = initRoomType(roomIndex, roomCount, largeHotel);             
    
				// prompt that a room was added
                System.out.println("\n>> " +  roomType.toUpperCase() + " room " + newRoom.getRoomName() + 
								" was added to " + this.HotelName + "\n");
            }
            else if (roomCount > 1) { // add multiple rooms, contains loop and differs in prompt
                roomIndex = HotelRooms.size() - 1;

                if (HotelRooms.size() != 1) {
                    roomIndex += 1;
                    largeHotel = true;
                }
                
                while (ctr < roomCount && HotelRooms.size() < 50) {
                    count = checkRoomNumber();

                    if (count == -1) {
                        roomNumber = generateRoomNumber(HotelRooms.size());
                        HotelRooms.add(new Room(String.valueOf(roomNumber)));
                    }
                    else {
                        roomNumber = generateRoomNumber(count);
                        HotelRooms.add(count, new Room(String.valueOf(roomNumber)));
                    }

                    ctr++;
                }

                roomType = initRoomType(roomIndex, roomCount, largeHotel);             
    
				// prompt that a range of rooms was added
				System.out.println("\n>> Multiple " + roomType + " rooms added. Refer below for changes.\n");
                displayRooms(HotelRooms);
            }
        }

        if (HotelRooms.size() == 50) {  // maximum number of rooms reached
            System.out.println(">> " + this.HotelName + 
                                " has reached maximum count of rooms (50).\n");
            if (ctr == 0)
                return false;
        }

        return true;
    }

     /**
     *  Removes a specified room based on the room name
     *      a customer specifies.
     * 
     *  @return true - if room removal was a succes;
     *          false - if room removal was a failure OR cancelled.
     */
    public boolean removeRoom() {
        String roomInput;

        int roomF, roomL;
        int i, ctr;

        boolean roomStatus = true;
        boolean cancel = false;

        Room currRoom;

        System.out.println("\n--- >> REMOVE ROOM -----------------\n");

        if(getHotelRooms().size() > 1) {   // checks number of rooms
            displayRooms(HotelRooms);

            do {    // taking first room of the range to remove
                System.out.print("Start at Room Number: ");
                roomInput = stringInput.nextLine();
                roomF = LinearSearch_Room(HotelRooms, roomInput);
                cancel = roomInput.equals("0");

                if (roomF == -1 && !cancel)
                    System.out.println(">> This room does not exist.\n");
                else if (cancel) {
                    System.out.println(">> [Exiting] Room removal...\n");
                    return false;
                }
            } while (roomF == -1 && !cancel);

            do {    // taking last room of the range to remove
                System.out.print("End at Room Number: ");
                roomInput = stringInput.nextLine();
                roomL = LinearSearch_Room(HotelRooms, roomInput);
                cancel = roomInput.equals("0");

                if (roomL == -1 && !cancel)
                    System.out.println(">> This room does not exist.\n");
                else if (cancel) {
                    System.out.println(">> [Exiting] Room removal...\n");
                    return false;
                }
            } while (roomL == -1 && !cancel);
            
            // checking if the rooms are all free for any day
            i = roomF;
            while (i <= roomL && roomStatus) {
                currRoom = HotelRooms.get(i);

                if (!currRoom.isRoomFree())
                    roomStatus = false;

                i++;
            }

            // checks if all of the rooms will be removed
            i = roomF;
            ctr = 0;
            while (i <= roomL) {
                ctr++;
                i++;
            }

            if (roomStatus == true) { // removing room/s
                if (ctr == HotelRooms.size()) {
                    System.out.println("\n>> Invalid removal. No rooms will be left.\n");
                    return false;
                }
                if (roomL < roomF) {
                    System.out.println("\n>> Please check your input.\n");
                    return false;
                }
                else if (roomF == roomL) {   // for only one room
                    System.out.println("\n>> Room " + HotelRooms.get(roomF).getRoomName() + 
                                        " has been removed.\n");
                    getHotelRooms().remove(roomF);
                }
                else if (roomF != roomL) {  // for more than one room
                    for (i = roomF; i <= roomL; i++)
                        getHotelRooms().remove(roomF);
                    System.out.println("\n>> Multiple rooms have been removed. Refer below for changes\n");
                }
                
                displayRooms(HotelRooms);
                return true;
            }
            else {
                System.out.println("\n>> Some rooms selected are reserved. Removal failed.\n");
                return false;
            }

        }
        else {
            System.out.println(">> There is only one room left. Removal failed.\n");
            return false;
        }
    }

    /**
     *  changeHotelName allows updating the hotel name. At the same time, it checks
     *      if the new name already exists.
     * 
     *  @param list The array list of Hotel to be searched.
     * 
     *  @return true - name change success; 
     *          false - name change failed OR user cancelled.
     */
    public boolean changeHotelName(ArrayList<Hotel> list) {
        String hotelNameInput;

        System.out.println("\n--- >> CHANGE HOTEL NAME -----------------\n");
		
		// New name input
        do {
            System.out.print("Enter new name: ");
            hotelNameInput = stringInput.nextLine();
            if (hotelNameInput.trim().isEmpty())
                System.out.println(">> Please enter a valid Hotel name.\n");
        } while (hotelNameInput.trim().isEmpty());

        if (hotelNameInput.equals("0")) // User cancelled
            System.out.println(">> [Exiting] Change Hotel Name...\n");
        else if (LinearSearch_Hotel(list, hotelNameInput) == -1) { // New name is unique
            HotelName = hotelNameInput;
            System.out.println(">> Succesfully changed to " 
                            + this.HotelName + "!\n");
            return true;
        }
        else  // New name exists
            System.out.println(">> " + hotelNameInput + " already exists.\n");
	
		return false;
    }

    /**
     *  createReservation() allows selection of check in and out dates and rooms to prepare for
     *      making a reservation.
     *  Also creates the reservation itself.
     * 
     *  @return true - success, reservation created; 
     *          false - not sucess, reservation cancelled.
     */
    public boolean createReservation() {
        int cIn, cOut;
        int selectRoom;
        boolean check;

        Room currRoom;
        String fname, lname, roomName, discountCode;
        float discount = 0f;
        Reservation currReservation;

        System.out.println("--- >> " + this.HotelName + " BOOKING --\n");

        // Renders a 31-day month
        renderMonth();

        // Printing sample price
        System.out.println("   [NOTE] : Standard base price for rooms is " + 
                        HotelRooms.get(0).getRoomPrice() + " per night.\n");
        
        // Select dates
        cIn = cOut = 0;
        check = false;
        do {
            cIn = SelectInt(1, 31, 0, "Choose Check-In Date:");

            if (cIn != 0)
                cOut = SelectInt(1, 31, 0, "Choose Check-Out Date:");

            if (cIn == 0 || cOut == 0)
                System.out.println(">> [Exiting] Reservation...");
            else
                check = checkDates(cIn, cOut);
        } while (!check && cIn != 0 && cOut != 0);
        System.out.println();

        // Select room
        if (check && cIn != 0 && cIn != 0) {
            displayRooms(HotelRooms);
            selectRoom = -1;
            do {
                System.out.print("Enter room name/number: ");
                roomName = EnterString();

                if (!roomName.equals("0")) {
                    selectRoom = LinearSearch_Room(HotelRooms, roomName);

                    if (selectRoom == -1) 
                        System.out.println(">> Room not found.\n");
                }
            } while (selectRoom == -1 && !roomName.equals("0"));

            if (selectRoom != -1) {
                currRoom = HotelRooms.get(selectRoom);

                // Checks if the room is free.
                if (currRoom.isFreeDays(cIn, cOut)) {
                    System.out.println("\nRESERVER NAME -- ");

                    do {
                        System.out.print("Enter Guest First Name: ");
                        fname = EnterString();
                        if (fname.trim().isEmpty())
                            System.out.println(">> Please enter a name.\n");
                    } while (fname.trim().isEmpty());

                   do {
                        System.out.print("Enter Guest Last Name: ");
                        lname = EnterString();
                        if (lname.trim().isEmpty())
                            System.out.println(">> Please enter a name.\n");
                    } while (lname.trim().isEmpty());

                    // discount code
                    do {
                        System.out.print("Enter discount code (If none, type N/A): ");
                        discountCode = EnterString();
                        
                        if (discountCode.trim().isEmpty())
                            System.out.println(">> Please enter a code.\n");
                        else {
                            discount = enterDiscount(discountCode, cIn, cOut);

                            if (discountCode.trim().equals("N/A")) {
                                System.out.println(">> No code entered.\n");
                            }
                            else if (discount == 0f)
                                System.out.println(">> Please enter a valid code.\n");
                            else
                                System.out.println(">> Discount code has been successfully applied.\n");
                        }
                        
                    } while (discountCode.trim().isEmpty() || discount == 0f ||
                            discountCode.trim().equals("N/A"));

                    // Creating and adding new reservation to the list
                    currReservation = new Reservation(currRoom, fname, lname, cIn, cOut, discount);
                    HotelReservations.add(currReservation);

                    // Updating the estimated earning
                    setEstimatedEarning();

                    // Printing reservation details
                    System.out.print("\n<< --------------------------------- >>");
                    System.out.println("\nWelcome to " + this.HotelName + ", " + currReservation.getReserveFName() + "!");
                    System.out.println("Here are the details of your reservation:");
                    currReservation.printReserveDetails();
                    System.out.println("<< --------------------------------- >>\n");

                    return true;
                }
                else
                    System.out.println("\n>> Sorry, room not available. Room " + currRoom.getRoomName() + " for June " + 
                                    cIn + " was already booked.\n");
            }
            else 
                System.out.println(">> [Exiting] Reservation...\n");
        }

        return false;
    }

    /**
     *  removeReservation allows selection of check in and out dates and room to prepare
     *      for removing a reservation.
     *  
     *  @return true - remove success; 
     *          false - remove cancelled/failed
     */
    public boolean removeReservation() {
        int cIn, cOut, index;
        boolean check;
        Reservation reserve;
        String fname, lname, choice, roomName;

        System.out.println("\n--- >> REMOVE RESERVATION -----------------");

        if (!HotelReservations.isEmpty()) {
            System.out.println("\nCHOOSE DATES --\n");

            // Select Date
            renderMonth();
            cIn = cOut = 0;
            check = false;

			// In a loop until user inputs valid dates OR cancelled
            do {
                cIn = SelectInt(1, 31, 0, "Choose Check-In Date:");

                if (cIn != 0)
                    cOut = SelectInt(1, 31, 0, "Choose Check-Out Date:");

                if (cIn == 0 || cOut == 0)
                    System.out.println(">> [Exiting] Remove Reservation...");
                else
                    check = checkDates(cIn, cOut);
            } while (!check && cIn != 0 && cOut != 0);
            System.out.println();

            if (check && cIn != 0 && cIn != 0) {
                //Enter Guest Name
                System.out.println("GUEST NAME --");
                do {
                    System.out.print("Enter Guest First Name: ");
                    fname = EnterString();
                    if (fname.trim().isEmpty())
                        System.out.println(">> Please enter a name.\n");
                } while (fname.trim().isEmpty());

                do {
                    System.out.print("Enter Guest Last Name: ");
                    lname = EnterString();
                    if (lname.trim().isEmpty())
                        System.out.println(">> Please enter a name.\n");
                } while (lname.trim().isEmpty());
                
                System.out.println("\nROOM NAME --");
                System.out.print("Enter Room to Find: ");
                roomName = stringInput.nextLine();

                if (!roomName.equals("0")) {
                    // Search the Reservations array for a match
                    index = LinearSearch_Reservation(HotelReservations, fname, lname, cIn, cOut, roomName);

                    if (index != -1) {
                        reserve = HotelReservations.get(index);

                        System.out.println("\n>> Reservation by " + reserve.getReserveName() + " found.\n");
                        System.out.println("Details:");
                        reserve.printReserveDetails();

                        do {
                            System.out.print("\nPlease confirm to remove [YES/NO]: ");
                            choice = EnterString();
                            choice = choice.toUpperCase();
                
                            if (!choice.equals("YES") && !choice.equals("NO"))
                                System.out.println(">> Invalid input.\n");
                        } while (!choice.equals("YES") && !choice.equals("NO"));

                        if (choice.equals("YES")) {
                            // Update the room's availability
                            reserve.getRoom().updateDays(cIn, cOut, "Free");

                            // Remove room
                            HotelReservations.remove(reserve);

                            // Update estimated earning
                            setEstimatedEarning();

                            // Prompt
                            System.out.println("\n>> Reservation has been successfully removed.\n");
                            return true;
                        }
                        else
                            System.out.println("\n>> Reservation was not removed.\n");
                    }
                    else
                        System.out.println("\n>> No resevations found.\n");
                }
                else
                    System.out.println("\n>> [Exiting] Remove Reservation...\n");
            }
        }
        else
            System.out.println("\n>> Hotel has no reservations.\n");

        return false;
    }

    /**
     *  Allows updating the base price of the rooms per night as long as there is no active reservations.
     *  The new price must be >= 100.00.
     * 
     *  @return true - price change succesful; 
     *          false - price change unsuccesful OR cancelled
     */
    public boolean changeRoomPrice() {
        int i;
        float price;

        System.out.println("\n--- >> CHANGE ROOM PRICE -----------------");
        
        // Get sample price, used for prompt later
        float origPrice = HotelRooms.get(0).getRoomPrice();

        if (HotelReservations.isEmpty()) { // no reservations
            System.out.println("\n>> Price change is possible. Current room price is P" + origPrice);
            
            // in a loop until user inputs valid price or 0
            do {
                System.out.print("   New Price: ");
                price = numInput.nextFloat();

                if (price < 100 && price != 0)
                    System.out.println("\n>> Higher price please.");
            } while (price < 100 && price != 0);

            if (price == 0) // user cancelled
                System.out.println(">> [Exiting] price change...\n");
            else { 
                // set price, applied to all rooms
                for (i = 0; i < HotelRooms.size(); i++)
                    HotelRooms.get(i).setPrice(price);

                System.out.println("\n>> Price change from P" + origPrice + " to P" + price + " was successful.\n");
                return true;
            }
        }
        else // reservations found
            System.out.println("\n>> Reservations found. Price change is not possible.\n");

        return false;
    }

    /**
     *  Displays the desired hotel information of the user.
     */
    public void displayHotelInfo() {
        String choice, fname, lname, roomName;
        int select, index, manageSelect;
        int date, cIn = 0, cOut = 0;
        boolean check;

        ArrayList<Room> freeRooms;
        ArrayList<Room> bookedRooms;

        // Display high-level information with respect to the MCO1 specification
		System.out.println();
        System.out.println(">> [PRINTING] Hotel Information...\n");
        System.out.println("     HOTEL               :  " + this.HotelName);
        System.out.println("     NUMBER OF ROOMS     :  " + this.HotelRooms.size());
        System.out.println("     ESTIMATE EARNINGS   :  P" + this.EstimatedEarning);

        // Allows selection if the user wants to view more specific information
        System.out.println("\n[M] More Info       [0] Back");
        do {
            System.out.print(">> Select [M/0]: ");
            choice = EnterString();
            choice = choice.toUpperCase();
            if (!choice.equals("M") && !choice.equals("0"))
                System.out.println(">> Invalid input.\n");
        } while (!choice.equals("M") && !choice.equals("0"));

        if (choice.equals("M")) {
            do {
                /*  
                    Low-level information selection.

                    Allows selection whether user wants to view a date, room, or
                        reservation.
                */
                System.out.println("\n--- >> View Information ");
                System.out.print("       [1] Select a Date\n");
                System.out.print("       [2] View a Room\n");
                System.out.print("       [3] View a Reservation\n");
                System.out.println("       [0] Back\n");
                manageSelect = SelectInt(1, 3, 0, "Select");

                switch (manageSelect) {
                    case 1: {
                        System.out.println();
                        // a date is selected, free and booked rooms on that date are shown
                        renderMonth();
                        date = SelectInt(1, 31, 0, "Choose date");

                        if (date != 0) {
                            freeRooms = getFreeRooms(date);
                            bookedRooms = getBookedRooms(date);

                            System.out.println("\n--- >> JUNE " + date + " STATUS:\n");

                            if (!freeRooms.isEmpty()) {
                                System.out.print("[Available Rooms] ");
                                displayRooms(freeRooms);
                            }
                            
                            if (!bookedRooms.isEmpty()) {
                                System.out.print("[Booked Rooms] ");
                                displayRooms(bookedRooms);
                            }
                        }
                    }
                        break;
                    case 2: {
                        // a room is selected, the room's status for the month is shown
                        // the name and price per night is also shown
                        System.out.println();
                        displayRooms(HotelRooms);

                        select = -1;
                        do {
                            System.out.print("Enter room name/number: ");
                            roomName = EnterString();
            
                            if (!roomName.equals("0")) {
                                select = LinearSearch_Room(HotelRooms, roomName);
            
                                if (select == -1) 
                                    System.out.println(">> Room not found.\n");
                            }
                        } while (select == -1 && !roomName.equals("0"));
                        System.out.println();

                        if (!roomName.equals("0"))
                            HotelRooms.get(select).displayRoomInfo();
                        else
                            System.out.print(">> [Exiting] View Room Information...\n");
                    }
                        break;
                    case 3: {
                        // view reservation details
                        // guest name, check in and out dates is used to find a reservation
                        System.out.println("\n--- >> Find a reservation");

                        if (!HotelReservations.isEmpty()) {
                            // Enter Guest Name
                            System.out.println("\nGUEST NAME --");
                            do {
                                System.out.print("Enter Guest First Name: ");
                                fname = EnterString();
                                if (fname.trim().isEmpty())
                                    System.out.println(">> Please enter a name.\n");
                            } while (fname.trim().isEmpty());

                            do {
                                System.out.print("Enter Guest Last Name: ");
                                lname = EnterString();
                                if (lname.trim().isEmpty())
                                    System.out.println(">> Please enter a name.\n");
                            } while (lname.trim().isEmpty());

                            // Select Date
                            if (!fname.equals("0") && !lname.equals("0")) {
                                System.out.println("\nCHOOSE DATES --\n");
                                renderMonth();

                                cIn = cOut = 0;
                                check = false;
                                do {
                                    cIn = SelectInt(1, 31, 0, "Choose Check-In Date:");
                                    
                                    if (cIn != 0)
                                        cOut = SelectInt(1, 31, 0, "Choose Check-Out Date:");

                                    if (cIn == 0 || cOut == 0)
                                        System.out.println(">> [Exiting] View Reservation...");
                                    else
                                        check = checkDates(cIn, cOut);
                                } while (!check && cIn != 0 && cOut != 0);
                                //System.out.println();

                                if (check && cIn != 0 && cOut != 0) {
                                    System.out.println("\nROOM NAME --");
                                    System.out.print("Enter Room to Find: ");
                                    roomName = stringInput.nextLine();
                                    System.out.println();

                                    if (!roomName.equals("0")) {
                                        index = LinearSearch_Reservation(HotelReservations, fname, lname, cIn, cOut, roomName);

                                        if (index != -1) {
                                            System.out.println(">> Reservation found. Here are the details...\n");
                                            System.out.println("--- >> " + this.HotelName + " Hotels");
                                            HotelReservations.get(index).printReserveDetails();
                                            System.out.println();
                                            
                                            do {
                                                System.out.print("View room details? [Y/N] : ");
                                                choice = EnterString();
                                                choice = choice.toUpperCase();
                                                if (!choice.equals("Y") && !choice.equals("N"))
                                                    System.out.println(">> Invalid input.\n");
                                            } while (!choice.equals("Y") && !choice.equals("N"));

                                            if (choice.equals("Y")) {
                                                System.out.println();
                                                HotelReservations.get(index).getRoom().displayRoomInfo();
                                            }
                                            else if (choice.equals("N"))
                                                System.out.println(">> Going back...\n");
                                        }
                                        else
                                        System.out.println(">> Reservation not found.\n");
                                    }
                                    else
                                        System.out.println(">> [Exiting] View Reservation...\n");
                                }
                            }
                            else
                                System.out.println(">> [Exiting] View Reservation...\n");
                        }
                        else
                            System.out.println("\n>> This hotel doesn't have any reservations.\n");
                    }
                        break;
                }
            } while (manageSelect != 0);
        }
		else
			System.out.println("\n>> Going back...\n");
    }
    
    /**
     *  isHotelFree() prepares the hotel for removal.
     *  If there are no reservations, hotel is ready for removal.
     *  If there are reservations, user needs to confirm.
     * 
     *  @return true - hotel can be removed; false - hotel won't be removed.
     */
    public boolean isHotelFree() {
        String choice;
        boolean end;

        if (HotelReservations.isEmpty())
            return true;
        else {
            System.out.println("\n>> Hotel has pending reservations.");
            System.out.print(">> Please confirm to remove [YES/NO]: ");
            do {
                choice = EnterString();
                choice = choice.toUpperCase();

                if (choice.equals("YES"))
                    return true;
                else if (choice.equals("NO") || choice.equals("0"));
                    end = true;

            } while (!end);
            System.out.println();
        }

        return false;
    }



    // GETTERS
    /**
     *  Gets the hotel name.
     * 
     *  @return A String that represents the hotel name.
     */
    public String getHotelName() {
        return this.HotelName;
    }

    /**
     *  Gets all the hotel rooms.
     * 
     *  @return The array list of Room.
     */
    public ArrayList<Room> getHotelRooms() {
        return this.HotelRooms;
    } 

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



    //SETTERS
    /**
     *  Sets the monthly estimated earning of the hotel.
     */
    public void setEstimatedEarning() {
        float sum = 0;

        for (int i = 0; i < HotelReservations.size(); i++)
            sum += HotelReservations.get(i).getTotalPrice();

        this.EstimatedEarning = sum;
    }



    // PRIVATE METHODS
    /**
     *  Allows integer selection within the provided bounds. Also displays
     *      the desired text -- for ex. "How many rooms?"
     * 
     *  @param lower The lower bound of the selection.
     *  @param upper The upper bound of the selection.
     *  @param terminate The value that determines when to terminate the loop,
     *      i.e. the user cancelled selection.
     *  @param text The desired text to be displayed.
     * 
     *  @return The user input.
     */
    private int SelectInt(int lower, int upper, int terminate, String text) {
        int userInput;

        do { 
            System.out.print(text + " [#]: ");
            userInput = numInput.nextInt();

            if ((userInput > upper || userInput < lower) && userInput != terminate)
                System.out.print("Invalid number. Try again.\n\n");
        } while ((userInput > upper || userInput < lower) && userInput != terminate);

        if (userInput == terminate)
            System.out.println(">> Going back...\n");

        return userInput;
    }

    /**
     *  EnterString() allows string input.
     * 
     *  @return The string input of the user.
     */
    private String EnterString() {
        String userinput = stringInput.nextLine();
        return userinput;
    }

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

        // roomCount = 9
        roomCount += 1; // roomCount = 10

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
     *  Checks if the given check in and out dates are valid.
     * 
     *  @param cIn The check in date.
     *  @param cOut The check out date.
     * 
     *  @return true - valid; false - invalid
     */
    private boolean checkDates(int cIn, int cOut) {
        if (cIn >= cOut || cOut == 1 || cIn == 31) {
            System.out.println(">> Invalid dates.\n");
            return false;
        }
        return true;
    }

    /**
     *  LinearSearch_Hotel searches the list with the provided name.
     * 
     *  @param list The array list to be searched.
     *  @param name The hotel name to find.
     * 
     *  @return found - index of the Object in the array; 
     *      -1 if not found
     */
    private int LinearSearch_Hotel(ArrayList<Hotel> list, String name) {
        int i, found;

        i = found = 0;
        while (i < list.size() && found != 1) 
        {
            if (((list.get(i)).getHotelName()).equals(name) == true)
                found = 1;
            else
                i++;
        }

        if (found == 1)
            return i;
        else
            return -1;
    }
    
    /**
     *  Searches the given name in the array list of room.
     * 
     *  @param list The hotel array list to be searched.
     *  @param name The hotel name to be searched.
     * 
     *  @return The index of the hotal if found; -1 if not found.
     */
    private int LinearSearch_Room(ArrayList<Room> list, String name) {
        int i, found;
        String roomname;

        i = found = 0;
        while (i < list.size() && found != 1) 
        {
            roomname = list.get(i).getRoomName();
            if (roomname.equals(name) == true)
                found = 1;
            else
                i++;
        }

        if (found == 1)
            return i;
        else
            return -1;
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
    private int LinearSearch_Reservation(ArrayList<Reservation> list, String fname, String lname,
                                    int cIn, int cOut, String room) {

        int i;
        boolean found;
        boolean checkpoint1 = false;
        boolean checkpoint2 = false;
        boolean checkpoint3 = false;

        i = 0;
        found = false;
        while (i < list.size() && !found) 
        {
            checkpoint1 = fname.equals(list.get(i).getReserveFName()) && lname.equals(list.get(i).getReserveLName());
            checkpoint2 = cIn == list.get(i).getCheckIn() && cOut == list.get(i).getCheckOut();
            checkpoint3 = room.equals(list.get(i).getRoom().getRoomName());

            if (checkpoint1 && checkpoint2 && checkpoint3)
                found = true;
            else
                i++;
        }

        if (found)
            return i;
        else
            return -1;
    }

    /**
     *  displayRooms() displays all the rooms stored on the provided list.
     * 
     *  @param list The array list of rooms.
     */
    private void displayRooms(ArrayList<Room> list) {
        System.out.println("-- >> LIST OF ROOMS\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[ ROOM ] -- " + list.get(i).getRoomName());
        }
        System.out.println();
    }

    /**
     *  renderMonth() just prints a 31-day calendar.
     */
    private void renderMonth() {
        System.out.println(" \t\t\t     [ JUNE ]\n");
        System.out.println("    |  SAT  |  MON  |  TUE  |  WED  |  THU  |  FRI  |  SUN  |");
        System.out.print("    |  ");
        
        int day = 1;
        for (int row = 1; row <= 6; row++){
            for (int col = 1; col <= 7; col++) {
                if (day <= 31) 
                    System.out.print( String.format("%2d", day) + "   |  ");

                day++;
            }

            if (day <= 31) {
                System.out.println();
                System.out.print("    |  ");
            }
        }
        System.out.println("\n");
    }
}