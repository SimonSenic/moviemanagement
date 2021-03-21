package sk.kosickaacademic.simon.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kosickaacademic.simon.Util;
import sk.kosickaacademic.simon.database.Database;
import sk.kosickaacademic.simon.entity.Movie;

import java.util.List;

@RestController
public class Controller {
    @PostMapping("/movie/add")
    public ResponseEntity<String> insertNewMovie(@RequestBody String data){
        try{
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            Util util = new Util();
            String title = (String) object.get("title");
            int year = Integer.parseInt(String.valueOf(object.get("year")));
            String genre = (String) object.get("genre");
            String director = (String) object.get("director");
            if(new Database().insertNewMovie(new Movie(util.normalizeString(title), util.normalizeString(genre),
                    util.normalizeString(director), util.checkYear(year)))){
                JSONObject json = new JSONObject();
                json.put("system", "Movie added successfully");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
            }else{
                JSONObject json = new JSONObject();
                json.put("system", "Insert unsuccessful");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/movies")
    public ResponseEntity<String> getAllMovies(){
        List<Movie> list = new Database().getAllMovies();
        String json = new Util().getJSON(list);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);
    }

    @DeleteMapping("/movie/delete")
    public ResponseEntity<String> deleteMovie(@RequestParam(value = "id") int id){
        if(id<=0){
            JSONObject json = new JSONObject();
            json.put("system", "Delete unsuccessful");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
        }
        if(new Database().deleteMovie(id)){
            JSONObject json = new JSONObject();
            json.put("system", "Movie deleted successfully");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
        }
        return null;
    }

    @PatchMapping("/movie/update")
    public ResponseEntity<String> updateTitle(@RequestParam(value = "id") int id, @RequestParam(value = "newTitle") String newTitle){
        if(id<=0 || newTitle==null || newTitle.equals("")){
            JSONObject json = new JSONObject();
            json.put("system", "Update unsuccessful");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
        }
        if(new Database().updateTitle(id, newTitle)){
            JSONObject json = new JSONObject();
            json.put("system", "Movie title changed");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(json.toJSONString());
        }
        return null;
    }

}
