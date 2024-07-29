package MVCFiles;

import ClassFiles.*;
import ExceptionFiles.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * Controller class for the booking page.
 * 
 * @author TOLENTINO, HEPHZI (main contributor)
 * @author SANTOS, FRANCINE
 */
public class BookingController implements ActionListener, ItemListener {
    /**
     * The view object for the booking page.
     */
    private BookingView view;

    /**
     * The model object for the hotel reservation system.
     */
    private HRSModel hrs;



    /**
     * Constructor for the CONTBook class.
     * 
     * @param gui  the view object for the booking page
     * @param model  the model object for the hotel reservation system
     */
    public BookingController(BookingView gui, HRSModel model) {
        this.view = gui;
        this.hrs = model;

        view.setActionListener(this);
        view.setCBHotel(hrs.getHotelList());
        view.setItemListener(this);
    }



    /**
     * Handles action events for the booking page.
     * 
     * @param e  the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ReservSUBMIT"))
            createReservation();
    }

    /**
     * Handles item state changes for the booking page.
     * 
     * @param e  the item event
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
     * Creates a new reservation based on user input.
     */
    private void createReservation() {
        String[] inputs = new String[7];
        int cIn, cOut, check;

        try {
            inputs[0] = view.getHotelSelected();
            checkSelect(inputs[0]);

            inputs[1] = view.getRoomSelected();
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

            inputs[6] = view.getVoucherInput();
            
            check = hrs.createReservation(inputs, cIn, cOut);
            switch (check) {
                case 1 -> 
                    view.clearTF();
                case 3 ->
                    view.setReservSubmitStatus("Room not available."); 
                case 2 -> 
                    view.setReservSubmitStatus("Please check your voucher codes.");
                default ->
                    view.setReservSubmitStatus("Please refresh window.");
            }
        } catch (NoSelectionException e) {
            view.setReservSubmitStatus("Please select hotel and room.");
        } catch (NoInputException e) {
            view.setReservSubmitStatus("Please fill in required blanks.");
        } catch (NumberFormatException | NumberException e) {
            view.setReservSubmitStatus("Please check your dates.");
        }
    }


    
    /**
     * Checks if a selection has been made.
     * 
     * @param input  the selected input
     * @throws NoSelectionException  if no selection has been made
     */
    private void checkSelect(String input) throws NoSelectionException {
        if (input.equals("No hotel selected") || input.equals("No room selected"))
            throw new NoSelectionException("No hotel or room selected.");
    }

    /**
     * Checks if an input has been provided.
     * 
     * @param input  the input to check
     * @throws NoInputException  if no input has been provided
     */
    private void checkInput(String input) throws NoInputException {
        if (input.equals(""))
            throw new NoInputException("No input.");
    }

    /**
     * Checks if the dates are valid.
     * 
     * @param cIn  the check-in date
     * @param cOut  the check-out date
     * @throws DateException  if the dates are invalid
     */
    private void checkTwoDates(int cIn, int cOut) throws NumberException {
        if ((cIn < 1 || cOut > 31) || cIn >= cOut)
            throw new NumberException("Invalid Dates.");
    }
}
