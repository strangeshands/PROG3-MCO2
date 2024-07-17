public class PAYDAY {
    private final String discountCode = "I_WORK_HERE";
    private final float multiplier = 0.1f;

    public PAYDAY() {

    }

    public float applyDiscount(float basePrice, int cIn, int cOut) {
        return basePrice - (basePrice * 0.07f);
    }
}