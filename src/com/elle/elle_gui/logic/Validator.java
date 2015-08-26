
package com.elle.elle_gui.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validator
 * This class is used to validate data 
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public class Validator {
    
    /**
     * 
     * This is used to validate the date
     * @param format
     * @param value
     * @return 
     */
    public static boolean isValidDate(String format, String dateValue) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(dateValue);
            if (!dateValue.equals(simpleDateFormat.format(date))) {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * This is used to validate the date
     * @param format
     * @param value
     * @return 
     */
    public static boolean isValidDate(String dateValue) {
        return isValidDate("yyyy-MM-dd", dateValue);
    }
}
