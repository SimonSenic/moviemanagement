package sk.kosickaacademic.simon;

import java.util.Calendar;

public class Util {
    public int checkYear(int year){
        if(year>1900 && year<=Calendar.getInstance().get(Calendar.YEAR))
            return year;

        return 0;
    }

    public String normalizeString(String input){
        if(input!=null || !input.equals(""))
            return input.toUpperCase().charAt(0) + input.toLowerCase().substring(1);

        return null;
    }
}
