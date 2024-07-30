package FormatFiles;

import java.awt.*;
import javax.swing.*;


/**
 * A header label formatted according to the author's liking
 */
public class HeaderLabel extends JLabel {
    /**
     * Constructs a JLabel with the provided label
     * 
     * @param label The string to show
     */
    public HeaderLabel(String label) {
        super(label);
        setForeground(Color.decode("#E43D12"));
        setFont(new Font("Century Gothic", Font.BOLD, 35));
        setBorder(BorderFactory.createEmptyBorder(25, 45, 0, 0));
    }
}
