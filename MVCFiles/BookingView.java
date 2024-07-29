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
 * @author TOLENTINO, HEPHZI (main contributor)
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
    private ComboBoxRoomTemplate CBRooms;

    /**
     * The input field for the first name.
     */
    private InputTextField fNameInput;

    /**
     * The input field for the last name.
     */
    private InputTextField lNameInput;

    /**
     * The input field for the check-in date.
     */
    private InputTextField cInInput;

    /**
     * The input field for the check-out date.
     */
    private InputTextField cOutInput;

    /**
     * The input field for the voucher code.
     */
    private InputTextField voucherInput;

    /**
     * The submit button for the reservation.
     */
    private Submit reservSubmit;



    /**
     * Constructor for the VIEWBook class.
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
        initializespecs();
    }



    /**
     * Initializes the specifications for the view components.
     */
    private void initializespecs() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#EBE9E1"));
        JPanel insidePanel = panelInside();

        HeaderLabel title = new HeaderLabel("BOOK");
        title.setBorder(BorderFactory.createEmptyBorder(25, 45, 5, 0));

        JScrollPane scroll = new JScrollPane(insidePanel);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a panel for the inside of the frame.
     * 
     * @return the inside panel
     */
    private JPanel panelInside() {
        JPanel panelInside = new JPanel();
        panelInside.setLayout(new BoxLayout(panelInside, BoxLayout.Y_AXIS));

        // Hotel List
        CBHotels = new ComboBoxHotel();
        panelInside.add(CBHotels);

        // Room List
        CBRooms = new ComboBoxRoomTemplate();
        panelInside.add(CBRooms);

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

        fNameInput = new InputTextField("First Name:", 16);
        lNameInput = new InputTextField("Last Name:", 16);
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
        cInInput = new InputTextField("Check In:", 13);
        cOutInput = new InputTextField("Check Out:", 13);
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

        voucherInput = new InputTextField("Discount Voucher", 13);
        panelInside.add(AddtlPanel);
        panelInside.add(voucherInput);

        // Submit Button
        reservSubmit = new Submit();
        reservSubmit.getButton().setActionCommand("ReservSUBMIT");
        panelInside.add(Box.createVerticalStrut(8));
        panelInside.add(reservSubmit);
        panelInside.add(Box.createVerticalStrut(8));

        return panelInside;
    }



   /**
     * Sets the action listener for the submit button.
     * 
     * @param listener  the action listener
     */
    public void setActionListener(ActionListener listener) {
        reservSubmit.getButton().addActionListener(listener);
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
     * @return the selected room
     */
    public String getRoomSelected() {
        return CBRooms.getRoomSelected();
    }

    /**
     * Gets the first name input.
     * 
     * @return the first name input
     */
    public String getFNameInput() {
        return fNameInput.getTextField().getText();
    }

    /**
     * Gets the last name input.
     * 
     * @return the last name input
     */
    public String getLNameInput() {
        return lNameInput.getTextField().getText();
    }

    /**
     * Gets the check-in date input.
     * 
     * @return the check-in date input
     */
    public String getCInInput() {
        return cInInput.getTextField().getText();
    }

    /**
     * Gets the check-out date input.
     * 
     * @return the check-out date input
     */
    public String getCOutInput() {
        return cOutInput.getTextField().getText();
    }

    /**
     * Gets the voucher code input.
     * 
     * @return the voucher code input
     */
    public String getVoucherInput() {
        return voucherInput.getTextField().getText();
    }



    /**
     * Sets the room combo box.
     * 
     * @param list  the list of rooms
     */
    public void setCBRoom(ArrayList<Room> list) {
        if (list != null)
            CBRooms.setComboBox(list);
        else
            CBRooms.setComboBox();
    }

    /**
     * Sets the hotel combo box.
     * 
     * @param list  the list of hotels
     */
    public void setCBHotel(ArrayList<Hotel> list) {
        if (list != null)
            CBHotels.setComboBox(list);
    }

    /**
     * Sets the status of the reservation submit button.
     * 
     * @param message the status message
     */
    public void setReservSubmitStatus(String message) {
        reservSubmit.getLabel().setText(message);
    }

    /**
     * Clears the input fields.
     */
    public void clearTF() {
        fNameInput.getTextField().setText("");
        lNameInput.getTextField().setText("");
        cInInput.getTextField().setText("");
        cOutInput.getTextField().setText("");
        voucherInput.getTextField().setText("");
    }
}
