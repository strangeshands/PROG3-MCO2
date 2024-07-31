package MVCFiles;

import ClassFiles.*;
import DialogFiles.HotelInfo;
import ExceptionFiles.*;

import java.awt.event.*;
import javax.swing.JOptionPane;


/**
 *  CONTManage is thr controller for the ManageController window.
 *  
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 */
public class ManageController implements ActionListener, ItemListener {
    /**
     *  view is the GUI/view for this controller.
     *  hrs is the model for this controller.
     */
    private ManageView view;
    private HRSModel hrs;



    /**
     *  Constructs a controller for the ManageView window.
     * 
     *  @param gui The gui to be used.
     *  @param model The model to be used.
     */
    public ManageController(ManageView gui, HRSModel model) {
        this.view = gui;
        this.hrs = model;

        view.setActionListener(this);
        view.setCBHotel(hrs.getHotelList());
        view.setItemListener(this);
    }



    /**
     *  Sets the action for the specific button.
     *  
     *  @param e The action event that happens.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Hotel Tab
        if (e.getActionCommand().equals("NameSUBMIT")) {
            changeHotelName();  
        }
        if (e.getActionCommand().equals("DELETE")) {
            deleteHotel();
        }

        // Room Tab
        if (e.getActionCommand().equals("AddSUBMIT")) {
            addRooms();
        }
        if (e.getActionCommand().equals("RemoveSUBMIT")) {
            removeRooms();
        }

        // Price Tab
        if (e.getActionCommand().equals("PriceSUBMIT")) {
            changeRoomPrice();
        }

        // Reservations Tab
        if (e.getActionCommand().equals("RRSUBMIT")) {
            removeReservation();
        }

        // DPM Tab
        if (e.getActionCommand().equals("DPMSUBMIT")) {
            manageDPM();
        }
        if (e.getActionCommand().equals("ViewDPM")) {
            viewDPM();
        }

        // Voucher Tab
        if (e.getActionCommand().equals("VoucherSUBMIT")) {
            createVoucher();
        }
        if (e.getActionCommand().equals("ViewVOUCHER")){
            ViewVoucher();
        }
        if (e.getActionCommand().equals("voucherREMOVE")) {
            RemoveVoucher();
        }
    }



    /**
     *  Sets the room information according to the hotel selected in the
     *      hotel combo box.
     * 
     *  @param e The event that happened.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String search = (String)view.getHotelCB().getComboBox().getSelectedItem();
            Hotel selectedHotel = hrs.findHotel(search);

            if (selectedHotel == null) {
                view.setCBRooms(null);
                view.setRoomStatus("No hotel selected.");
                view.setPriceTable(0, 0, 0);
            }
            else {
                String message = "CURRENT ROOM COUNT: " + selectedHotel.getRoomList().size();
                view.setRoomStatus(message);

                // Setting up Comboboxes for Rooms
                view.setCBRooms(selectedHotel.getRoomList());

                // Setting up the price label
                float price1 = selectedHotel.getRoomPrice(1);
                float price2 = selectedHotel.getRoomPrice(2);
                float price3 = selectedHotel.getRoomPrice(3);
                view.setPriceTable(price1, price2, price3);
            }
        }
    }


    
    /**
     *  [DONE] Allows changing of hotel name.
     */
    private void changeHotelName() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            String newName = view.getNameInput();
            checkInput(newName);

            if (hrs.changeHotelName(hotel, newName)) {
                view.setSubmitStatus("Name successfully changed!", 0);

                String message =    "Hotel name changed from " +
                                    hotel + " to " + newName + ".";
                JOptionPane.showMessageDialog(null, message, "Change Name", 
                                            JOptionPane.INFORMATION_MESSAGE);

                view.clearInputTF();
                view.setSubmitStatus("", 0);
                refreshWindow();
            }
            else
                view.setSubmitStatus("New name already exists.", 0);
        } catch (NoSelectionException e) {
            view.setSubmitStatus("Please select hotel.", 0);
        } catch (NoInputException e) {
            view.setSubmitStatus("Please input needed field.", 0);
        } catch (Exception e) {
            view.setSubmitStatus("Please refresh window.", 0);
        }
    }

    /**
     *  [DONE] Allows deletion of a hotel.
     */
    private void deleteHotel() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);

            if (hrs.getHotelReservNum(hotel) != -1) 
            {
                String message =    "Are you sure you want to delete?\n" +
                                    "This hotel has currently " +
                                    hrs.getHotelReservNum(hotel) +
                                    " reservation/s.\n";
                int option = JOptionPane.showConfirmDialog(null, message, 
                                "Confirm Action", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    hrs.deleteHotel(hotel);
                    view.setDeleteStatus("Successfully deleted.");
                    refreshWindow();
                }
                else
                    view.setDeleteStatus("Hotel was not deleted.");
            }
            else 
                view.setDeleteStatus("An error occured. Refresh window.");
        } catch (NoSelectionException e) {
            view.setDeleteStatus("Please select hotel.");
        } catch (Exception e) {
            view.setDeleteStatus("Please refresh window.");
        }
    }

    /**
     *  [DONE] Adds room according to the input.
     */
    private void addRooms() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);

            String[] input = view.getAddRoomInput();
            int[] numInputs = new int[3];
            for (int i = 0; i < 3; i++)
                numInputs[i] = tryNumInput(input[i]);

            int check = hrs.addRoom(hotel, numInputs[0], numInputs[1], numInputs[2]);
            switch (check) {
                case 1 -> {
                    view.setSubmitStatus("Rooms successfully added.", 1);
                    Hotel selectedHotel = hrs.findHotel(hotel);

                    String message = "CURRENT ROOM COUNT: " + selectedHotel.getRoomList().size();
                    view.setRoomStatus(message);
                    HotelInfo prompt = new HotelInfo(selectedHotel, "New Rooms Added!", 1);

                    refreshWindow();
                    view.clearInputTF();
                    view.setSubmitStatus("", 1);
                }
                case 2 -> view.setSubmitStatus("Maximum of 50 rooms only.", 1);
                default -> view.setSubmitStatus("No room added.", 1);
            }
        } catch (NoSelectionException e) {
            view.setSubmitStatus("Please select hotel.", 1);
        } catch (Exception e) {
            view.setSubmitStatus("Please refresh window.", 1);
        }
    }

    /**
     *  [DONE] Removes the selected rooms.
     */
    private void removeRooms() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);

            int room1 = view.getRoomSelectedIndex(1);
            int room2 = view.getRoomSelectedIndex(2);
            checkRoomInputs(room1, room2);

            if (room2 == 0)
                room2 = room1;
            
            if (hrs.removeRooms(hotel, room1 - 1, room2 - 1)) {
                view.setSubmitStatus("Rooms successfully removed.", 2);

                // Show the changes made
                refreshWindow();
                //String message = "CURRENT ROOM COUNT: " + (hrs.findHotel(hotel)).getRoomList().size();
                view.setRoomStatus("");

                // Pop up window of the changes made.
                @SuppressWarnings("unused")
                HotelInfo prompt = new HotelInfo(hrs.findHotel(hotel), "Room/s Removed!", 1);
                view.clearInputTF();
            }
            else
                view.setSubmitStatus("Unable to remove room/s.", 2);

        } catch (NoSelectionException e) {
            view.setSubmitStatus("Please select hotel.", 2);
        } catch (NumberException e) {
            view.setSubmitStatus("Please check room selection.", 2);
        } catch (Exception e) {
            view.setSubmitStatus("Please refresh window.", 2);
        }
    }

    /**
     *  [DONE] Changes the price of the rooms.
     */
    private void changeRoomPrice() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            Hotel selectedHotel = hrs.findHotel(hotel);

            float newPrice = Float.parseFloat(view.getPriceInput());
            checkPriceInputs(newPrice);

            if (hrs.changeRoomPrice(hotel, newPrice)) {
                view.setSubmitStatus("Price successfully changed.", 3);

                // Setting up the price label
                float price1 = selectedHotel.getRoomPrice(1);
                float price2 = selectedHotel.getRoomPrice(2);
                float price3 = selectedHotel.getRoomPrice(3);
                view.setPriceTable(price1, price2, price3);
                view.clearInputTF();
            }
            else 
                view.setSubmitStatus("Can not change price.", 3);
        } catch (NoSelectionException e) {
            view.setSubmitStatus("Please select hotel.", 3);
        } catch (NumberException | NumberFormatException e) {
            view.setSubmitStatus("Minimum base price is 100.", 3);
        } catch (Exception e) {
            view.setSubmitStatus("Please refresh window.", 3);
        }
    }

    /**
     *  [DONE] Removes the reservation based on the user inputs.
     */
    private void removeReservation() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);

            String room = view.getRoomSelected();
            checkSelect(room);

            String fName = view.getFNameInput();
            checkInput(fName);
            String lName = view.getLNameInput();
            checkInput(lName);
            
            int cIn = Integer.parseInt(view.getCInInput());
            int cOut = Integer.parseInt(view.getCOutInput());
            checkTwoDates(cIn, cOut);

            if (hrs.findReservation(hotel, fName, lName, room, cIn, cOut)) 
            {
                // Prompt to confirm
                String message =    "Reservation found.\n" +
                                    "Name: " + fName + " " + lName + "\n" +
                                    "Room: " + room;
                int option = JOptionPane.showConfirmDialog(null, message, 
                            "Confirm Action", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    int check = hrs.removeReservation(hotel, fName, lName, room, cIn, cOut);

                    if (check == 1) {
                        view.setSubmitStatus("Reservation successfully removed.", 4);
                        view.clearInputTF();
                    }
                    else {
                        view.setSubmitStatus("An error occured.", 4);
                        view.clearInputTF();
                    }
                }
                else
                view.setSubmitStatus("Removal cancelled.", 4);
            }
            else
                view.setSubmitStatus("Reservation not found.", 4);
        } catch (NoSelectionException e) {
            view.setSubmitStatus("Please select hotel and room.", 4);
        } catch (NumberException e) {
            view.setSubmitStatus("Invalid dates.", 4);
        } catch (NoInputException e) {
            view.setSubmitStatus("Please fill in all required blanks.", 4);
        } catch (NumberFormatException e) {
            view.setSubmitStatus("Invalid dates.", 4);
        } catch (Exception e) {
            view.setSubmitStatus("Please refresh window.", 4);
        }
    }

    /**
     * [DONE] Allows updating of DPM.
     */
    private void manageDPM() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);

            int date = Integer.parseInt(view.getDateInput());
            float rate = Float.parseFloat(view.getDPMRateInput());
            checkRateDate(rate, date);

            int check = hrs.updateDPM(hotel, date, rate);

            switch (check) {
                case 1 -> {
                    view.setSubmitStatusDPMVouch("Successfully updated.", 1);

                    Hotel currHotel = hrs.findHotel(hotel);
                    HotelInfo dpmView = new HotelInfo(currHotel, "DPM", 2);

                    view.clearInputTF();
                    view.setSubmitStatusDPMVouch("", 1);
                }
                case 2 -> view.setSubmitStatusDPMVouch("Rate is only 50% to 150%.", 1);
                default -> view.setSubmitStatusDPMVouch("Please refresh window.", 1);
            }
        } catch (NumberFormatException | NumberException e) {
            view.setSubmitStatusDPMVouch("Please input valid date and rate.", 1);
        } catch (NoSelectionException e) {
            view.setSubmitStatusDPMVouch("Please select hotel.", 1);
        } catch (Exception e) {
            view.setSubmitStatusDPMVouch("Please refresh window.", 1);
        }
    }

    /**
     * [DONE] Allows viewing of DPM
     */
    private void viewDPM() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            Hotel currHotel = hrs.findHotel(hotel);

            HotelInfo dpmView = new HotelInfo(currHotel, "DPM", 2);
            view.setSubmitStatusDPMVouch("Click here to view DPM info.", 2);
        } catch (NoSelectionException e) {
            view.setSubmitStatusDPMVouch("Please select hotel.", 2);
        } catch (Exception e) {
            view.setSubmitStatusDPMVouch("Please refresh window.", 2);
        }
    }

    /**
     * [DONE] Allows creation of voucher
     */
    private void createVoucher() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            Hotel currHotel = hrs.findHotel(hotel);
            int typeSelect = view.getVoucherType();
            checkType(typeSelect);

            switch (typeSelect) {
                case 1 -> {
                    VoucherCreateView type1 = new VoucherCreateView(view, typeSelect);
                    VoucherCreateController cont1 = new VoucherCreateController(currHotel, 
                                                        type1, typeSelect);
                }
                case 2 -> {
                    VoucherCreateView type2 = new VoucherCreateView(view, typeSelect);
                    VoucherCreateController cont1 = new VoucherCreateController(currHotel, 
                                                        type2, typeSelect);
                }
                case 3 -> {
                    VoucherCreateView type3 = new VoucherCreateView(view, typeSelect);
                    VoucherCreateController cont1 = new VoucherCreateController(currHotel, 
                                                        type3, typeSelect);
                }
            }
        } catch (NoSelectionException e) {
            view.setSubmitStatusDPMVouch("Please select hotel.", 3);
        } catch (NumberException e) {
            view.setSubmitStatusDPMVouch("Please select voucher type.", 3);
        } catch (Exception e) {
            view.setSubmitStatusDPMVouch("Please refresh window.", 3);
        }
    }

    /**
     * [DONE] Allows viewing of voucher
     */
    private void ViewVoucher() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            Hotel currHotel = hrs.findHotel(hotel);

            HotelInfo vouView = new HotelInfo(currHotel, "Voucher List", 3);
            view.setSubmitStatusDPMVouch("Click here to view Vouchers.", 5);
        } catch (NoSelectionException e) {
            view.setSubmitStatusDPMVouch("Please select hotel.", 5);
        } catch (Exception e) {
            view.setSubmitStatusDPMVouch("Please refresh window.", 5);
        }
    }

    /**
     * [DONE] Allows removal of vouchers
     */
    private void RemoveVoucher() {
        try {
            String hotel = view.getHotelSelected();
            checkSelect(hotel);
            Hotel currHotel = hrs.findHotel(hotel);

            String code = view.getRVCode();

            int check = currHotel.removeVoucher(code);
            switch (check) {
                case 1 -> {
                    JOptionPane.showMessageDialog(null,
                            code + " has been successfully deleted.",
                            "Voucher Deleted",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Show update
                    HotelInfo vouView = new HotelInfo(currHotel, "Voucher List", 3);
                    
                    view.setSubmitStatusDPMVouch("Click here to view Vouchers.", 4);
                    view.clearInputTF();
                }
                case 3 ->   JOptionPane.showMessageDialog(null,
                            code + " cannot be deleted.",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                default ->  JOptionPane.showMessageDialog(null,
                            code + " was not found.",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NoSelectionException e) {
            view.setSubmitStatusDPMVouch("Please select hotel.", 4);
        } catch (Exception e) {
            view.setSubmitStatusDPMVouch("Please refresh window.", 4);
        }
    }

    

    // ----- PRIVATE METHODS ----- // 

    /**
     *  Refreshes the comboboxes everytime there are changes made.
     */
    private void refreshWindow() {
        view.setCBHotel(hrs.getHotelList());
        view.repaint();
        view.revalidate();

        // Update the comboboxes and labels dependent on the hotel
        view.setCBHotel(hrs.getHotelList());
        view.setCBRooms(null);
        view.setRoomStatus("No hotel selected.");
        view.setPriceTable(0, 0, 0);
    }

    

    // ----- EXCEPTIONS ----- // 

    /**
     *  Checks the selection of the combo boxes.
     * 
     *  @param input The text acquired from the selected item.
     *  @throws NoSelectionException When the selection is in the default.
     */
    private void checkSelect(String input) throws NoSelectionException {
        if (input.equals("No hotel selected") || input.equals("No room selected"))
            throw new NoSelectionException("No hotel or room selected.");
    }

    /**
     *  Checks if the input is not empty.
     * 
     *  @param input The input from a text field.
     *  @throws NoInputException When the input is empty.
     */
    private void checkInput(String input) throws NoInputException {
        if (input.equals(""))
            throw new NoInputException("No input.");
    }

    /**
     *  Checks if there are selected room inputs. lb can't be 0 and ub can't
     *      be less than lb.
     * 
     *  @param lb The lower bound index.
     *  @param ub The upper bound index.
     *  @throws NumberException When the conditions is not satisfied.
     */
    private void checkRoomInputs(int lb, int ub) throws NumberException {
        if (lb == 0 || (lb > ub && ub != 0))
            throw new NumberException("Invalid selections");
    }

    /**
     *  Checks if the price input is < 100.
     * 
     *  @param price The price input.
     *  @throws NumberException When the price is less than 100.
     */
    private void checkPriceInputs(double price) throws NumberException {
        if (price < 100)
            throw new NumberException("Invalid price");
    }

    /**
     *  Checks the two dates (cIn and cOut) if it satisfies the conditions
     *      cIn >= 1
     *      cOut <= 31
     *      cIn < cOut
     * 
     *  @param cIn The check in date.
     *  @param cOut The check out date.
     *  @throws NumberException When the conditions are not satisfied.
     */
    private void checkTwoDates(int cIn, int cOut) throws NumberException {
        if ((cIn < 1 || cOut > 31) || cIn >= cOut)
            throw new NumberException("Invalid Dates.");
    }

    /**
     *  Tries number inputs and returns the value. 0 if the input is 
     *      not a number.
     * 
     *  @param source The String input that MUST be a number.
     *  @return The parsed integer of the String, 0 if not a number.
     */
    private int tryNumInput(String source) {
        int dest;

        try {
            dest = Integer.parseInt(source);

            if (dest < 0)
                dest = 0;
        } catch (NumberFormatException e) {
            dest = 0;
        }

        return dest;
    }

    /**
     * Checks if the provided type is valid.
     * 
     * @param type the type to be checked
     * @throws NumberException if the type is invalid
     */
    private void checkType(int type) throws NumberException {
        if (type == 0)
            throw new NumberException("Invalid Selection.");
    }

    /**
     * Checks if the provided rate and date are valid.
     * 
     * @param rate the rate to be checked
     * @param date the date to be checked
     * @throws NumberException if the rate or date is invalid
     */
    private void checkRateDate(float rate, int date) throws NumberException {
        if (rate < 1 || date < 1 || date > 31)
            throw new NumberException("Invalid Rate.");
    }
}
