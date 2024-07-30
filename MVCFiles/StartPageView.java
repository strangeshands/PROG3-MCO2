/*
 *  AUTHOR'S NOTE
 *  For accessing menuButtons:
 *      index 0 is CREATE button
 *      index 1 is VIEW button
 *      index 2 is MANAGE button
 *      index 3 is BOOK button
 */


package MVCFiles;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import FormatFiles.*;


/**
 *  StartPageView is the main menu page of the program.
 * 
 *  @author HEPHZI TOLENTINO
 *  @author FRANCINE SANTOS
 */
public class StartPageView extends FrameTemplate{
    /**
     *  menuButtons is an array list of buttons to be used
     *      in the main menu.
     */
    private ArrayList<Buttons> menuButtons;
    


    /**
     *  Constructs a start page (main menu).
     */
    public StartPageView() {
        super("Main Menu", 450, 350);
        initialize();
        setVisible(true);
    }



    /**
     *  Initializes the panels.
     */
    protected void initialize() {
        initializeNorth();
        initializeCenter();
        initializeSouth();
    }

    

    /**
     *  Initializes the north panel.
     */
    private void initializeNorth() {
        ImageIcon bgImage;
        JLabel imageContainer;
        JPanel northPanel;

        // Creating north panel
        northPanel = new JPanel();
        northPanel.setBackground(Color.decode("#EBE9E2"));
        northPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        // Setting up the image
        bgImage = new ImageIcon(this.getClass().getResource("Images/Title.png"));
        Image image = bgImage.getImage();
        Image newimg = image.getScaledInstance(420, 140, Image.SCALE_SMOOTH); 
        bgImage = new ImageIcon(newimg);
            
        // Adding to panels
        imageContainer = new JLabel(bgImage);
        northPanel.add(imageContainer);
        this.add(northPanel, BorderLayout.NORTH);
    }

    /**
     *  Initializes the center panel.
     */
    private void initializeCenter() {
        ArrayList<String> menuButtonsLabel;
        ArrayList<ImageIcon> menuButtonsIcon;
        Buttons newButton;

        menuButtons = new ArrayList<Buttons>();
        menuButtonsLabel = new ArrayList<String>();
        menuButtonsIcon = new ArrayList<ImageIcon>();

        populateButtonsLabel(menuButtonsLabel);
        populateButtonsImage(menuButtonsIcon);
    
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        centerPanel.setBackground(Color.decode("#EBE9E2"));
    
        for (int i = 0; i < 4; i++) {
            newButton = new Buttons(menuButtonsLabel.get(i), menuButtonsIcon.get(i));
            newButton.setActionCommand(menuButtonsLabel.get(i));

            menuButtons.add(newButton);
            centerPanel.add(menuButtons.get(i));
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     *  Initializes the south panel.
     */
    private void initializeSouth() {
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Credits
        JLabel name1 = new JLabel("By Hephzi Tolentino and Francine Santos");
        name1.setFont(new Font("Century Gothic", Font.PLAIN, 10));

        south.add(name1);
        add(south, BorderLayout.SOUTH);
    }

    /**
     *  Populates the menuButtonsLabel to be used in creating
     *      instances of buttons.
     * 
     *  @param list A String array list where the names are stored.
     */
    private void populateButtonsLabel(ArrayList<String> list) {
        list.add("CREATE");
        list.add("VIEW");
        list.add("MANAGE");
        list.add("BOOK");
    }

    /**
     *  Populates the menuButtonsIcon to be used in creating
     *      instances of buttons.
     * 
     *  @param list Am ImageIcon array list where the icons are stored.
     */
    private void populateButtonsImage(ArrayList<ImageIcon> list) {
        ArrayList<String> address = new ArrayList<String>();
        address.add("MVCFiles/Images/CreateIcon.png");
        address.add("MVCFiles/Images/ViewIcon.png");
        address.add("MVCFiles/Images/ManageIcon.png");
        address.add("MVCFiles/Images/BookIcon.png");

        for (int i = 0; i < 4; i++) {
            ImageIcon newImage = new ImageIcon(address.get(i));
            Image image = newImage.getImage();
            Image newimg = image.getScaledInstance(40, 40,  Image.SCALE_SMOOTH); 
            newImage = new ImageIcon(newimg);

            list.add(newImage);
        }
    }

    /**
     * Sets the action listener for the buttons
     * 
     * @param listener The action listener to pass
     */
    public void setActionListener(ActionListener listener) {
        for (int i = 0; i < 4; i++)
            menuButtons.get(i).addActionListener(listener);
    }
}

