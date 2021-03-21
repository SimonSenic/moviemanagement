package sk.kosickaacademic.simon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sk.kosickaacademic.simon.entity.Movie;

import java.util.Calendar;
import java.util.List;

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

    public String getJSON(List<Movie> list){
        if(list==null || list.isEmpty()) return null;
        JSONArray jArray = new JSONArray();
        JSONObject object = new JSONObject();
        for(Movie temp : list){
            JSONObject jObj = new JSONObject();
            jObj.put("title", temp.getTitle());
            jObj.put("year", temp.getYear());
            jObj.put("genre", temp.getGenre());
            jObj.put("director", temp.getDirector());
            jArray.add(jObj);
        }
        object.put("movies", jArray);
        return object.toString();
    }
}
