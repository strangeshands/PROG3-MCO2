import MVCFiles.*;

/**
 *  Driver file for the MCO2 implementaion of
 *      @author SANTOS, Francine
 *      @author TOLENTINO, Hephzi
 */
public class Driver {
    public static void main(String[] args) {
        StartPageView viewStart = new StartPageView();
        HRSModel modHRS = new HRSModel();
        StartPageController contStart = new StartPageController(viewStart, modHRS);
    }
}
