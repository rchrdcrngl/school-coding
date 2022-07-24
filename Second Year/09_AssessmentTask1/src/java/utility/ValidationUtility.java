package utility;

import java.sql.Date;

public class ValidationUtility{
    public static Date parseDate(String year, String month, String day){
        int yr = Integer.parseInt(year.trim());
        int mn = Integer.parseInt(month.trim());
        int dy = Integer.parseInt(day.trim());
        return new Date(yr-1900, mn-1, dy);
    }
    
    public static boolean inputIsNull(String[] input){
        for(String i: input){
            if(i==null)return true;
        }
        return false;
    }
}