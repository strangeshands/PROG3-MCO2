package FormatFiles;

import java.awt.*;
import javax.swing.*;


/**
 * A custom JPanel for a submit button and a status label.
 */
public class Submit extends JPanel{
    /**
     * The submit button.
     */
    private Buttons submit;

    /**
     * The status label.
     */
    private JLabel status;



    /**
     * Constructs a new Submit panel.
     */
    public Submit() {
        super();
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(330, 30));

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.setMaximumSize(new Dimension(150, 35));

        status = new JLabel();
        status.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        status.setForeground(Color.decode("#E43D13"));
        statusPanel.add(status);

        submit = new Buttons("SUBMIT");
        
        add(statusPanel, BorderLayout.WEST);
        add(submit, BorderLayout.EAST);
    }


    
    /**
     * Returns the submit button.
     * 
     * @return the submit button
     */
    public JButton getButton() {
        return submit;
    }

    /**
     * Returns the status label.
     * 
     * @return the status label
     */
    public JLabel getLabel() {
        return status;
    }
}
