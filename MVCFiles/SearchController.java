package MVCFiles;

import ClassFiles.*;
import ExceptionFiles.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * This class represents a controller for searching hotel reservations in a hotel reservation system.
 * It handles user input from the SearchView and interacts with the HRSModel to perform searches.
 *
 * @author Tolentino
 * @author Santos
 */
public class SearchController implements ActionListener, ItemListener {
    /**
     * The view that this controller is associated with.
     */
    private SearchView view;

    /**
     * The hotel reservation system model that this controller interacts with.
     */
    private HRSModel hrs;



    /**
     * Constructs a new SearchController instance.
     *
     * @param gui  the search view
     * @param model the hotel reservation system model
     */
    public SearchController(SearchView gui, HRSModel model) {
        this.view = gui;
        this.hrs = model;
        
        view.setActionListener(this);
        view.setCBHotel(hrs.getHotelList());
        view.setItemListener(this);
    }



    /**
     * Handles user input and button clicks from the SearchView.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DateSUBMIT")) {
            searchByDate();
            view.clearInput();
        }
        else if (e.getActionCommand().equals("RoomSUBMIT")) {
            searchByRoom();
            view.clearInput();
        }
        else if (e.getActionCommand().equals("ReservSUBMIT")) {
            searchByReserve();
        }
    }

    /**
     * Handles item state changes in the SearchView.
     *
     * @param e the item event
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String search = (String) view.getHotelCB().getComboBox().getSelectedItem();
            Hotel selectedHotel = hrs.findHotel(search);

            if (selectedHotel == null)
                view.setCBRoom(null);
            else {
                ArrayList<Room> roomList = selectedHotel.getRoomList();
                view.setCBRoom(roomList);
            }
        }
    }



    /**
     * Searches for reservations by date.
     */
    private void searchByDate() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            int date = Integer.parseInt(view.getDateInput());
            checkSingleDate(date);

            if (hrs.searchByDate(hotel, date)) {
                view.setDateSubmitStatus("");
                view.clearInput();
            }
            else
                view.setDateSubmitStatus("Please refresh window.");
        } catch (NoSelectionException e) {
            view.setDateSubmitStatus("Please select hotel.");
        } catch (NumberException | NumberFormatException e) {
            view.setDateSubmitStatus("Please check your date input.");
        }
    }

    /**
     * Searches for reservations by room.
     */
    private void searchByRoom() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            String room = view.getRoomSelected(1);
            checkSelect(room);

            if (hrs.searchByRoom(hotel, room))
                view.setRoomSubmiStatus("");
            else
                view.setRoomSubmiStatus("Please refresh window.");
        } catch (NoSelectionException e) {
            view.setRoomSubmiStatus("Please select hotel and room.");
        }
    }

    /**
     * Searches for reservations by reservation details.
     */
    private void searchByReserve() {
        String[] inputs = new String[6];
        int cIn, cOut;

        try {
            inputs[0] = view.getHotelSelected();
            checkSelect(inputs[0]);
            inputs[1] = view.getRoomSelected(2);
            checkSelect(inputs[1]);

            inputs[2] = view.getFNameInput();
            checkInput(inputs[2]);
            inputs[3] = view.getLNameInput();
            checkInput(inputs[3]);

            inputs[4] = view.getCInInput();
            cIn = Integer.parseInt(inputs[4]);
            inputs[5] = view.getCOutInput();
            cOut = Integer.parseInt(inputs[5]);
            checkTwoDates(cIn, cOut);

            if (hrs.searchByReserve(inputs, cIn, cOut)) {
                view.setReservSubmitStatus("");
                view.clearInput();
            }
            else
                view.setReservSubmitStatus("Reservation not found.");
        } catch (NoSelectionException e) {
            view.setReservSubmitStatus("Please select hotel and room.");
        } catch (NoInputException f) {
            view.setReservSubmitStatus("Please fill in all blanks.");
        } catch (NumberFormatException | NumberException e) {
            view.setReservSubmitStatus("Please check your dates.");
        }
    }



    // ----- EXCEPTIONS ----- //

    /**
     * Checks if a selection has been made.
     *
     * @param input the input to check
     * @throws NoSelectionException if no selection has been made
     */
    private void checkSelect(String input) throws NoSelectionException {
        if (input.equals("No hotel selected") || input.equals("No room selected"))
            throw new NoSelectionException("No hotel or room selected.");
    }

    /**
     * Checks if input has been provided.
     *
     * @param input the input to check
     * @throws NoInputException if no input has been provided
     */
    private void checkInput(String input) throws NoInputException {
        if (input.equals(""))
            throw new NoInputException("No input.");
    }

    /**
     * Checks if two dates are valid.
     *
     * @param cIn the check-in date
     * @param cOut the check-out date
     * @throws NumberException if the dates are invalid
     */
    private void checkTwoDates(int cIn, int cOut) throws NumberException {
        if ((cIn < 1 || cOut > 31) || cIn >= cOut)
            throw new NumberException("Invalid Dates.");
    }

    /**
     * Checks if a single date is valid.
     *
     * @param cIn the date to check
     * @throws NumberException if the date is invalid
     */
    private void checkSingleDate(int cIn) throws NumberException {
        if (cIn < 1 || cIn > 31)
            throw new NumberException("Invalid Dates.");
    }
}