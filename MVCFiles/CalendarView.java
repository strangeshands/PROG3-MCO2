package MVCFiles;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import ClassFiles.*;


/**
 * View class for a calendar and also allows selection of dates
 * 
 * @author TOLENTINO, HEPHZI
 * @author SANTOS, FRANCINE
 */
public class CalendarView extends JDialog {
    /**
     * Dates are the date buttons for the calendar
     * disabledDates are the dates unavailable for the selected room
     * submit is the submit button for the dates
     * clear is the button to clear the date selection
     */
    private ArrayList<JButton> Dates;
    private ArrayList<JButton> disabledDates;
    private JButton submit;
    private JButton clear;

    /**
     * Varied fonts used in this class
     */
    private final Font font1 = new Font("Century Gothic", Font.BOLD, 12);
    private final Font font2 = new Font("Century Gothic", Font.PLAIN, 12);
    private final Font font3 = new Font("Century Gothic", Font.BOLD, 15);



    /**
     * Constructs a new CalendarView
     */
    public CalendarView() {
        setLayout(new BorderLayout());
        setTitle("Select Date");

        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel month = new JLabel("JUNE");
        month.setFont(font3);
        monthPanel.add(month);

        JPanel calPanel = new JPanel();
        calPanel.setLayout(new GridLayout(0, 7));
        Dates = new ArrayList<JButton>();
        disabledDates = new ArrayList<JButton>(); 

        // Create the day of the week labels
        String[] daysOfWeek = {"Sat", "Mon", "Tue", "Wed", "Thu", "Fri", "Sun"};
        for (int i = 0; i < 7; i++) {
            JLabel label = new JLabel(daysOfWeek[i]);
            label.setFont(font1);
            label.setHorizontalAlignment(JLabel.CENTER);
            calPanel.add(label);
        }

        JButton newButton;
        for (int i = 1; i <= 31; i++) {
            newButton = new JButton(String.valueOf(i));
            newButton.setFont(font2);
            calPanel.add(newButton);
            Dates.add(newButton);
        }

        JPanel bottom = new JPanel(new GridLayout(0, 2));

        submit = new JButton("SUBMIT");
        submit.setActionCommand("submitDates");
        submit.setFont(font1);
        bottom.add(submit);

        clear = new JButton("CLEAR");
        clear.setActionCommand("clearDates");
        clear.setFont(font1);
        bottom.add(clear);
        
        add(monthPanel, BorderLayout.NORTH);
        add(calPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(400, 250));
        setMaximumSize(new Dimension(400, 300));
        setResizable(false);
    }



    /**
     * Constructs a new CalendarView with the provided hotel and room name
     * Used in room dependent booking
     * 
     * @param hotel The hotel selected
     * @param roomInput The room name
     */
    public CalendarView(Hotel hotel, String roomInput) {
        setLayout(new BorderLayout());
        setTitle("Select Date");

        ArrayList<Day> days = hotel.getRoomDays(roomInput);

        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel month = new JLabel("JUNE");
        month.setFont(font3);
        monthPanel.add(month);

        JPanel calPanel = new JPanel();
        calPanel.setLayout(new GridLayout(0, 7));
        
        Dates = new ArrayList<JButton>();
        disabledDates = new ArrayList<JButton>(); 

        // Create the day of the week labels
        String[] daysOfWeek = {"Sat", "Mon", "Tue", "Wed", "Thu", "Fri", "Sun"};
        for (int i = 0; i < 7; i++) {
            JLabel label = new JLabel(daysOfWeek[i]);
            label.setFont(font1);
            label.setHorizontalAlignment(JLabel.CENTER);
            calPanel.add(label);
        }

        JButton newButton;
        for (int i = 1; i <= 31; i++) {
            newButton = new JButton(String.valueOf(i));
            newButton.setFont(font2);

            if (days.get(i-1).getStatus().equals("Booked")) {
                newButton.setEnabled(false);
                disabledDates.add(newButton);
            }

            calPanel.add(newButton);
            Dates.add(newButton);
        }

        JPanel bottom = new JPanel(new GridLayout(0, 2));

        submit = new JButton("SUBMIT");
        submit.setActionCommand("submitDates");
        submit.setFont(font1);
        bottom.add(submit);

        clear = new JButton("CLEAR");
        clear.setActionCommand("clearDates");
        clear.setFont(font1);
        bottom.add(clear);
        
        add(monthPanel, BorderLayout.NORTH);
        add(calPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(400, 250));
        setMaximumSize(new Dimension(400, 300));
        setResizable(false);
    }



    /**
     * Sets the action listener for the buttons
     * 
     * @param listener The action listener to pass
     */
    public void setActionListener(ActionListener listener) {
        for (int i = 0; i < 31; i++)
            Dates.get(i).addActionListener(listener);

        submit.addActionListener(listener);
        clear.addActionListener(listener);
    }



    /**
     * Gets the button date with the specified index
     * 
     * @param index The index of the button
     * @return The button specified by the index
     */
    public JButton getButton(int index) {
        return Dates.get(index);
    }

    /**
     * Gets the array list of disabled dates
     * 
     * @return The disabled dates
     */
    public ArrayList<JButton> getDisabledDates() {
        return disabledDates;
    }

    /**
     * Gets the list of buttons
     * 
     * @return The button dates
     */
    public ArrayList<JButton> getButtons() {
        return Dates;
    }
}