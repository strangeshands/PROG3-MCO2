package MVCFiles;

import ClassFiles.*;
import ExceptionFiles.*;

import java.awt.event.*;
import java.util.ArrayList;


/**
 * Controller class for the booking page.
 * 
 * @author TOLENTINO, HEPHZI
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
     * The model object used for the Calendar
     */
    private CalendarModel booking;



    /**
     * Constructor for the BookingController class.
     * 
     * @param gui  the view object for the booking page
     * @param model  the model object for the hotel reservation system
     */
    public BookingController(BookingView gui, HRSModel model) {
        this.view = gui;
        this.hrs = model;

        booking = new CalendarModel();

        view.setActionListener(this);
        view.setCBHotel(hrs.getHotelList());
        view.setItemListener(this);
    }



    /**
     * Handles action events for the booking page.
     * 
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ReservSUBMIT" -> createReservation();
            case "select1DATES" -> bookingDate();
            case "Reserv2SUBMIT" -> createReservation(booking.getStartDay(), booking.getEndDay(), 2);
            case "select2DATES" -> bookingRoom();
            case "Reserv3SUBMIT" -> createReservation(booking.getStartDay(), booking.getEndDay(), 3);
        }
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

            if (selectedHotel == null) {
                view.setCBRoom1(null);
            }
            else {
                try {
                    ArrayList<Room> roomList = selectedHotel.getRoomList();
                    view.setCBRoom1(roomList);

                    ArrayList<Room> freeRooms = selectedHotel.getFreeRooms
                                                (booking.getStartDay(), booking.getEndDay());
                    view.setCBRoom2(freeRooms);
                } catch (Exception f) {
                    view.setCBRoom1(null);
                    view.setCBRoom2(null);
                }
            }
        }
    }



    /**
     * Controls the create new reservation based on user input.
     */
    private void createReservation() {
        String[] inputs = new String[7];
        int cIn, cOut, check;

        try {
            inputs[0] = view.getHotelSelected();
            checkSelect(inputs[0]);

            inputs[1] = view.getRoomSelected(1);
            checkSelect(inputs[1]);

            inputs[2] = view.getInputsTab1(1);
            checkInput(inputs[2]);
            inputs[3] = view.getInputsTab1(2);
            checkInput(inputs[3]);

            inputs[4] = view.getInputsTab1(3);
            cIn = Integer.parseInt(inputs[4]);
            inputs[5] = view.getInputsTab1(4);
            cOut = Integer.parseInt(inputs[5]);
            checkTwoDates(cIn, cOut);

            inputs[6] = view.getInputsTab1(5);
            
            check = hrs.createReservation(inputs, cIn, cOut);
            switch (check) {
                case 1 -> {
                    view.clearTF();
                    refreshWindow();
                }
                case 3 ->
                    view.setReservSubmitStatus("Room not available.", 1); 
                case 2 -> 
                    view.setReservSubmitStatus("Please check your voucher codes.", 1);
                default ->
                    view.setReservSubmitStatus("Please refresh window.", 1);
            }
        } catch (NoSelectionException e) {
            view.setReservSubmitStatus("Please select hotel and room.", 1);
        } catch (NoInputException e) {
            view.setReservSubmitStatus("Please fill in required blanks.", 1);
        } catch (NumberFormatException | NumberException e) {
            view.setReservSubmitStatus("Please check your dates.", 1);
        } catch (Exception e) {
            view.setReservSubmitStatus("Please refresh window.", 1);
        }
    }

    /**
     * Controls the date dependent booking.
     */
    private void bookingDate() {
        try {
            String hotelInput = view.getHotelSelected();
            checkSelect(hotelInput);
            Hotel currHotel = hrs.findHotel(hotelInput);

            CalendarView calPal = new CalendarView();
            calPal.setVisible(true);
            CalendarController calCon = new CalendarController(calPal, booking);

            calPal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent d) {
                    if (!(booking.getStartDay() == 0 || booking.getEndDay() == 0)) 
                    {
                        int startDay = booking.getStartDay();
                        int endDay = booking.getEndDay();
                        view.setSelectedDatesLabel("Selected Dates: June " + startDay + 
                                                " to June " + endDay, 1);

                        try {
                            ArrayList<Room> freeRooms;
                            freeRooms = currHotel.getFreeRooms(startDay, endDay);
                            view.setCBRoom2(freeRooms);
                        } catch (Exception f) {
                            view.setCBRoom2(null);
                        }
                    }
                }
            });
        } catch (NoSelectionException f) {
            view.setSelectedDatesLabel("Please select hotel.", 1);
        } catch (Exception e) {
            view.setSelectedDatesLabel("Please refresh window.", 1);
        }
    }

    /**
     * Controls the room dependent booking.
     */
    private void bookingRoom() {
        try {
            String hotelInput = view.getHotelSelected();
            checkSelect(hotelInput);
            Hotel currHotel = hrs.findHotel(hotelInput);

            String roomInput = view.getRoomSelected(3);
            checkSelect(roomInput);
            roomInput = roomInput.substring(0, 3);

            CalendarView calPal = new CalendarView(currHotel, roomInput);
            calPal.setVisible(true);
            CalendarController calCon = new CalendarController(calPal, booking);

            calPal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent d) {
                    if (!(booking.getStartDay() == 0 || booking.getEndDay() == 0)) 
                    {
                        int startDay = booking.getStartDay();
                        int endDay = booking.getEndDay();
                        view.setSelectedDatesLabel("Selected Dates: June " + startDay + 
                                                " to June " + endDay, 2);
                    }
                }
            });
        } catch (NoSelectionException e) {
            view.setSelectedDatesLabel("Please select hotel and room.", 2);
        } catch (Exception e) {
            view.setSelectedDatesLabel("Please refresh window.", 2);
        }
    }

    /**
     * Controls the create reservation portion with the provided dates
     * 
     * @param date1 The check in date
     * @param date2 The check out date
     * @param mode The mode of booking (2 - DATE, 3 - ROOM)
     */
    private void createReservation(int date1, int date2, int mode) {
        String[] inputs = new String[7];
        int check;

        try {
            inputs[0] = view.getHotelSelected();
            checkSelect(inputs[0]);

            inputs[1] = view.getRoomSelected(mode);
            checkSelect(inputs[1]);

            switch (mode) {
                case 2 -> {
                    inputs[2] = view.getInputsTab2(1);
                    inputs[3] = view.getInputsTab2(2);
                    inputs[6] = view.getInputsTab2(3);
                }
                case 3 -> {
                    inputs[2] = view.getInputsTab3(1);
                    inputs[3] = view.getInputsTab3(2);
                    inputs[6] = view.getInputsTab3(3);
                }
            }
            
            checkInput(inputs[2]);
            checkInput(inputs[3]);
            checkTwoDates(date1, date2);
            
            check = hrs.createReservation(inputs, date1, date2);
            switch (check) {
                case 1 -> {
                    view.clearTF();
                    refreshWindow();
                }
                case 3 ->
                    view.setReservSubmitStatus("Room not available.", mode); 
                case 2 -> 
                    view.setReservSubmitStatus("Please check your voucher codes.", mode);
                default ->
                    view.setReservSubmitStatus("Please refresh window.", mode);
            }
        } catch (NoSelectionException e) {
            view.setReservSubmitStatus("Please select hotel and room.", mode);
        } catch (NoInputException e) {
            view.setReservSubmitStatus("Please fill in required blanks.", mode);
        } catch (NumberException e) {
            view.setReservSubmitStatus("Please check your dates.", mode);
        } catch (Exception e) {
            view.setReservSubmitStatus("Please refresh window.", mode);
        }
    }

    /**
     *  Refreshes the comboboxes everytime there are changes made.
     */
    private void refreshWindow() {
        view.setCBHotel(hrs.getHotelList());

        view.repaint();
        view.revalidate();

        view.setCBRoom1(null);
        view.setCBRoom2(null);

        view.setSelectedDatesLabel("No hotel selected.", 1);
        view.setSelectedDatesLabel("No hotel selected.", 2);

        booking.setDate1st(0);
        booking.setDate2nd(0);
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
