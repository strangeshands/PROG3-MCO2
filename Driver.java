import MVCFiles.*;

public class Driver {
    public static void main(String[] args) {
        StartPageView viewStart = new StartPageView();
        HRSModel modHRS = new HRSModel();
        @SuppressWarnings("unused")
        StartPageController contStart = new StartPageController(viewStart, modHRS);
    }
}
