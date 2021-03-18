package sk.kosickaacademic.simon;

import sk.kosickaacademic.simon.database.Database;
import sk.kosickaacademic.simon.entity.Movie;

public class App
{
    public static void main( String[] args )
    {
        //new Database().insertNewMovie(new Movie("LOrD Of the RIngS", "FAntasY", "pETEr JACKsoN", 2001));
        new Database().getAllMovies();
    }
}
