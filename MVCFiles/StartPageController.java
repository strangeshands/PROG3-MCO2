package MVCFiles;

import java.awt.event.*;
import javax.swing.*;


/**
 * Controller class for the start page.
 * 
 * @author TOLENTINO, HEPHZI (main contributor)
 * @author SANTOS, FRANCINE
 */
public class StartPageController implements ActionListener {
    /**
     * start is the view object for the start page.
     * hrs is the model object for the hotel reservation system.
     */
    private StartPageView start;
    private HRSModel hrs;



    /**
     * Constructor for the CONTStart class.
     * 
     * @param gui the view object for the start page
     * @param model the model object for the hotel reservation system
     */
    public StartPageController(StartPageView gui, HRSModel model) {
        this.start = gui;
        this.hrs = model;
        
        start.setActionListener(this);
    }


    
    /**
     * Handles action events from the start page.
     * 
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CREATE")) {
            // Open a Create Window
            CreateView viewCreate = new CreateView();
            CreateController contCreate = new CreateController(viewCreate, hrs);
            viewCreate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else if (e.getActionCommand().equals("VIEW")) {
            // Open a Search Window
            SearchView viewHotel = new SearchView();
            SearchController contHotel = new SearchController(viewHotel, hrs);
            viewHotel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else if (e.getActionCommand().equals("MANAGE")) {
            // Open a Manage Window
            ManageView viewManage = new ManageView();
            ManageController contManage = new ManageController(viewManage, hrs);
            viewManage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else if (e.getActionCommand().equals("BOOK")) {
            // Open a Book Window
            BookingView viewBook = new BookingView();
            BookingController contBook = new BookingController(viewBook, hrs);
            viewBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}
 