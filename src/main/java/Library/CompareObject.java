package Library;
import java.util.Comparator;


//Compare Author object by overriding compare method in Comparator<Author> interface.
//By title.
class TitleAscComparator implements Comparator<Author> {
    @Override
    public int compare(Author obj1, Author obj2) {
        return obj1.getBooks().getTitle().compareTo(obj2.getBooks().getTitle());
    }
}

//Compare Author object by overriding compare method in Comparator<Author> interface.
//By title.
class RatingDescComparator implements Comparator<Author> {
    @Override
    public int compare(Author obj1, Author obj2) {

        double rating1 = obj1.getBooks().getRating();
        double rating2 = obj2.getBooks().getRating();
        if (rating1 == rating2) {
            return 0;
        } else if (rating1 < rating2) {
            return 1;
        } else {
            return -1;
        }

    }
}

//Compare Author object by overriding compare method in Comparator<Author> interface.
//By title.
class YearDescComparator implements Comparator<Author> {
    @Override
    public int compare(Author obj1, Author obj2) {

        int year1 = obj1.getBooks().getYear();
        int year2 = obj2.getBooks().getYear();
        if (year1 == year2) {
            return 0;
        } else if (year1 < year2) {
            return 1;
        } else {
            return -1;
        }
    }
}
