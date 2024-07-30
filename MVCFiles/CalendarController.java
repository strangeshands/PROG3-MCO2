package MVCFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * The CalendarController class is responsible for handling user 
 *      interactions with the calendar view.
 * 
 * @author TOLENTINO, HEPHZI
 * @author SANTOS, FRANCINE
 */
public class CalendarController implements ActionListener{
    /**
     * view is the View class for calendar
     * booking is the Model class for calendar
     */
    private CalendarView view;
    private CalendarModel booking;

    /**
     * startDay is the check in date set inside the controller
     * endday is the check out date set inside the controller
     */
    private int startDay;
    private int endDay;



    /**
     * Constructs a new CalendarController instance.
     *
     * @param gui   the calendar view
     * @param model the calendar model
     */
    public CalendarController(CalendarView gui, CalendarModel model) {
        this.view = gui;
        this.booking = model;

        startDay = 0;
        endDay = 0;
        
        view.setActionListener(this);
    }



    /**
     * Handles action events triggered by button clicks in the calendar view.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JButton button = (JButton)e.getSource();
            int selectedDay = Integer.parseInt(button.getText());

            if (startDay == 0)
                startDay = selectedDay;
            else {
                if (endDay != startDay) {
                    endDay = selectedDay;

                    // Disable buttons after the selected end date
                    for (int i = endDay + 1; i <= 31; i++) {
                        JButton dateButton = view.getButton(i - 1);
                        dateButton.setEnabled(false);
                    }
                }
            }

            // Disable buttons before the selected start date
            for (int i = 1; i < startDay; i++) {
                JButton dateButton = view.getButton(i - 1);
                dateButton.setEnabled(false);
            }
        } catch (NumberFormatException d) {
            JButton button = (JButton)e.getSource();
            if (button.getActionCommand().equals("clearDates")) {
                for (int i = 1; i <= 31; i++) {
                    JButton dateButton = view.getButton(i - 1);
                    if (!view.getDisabledDates().contains(dateButton))
                        dateButton.setEnabled(true);
            
                    startDay = 0;
                    endDay = 0;
                }
            }
            else if (button.getActionCommand().equals(("submitDates"))){
                setDates(startDay, endDay);
                view.dispose();
            }
        }
    }



    /**
     * Sets the start and end dates in the calendar model.
     *
     * @param date1 the start date
     * @param date2 the end date
     */
    private void setDates(int date1, int date2) {
        booking.setDate1st(date1);
        booking.setDate2nd(date2);
    } 
}
