/*
 *  AUTHOR'S NOTE
 *  For accessing the submitButtons:
 *      index 0 is for name submit
 *      index 1 is for add submit
 *      index 2 is for remove room submit
 *      index 3 is for price submit
 *      index 4 is for reservation submit
 */


package MVCFiles;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

import FormatFiles.*;
import ClassFiles.*;


/**
 *  This is a view class for the VIEWManage window and extends FORMATFrame.
 * 
 *  @author TOLENTINO, HEPHZI (main contributor)
 *  @author SANTOS, FRANCINE
 */
public class ManageView extends FrameTemplate {
    /**
     *  CBHotels is the combo box for the hotels.
     */
    private ComboBoxHotel CBHotels;

    /**
     *  nameInput is a panel where the user can input the name of the hotel.
     */
    private InputTextField nameInput;

    /**
     *  delete is a button that tells the controller to delete the selected hotel.
     *  delStatus is the status set depending on what action happened.
     */
    private Buttons delete;
    private JLabel delStatus;

    /**
     *  roomStatusLabel is a label that displays the room count.
     *  addInput is an array list of input fields for adding rooms.
     *  CBRoomsLB and CBRoomsUB are combo boxes for selecting rooms.
     */
    private JLabel roomStatusLabel;
    private ArrayList<InputTextField> addInput;
    private ComboBoxRoom CBRoomsLB;
    private ComboBoxRoom CBRoomsUB;

    /**
     *  priceInput is a panel where the user can input the new price.
     *  priceTable is a table that displays the current prices.
     */
    private InputTextField priceInput;
    private JTable priceTable;

    /**
     *  fNameInput, lNameInput, CBRooms, cInInput, and cOutInput are input fields 
     *      for reservation details.
     */
    private InputTextField fNameInput;
    private InputTextField lNameInput;
    private ComboBoxRoom CBRooms;
    private InputTextField cInInput;
    private InputTextField cOutInput;

    /**
     * submitButtons is an array list of submit buttons.
     */
    private ArrayList<Submit> submitButtons;

    /**
     *  DPMRateInput is the panel where user can input rate
     *  DateSelect is the combobox where user can select date
     *  DPMSubmit is a panel with label and submit button for DPM
     */
    private InputTextField DPMRateInput;
    private InputTextField DateInput;
    private Submit DPMSubmit;
    private Submit ViewDPM;

    /**
     * voucherType allows selection of voucher types when creating a new one
     * voucherSubmit submits the selected type
     * viewVoucher is a button that allows viewing of vouchers
     * removeVoucher is a field where user can input the code for deletion
     * removeVouchSubmit confirms removal
     */
    private JComboBox<String> voucherType;
    private Submit voucherSubmit;
    private Submit ViewVoucher;
    private InputTextField removeVoucher;
    private Submit removeVouchSubmit;
    
    

    /**
     * Constructor for VIEWManage.
     */
    public ManageView() {
        super("Manage", 450, 575);
        initialize();
        setVisible(true);
    }



    /**
     * Initializes the VIEWManage window.
     */
    protected void initialize() {
        initializeNorth();
        initializeCenter();
    }
    

    
    /**
     * Initializes the north panel of the VIEWManage window.
     */
    private void initializeNorth() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.decode("#EBE9E1"));

        HeaderLabel title = new HeaderLabel("MANAGE");
        title.setBorder(BorderFactory.createEmptyBorder(25, 45, 5, 0));

        CBHotels = new ComboBoxHotel();
        CBHotels.setBackground(Color.decode("#EBE9E1"));

        northPanel.add(title, BorderLayout.NORTH);
        northPanel.add(CBHotels, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Initializes the center panel of the VIEWManage window.
     */
    private void initializeCenter() {
        submitButtons = new ArrayList<Submit>();
        populateSubmitButtons();

        JPanel modifyHotelPanel = new JPanel();
        modifyHotelPanel.add(makeModHotelPanel());

        JPanel modifyRoomPanel = new JPanel();
        modifyRoomPanel.add(makeModRoomPanel());
        JScrollPane modifyRoomScroll = new JScrollPane(modifyRoomPanel);

        JPanel modifyPricePanel = new JPanel();
        modifyPricePanel.add(makeModPricePanel());
        
        JPanel modifyReservationPanel = new JPanel();
        modifyReservationPanel.add(makeModReservPanel());
        JScrollPane modifyReserScroll = new JScrollPane(modifyReservationPanel);

        JPanel modifyDPM = new JPanel();
        modifyDPM.add(makeMODDPMPanel());

        JPanel modifyVoucher = new JPanel();
        modifyVoucher.add(makeVoucherPanel());

        JTabbedPane manageTabs = new JTabbedPane();
        manageTabs.setFont(new Font("Century Gothic", Font.BOLD, 13));
        manageTabs.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

        manageTabs.add("Hotel", modifyHotelPanel);
        manageTabs.add("Room", modifyRoomScroll);
        manageTabs.add("Prices", modifyPricePanel);
        manageTabs.add("Reservation", modifyReserScroll);
        manageTabs.add("DPM", modifyDPM);
        manageTabs.add("Voucher", modifyVoucher);
        
        add(manageTabs, BorderLayout.CENTER);
    }

    /**
     * Populates the submitButtons array list.
     */
    private void populateSubmitButtons() {
        Submit newButton;
        for (int i = 0; i < 5; i++) {
            newButton = new Submit();
            newButton.getLabel().setBackground(Color.decode("#F0F0F0"));
            newButton.getLabel().setVisible(true);

            switch (i) {
                case 0 -> newButton.getButton().setActionCommand("NameSUBMIT");
                case 1 -> newButton.getButton().setActionCommand("AddSUBMIT");
                case 2 -> newButton.getButton().setActionCommand("RemoveSUBMIT");
                case 3 -> newButton.getButton().setActionCommand("PriceSUBMIT");
                case 4 -> newButton.getButton().setActionCommand("ReservSUBMIT");
            }

            submitButtons.add(newButton);
        }
    }

    /**
     * Creates the modify hotel panel.
     * 
     * @return the modify hotel panel
     */
    private JPanel makeModHotelPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel changePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        changePanel.setMaximumSize(new Dimension(350, 40));

        HeaderLabel changeLabel = new HeaderLabel("Change Hotel Name");
        changeLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        changeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        changePanel.add(changeLabel);

        nameInput = new InputTextField("Enter New Name:", 14);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deletePanel.setMaximumSize(new Dimension(350, 40));

        HeaderLabel deleteLabel = new HeaderLabel("Delete Hotel");
        deleteLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        deleteLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        deletePanel.add(deleteLabel);

        JPanel delButtonPanel = new JPanel(new BorderLayout());
        delButtonPanel.setMaximumSize(new Dimension(300, 50));
            
        delStatus = new JLabel("Once you have clicked DELETE, you can not undo.");
        delStatus.setForeground(Color.decode("#E43D12"));
        delStatus.setFont(new Font("Century Gothic", Font.BOLD, 11));
        delStatus.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            
        delete = new Buttons("DELETE");
        delete.setActionCommand("DELETE");
    
        delButtonPanel.add(delete, BorderLayout.NORTH);
        delButtonPanel.add(delStatus, BorderLayout.SOUTH);

        panel.add(changePanel);
        panel.add(nameInput);
        panel.add(Box.createVerticalStrut(8));
        panel.add(submitButtons.get(0));

        panel.add(Box.createVerticalStrut(10));
        panel.add(deletePanel);
        panel.add(delButtonPanel);

        return panel;
    }

    /**
     * Creates the modify room panel.
     * 
     * @return the modify room panel
     */
    private JPanel makeModRoomPanel() {
        addInput = new ArrayList<InputTextField>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.setMaximumSize(new Dimension(350, 40));
        
        JLabel addRoomLabel = new JLabel("Add Room");
        addRoomLabel.setForeground(Color.decode("#E43D12"));
        addRoomLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        addRoomLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        addPanel.add(addRoomLabel);

        JPanel roomStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomStatusPanel.setMaximumSize(new Dimension(350, 30));

        roomStatusLabel = new JLabel();
        roomStatusLabel.setForeground(Color.decode("#E43D12"));
        roomStatusLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
        roomStatusLabel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        roomStatusPanel.add(roomStatusLabel);

        addInput.add(new InputTextField("Standard Rooms:", 10));
        addInput.add(new InputTextField("Deluxe Rooms:", 10));
        addInput.add(new InputTextField("Executive Rooms:", 10));

        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        removePanel.setMaximumSize(new Dimension(350, 40));
        
        JLabel removeRoomLabel = new JLabel("Remove Room");
        removeRoomLabel.setForeground(Color.decode("#E43D12"));
        removeRoomLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        removeRoomLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        removePanel.add(removeRoomLabel);

        JPanel lowerBoundPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lowerBoundPanel.setMaximumSize(new Dimension(355, 40));

        JLabel lowerBoundLabel = new JLabel("Choose Room (Lower Bound):");
        lowerBoundLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));

        CBRoomsLB = new ComboBoxRoom();
        CBRoomsLB.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        lowerBoundPanel.add(lowerBoundLabel);
        lowerBoundPanel.add(CBRoomsLB);

        JPanel upperBoundPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upperBoundPanel.setMaximumSize(new Dimension(355, 40));

        JLabel upperBoundLabel = new JLabel("Choose Room (Upper Bound):");
        upperBoundLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));

        CBRoomsUB = new ComboBoxRoom();
        CBRoomsUB.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        upperBoundPanel.add(upperBoundLabel);
        upperBoundPanel.add(CBRoomsUB);

        panel.add(roomStatusPanel);
        panel.add(addPanel);
        panel.add(addInput.get(0));
        panel.add(addInput.get(1));
        panel.add(addInput.get(2));
        panel.add(Box.createVerticalStrut(5));
        panel.add(submitButtons.get(1));
        panel.add(Box.createVerticalStrut(10));

        panel.add(removePanel);
        panel.add(lowerBoundPanel);
        panel.add(upperBoundPanel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(submitButtons.get(2));

        return panel;
    }

    /**
     * Creates the modify price panel.
     * 
     * @return the modify price panel
     */
    private JPanel makeModPricePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel changePricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        changePricePanel.setMaximumSize(new Dimension(350, 30));
        
        JLabel changePriceLabel = new JLabel("Change Room Price");
        changePriceLabel.setForeground(Color.decode("#E43D12"));
        changePriceLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        changePriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        changePricePanel.add(changePriceLabel);

        String[] columns = {"Room Type", "Current Price"};
        Object[][] data = new Object[][]{{"STANDARD", 0.0}, {"DELUXE", 0.0}, {"EXECUTIVE", 0.0}};

        priceTable = new JTable(data, columns);
        priceTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        priceTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(priceTable);
        scrollPane.setPreferredSize(new Dimension(200, 73));

        priceInput = new InputTextField("New Price:", 13);

        panel.add(changePricePanel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(8));
        panel.add(priceInput);
        panel.add(Box.createVerticalStrut(8));
        panel.add(submitButtons.get(3));

        return panel;
    }

    /**
     * Creates the modify reservation panel.
     * 
     * @return the modify reservation panel
     */
    private JPanel makeModReservPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel removeReservPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        removeReservPanel.setMaximumSize(new Dimension(350, 30));
        
        JLabel removeReservLabel = new JLabel("Remove Reservation");
        removeReservLabel.setForeground(Color.decode("#E43D12"));
        removeReservLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        removeReservLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 0));
        removeReservPanel.add(removeReservLabel);
        
        panel.add(removeReservPanel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("Reservation Details");
        title.setFont(new Font ("Century Gothic", Font.BOLD, 13));
        title.setForeground(Color.decode("#E43D13"));
        panel2.add(title);

        panel.add(Box.createVerticalStrut(5));
        panel.add(panel2);

        fNameInput = new InputTextField("First Name:", 16);
        lNameInput = new InputTextField("Last Name:", 16);

        JPanel cbPanel = new JPanel(new BorderLayout());
        JLabel cbLabel = new JLabel("Choose Room:");
        cbLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
        cbLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        cbLabel.setForeground(Color.decode("#E43D12"));
        CBRooms = new ComboBoxRoom();
        CBRooms.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        cbPanel.add(cbLabel, BorderLayout.WEST);
        cbPanel.add(CBRooms, BorderLayout.EAST);

        cInInput = new InputTextField("Check In Date:", 13);
        cOutInput = new InputTextField("Check Out Date:", 13);

        panel.add(fNameInput);
        panel.add(lNameInput);
        panel.add(cbPanel);
        panel.add(cInInput);
        panel.add(cOutInput);

        submitButtons.get(4).getButton().setText("REMOVE");
        submitButtons.get(4).getButton().setActionCommand("RRSUBMIT");
        submitButtons.get(4).getButton().setPreferredSize(new Dimension(100, 30));
        submitButtons.get(4).getButton().setVisible(true);

        panel.add(Box.createVerticalStrut(8));
        panel.add(submitButtons.get(4));

        return panel;
    }

    /**
     * Creates the modify DPM panel.
     * 
     * @return the modify DPM panel
     */
    private JPanel makeMODDPMPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel DPMPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DPMPanel.setMaximumSize(new Dimension(350, 40));
        
        JLabel DPMLabel = new JLabel("Date Price Modifier");
        DPMLabel.setForeground(Color.decode("#E43D12"));
        DPMLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        DPMLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        DPMPanel.add(DPMLabel);
        
        panel.add(DPMPanel);

        DateInput = new InputTextField("Enter Date:", 10);
        DPMRateInput = new InputTextField("Date Rate:", 10);
        DPMSubmit = new Submit();
        DPMSubmit.getButton().setActionCommand("DPMSUBMIT");

        ViewDPM = new Submit();
        ViewDPM.getLabel().setText("Click here to view DPM info.");
        ViewDPM.getButton().setText("VIEW");
        ViewDPM.getButton().setActionCommand("ViewDPM");

        panel.add(DateInput);
        panel.add(DPMRateInput);
        panel.add(Box.createVerticalStrut(10));
        panel.add(DPMSubmit);
        panel.add(Box.createVerticalStrut(10));
        panel.add(ViewDPM);

        return panel;
    }
    

    /**
     * Creates the voucher panel.
     * 
     * @return the voucher panel
     */
    private JPanel makeVoucherPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel createvoucherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createvoucherPanel.setMaximumSize(new Dimension(350, 30));
        
        JLabel cvLabel = new JLabel("Create Voucher");
        cvLabel.setForeground(Color.decode("#E43D12"));
        cvLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        cvLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 0));
        createvoucherPanel.add(cvLabel);

        JPanel voucherPanel = new JPanel(new BorderLayout());
        JLabel selectType = new JLabel("Select Voucher Type:");
        selectType.setFont(new Font("Century Gothic", Font.BOLD, 12));
        selectType.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 25));
        String[] voucherTypes = {"No item selected", "Discount Voucher", "Date Voucher", "Stay Voucher"};
        voucherType = new JComboBox<>(voucherTypes);
        voucherPanel.add(selectType, BorderLayout.WEST);
        voucherPanel.add(voucherType, BorderLayout.EAST);

        voucherSubmit = new Submit();
        voucherSubmit.getButton().setActionCommand("VoucherSUBMIT");

        ViewVoucher = new Submit();
        ViewVoucher.getLabel().setText("Click here to view Vouchers.");
        ViewVoucher.getButton().setText("VIEW");
        ViewVoucher.getButton().setActionCommand("ViewVOUCHER");

        JPanel removeVoucherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        removeVoucherPanel.setMaximumSize(new Dimension(350, 30));
        
        JLabel rvLabel = new JLabel("Delete Voucher");
        rvLabel.setForeground(Color.decode("#E43D12"));
        rvLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        rvLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 2, 0));
        removeVoucherPanel.add(rvLabel);

        removeVoucher = new InputTextField("Voucher Code:", 15);
        removeVouchSubmit = new Submit();
        removeVouchSubmit.getButton().setActionCommand("voucherREMOVE");

        panel.add(createvoucherPanel);
        panel.add(voucherPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(voucherSubmit);
        panel.add(Box.createVerticalStrut(10));
        panel.add(ViewVoucher);
        panel.add(Box.createVerticalStrut(20));
        panel.add(removeVoucherPanel);
        panel.add(removeVoucher);
        panel.add(Box.createVerticalStrut(10));
        panel.add(removeVouchSubmit);

        return panel;
    }
    
    
    
    // ----- CONTROLLER CONNECTION ----- //

    /**
     * Sets the action listener for the submit buttons.
     * 
     * @param listener the action listener
     */
    public void setActionListener(ActionListener listener) {
        for (int i = 0; i < 5; i++)
            submitButtons.get(i).getButton().addActionListener(listener);

        delete.addActionListener(listener);
        DPMSubmit.getButton().addActionListener(listener);
        ViewDPM.getButton().addActionListener(listener);
        voucherSubmit.getButton().addActionListener(listener);
        ViewVoucher.getButton().addActionListener(listener);
        removeVouchSubmit.getButton().addActionListener(listener);
    }

    /**
     * Sets the item listener for the hotel combo box.
     * 
     * @param listener the item listener
     */
    public void setItemListener(ItemListener listener) {
        CBHotels.getComboBox().addItemListener(listener);
    }

    /**
     * Clears the input fields and labels.
     */
    public void clearInputTF() {
        nameInput.getTextField().setText("");
        priceInput.getTextField().setText("");
        DateInput.getTextField().setText("");
        DPMRateInput.getTextField().setText("");
        removeVoucher.getTextField().setText("");

        for (int i = 0; i < 3; i++) 
            addInput.get(i).getTextField().setText("");

        for (int i = 0; i < 5; i++) 
            submitButtons.get(i).getLabel().setText("");

        fNameInput.getTextField().setText("");
        lNameInput.getTextField().setText("");
        cInInput.getTextField().setText("");
        cOutInput.getTextField().setText("");
    }



    /**
     * Gets the name input.
     * 
     * @return the name input
     */
    public String getNameInput() {
        return nameInput.getTextField().getText();
    }

    /**
     * Gets the hotel selected.
     * 
     * @return the hotel selected
     */
    public String getHotelSelected() {
        return CBHotels.getHotelSelected();
    }

    /**
     * Returns the hotel combo box.
     * 
     * @return the hotel combo box
     */
    public ComboBoxHotel getHotelCB() {
        return CBHotels;
    }

    /**
     * Returns an array of strings containing the input values for adding rooms.
     * 
     * @return an array of strings containing the input values for adding rooms
     */
    public String[] getAddRoomInput() {
        String[] inputs = new String[3];

        inputs[0] = addInput.get(0).getTextField().getText();
        inputs[1] = addInput.get(1).getTextField().getText();
        inputs[2] = addInput.get(2).getTextField().getText();

        return inputs;
    }

    /**
     * Returns the first name input.
     * 
     * @return the first name input
     */
    public String getFNameInput() {
        return fNameInput.getTextField().getText();
    }

    /**
     * Returns the last name input.
     * 
     * @return the last name input
     */
    public String getLNameInput() {
        return lNameInput.getTextField().getText();
    }

    /**
     * Returns the check-in date input.
     * 
     * @return the check-in date input
     */
    public String getCInInput() {
        return cInInput.getTextField().getText();
    }

    /**
     * Returns the check-out date input.
     * 
     * @return the check-out date input
     */
    public String getCOutInput() {
        return cOutInput.getTextField().getText();
    }

    /**
     * Returns the selected room index based on the specified mode.
     * 
     * @param mode the mode (1 for lower bound, 2 for upper bound)
     * @return the selected room index
     */
    public int getRoomSelectedIndex(int mode) {
        if (mode == 1)
            return CBRoomsLB.getRoomSelectedIndex();
        else 
            return CBRoomsUB.getRoomSelectedIndex();
    }

    /**
     * Returns the selected room.
     * 
     * @return the selected room
     */
    public String getRoomSelected() {
        return (String)CBRooms.getSelectedItem();
    }

    /**
     * Returns the price input.
     * 
     * @return the price input
     */
    public String getPriceInput() {
        return priceInput.getTextField().getText();
    }

    /**
     * Returns the text input by the user in the date input field.
     * 
     * @return the text input by the user in the date input field
     */
    public String getDateInput() {
        return DateInput.getTextField().getText();
    }

    /**
     * Returns the text input by the user in the DPM rate input field.
     * 
     * @return the text input by the user in the DPM rate input field
     */
    public String getDPMRateInput() {
        return DPMRateInput.getTextField().getText();
    }

    /**
     * Returns the index of the selected voucher type.
     * 
     * @return the index of the selected voucher type
     */
    public int getVoucherType() {
        return voucherType.getSelectedIndex();
    }

    /**
     * Returns the code input for voucher removal
     * 
     * @return the String code of voucher
     */
    public String getRVCode() {
        return removeVoucher.getTextField().getText();
    }



    /**
     * Sets the submit status message for the specified submit button.
     * 
     * @param message the submit status message
     * @param i the index of the submit button
     */
    public void setSubmitStatus(String message, int i) {
        submitButtons.get(i).getLabel().setText(message);
    }

    /**
     * Sets the delete status message.
     * 
     * @param message the delete status message
     */
    public void setDeleteStatus(String message) {
        delStatus.setText(message);
    }

    /**
     * Sets the room status message.
     * 
     * @param message the room status message
     */
    public void setRoomStatus(String message) {
        roomStatusLabel.setText(message);
    }

    /**
     * Sets the combo box for selecting rooms.
     * 
     * @param list the list of rooms to populate the combo box with
     */
    public void setCBRooms(ArrayList<Room> list) {
        if (list != null) {
            CBRoomsLB.setComboBox(list);
            CBRoomsUB.setComboBox(list);
            CBRooms.setComboBox(list);
        } 
        else {
            CBRoomsLB.setComboBox();
            CBRoomsUB.setComboBox();
            CBRooms.setComboBox();
        }
    }

    /**
     * Sets the combo box for selecting hotels.
     * 
     * @param list the list of hotels to populate the combo box with
     */
    public void setCBHotel(ArrayList<Hotel> list) {
        if (list != null)
            CBHotels.setComboBox(list);
    }

    /**
     * Sets the price table with the specified prices.
     * 
     * @param price1 the first price
     * @param price2 the second price
     * @param price3 the third price
     */
    public void setPriceTable(float price1, float price2, float price3) {
        DecimalFormat df = new DecimalFormat("#.##");
    
        priceTable.setValueAt(df.format(price1), 0, 1);
        priceTable.setValueAt(df.format(price2), 1, 1);
        priceTable.setValueAt(df.format(price3), 2, 1);
    }

    /**
     * Sets the submit status for other buttons
     * 
     * @param message The message to set
     * @param mode Tells which specific label to set
     */
    public void setSubmitStatusDPMVouch(String message, int mode) {
        switch (mode) {
            case 1 -> DPMSubmit.getLabel().setText(message);
            case 2 -> ViewDPM.getLabel().setText(message);
            case 3 -> voucherSubmit.getLabel().setText(message);
            case 4 -> removeVouchSubmit.getLabel().setText(message);
            case 5 -> ViewVoucher.getLabel().setText(message);
        }
    }
}