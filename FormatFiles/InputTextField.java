package FormatFiles;

import java.awt.*;
import javax.swing.*;


/**
 * A custom JPanel that contains a labeled JTextField for user input.
 * 
 *  @author HEPHZI TOLENTINO (main contributor)
 *  @author FRANCINE SANTOS
 */
public class InputTextField extends JPanel {
    /**
     * The JTextField component for user input.
     */
    private JTextField inputTF;



    /**
     * Constructs an InputTextField panel with the given label and column size.
     * 
     * @param tfName the label text for the input field
     * @param tfColumn the number of columns for the input field
     */
    public InputTextField(String tfName, int tfColumn) {
        super();

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.decode("#EBE9E2"));
        setMaximumSize(new Dimension(325, 35));

        JPanel inputBar = new JPanel();
        inputBar.setLayout(new BorderLayout());
        inputBar.setBackground(Color.decode("#EFB11D"));
        inputBar.setPreferredSize(new Dimension(325, 30));

        JLabel inputLabel = new JLabel(tfName);
        inputLabel.setForeground(Color.decode("#3A3A3A"));
        inputLabel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        inputLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        inputTF = new JTextField(tfColumn);
        inputTF.setFont(new Font("Century Gothic", Font.PLAIN, 13));

        inputBar.add(inputLabel, BorderLayout.WEST);
        inputBar.add(inputTF, BorderLayout.EAST);

        add(inputBar);
    }


    
    /**
     * Returns the JTextField component for user input.
     * 
     * @return the input field
     */
    public JTextField getTextField() {
        return inputTF;
    }
}
