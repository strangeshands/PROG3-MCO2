package MVCFiles;

import DialogFiles.*;
import ExceptionFiles.*;
import java.awt.event.*;


/**
 *  This is a controller class for the VIEWCreate window.
 * 
 *  @author TOLENTINO, HEPHZI (main contributor)
 *  @author SANTOS, FRANCINE
 */
public class CreateController implements ActionListener {
    /**
     *  create represents the GUI window (VIEW).
     *  hrs is the MODEL that represents the hotel reservation system.
     */
    private CreateView create;
    private HRSModel hrs;

    

    /**
     *  This constructs a new CONTCreate Object.
     * 
     *  @param gui The gui to be controlled.
     *  @param hrs The model to be used.
     */
    public CreateController(CreateView gui, HRSModel hrs) {
        this.create = gui;
        this.hrs = hrs;
        
        create.setActionListener(this);
    }



    /**
     *  actionPerformed() sets actions to be done once a button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE"))
            createHotel();
        else if (e.getActionCommand().equals("EXIT")) 
            create.dispose();
    }

    

    // ----- FUNCTION METHODS ----- //

    /**
     *  createHotel() creates a hotel in the HRS.
     *  Calls the createHotel() method under HRS.
     */
    private void createHotel() {
        try {
            // Check name input
            String newHName = create.getHotelTF().getText();
            checkInput(newHName);

            // Check date inputs
            int stndrdNum = tryNumInput(create.getStndrdTF().getText());
            int delNum = tryNumInput(create.getDelTF().getText());
            int execNum = tryNumInput(create.getExecTF().getText());

            // Creating a hotel
            int result = hrs.createHotel(newHName, stndrdNum, delNum, execNum);

            switch (result) {
                case 1 -> {
                    create.updateStatusLabel("Changes saved.");

                    @SuppressWarnings("unused")
                    HotelInfo prompt = new HotelInfo(hrs.getRecentHotel(), 
                                            "New Hotel Created!", 1);

                    create.clearTF();
                    create.updateStatusLabel("You have unsaved changes.");
                }
                case 2 -> create.updateStatusLabel("Please check room count (at least 1 and at most 50).");
                case 3 -> create.updateStatusLabel(newHName + " already exists.");
                default -> create.updateStatusLabel("An error occured. Please check your input.");
            }
        } catch (NoInputException e) {
            create.updateStatusLabel("Please enter a name.");
        }
    }



    // ----- EXCEPTIONS ----- //

    /**
     *  This uses NumberFormatException and returns the number input.
     * 
     *  @param source The input source, mainly from a text field.
     * 
     *  @return The number input in integer format.
     *          0 if the input can't be parsed.
     */
    private int tryNumInput(String source) {
        int dest;

        try {
            dest = Integer.parseInt(source);
            
            if (dest < 0)
                dest = 0;
        } catch (NumberFormatException e) {
            dest = 0;
        }

        return dest;
    }

    /**
     *  This uses a custom exception by the author.
     * 
     *  @param input The input to be checked.
     * 
     *  @throws NoInputException When the input is blank.
     */
    private void checkInput(String input) throws NoInputException {
        if (input.equals(""))
            throw new NoInputException("No input.");
    }
}
