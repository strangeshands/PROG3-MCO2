import java.util.ArrayList;
import java.util.Scanner;

/**
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 * 
 *  This class represents a Hotel Reservation System (HRS).
 */
public class HRS {
    private String HRSName;
    private ArrayList<Hotel> HotelList;



    // CONSTRUCTOR
    /**
     *  Constructs an HRS object with the provided name.
     * 
     *  @param name The String name that represents the name of the HRS.
     */
    public HRS (String name) {
        this.HRSName = name;
        this.HotelList = new ArrayList<Hotel>();
    }



    // PUBLIC METHODS
    /**
     *  renderWelcome() renders the welcome line.
     */
    public void renderWelcome() {
        System.out.print("\n\n================ [ WELCOME TO ");
        System.out.print(getHRSName().toUpperCase());
        System.out.print(" ] ================\n\n");
    }

    /**
     *  renderExit() renders the exit line.
     */
    public void renderExit() {
        System.out.print("\n  =========== THANK YOU FOR USING ");
        System.out.print(getHRSName().toUpperCase());
        System.err.print(" ===========\n");
    }

    /**
     *  startOperation() is the whole HRS.
     * 
     *  It allows:
     *      1. Create Hotel
     *      2. View Hotel
     *      3. Manage Hotel
     *      4. Book Hotel
     */
    public void startOperation() {
        // Index, Selections, Inputs
        int MenuSelect, HotelSelect, HotelEditSelect;
		int index;
        boolean endManage, end;
        String hotelNameInput;

        // Scanners
        Scanner numInput = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);

        // Additional Variables
		Hotel currHotel;

        MenuSelect = 0;
        do {
            printMenuSelect();
            do { 
                System.out.print("Select [#]: ");
                MenuSelect = numInput.nextInt();
    
                if (MenuSelect > 4 || MenuSelect < 0)
                    System.out.print("Invalid selection. Try again.\n\n");
            } while (MenuSelect > 4 || MenuSelect < 0);

            switch (MenuSelect) {
                case 1: {
                    do {
                        System.out.print("\n---------- >> CREATE HOTEL << ----------\n\n");
                        do {
                            System.out.print("Enter Hotel Name: ");
                            hotelNameInput = stringInput.nextLine();
                            if (hotelNameInput.trim().isEmpty())
                                System.out.println(">> Please enter a valid Hotel name.\n");
                        } while (hotelNameInput.trim().isEmpty());

                        if (!hotelNameInput.equals("0")) {
                            // Check if there is an existing hotel with the same name.
                            index = -1;
                            index = LinearSearch_Hotel(HotelList, hotelNameInput);
                            
                            if (index == -1) { // no duplicates found, creates a new hotel
                                HotelList.add(new Hotel(hotelNameInput, numInput, stringInput));
                                System.out.println(">> " + hotelNameInput + " was added!\n");

                                currHotel = HotelList.get(HotelList.size() - 1);
                                currHotel.createRoom();
                            }
                            else // duplicate found
                                System.out.println(">> " + hotelNameInput + " already exists.\n");
                        }
                        else // user cancelled
                            System.out.println(">> Going back...\n");
                    } while (!hotelNameInput.equals("0"));
                }
                    break;
                case 2: {
                    System.out.print("\n---------- >> VIEW HOTEL << ----------\n\n");

                    if (!HotelList.isEmpty()) {
                        displayHotels();
                        HotelSelect = -1;

                        do {
                            do {
                                System.out.print("Find Hotel: ");
                                hotelNameInput = stringInput.nextLine();
                                if (hotelNameInput.trim().isEmpty())
                                    System.out.println(">> Please enter a valid Hotel name.\n");
                            } while (hotelNameInput.trim().isEmpty());

                            if (!hotelNameInput.equals("0")) {
                                HotelSelect = LinearSearch_Hotel(HotelList, hotelNameInput);

                                if (HotelSelect != -1) {
                                    currHotel = HotelList.get(HotelSelect);
                                    System.out.println(">> Entering " + currHotel.getHotelName() + "...\n");
                                    currHotel.displayHotelInfo();

                                    System.out.print("\n---------- >> VIEW HOTEL << ----------\n\n");
                                    displayHotels();
                                    HotelSelect = -1;
                                }
                                else
                                    System.out.println(">> Hotel not found.\n");
                            }
                            else
                                System.out.println(">> Going back...\n");
                        } while (HotelSelect == -1 && !hotelNameInput.equals("0"));
                    }
                    else
                        System.out.println(">> No hotels found.\n");
                }
                    break;
                case 3: {
                    System.out.print("\n---------- >> MANAGE HOTEL << ----------\n\n");

                    endManage = false;

                    if (HotelList.size() > 0) {
                        displayHotels();
                        do {
                            do {
                                System.out.print("Search Hotel: ");
                                hotelNameInput = stringInput.nextLine();
                                if (hotelNameInput.trim().isEmpty())
                                    System.out.println(">> Please enter a valid Hotel name.\n");
                            } while (hotelNameInput.trim().isEmpty());
                            
                            if (hotelNameInput.equals("0")) // user cancelled
                                System.out.println(">> Going back...\n");
                            else {
                                // finds the hotel using the name input
                                HotelSelect = LinearSearch_Hotel(HotelList, hotelNameInput);
                                
                                if (HotelSelect != -1) {
                                    currHotel = HotelList.get(HotelSelect);
                                    System.out.println(">> Entering " + currHotel.getHotelName() + "...\n");
                                    
                                    do{
                                        printManageSelect(currHotel.getHotelName());

                                        do { 
                                            System.out.print("Select [#]: ");
                                            HotelEditSelect = numInput.nextInt();
                                
                                            if (HotelEditSelect > 7 || HotelEditSelect < 0)
                                                System.out.print("Invalid selection. Try again.\n\n");
                                        } while (HotelEditSelect > 7 || HotelEditSelect < 0);
                                        
                                        switch (HotelEditSelect) {
                                            case 1: 
                                                // change hotel name, happens all under the Hotel object
                                                currHotel.changeHotelName(HotelList);
                                                break;
                                            case 2:
                                                // create 1 or many rooms, happens all under the Hotel object
                                                currHotel.createRoom();
                                                break;
                                            case 3:
                                                // removes
                                                HotelList.get(HotelSelect).removeRoom();
                                                break;
                                            case 4:
                                                // change the base price of the rooms, happens all under the Hotel object
                                                currHotel.changeRoomPrice();
                                                break;
                                            case 5: 
                                                // removes an existing reservation in the hotel
                                                currHotel.removeReservation();
                                                break;
                                            case 6:
                                                // Removes an existing hotel
                                                if (currHotel.isHotelFree()) {
                                                    HotelList.remove(currHotel);
                                                    System.out.println ("\n>> " + currHotel.getHotelName() + 
                                                                    " has been successfully removed.\n");
                                                    endManage = true;
                                                }
                                                else
                                                    System.out.println(">> [Exiting] Remove Hotel...\n");

                                                if (HotelList.isEmpty())
                                                    System.out.println(">> All hotels have been removed.\n");
                                                break;
                                            case 7: 
                                                currHotel.DPModifier();
                                                break;
                                            case 0: {
                                                // goes back to the menu
                                                System.out.println(">> Going back...\n");
                                                endManage = true;
                                            }
                                                break;
                                        }
                                    } while (!endManage);

                                    if (!HotelList.isEmpty()) {
                                        System.out.print("\n---------- >> MANAGE HOTEL << ----------\n\n");
                                        displayHotels();
                                    }
                                }
                                else
                                    System.out.println(">> Hotel not found.\n");
                            }
                        } while (!hotelNameInput.equals("0") && !HotelList.isEmpty());
                    }
                    else
                        System.out.println(">> No hotels found.\n");
                }
                    break;
                case 4: {
                    System.out.print("\n---------- >> BOOK HOTEL << ----------\n\n");

                    if (HotelList.size() > 0) {
                        displayHotels();
                        do {
                            do {
                                System.out.print("Search Hotel: ");
                                hotelNameInput = stringInput.nextLine();
                                if (hotelNameInput.trim().isEmpty())
                                    System.out.println(">> Please enter a valid Hotel name.\n");
                            } while (hotelNameInput.trim().isEmpty());

                            HotelSelect = LinearSearch_Hotel(HotelList, hotelNameInput);

                            if (hotelNameInput.equals("0"))
                                System.out.println(">> Going back...\n");
                            else {
                                if (HotelSelect != -1) {
                                    System.out.println(">> Entering " + HotelList.get(HotelSelect).getHotelName() + "...\n");
                                    end = true;
                                    while (end) {
                                        System.out.println();
                                        end = HotelList.get(HotelSelect).createReservation();
                                    }

                                    HotelSelect = -1;
                                    System.out.print("\n---------- >> BOOK HOTEL << ----------\n\n");
                                    displayHotels();
                                }
                                else
                                    System.out.println(">> Hotel not found.\n");
                            }
                        } while (HotelSelect == -1 && !hotelNameInput.equals("0"));
                    }
                    else
                        System.out.println(">> No hotels found.\n");
                }
                    break;
            }
        } while (MenuSelect != 0);

        numInput.close();
        stringInput.close();
    }



    //  GETTERS
    /**
     *  Gets the HRS name.
     * 
     *  @return A String that represents the name of the HRS.
     */
    public String getHRSName() {
        return HRSName;
    }



    // PRIVATE METHODS
    /**
     *  Prints the Menu selection.
     */
    private void printMenuSelect() {
        System.out.print("     [ ------------------------------------------------------- ]\n\n"); 

        System.out.print("                  [ ---------- HOTELS ----------- ]\n");
        System.out.print("                           [1] Create Hotel\n");
        System.out.print("                           [2] View Hotel\n");
        System.out.print("                           [3] Manage Hotel\n\n");

        System.out.print("                   [ ---------- BOOK ----------- ]\n");
        System.out.print("                           [4] Book Hotel\n\n");

        System.out.print("                           [0] Exit\n\n");
    }

    /**
     *  Displays the hotel/s in the list.
     */
    private void displayHotels() {
        int ctr;
        
        System.out.println("-- >> LIST OF HOTELS\n");
        for (int i = 0; i < HotelList.size(); i++) {
            ctr = i + 1;
            System.out.println("[ " + String.format("%03d", ctr) + " ] -- " + HotelList.get(i).getHotelName());
        }
        System.out.println();
    }

    /**
     *  Prints the Manage selection.
     * 
     *  @param name The hotel name to present.
     */
    private void printManageSelect(String name) {
        System.out.println("\n--- >> MANAGE HOTEL " + name);
        System.out.print("       [1] Change Hotel Name\n");
        System.out.print("       [2] Add Room\n");
        System.out.print("       [3] Remove Room\n");
        System.out.print("       [4] Update Base Price\n");
        System.out.print("       [5] Remove Reservation\n");
        System.out.print("       [6] Remove Hotel\n");
        System.out.print("       [7] Date Price Modifier\n");
        System.out.print("       [0] Back\n");
        System.out.println();
    }

    /**
     *  Searches the provided hotel name on the array list of hotel.
     * 
     *  @param list The hotel list to search.
     *  @param name The hotel name to find.
     * 
     *  @return The index of the object in the array; -1 if not found.
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
}
