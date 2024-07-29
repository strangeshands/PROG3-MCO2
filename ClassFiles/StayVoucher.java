package ClassFiles;


/**
 * This class represents a stay voucher, which is a type of voucher that applies a 
 *      discount to a reservation based on the length of stay.
 *
 * @author Tolentino, Hephzi
 * @author Santos, Francine
 */
public class StayVoucher extends Voucher {
    /**
     * The minimum length of stay required for this voucher to be applicable.
     */
    private int dayLength;

    /**
     * The day of the stay that the discount is applied to (1-indexed).
     */
    private int discountedDay;

    /**
     * The discount amount that is applied to the reservation.
     */
    private float discount;



    /**
     * Constructs a new StayVoucher instance.
     *
     * @param name  the name of the voucher
     * @param mult  the discount multiplier
     * @param length the minimum length of stay required
     * @param day   the day of the stay that the discount is applied to
     */
    public StayVoucher(String name, float mult, int length, int day) {
        super(name, mult);
        dayLength = length;
        discountedDay = day;
    }



    /**
     * Applies the discount to the base price of the reservation.
     *
     * @param base the base price of the reservation
     * @return the discounted price
     */
    @Override
    protected float applyDiscount(float base) {
        return base - discount;
    }
    
    /**
     * Checks if this voucher is applicable to the given reservation.
     *
     * @param reservation the reservation to check
     * @return true if the voucher is applicable, false otherwise
     */
    @Override
    protected boolean checkApplicable(Reservation reservation) {
        int length = reservation.getCheckOut() - reservation.getCheckIn();
        return length >= dayLength;
    }



    /**
     * Sets the discount amount based on the given reservation.
     *
     * @param reserv the reservation to use
     */
    public void setDiscount(Reservation reserv) {
        float dpm = reserv.getReserveDays().get(discountedDay - 1).getDPMRate();
        float roomPrice = reserv.getRoom().getRoomPrice();
        discount = roomPrice * dpm * getDiscountMultiplier();
    }



    /**
     * Gets the discount amount.
     *
     * @return the discount amount
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Gets the day of the stay that the discount is applied to.
     *
     * @return the day of the stay
     */
    public int getDiscountedDay() {
        return discountedDay;
    }

    /**
     * Gets the minimum length of stay required for this voucher to be applicable.
     *
     * @return the minimum length of stay
     */
    public int getDaysLength() {
        return dayLength;
    }

    /**
     * Gets the type of voucher.
     *
     * @return the type of voucher
     */
    @Override
    public String getType() {
        return "Stay";
    }

    /**
     * Gets the condition of the voucher.
     *
     * @return the condition of the voucher
     */
    @Override
    public String getCondition() {
        switch (discountedDay) {
            case 1 -> {
                return  dayLength + " days -- " + discountedDay + "st day";
            }
            case 2 -> {
                return  dayLength + " days -- " + discountedDay + "nd day";
            }
            case 3 -> {
                return  dayLength + " days -- " + discountedDay + "rd day";
            }
            default -> {
                return  dayLength + " days -- " + discountedDay + "th day";
            }
        }
    }
}
