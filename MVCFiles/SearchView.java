package MVCFiles;

import ClassFiles.*;
import FormatFiles.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 *  View class for the Hotel View window.
 * 
 *  @author Tolentino
 *  @author Santos
 */
public class SearchView extends FrameTemplate {
    /**
     *  Combobox which contains the list of hotels.
     */
    private ComboBoxHotel CBHotels;

    /**
     *  dateInput is a panel where user can input dates
     *  dateSubmit is a panel where a submit buttton is found
     *      and status label can be set.
     */
    private InputTextField dateInput;
    private Submit dateSubmit;

    /**
     *  CBRoom1 is combo box for rooms.
     *  roomSubmit is a panel where a submit buttton is found
     *      and status label can be set.
     */
    private ComboBoxRoomTemplate CBRoom1;
    private Submit roomSubmit; 

    /**
     *  fNameInput, lNameInput, cInInput, cOutInput are panels
     *      where user can input reservation details.
     *  CBRoom2 is combo box for rooms.
     *  reservSubmit is a panel where a submit buttton is found
     *      and status label can be set.
     */
    private InputTextField fNameInput;
    private InputTextField lNameInput;
    private ComboBoxRoomTemplate CBRoom2;
    private InputTextField cInInput;
    private InputTextField cOutInput;
    private Submit reservSubmit;



    /**
     * Constructor for the VIEWHotelView class.
     */
    public SearchView() {
        super("View", 450, 575);

        initialize();
        setVisible(true);
    }

    

    /**
     * Initializes the view components.
     */
    protected void initialize() {
        initializeNorth();
        initializeCenter();
    }

    
    
    /**
     * Initializes the north panel.
     */
    private void initializeNorth() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.decode("#EBE9E1"));

        HeaderLabel title = new HeaderLabel("VIEW");
        title.setBorder(BorderFactory.createEmptyBorder(25, 45, 5, 0));

        JPanel CBPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        CBPanel.setPreferredSize(new Dimension(350, 100));
        CBPanel.setBackground(Color.decode("#EBE9E1"));

        CBHotels = new ComboBoxHotel();
        CBHotels.setBackground(Color.decode("#EBE9E1"));
        CBPanel.add(CBHotels);

        northPanel.add(title, BorderLayout.NORTH);
        northPanel.add(CBPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Initializes the center panel.
     */
    private void initializeCenter() {
        JPanel SBDate = new JPanel();
        SBDate.add(initializeSBDate());

        JPanel SBRoom = new JPanel();
        SBRoom.add(initializeSBRoom());

        JPanel SBReserv = new JPanel();
        SBReserv.add(initializeSBReserve());

        JTabbedPane tabbedPanes = new JTabbedPane();
        tabbedPanes.setFont(new Font("Century Gothic", Font.BOLD, 13));
        tabbedPanes.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        tabbedPanes.add("Search: DATE", SBDate);
        tabbedPanes.add("Search: ROOM", SBRoom);
        tabbedPanes.add("Search: RESERVATION", SBReserv);

        add(tabbedPanes, BorderLayout.CENTER);
    }

    /**
     * Initializes the search by date panel.
     * 
     * @return the search by date panel
     */
    private JPanel initializeSBDate() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ImageIcon bgimg;
        bgimg = new ImageIcon(this.getClass().getResource("Images/Calendar.png"));
        Image image = bgimg.getImage();
        Image newimg = image.getScaledInstance(350, 191,  Image.SCALE_SMOOTH); 
        bgimg = new ImageIcon(newimg);

        JPanel imageContainer = new JPanel();
        imageContainer.setMaximumSize(new Dimension(350, 191));

        JLabel Calendar = new JLabel(bgimg);
        Calendar.setMaximumSize(new Dimension(350, 191));

        imageContainer.add(Calendar);
        panel.add(imageContainer);

        dateInput = new InputTextField("Select Date:", 10);
        dateSubmit = new Submit();
        dateSubmit.getButton().setActionCommand("DateSUBMIT");

        panel.add(Box.createVerticalStrut(5));
        panel.add(dateInput);
        panel.add(Box.createVerticalStrut(5));
        panel.add(dateSubmit);

        return panel;
    }

    /**
     * Initializes the search by reservation panel.
     * 
     * @return the search by reservation panel
     */
    private JPanel initializeSBRoom() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(350, 300));

        CBRoom1 = new ComboBoxRoomTemplate();
        panel.add(CBRoom1);

        roomSubmit = new Submit();
        roomSubmit.getButton().setActionCommand("RoomSUBMIT");
        panel.add(roomSubmit);

        return panel;
    }

    /**
     * Sets the action listener for the submit buttons.
     * 
     * @param listener  the action listener
     */
    private JPanel initializeSBReserve() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("Reservation Details");
        title.setFont(new Font ("Century Gothic", Font.BOLD, 15));
        title.setForeground(Color.decode("#E43D13"));
        panel2.add(title);

        panel.add(panel2);

        fNameInput = new InputTextField("First Name:", 16);
        lNameInput = new InputTextField("Last Name:", 16);
        CBRoom2 = new ComboBoxRoomTemplate();
        cInInput = new InputTextField("Check In:", 13);
        cOutInput = new InputTextField("Check Out:", 13);

        panel.add(fNameInput);
        panel.add(lNameInput);
        panel.add(Box.createVerticalStrut(5));
        panel.add(CBRoom2);
        panel.add(cInInput);
        panel.add(cOutInput);

        reservSubmit = new Submit();
        reservSubmit.getButton().setActionCommand("ReservSUBMIT");

        panel.add(reservSubmit);

        return panel;
    }



    /**
     * Sets the action listener for the submit buttons.
     * 
     * @param listener  the action listener
     */
    public void setActionListener(ActionListener listener) {
        dateSubmit.getButton().addActionListener(listener);
        roomSubmit.getButton().addActionListener(listener);
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
     * Clears the input fields.
     */
    public void clearInput() {
        dateInput.getTextField().setText("");
        fNameInput.getTextField().setText("");
        lNameInput.getTextField().setText("");
        cInInput.getTextField().setText("");
        cOutInput.getTextField().setText("");
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
     * Gets the date input.
     * 
     * @return the date input
     */
    public String getDateInput() {
        return dateInput.getTextField().getText();
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
     * @param mode  the mode (1 or 2)
     * @return the selected room
     */
    public String getRoomSelected(int mode) {
        if (mode == 1)
            return CBRoom1.getRoomSelected();
        else
            return CBRoom2.getRoomSelected();
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
     * Sets the status of the date submit button.
     * 
     * @param status  the status message
     */
    public void setDateSubmitStatus(String status) {
        dateSubmit.getLabel().setText(status);
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
     * Sets the room combo box.
     *   
     * @param list  the list of rooms
     */
    public void setCBRoom(ArrayList<Room> list) {
        if (list != null) {
            CBRoom1.setComboBox(list);
            CBRoom2.setComboBox(list);
        }
        else {
            CBRoom1.setComboBox();
            CBRoom2.setComboBox();
        }
    }

    /**
     * Sets the status of the room submit button.
     * 
     * @param status  the status message
     */
    public void setRoomSubmiStatus(String status) {
        roomSubmit.getLabel().setText(status);
    }

    /**
     * Sets the status of the reservation submit button.
     * 
     * @param message  the status message
     */
    public void setReservSubmitStatus(String message) {
        reservSubmit.getLabel().setText(message);
    }
}
