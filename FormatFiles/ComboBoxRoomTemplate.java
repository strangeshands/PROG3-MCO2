package FormatFiles;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import ClassFiles.Room;


/**
 * A custom JPanel template for a JComboBox to select a room.
 */
public class ComboBoxRoomTemplate extends JPanel {
    /**
     * The JComboBox to select a room.
     */
    private JComboBox<String> comboBox;

    

    /**
     * Constructs a new ComboBoxRoomTemplate.
     */
    public ComboBoxRoomTemplate() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "No room selected" }));
        comboBox.setMaximumSize(new Dimension(200, 30));

        JPanel ChooseRoomPanel = new JPanel();
        ChooseRoomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ChooseRoomPanel.setBackground(new Color(214, 83, 109));
        ChooseRoomPanel.setMaximumSize(new Dimension(320, 35));

        JLabel chooseRoomLabel = new JLabel("Choose Room");
        chooseRoomLabel.setFont(new Font("Century Gothic", 1, 15));
        chooseRoomLabel.setForeground(new Color(235, 233, 225));

        JPanel RoomListLabel = new JPanel();
        RoomListLabel.setBackground(new Color(239, 177, 31));
        RoomListLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        RoomListLabel.setMaximumSize(new Dimension(320, 35));

        ChooseRoomPanel.add(chooseRoomLabel);
        RoomListLabel.add(comboBox);

        add(ChooseRoomPanel);
        add(Box.createVerticalStrut(5));
        add(RoomListLabel);
        add(Box.createVerticalStrut(10));
    }



    /**
     * Sets the JComboBox model with the given list of rooms.
     * 
     * @param roomList the list of rooms to display in the JComboBox
     */
    public void setComboBox(ArrayList<Room> roomList) {
        String[] stringList = new String[roomList.size() + 1];
        
        stringList[0] = "No room selected";
        for (int i = 0; i < roomList.size(); i++) 
            stringList[i + 1] = roomList.get(i).getRoomNameType();
    
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(stringList);
        comboBox.setModel(model);
    }
    
    /**
     * Resets the JComboBox model to its default state with no rooms selected.
     */
    public void setComboBox() {
        String[] stringList = new String[1];

        stringList[0] = "No room selected";
    
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(stringList);
        comboBox.setModel(model);
    }

    /**
     * Returns the JComboBox component.
     * 
     * @return the JComboBox component
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * Returns the selected room in the JComboBox.
     * 
     * @return the selected room in the JComboBox
     */
    public String getRoomSelected() {
        return (String)comboBox.getSelectedItem();
    }
}
