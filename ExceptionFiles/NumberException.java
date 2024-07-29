package ExceptionFiles;


/**
 * The NumberException is a custom exception that is thrown when an 
 *  invalid or unexpected number is encountered.
 */
public class NumberException extends Exception{
    /**
     * Constructs a new NumberException with the specified message.
     * 
     * @param message the message to be displayed when the exception is thrown
     */
    public NumberException(String message) {
        super(message);
    }
}
