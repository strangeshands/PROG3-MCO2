public class I_WORK_HERE {
    private final String discountCode = "I_WORK_HERE";
    private final float multiplier = 0.1f;

    public I_WORK_HERE() {

    }

    public float applyDiscount(float basePrice) {
        return basePrice - (basePrice * 0.1f);
    }
}