package sk.kosickaacademic.simon.entity;

public class Movie {
    private String title, genre, director;
    private int year;

    public Movie(String title, String genre, String director, int year) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }
}
