package Library;
import java.util.Comparator;

public class Book{
     String title;    //book name
     String year;     //publishing year
     String rating;   //book rating


     public Book(){ }  //Default(Empty Constructor)
    //ParameterizeConstructor for initializing members
    public Book(String title, String year, String  rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    //getter methods for variable
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
