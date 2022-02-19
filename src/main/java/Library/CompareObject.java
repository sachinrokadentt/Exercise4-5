package Library;

import java.util.Comparator;

class TitleAscComparator implements Comparator<Author> {
    @Override
    public int compare(Author obj1, Author obj2) {
        return obj1.getBooks().getTitle().compareTo(obj2.getBooks().getTitle());
    }
}

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
