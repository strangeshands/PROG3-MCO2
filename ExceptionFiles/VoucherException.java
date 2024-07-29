package ExceptionFiles;


/**
 * VoucherException is thrown when the voucher is 
 *  either nonexistent or not applicable
 */
public class VoucherException extends Exception {
    /**
     * Constructs a new voucher exception.
     */
    public VoucherException() {
        super("Invalid Code.");
    }
}
