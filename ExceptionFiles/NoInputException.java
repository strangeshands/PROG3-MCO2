package ExceptionFiles;


/**
 * Exception to throw when no input is provided
 */
public class NoInputException extends Exception {
    /**
     * Constructs an exception with the provided message
     * 
     * @param message The message to show if exception is thrown
     */
    public NoInputException(String message) {
        super(message);
    }
}
