package MVCFiles;

import ClassFiles.*;
import FormatFiles.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * View class for the booking page.
 * 
 * @author TOLENTINO, HEPHZI
 * @author SANTOS, FRANCINE
 */
public class BookingView extends FrameTemplate {
    /**
     * The combo box for selecting hotels.
     */
    private ComboBoxHotel CBHotels;

    /**
     * The combo box for selecting rooms.
     */
    private ComboBoxRoomTemplate CBRooms1;
    private ComboBoxRoomTemplate CBRooms2;
    private ComboBoxRoomTemplate CBRooms3;

    /**
     * The input fields for each tabs
     */
    private ArrayList<InputTextField> inputsTab1;
    private ArrayList<InputTextField> inputsTab2;
    private ArrayList<InputTextField> inputsTab3;

    /**
     * The submit button for the reservation.
     */
    private Submit reservSubmit1;
    private Submit reservSubmit2;
    private Submit reservSubmit3;

    /**
     * The select date options
     */
    private Submit selectDate1;
    private Submit selectDate2;

    /**
     * The label for selected dates
     */
    private JLabel selectedDate1;
    private JLabel selectedDate2;


    
    /**
     * Constructor for the BookingView class.
     */
    public BookingView() {
        super("Book", 450, 500);
        initialize();
        setVisible(true);
    }


    
    /**
     * Initializes the view components.
     */
    protected void initialize() {
        inputsTab1 = new ArrayList<InputTextField>();
        inputsTab2 = new ArrayList<InputTextField>();
        inputsTab3 = new ArrayList<InputTextField>();

        initializeSpecs();
    }



    /**
     * Initializes the specifications for the view components.
     */
    private void initializeSpecs() {
        HeaderLabel title = new HeaderLabel("BOOK");
        title.setBorder(BorderFactory.createEmptyBorder(25, 45, 5, 0));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#EBE9E1"));

        CBHotels = new ComboBoxHotel();
        CBHotels.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

        // Create scroll panes for tabs
        JScrollPane freeScroll = new JScrollPane(panelInside());
        JScrollPane dateDepScroll = new JScrollPane(panelDateDept());
        JScrollPane roomDepScroll = new JScrollPane(panelRoomDept());

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("FREE BOOKING", freeScroll);
        tabbedPane.addTab("DATE BOOKING", dateDepScroll);
        tabbedPane.addTab("ROOM BOOKING", roomDepScroll);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(CBHotels, BorderLayout.NORTH);
        centerPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add components to main panel
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Creates a panel for the inside of the frame.
     * 
     * @return the inside panel
     */
    private JPanel panelInside() {
        JPanel panelInside = new JPanel();
        panelInside.setLayout(new BoxLayout(panelInside, BoxLayout.Y_AXIS));

        // Room List
        CBRooms1 = new ComboBoxRoomTemplate();
        panelInside.add(Box.createVerticalStrut(10));
        panelInside.add(CBRooms1);

        // Reserver's Details
        JPanel RDPanel = new JPanel();
        RDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        RDPanel.setBackground(new Color(214, 83, 109));
        RDPanel.setMaximumSize(new Dimension(350, 35));

        JLabel RDLabel = new JLabel("Reserver's Details");
        RDLabel.setFont(new Font("Century Gothic", 1, 15));
        RDLabel.setForeground(new Color(235, 233, 225));

        RDPanel.add(RDLabel);
        panelInside.add(RDPanel);

        InputTextField fNameInput = new InputTextField("First Name:", 16);
        InputTextField lNameInput = new InputTextField("Last Name:", 16);
        inputsTab1.add(fNameInput);
        inputsTab1.add(lNameInput);

        panelInside.add(fNameInput);
        panelInside.add(lNameInput);
        panelInside.add(Box.createVerticalStrut(5));

        // Calendar Image
        JPanel ChooseDatePanel = new JPanel();
        ChooseDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ChooseDatePanel.setBackground(new Color(214, 83, 109));
        ChooseDatePanel.setMaximumSize(new Dimension(350, 35));

        JLabel ChooseDateLabel = new JLabel("Choose Date");
        ChooseDateLabel.setFont(new Font("Century Gothic", 1, 15));
        ChooseDateLabel.setForeground(new Color(235, 233, 225));

        ChooseDatePanel.add(ChooseDateLabel);

        ImageIcon bgimg;
        bgimg = new ImageIcon(this.getClass().getResource("Images/Calendar.png"));
        Image image = bgimg.getImage();
        Image newimg = image.getScaledInstance(350, 191,  Image.SCALE_SMOOTH); 
        bgimg = new ImageIcon(newimg);

        JPanel imageContainer = new JPanel();
        imageContainer.setPreferredSize(new Dimension(350, 195));

        JLabel Calendar = new JLabel(bgimg);
        Calendar.setMaximumSize(new Dimension(350, 191));

        panelInside.add(ChooseDatePanel);
        imageContainer.add(Calendar);
        panelInside.add(imageContainer);
        panelInside.add(Box.createVerticalStrut(5));

        // Date Input
        InputTextField cInInput = new InputTextField("Check In:", 13);
        InputTextField cOutInput = new InputTextField("Check Out:", 13);
        inputsTab1.add(cInInput);
        inputsTab1.add(cOutInput);

        panelInside.add(cInInput);
        panelInside.add(cOutInput);
        panelInside.add(Box.createVerticalStrut(5));

        // Voucher Input
        JPanel AddtlPanel = new JPanel();
        AddtlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        AddtlPanel.setBackground(new Color(214, 83, 109));
        AddtlPanel.setMaximumSize(new Dimension(350, 35));

        JLabel AddtlLabel  = new JLabel("Additional");
        AddtlLabel.setFont(new Font("Century Gothic", 1, 15));
        AddtlLabel.setForeground(new Color(235, 233, 225));

        AddtlPanel.add(AddtlLabel);

        InputTextField voucherInput = new InputTextField("Discount Voucher", 13);
        inputsTab1.add(voucherInput);

        panelInside.add(AddtlPanel);
        panelInside.add(voucherInput);

        // Submit Button
        reservSubmit1 = new Submit();
        reservSubmit1.getButton().setActionCommand("ReservSUBMIT");
        panelInside.add(Box.createVerticalStrut(8));
        panelInside.add(reservSubmit1);
        panelInside.add(Box.createVerticalStrut(8));

        return panelInside;
    }

    /**
     * Creates a panel designed for date dependent booking
     * 
     * @return The panel made
     */
    private JPanel panelDateDept() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        selectDate1 = new Submit();
        selectDate1.getLabel().setText("Click here to select date.");
        selectDate1.getButton().setText("DATES");
        selectDate1.getButton().setActionCommand("select1DATES");
        panel.add(Box.createVerticalStrut(10));
        panel.add(selectDate1);

        JPanel labelPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectedDate1 = new JLabel("No date selected.");
        selectedDate1.setFont(new Font ("Century Gothic", Font.BOLD, 12));
        labelPanel1.add(selectedDate1);
        panel.add(labelPanel1);
        panel.add(Box.createVerticalStrut(8));

        CBRooms2 = new ComboBoxRoomTemplate();
        panel.add(CBRooms2);

        // Reserver's Details
        JPanel RDPanel = new JPanel();
        RDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        RDPanel.setBackground(new Color(214, 83, 109));
        RDPanel.setMaximumSize(new Dimension(350, 35));

        JLabel RDLabel = new JLabel("Reserver's Details");
        RDLabel.setFont(new Font("Century Gothic", 1, 15));
        RDLabel.setForeground(new Color(235, 233, 225));

        RDPanel.add(RDLabel);
        panel.add(RDPanel);

        InputTextField fNameInput = new InputTextField("First Name:", 16);
        InputTextField lNameInput = new InputTextField("Last Name:", 16);
        inputsTab2.add(fNameInput);
        inputsTab2.add(lNameInput);

        panel.add(fNameInput);
        panel.add(lNameInput);
        panel.add(Box.createVerticalStrut(5));

        JPanel AddtlPanel = new JPanel();
        AddtlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        AddtlPanel.setBackground(new Color(214, 83, 109));
        AddtlPanel.setMaximumSize(new Dimension(350, 35));

        JLabel AddtlLabel  = new JLabel("Additional");
        AddtlLabel.setFont(new Font("Century Gothic", 1, 15));
        AddtlLabel.setForeground(new Color(235, 233, 225));

        AddtlPanel.add(AddtlLabel);

        InputTextField voucherInput = new InputTextField("Discount Voucher", 13);
        inputsTab2.add(voucherInput);

        panel.add(AddtlPanel);
        panel.add(voucherInput);

        reservSubmit2 = new Submit();
        reservSubmit2.getButton().setActionCommand("Reserv2SUBMIT");

        panel.add(Box.createVerticalStrut(8));
        panel.add(reservSubmit2);
        panel.add(Box.createVerticalStrut(8));

        panel.setMaximumSize(new Dimension(200,300));
        return panel;
    }

    /**
     * Creates a panel designed for room dependent booking
     * 
     * @return The panel created
     */
    private JPanel panelRoomDept() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        CBRooms3 = new ComboBoxRoomTemplate();

        panel.add(Box.createVerticalStrut(10));
        panel.add(CBRooms3);

        selectDate2 = new Submit();
        selectDate2.getLabel().setText("Click here to select date.");
        selectDate2.getButton().setText("DATES");
        selectDate2.getButton().setActionCommand("select2DATES");

        panel.add(Box.createVerticalStrut(5));
        panel.add(selectDate2);

        JPanel labelPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectedDate2 = new JLabel("No date selected.");
        selectedDate2.setFont(new Font ("Century Gothic", Font.BOLD, 12));
        labelPanel1.add(selectedDate2);

        panel.add(labelPanel1);
        panel.add(Box.createVerticalStrut(8));

        // Reserver's Details
        JPanel RDPanel = new JPanel();
        RDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        RDPanel.setBackground(new Color(214, 83, 109));
        RDPanel.setMaximumSize(new Dimension(350, 35));

        JLabel RDLabel = new JLabel("Reserver's Details");
        RDLabel.setFont(new Font("Century Gothic", 1, 15));
        RDLabel.setForeground(new Color(235, 233, 225));

        RDPanel.add(RDLabel);
        panel.add(RDPanel);

        InputTextField fNameInput = new InputTextField("First Name:", 16);
        InputTextField lNameInput = new InputTextField("Last Name:", 16);
        inputsTab3.add(fNameInput);
        inputsTab3.add(lNameInput);

        panel.add(fNameInput);
        panel.add(lNameInput);
        panel.add(Box.createVerticalStrut(5));

        JPanel AddtlPanel = new JPanel();
        AddtlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        AddtlPanel.setBackground(new Color(214, 83, 109));
        AddtlPanel.setMaximumSize(new Dimension(350, 35));

        JLabel AddtlLabel  = new JLabel("Additional");
        AddtlLabel.setFont(new Font("Century Gothic", 1, 15));
        AddtlLabel.setForeground(new Color(235, 233, 225));

        AddtlPanel.add(AddtlLabel);

        InputTextField voucherInput = new InputTextField("Discount Voucher", 13);
        inputsTab3.add(voucherInput);

        panel.add(AddtlPanel);
        panel.add(voucherInput);

        reservSubmit3 = new Submit();
        reservSubmit3.getButton().setActionCommand("Reserv3SUBMIT");

        panel.add(Box.createVerticalStrut(8));
        panel.add(reservSubmit3);
        panel.add(Box.createVerticalStrut(8));

        panel.setMaximumSize(new Dimension(200,300));
        return panel;
    }



   /**
     * Sets the action listener for the submit button.
     * 
     * @param listener  the action listener
     */
    public void setActionListener(ActionListener listener) {
        selectDate1.getButton().addActionListener(listener);
        selectDate2.getButton().addActionListener(listener);
        
        reservSubmit1.getButton().addActionListener(listener);
        reservSubmit2.getButton().addActionListener(listener);
        reservSubmit3.getButton().addActionListener(listener);
    }

    /**
     * Sets the item listener for the hotel combo box.
     * 
     * @param listener  the item listener
     */
    public void setItemListener(ItemListener listener) {
        CBHotels.getComboBox().addItemListener(listener);
    }



    /**
     * Gets the hotel combo box.
     * 
     * @return the hotel combo box
     */
    public ComboBoxHotel getHotelCB() {
        return CBHotels;
    }

    /**
     * Gets the selected hotel.
     * 
     * @return the selected hotel
     */
    public String getHotelSelected() {
        return CBHotels.getHotelSelected();
    }

    /**
     * Gets the selected room.
     * 
     * @param mode The mode of selection
     * @return the selected room
     */
    public String getRoomSelected(int mode) {
        return switch (mode) {
            case 1 -> CBRooms1.getRoomSelected();
            case 2 -> CBRooms2.getRoomSelected();
            case 3 -> CBRooms3.getRoomSelected();
            default -> "No room selected";
        };
    }
    
    /**
     * Gets the input on the specified index
     * 
     * @param mode The index of the input
     * 
     * @return The string input on the specified index
     */
    public String getInputsTab1(int mode) {
        return inputsTab1.get(mode - 1).getTextField().getText();
    }

    /**
     * Gets the input on the specified index
     * 
     * @param mode The index of the input
     * 
     * @return The string input on the specified index
     */
    public String getInputsTab2(int mode) {
        return inputsTab2.get(mode - 1).getTextField().getText();
    }

    /**
     * Gets the input on the specified index
     * 
     * @param mode The index of the input
     * 
     * @return The string input on the specified index
     */
    public String getInputsTab3(int mode) {
        return inputsTab3.get(mode - 1).getTextField().getText();
    }


    
    /**
     * Sets the room combo box.
     * 
     * @param list  the list of rooms
     */
    public void setCBRoom1(ArrayList<Room> list) {
        if (list != null) {
            CBRooms1.setComboBox(list);
            CBRooms3.setComboBox(list);
        }
        else {
            CBRooms1.setComboBox();
            CBRooms3.setComboBox();
        }
    }

    /**
     * Sets the room combo box.
     * 
     * @param list  the list of rooms
     */
    public void setCBRoom2(ArrayList<Room> list) {
        if (list != null)
            CBRooms2.setComboBox(list);
        else
            CBRooms2.setComboBox();
    }

    /**
     * Sets the hotel combo box.
     * 
     * @param list  the list of hotels
     */
    public void setCBHotel(ArrayList<Hotel> list) {
        if (list != null) {
            CBHotels.setComboBox(list);
        }
    }

    /**
     * Sets the status of the reservation submit button.
     * 
     * @param message the status message
     * @param mode The mode of selection
     */
    public void setReservSubmitStatus(String message, int mode) {
        switch (mode) {
            case 1 -> reservSubmit1.getLabel().setText(message);
            case 2 -> reservSubmit2.getLabel().setText(message);
            case 3 -> reservSubmit3.getLabel().setText(message);
        }
    }

    /**
     * Clears the input fields.
     */
    public void clearTF() {
        for (int i = 0; i < inputsTab1.size(); i++)
            inputsTab1.get(i).getTextField().setText("");

        for (int i = 0; i < inputsTab2.size(); i++)
            inputsTab2.get(i).getTextField().setText("");

        for (int i = 0; i < inputsTab3.size(); i++)
            inputsTab3.get(i).getTextField().setText("");
    }

    /**
     * Updates the selected date label.
     * 
     * @param message The message to show
     * @param mode The mode of booking
     */
    public void setSelectedDatesLabel(String message, int mode) {
        switch (mode) {
            case 1 -> selectedDate1.setText(message);
            case 2 -> selectedDate2.setText(message);
        }
    }
}
