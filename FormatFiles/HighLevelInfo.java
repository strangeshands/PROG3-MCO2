package FormatFiles;

import ClassFiles.*;
import java.awt.*;
import javax.swing.*;


/**
 * A JPanel that displays high-level information about a hotel.
 * 
 *  @author HEPHZI TOLENTINO (main contributor)
 *  @author FRANCINE SANTOS
 */
public class HighLevelInfo extends JPanel {
    /**
     * A bold font used for the title label.
     */
    private final Font font1 = new Font("Century Gothic", Font.BOLD, 15);

    /**
     * A plain font used for the info labels.
     */
    private final Font font2 = new Font("Century Gothic", Font.PLAIN, 12);


    
    /**
     * Constructs a HighLevelInfo panel with the given hotel information.
     * 
     * @param hotel the hotel to display information about
     */
    public HighLevelInfo(Hotel hotel) {
        super();
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("WELCOME TO -- " + hotel.getHotelName() + "!");
        title.setFont(font1);
        titlePanel.add(title);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(250, 200));

        JLabel hotelName = new JLabel(">> HOTEL NAME: " + hotel.getHotelName());
        hotelName.setFont(font2);
        JLabel roomNum = new JLabel(">> NUMBER OF ROOMS: " + hotel.getRoomList().size());
        roomNum.setFont(font2);
        JLabel estEarn = new JLabel(">> ESTIMATED EARNING: " + hotel.getEstimatedEarning());
        estEarn.setFont(font2);

        infoPanel.add(hotelName);
        infoPanel.add(roomNum);
        infoPanel.add(estEarn);

        add(titlePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
    }
}
