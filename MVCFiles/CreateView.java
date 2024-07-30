package MVCFiles;

import FormatFiles.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *  This is a view class for the CreateView window and extends FrameTemplate.
 * 
 *  @author TOLENTINO, HEPHZI
 *  @author SANTOS, FRANCINE
 */
public class CreateView extends FrameTemplate {
    /**
     *  hotelNameInput is a panel where user can input the hotel name.
     *  stdrdRoomInput is a panel where user can input the standard room count.
     *  delRoomInput is a panel where user can input the deluxe room count.
     *  execRoomInput is a panel where user can input the executive room count.
     */
    private InputTextField hotelNameInput;
    private InputTextField stdrdRoomInput;
    private InputTextField delRoomInput;
    private InputTextField execRoomInput;

    /**
     *  cancel is a formatted button that acts as a cancel button.
     *  save is a formatted button that allows saving of changes.
     *  saveStatus is a JLabel that is set in accordance to the action perfomed.
     */
    private Buttons cancel;
    private Buttons save;
    private JLabel saveStatus;

    

    /**
     *  Constructs a new CreateView window.
     */
    public CreateView() {
        super("Create", 450, 500);
        initialize();
        setVisible(true);
    }



    /**
     *  Initializes the window with the layout the author desired.
     */
    protected void initialize() {
        initializeNorth();
        initializeCenter();
    }

    

    /**
     *  Initializes the north panel with a title.
     */
    private void initializeNorth() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.decode("#EBE9E1"));

        HeaderLabel title = new HeaderLabel("CREATE");

        northPanel.add(title, BorderLayout.WEST);
        add(northPanel, BorderLayout.NORTH);
    }

    /**
     *  Initializes the center panel. Mainly where the input is done.
     */
    private void initializeCenter() {
        JPanel centerPanel;
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.decode("#EBE9E2"));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        hotelNameInput = new InputTextField("Hotel Name:", 16);

        HeaderLabel addRoomLabel = new HeaderLabel("Add Rooms");
        addRoomLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
        addRoomLabel.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        
        JPanel addRoomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addRoomPanel.setBackground(Color.decode("#EBE9E2"));
        addRoomPanel.setMaximumSize(new Dimension(450, 30));
        addRoomPanel.add(addRoomLabel);

        stdrdRoomInput = new InputTextField("Standard Rooms:", 10);
        delRoomInput = new InputTextField("Deluxe Rooms:", 10);
        execRoomInput = new InputTextField("Executive Rooms:", 10);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
        buttonPanel.setMaximumSize(new Dimension(450, 40));
        buttonPanel.setBackground(Color.decode("#EBE9E2"));

        cancel = new Buttons("EXIT", "#EFB11F", "#000000");
        cancel.setActionCommand("EXIT");
        buttonPanel.add(cancel);

        save = new Buttons("SAVE", "#D6536D", "#EBE9E1");
        save.setActionCommand("SAVE");
        buttonPanel.add(save);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(Color.decode("#EBE9E2"));
        statusPanel.setMaximumSize(new Dimension(350, 35));

        saveStatus = new JLabel("You have unsaved changes.");
        saveStatus.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        saveStatus.setForeground(Color.decode("#E43D13"));
        statusPanel.add(saveStatus);

        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(hotelNameInput);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(addRoomPanel);
        centerPanel.add(stdrdRoomInput);
        centerPanel.add(delRoomInput);
        centerPanel.add(execRoomInput);
        centerPanel.add(Box.createVerticalStrut(3));
        centerPanel.add(Box.createVerticalStrut(18));
        centerPanel.add(buttonPanel);
        centerPanel.add(statusPanel);

        add(centerPanel, BorderLayout.CENTER);
    }



    // ----- ACTION ----- //

    /**
     *  Sets the action listener for the buttons.
     * 
     *  @param listener The action listener from the controller.
     */
    public void setActionListener(ActionListener listener) {
        save.addActionListener(listener);
        cancel.addActionListener(listener);
    }



    // ----- GETTERS ----- //

    /**
     *  Gets the text field for the hotel name input.
     * 
     *  @return The JTextField for the hotel name input.
     */
    public JTextField getHotelTF() {
        return hotelNameInput.getTextField();
    }

    /**
     *  Gets the text field for the standard room count input.
     * 
     *  @return The JTextField for the standard room count input.
     */
    public JTextField getStndrdTF() {
        return stdrdRoomInput.getTextField();
    }

    /**
     *  Gets the text field for the deluxe room count input.
     * 
     *  @return The JTextField for the deluxe room count input.
     */
    public JTextField getDelTF() {
        return delRoomInput.getTextField();
    }

    /**
     *  Gets the text field for the executive room count input.
     * 
     *  @return The JTextField for the executive room count input.
     */
    public JTextField getExecTF() {
        return execRoomInput.getTextField();
    }


    
    // ----- SETTERS ----- //

    /**
     *  Sets the status HeaderLabel with the provided string.
     * 
     *  @param message The new message to be shown.
     */
    public void updateStatusLabel(String message) {
        saveStatus.setText(message);
    }

    /**
     *  Clears the text fields.
     */
    public void clearTF() {
        hotelNameInput.getTextField().setText("");
        stdrdRoomInput.getTextField().setText("");
        delRoomInput.getTextField().setText("");
        execRoomInput.getTextField().setText("");
    }
}
