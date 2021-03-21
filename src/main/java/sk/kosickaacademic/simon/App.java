package sk.kosickaacademic.simon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sk.kosickaacademic.simon.database.Database;
import sk.kosickaacademic.simon.entity.Movie;

@ComponentScan(basePackages = "sk.kosickaacademic.simon.controller")
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        //new Database().insertNewMovie(new Movie("LOrD Of the RIngS", "FAntasY", "pETEr JACKsoN", 2001));
        //new Database().getAllMovies();
        //new Database().deleteMovie(9);

        SpringApplication.run(App.class, args);
    }
}
