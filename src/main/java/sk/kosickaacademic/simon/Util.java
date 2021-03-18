package sk.kosickaacademic.simon;

import java.util.Calendar;

public class Util {
    public int checkYear(int year){
        if(year>1900 && year<=Calendar.getInstance().get(Calendar.YEAR))
            return year;

        return 0;
    }

    public String normalizeString(String data){
        if(data!=null || !data.equals(""))
            return data.toUpperCase().charAt(0) + data.toLowerCase().substring(1);

        return null;
    }
}
