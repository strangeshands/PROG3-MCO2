package ClassFiles;


/**
 *  This is just a simple discount voucher which deducts
 *      a percentage of the base price to the total price.
 * 
 *  No conditions are checked.
 */
public class DiscountVoucher extends Voucher{
    /**
     * Constructs a discount voucher with the provided name and multipler.
     * 
     * @param name The name of the voucher
     * @param mult The multipler of the discount
     */
    public DiscountVoucher(String name, float mult) {
        super(name, mult);
    }



    /**
     *  Returns the discounted price.
     * 
     *  @param basePrice The base price where the discount is based on.
     *  @return The discounted price.
     */
    @Override
    protected float applyDiscount(float basePrice) {
        return basePrice - (basePrice * getDiscountMultiplier());
    }

    /**
     *  For this subclass, this only returns true as there's no condition to meet.
     *  
     *  @param reservation The reservation where it is applied.
     *  @return true - since there are no conditions to check.
     */
    @Override
    protected boolean checkApplicable(Reservation reservation) {
        return true;
    }



    /**
     * Gets the type of the voucher
     * @return "Discount"
     */
    @Override
    public String getType() {
        return "Discount";
    }

    /**
     * Gets the condition of the voucher
     * @return "None"
     */
    @Override
    public String getCondition() {
        return "None";
    }
}

