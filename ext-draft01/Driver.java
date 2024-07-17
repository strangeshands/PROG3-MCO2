public class Driver {
    public static void main (String[] args) {
        HRS GrandBudapest = new HRS("Grand Budapest Booking");

        // Print welcome page
        GrandBudapest.renderWelcome();

        // Start operation
        GrandBudapest.startOperation();

        // Print exit page
        GrandBudapest.renderExit();
    }
}
