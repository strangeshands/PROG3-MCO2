package FormatFiles;
import ClassFiles.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 *  This is a child class of JPanel which has combobox according
 *      to the author's liking.
 * 
 *  @author HEPHZI TOLENTINO (main contributor)
 *  @author FRANCINE SANTOS
 */
public class ComboBoxHotel extends JPanel {
    /**
     * The combobox where the list of hotels are shown.
     */
    private JComboBox<String> comboBox;



    /**
     * Constructs a new FORMATCBHotel
     */
    public ComboBoxHotel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "No hotel selected" }));
        comboBox.setPreferredSize(new Dimension(330, 30));

        JPanel ChooseHotelPanel = new JPanel();
        ChooseHotelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ChooseHotelPanel.setBackground(new Color(214, 83, 109));
        ChooseHotelPanel.setMaximumSize(new Dimension(350, 35));

        JLabel chooseHotelLabel = new JLabel("Choose Hotel");
        chooseHotelLabel.setFont(new Font("Century Gothic", 1, 15));
        chooseHotelLabel.setForeground(new Color(235, 233, 225));

        JPanel HotelListPanel = new JPanel();
        HotelListPanel.setBackground(new Color(239, 177, 31));
        HotelListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        HotelListPanel.setMaximumSize(new Dimension(350, 35));

        ChooseHotelPanel.add(chooseHotelLabel);
        HotelListPanel.add(comboBox);

        add(ChooseHotelPanel);
        add(Box.createVerticalStrut(5));
        add(HotelListPanel);
        add(Box.createVerticalStrut(10));
    }



    /**
     * Sets the combobox with the given hotelList
     * 
     * @param hotelList The array list of hotels
     */
    public void setComboBox(ArrayList<Hotel> hotelList) {
        String[] stringList = new String[hotelList.size() + 1];

        stringList[0] = "No hotel selected";
        for (int i = 0; i < hotelList.size(); i++) 
            stringList[i + 1] = hotelList.get(i).getHotelName();
    
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(stringList);
        comboBox.setModel(model);
    }


    
    /**
     * Gets the String name of the selected hotel
     * 
     * @return the name of the selected hotel
     */
    public String getHotelSelected() {
        String selected = (String)comboBox.getSelectedItem();
        
        return selected;
    }

    /**
     * Gets the combobox object
     * 
     * @return the combobox object
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
