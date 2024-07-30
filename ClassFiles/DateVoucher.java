package ClassFiles;

import java.util.ArrayList;


/**
 *  A voucher that is dependent on at most 2 specific dates.
 * 
 *  @author HEPHZI TOLENTINO
 *  @author FRANCINE SANTOS
 */
public class DateVoucher extends Voucher {
    /**
     * The first date that this voucher is dependent on.
     */
    private int Date1;

    /**
     * The second date that this voucher is dependent on.
     */
    private int Date2;



    /**
     * Constructs a new date-dependent voucher with the given discount code, multiplier, and dates.
     * 
     * @param name the discount code
     * @param mult the discount multiplier
     * @param Date1 the first date
     * @param Date2 the second date
     */
    public DateVoucher(String name, float mult, int Date1, int Date2) {
        super(name, mult);
        this.Date1 = Date1;
        this.Date2 = Date2;
    }



    /**
     * Applies the discount to the given base price.
     * 
     * The discount is calculated by subtracting the product of the base price and the discount multiplier from the base price.
     * 
     * @param basePrice the base price to apply the discount to
     * @return the discounted price
     */
    @Override
    protected float applyDiscount(float basePrice) {
        return basePrice - (basePrice * getDiscountMultiplier());
    }

    /**
     * Checks if this voucher is applicable to the given reservation.
     * 
     * The voucher is applicable if any of the dates in the reservation (excluding the check-out date) match either of the two dates
     * specified in this voucher.
     * 
     * @param reservation the reservation to check
     * @return true if the voucher is applicable, false otherwise
     */
    @Override
    protected boolean checkApplicable(Reservation reservation) {
        ArrayList<Day> reservDays = reservation.getReserveDays();

        for (int i = 0; i < reservDays.size()-1; i++) {
            int currDate = reservDays.get(i).getDate();

            if (currDate == Date1 || currDate == Date2)
                return true;
        }

        return false;
    }

    

    /**
     *  Gets the type of the voucher
     *  @return the String type of the voucher
     */
    @Override
    public String getType() {
        return "Date";
    }

    /**
     *  Gets the condition of the voucher
     *  @return The date where the voucher can be applied
     */
    @Override
    public String getCondition() {
        if (Date2 == 0)
            return "June " + Date1;
        else
            return "June " + Date1 + " & " + Date2;
    }
}
