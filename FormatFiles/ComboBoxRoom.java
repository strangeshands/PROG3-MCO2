package FormatFiles;
import ClassFiles.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * A custom JComboBox for selecting a room.
 * 
 *  @author HEPHZI TOLENTINO (main contributor)
 *  @author FRANCINE SANTOS
 */
public class ComboBoxRoom extends JComboBox<String> {
    /**
     * Constructs a new FORMATCBRoom with a default model containing a single element "No room selected".
     */
    public ComboBoxRoom() {
        super();
        this.setModel(new DefaultComboBoxModel<>(new String[] { "No room selected" }));
        this.setMaximumSize(new Dimension(275, 30));
    }



    /**
     * Sets the combo box model with a list of rooms.
     * 
     * @param roomList the list of rooms to populate the combo box
     */
    public void setComboBox(ArrayList<Room> roomList) {
        String[] stringList = new String[roomList.size() + 1];
        
        stringList[0] = "No room selected";
        for (int i = 0; i < roomList.size(); i++) 
            stringList[i + 1] = roomList.get(i).getRoomNameType();
    
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(stringList);
        this.setModel(model);
    }
    
    /**
     * Resets the combo box model to a single element "No room selected".
     */
    public void setComboBox() {
        String[] stringList = new String[1];

        stringList[0] = "No room selected";
    
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(stringList);
        this.setModel(model);
    }

    

    /**
     * Returns the selected room as a string.
     * 
     * @return the selected room
     */
    public String getRoomSelected() {
        return (String)this.getSelectedItem();
    }

    /**
     * Returns the index of the selected room.
     * 
     * @return the index of the selected room
     */
    public int getRoomSelectedIndex() {
        return this.getSelectedIndex();
    }
}
