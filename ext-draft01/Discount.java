public abstract class Discount {
    protected String discountCode;
    protected float multiplier;

    public abstract float applyDiscount();

    public String getDiscountCode() {
        return this.discountCode;
    }

    public String getMultiplier() {
        return this.multiplier;
    }
}