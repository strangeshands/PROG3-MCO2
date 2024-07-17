import java.sql.Date;
import java.util.ArrayList;

public class DPM {
    /*
            TASK 3 <DATE PRICE MODIFIER>
            1. make an array of date price modifiers that have the 
            number of days and their mark up/down.

            2. print: The following are the increase or decrease in 
            prices given the number of days the customer will be staying in.
            
            3. if the DPM array is empty, print 
            [THERE IS CURRENTLY NO MARK UP OR DOWN IN PRICES]

            4. if not, proceed to step ...

            5. ask user for length of days and new rate
                >> make sure to keep asking if...
                    - date is not valid ( < 1 or > 31)
                    - rate < 50 or > 150
                >> end if the input is empty (user just presses enter) or
                    if they press 0 (idk ask hep pano niya ginawa sa gui)

            **GIRLY MAKE SURE THAT THESE NEW RATES SHOW UP SA RECEIPT**
            
        */
    
    int daysLength;
    float rate;
        
    public DPM(int length, int rate) {
        this.daysLength = length;
        this.rate = (float) rate / 100f;
    }
        
    public int getDaysLength() {
        return daysLength;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float lessDPM(float basePrice) {
        return basePrice - (basePrice * rate);
    }

    public float moreDPM(float basePrice) {
        return basePrice * rate;
    }
}