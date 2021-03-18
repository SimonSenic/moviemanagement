package sk.kosickaacademic.simon.database;

import sk.kosickaacademic.simon.Util;
import sk.kosickaacademic.simon.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Connection getConnection() {
        try{
            String url = "jdbc:mysql://localhost:3308/movie_list";
            String username = "root";
            String password = "";
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");
            return con;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<Movie> executeSelect(PreparedStatement ps) throws Exception{
        List<Movie> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while(rs.next()){
            String title = rs.getString("title");
            int year = rs.getInt("year");
            String genre = rs.getString("genre");
            String director = rs.getString("director");
            list.add(new Movie(title, genre, director, year));
            count++;
        }
        System.out.println("Movies found - " +count);
        return list;
    }

    public boolean insertNewMovie(Movie movie){
        String query = "INSERT INTO movie (title, year, genre, director) VALUES(?, ?, ?, ?)";
        Util util = new Util();
        try(Connection con = getConnection()){
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                String title = util.normalizeString(movie.getTitle());
                int year = util.checkYear(movie.getYear());
                String genre = util.normalizeString(movie.getGenre());
                String director = util.normalizeString(movie.getDirector());
                if(title==null || year==0 || genre==null || director==null){
                    System.out.println("Insert unsuccessful");
                    return false;
                }
                ps.setString(1, title);
                ps.setInt(2, year);
                ps.setString(3, genre);
                ps.setString(4, director);
                int result = ps.executeUpdate();
                if(result==1){
                    System.out.println("Movie successfully added");
                    return true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Movie> getAllMovies(){
        String query = "SELECT * FROM movie";
        try(Connection con = getConnection()){
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                return executeSelect(ps);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
