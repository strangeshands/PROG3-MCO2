package MVCFiles;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * A custom JDialog for creating a voucher.
 */
public class VoucherCreateView extends JDialog {
    /**
     * The text field for the discount rate.
     */
    private JTextField discountRateField;

    /**
     * The text field for the discount code.
     */
    private JTextField discountCodeField;

    /**
     * The OK button.
     */
    private JButton okButton;

    /**
     * The Cancel button.
     */
    private JButton cancelButton;

    /**
     * The text field for the first number.
     */
    private JTextField numField1;

    /**
     * The text field for the second number.
     */
    private JTextField numField2;



    /**
     * Constructs a new VoucherCreateView.
     * 
     * @param parent the parent JFrame
     * @param type the type of voucher to create (1, 2, or 3)
     */
    public VoucherCreateView(JFrame parent, int type) {
        super(parent, "Discount Voucher", true);

        switch (type) {
            case 1 -> type1();
            case 2 -> type2();
            case 3 -> type3();
        }
        
        setLocationRelativeTo(parent);
    }



    /**
     * Sets up the dialog for creating a type 1 voucher.
     */
    private void type1() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Discount Code:"));
        discountCodeField = new JTextField(10);
        panel.add(discountCodeField);
        panel.add(new JLabel("Discount Rate (%):"));
        discountRateField = new JTextField(10);
        panel.add(discountRateField);

        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        panel.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        setSize(250, 125);
    }

    /**
     * Sets up the dialog for creating a type 2 voucher.
     */
    private void type2() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Discount Code:"));
        discountCodeField = new JTextField(10);
        panel.add(discountCodeField);

        panel.add(new JLabel("Discount Rate (%):"));
        discountRateField = new JTextField(10);
        panel.add(discountRateField);

        panel.add(new JLabel("Date 1 (1-31):"));
        numField1 = new JTextField(10);
        panel.add(numField1);
        
        panel.add(new JLabel("Date 2 (1-31):"));
        numField2 = new JTextField(10);
        panel.add(numField2);

        JPanel buttonPanel = new JPanel();
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(250, 200);
    }

    /**
     * Sets up the dialog for creating a type 3 voucher.
     */
    private void type3() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Discount Code:"));
        discountCodeField = new JTextField(10);
        panel.add(discountCodeField);

        panel.add(new JLabel("Discount Rate (%):"));
        discountRateField = new JTextField(10);
        panel.add(discountRateField);

        panel.add(new JLabel("Length of Stay:"));
        numField1 = new JTextField(10);
        panel.add(numField1);
        
        panel.add(new JLabel("Free Day:"));
        numField2 = new JTextField(10);
        panel.add(numField2);

        JPanel buttonPanel = new JPanel();
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(250, 200);
    }



    /**
     * Sets the action listener for the OK and Cancel buttons.
     * 
     * @param listener the action listener to set
     */
    public void setActionListener(ActionListener listener) {
        okButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }

    

    /**
     * Returns the text field for the discount rate.
     * 
     * @return the text field for the discount rate
     */
    public JTextField getDiscountRateField() {
        return discountRateField;
    }

    /**
     * Returns the text field for the discount code.
     * 
     * @return the text field for the discount code
     */
    public JTextField getDiscountCodeField() {
        return discountCodeField;
    }

    /**
     * Returns the text in the first number field.
     * 
     * @return the text in the first number field
     */
    public String getNum1Field() {
        return numField1.getText();
    }

    /**
     * Returns the text in the second number field.
     * 
     * @return the text in the second number field
     */
    public String getNum2Field() {
        return numField2.getText();
    }

    /**
     * Returns the OK button.
     * 
     * @return the OK button
     */
    public JButton getOkButton() {
        return okButton;
    }

    /**
     * Returns the Cancel button.
     * 
     * @return the Cancel button
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
}
