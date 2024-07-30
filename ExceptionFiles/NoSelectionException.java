package ExceptionFiles;


/**
 * Exception to throw when there is no selection for room/hotel
 */
public class NoSelectionException extends Exception{
    /**
     * Constructs a NoSelectionException with the provided message
     * 
     * @param message The message to show when exception is thrown
     */
    public NoSelectionException(String message) {
        super(message);
    }
}