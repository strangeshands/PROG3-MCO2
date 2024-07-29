package FormatFiles;

import java.awt.*;
import javax.swing.*;


/**
 *  This is a child class of JButton which has its own format according
 *      to the author's liking.
 * 
 *  @author HEPHZI TOLENTINO (main contributor)
 *  @author FRANCINE SANTOS
 */
public class Buttons extends JButton {
    /**
     * font1 and font2 are fonts used in this class.
     */
    private Font font1 = new Font("Century Gothic", Font.BOLD, 15);
    private Font font2 = new Font("Century Gothic", Font.BOLD, 10);

    

    /**
     *  This constructs a big button; this is used in main menu.
     * 
     *  @param btnLabel The String label of a button.
     *  @param icon The icon used for the button.
     */
    public Buttons(String btnLabel, ImageIcon icon) {
        super(btnLabel, icon);

        setFont(font1);
        setBackground(Color.decode("#D6536D"));
        setForeground(Color.decode("#EBE9E1"));

        setOpaque(true);
        setBorderPainted(false);

        setMargin(new Insets(30, 10, 30, 10));
        setIconTextGap(10);
        setPreferredSize(new Dimension(150, 40));

        setEnabled(true);
        setVisible(true);
    }



    /**
     *  This constructs a small button; this is used mainly for "SUBMIT"
     *      buttons.
     * 
     *  @param btnLabel The String label of a button.
     */
    public Buttons(String btnLabel) {
        super(btnLabel);

        setFont(font2);
        setBackground(Color.decode("#D6536D"));
        setForeground(Color.decode("#EBE9E1"));

        setOpaque(true);
        setBorderPainted(false);
        setIconTextGap(10);

        setPreferredSize(new Dimension(78, 28));
        setEnabled(true);
    }

    

    /**
     *  This constructs a small button and allows changing of
     *      colors.
     * 
     *  @param btnLabel The String label of a button.
     *  @param color1 THe background color of a button.
     *  @param color2 The foreground color of a button.
     */
    public Buttons(String btnLabel, String color1, String color2) {
        super(btnLabel);

        setFont(font2);
        setBackground(Color.decode(color1));
        setForeground(Color.decode(color2));

        setOpaque(true);
        setBorderPainted(false);
        setIconTextGap(10);

        setPreferredSize(new Dimension(78, 28));
    }
}