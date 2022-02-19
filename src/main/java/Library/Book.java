package Library;

import java.util.Comparator;

public class Book{
     String title;
     String year;
     String rating;
     public Book(){

     }

    public Book(String title, String year, String  rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }



    public int getYear() {
        return (int)Double.parseDouble(year);
    }

    public double getRating() {
        return (double) Double.parseDouble(rating);
    }





}
