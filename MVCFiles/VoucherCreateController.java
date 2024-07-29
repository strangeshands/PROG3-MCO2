package MVCFiles;

import ClassFiles.*;
import ExceptionFiles.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * This class represents a controller for creating vouchers in a hotel management system.
 * It handles user input from the VoucherCreateView and interacts with the Hotel model to create vouchers.
 *
 * @author Tolentino, Hephzi (main contributor)
 * @author Santos, Francine
 */
public class VoucherCreateController implements ActionListener {
    /**
     * The hotel model that this controller interacts with.
     */
    private Hotel hotel;

    /**
     * The view that this controller is associated with.
     */
    private VoucherCreateView view;

    /**
     * The type of voucher being created (1, 2, or 3).
     */
    private int type;



    /**
     * Constructs a new VoucherCreateController instance.
     *
     * @param model the hotel model
     * @param view  the voucher create view
     * @param type  the type of voucher being created
     */
    public VoucherCreateController(Hotel model, VoucherCreateView view, int type) {
        this.hotel = model;
        this.view = view;
        this.type = type;

        view.setActionListener(this);
        view.setVisible(true);
    }



    /**
     * Handles user input from the VoucherCreateView.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK"))
            createVoucher(type);
        else
            view.setVisible(false);
    }

    /**
     * Creates a new voucher based on the user input and voucher type.
     *
     * @param type the type of voucher being created
     */
    public void createVoucher(int type) {
        try {
            String code = view.getDiscountCodeField().getText();
            float rate = Float.parseFloat(view.getDiscountRateField().getText());
            checkRate(rate);
            rate /= 100;

            switch (type) {
                case 1 -> {
                    if (hotel.createVoucherType1(code, rate))
                        JOptionPane.showMessageDialog(view, "Voucher " + code + " added!");
                }
                case 2 -> {
                    int date1 = tryNumInput(view.getNum1Field());
                    int date2 = tryNumInput(view.getNum2Field());
                    checkDate(date1, date2);

                    if (hotel.createVoucherType2(code, rate, date1, date2))
                        JOptionPane.showMessageDialog(view, "Voucher " + code + " added!");
                }
                case 3 -> {
                    int length = Integer.parseInt(view.getNum1Field());
                    checkRate((float)length);
                    int day = Integer.parseInt(view.getNum2Field());
                    checkRate((float)day);

                    checkLength(length, day);

                    if (hotel.createVoucherType3(code, rate, length, day))
                        JOptionPane.showMessageDialog(view, "Voucher " + code + " added!");
                }
            }
        } catch (NumberFormatException | NumberException ex) {
            JOptionPane.showMessageDialog(view, 
            "Invalid input.");
        }
    }



    // ----- EXCEPTION ----- //
    
    /**
     * Checks if the rate is valid (non-negative).
     *
     * @param rate the rate to check
     * @throws NumberException if the rate is invalid
     */
    private void checkRate(float rate) throws NumberException {
        if (rate < 0)
            throw new NumberException("Invalid rate.");
    }

    /**
     * Attempts to parse a string as an integer.
     *
     * @param source the string to parse
     * @return the parsed integer, or 0 if parsing fails
     */
    private int tryNumInput(String source) {
        int dest;

        try {
            dest = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            dest = 0;
        }

        return dest;
    }

    /**
     * Checks if the date range is valid (1-31).
     *
     * @param date1 the start date
     * @param date2 the end date
     * @throws NumberException if the date range is invalid
     */
    private void checkDate(int date1, int date2) throws NumberException {
        if (date1 < 1 || date1 > 31 || date2 > 31 || date2 < 0)
            throw new NumberException("Invalid date.");
    }

    /**
     * Checks if the length and day are valid (1-31, length >= day).
     *
     * @param length the length
     * @param day    the day
     * @throws NumberException if the length and day are invalid
     */
    private void checkLength(int length, int day) throws NumberException {
        if (length < 1 || length > 31 || length < day)
            throw new NumberException("Invalid length and day.");
    }
}

