package ClassFiles;


/**
 * Abstract class representing a voucher that can be applied to a reservation.
 * 
 * A voucher has a unique discount code and a discount multiplier that is used to calculate the discount amount.
 */
public abstract class Voucher {
    /**
     * The unique discount code for this voucher.
     */
    private String DiscountCode;

    /**
     * The discount multiplier used to calculate the discount amount.
     */
    private float DiscountMultipler;



    /**
     * Constructs a new voucher with the given discount code and multiplier.
     * 
     * @param name the discount code
     * @param mult the discount multiplier
     */
    public Voucher (String name, float mult) {
        DiscountCode = name;
        DiscountMultipler = mult;
    }



    /**
     * Applies the discount to the given base amount.
     * 
     * This method must be implemented by subclasses.
     * 
     * @param base the base amount to apply the discount to
     * @return the discounted amount
     */
    protected abstract float applyDiscount(float base);

    /**
     * Checks if this voucher is applicable to the given reservation.
     * 
     * This method must be implemented by subclasses.
     * 
     * @param reservation the reservation to check
     * @return true if the voucher is applicable, false otherwise
     */
    protected abstract boolean checkApplicable(Reservation reservation);

    /**
     * Gets the type of the voucher
     * 
     * @return the type of the voucher
     */
    public abstract String getType();

    /**
     * Gets the condition of the voucher
     * 
     * @return the condition of the voucher
     */
    public abstract String getCondition();


    
    /**
     * Returns the discount multiplier.
     * 
     * @return the discount multiplier
     */
    public float getDiscountMultiplier() {
        return DiscountMultipler;
    }

    /**
     * Returns the discount code.
     * 
     * @return the discount code
     */
    public String getDiscountCode() {
        return DiscountCode;
    }

    /**
     * Calculates the discount amount based on the given base amount.
     * 
     * @param base the base amount
     * @return the discount amount
     */
    public float getDiscount(float base) {
        return base * DiscountMultipler;
    }
}

