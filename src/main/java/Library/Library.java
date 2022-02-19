package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Comparator;

public class Library{
    private static Scanner sc=new Scanner(System.in);
    ArrayList<Author> author;
    public Library(){}
    public Library(ArrayList<Author> author) {
        this.author = author;
    }
    public ArrayList<Author> getAuthor() {
        return author;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File("src\\main\\resources\\Books.csv"));
            Library libraryObj = new Library();
            scanner.useDelimiter(",");
            String author;
            String title;
            String year;
            String rating;
            String lineInResultFile;
            ArrayList<String> strarrayList = new ArrayList<>();
            ArrayList<Book> bookArrayList = new ArrayList<>();
            ArrayList<Author> authorArrayList = new ArrayList<>();
            String header = scanner.nextLine();
            int count = 0;
            while (scanner.hasNext()) {
                count++;
                lineInResultFile = scanner.nextLine();
                String booksCSVArray[] = lineInResultFile.split(";");
                author = booksCSVArray[6];
                year = booksCSVArray[7];
                title = booksCSVArray[8];
                rating = booksCSVArray[10];
                bookArrayList.add(new Book(title, year, rating));
                String authorArray[] = author.split(",");
                for (int i = 0; i < authorArray.length; i++) {
                    authorArrayList.add(new Author(authorArray[i], new Book(title, year, rating)));
                }
            }//while ends

            //Composition Achieve
            Library library = new Library(authorArrayList);
            ArrayList<Author> libraryDetails = library.getAuthor();
            String c;
            int num;
            do {
                System.out.println("Enter choice:-\n"
                        + "1. List all books written by an author by alphabetical asc, publicationDate desc, rating desc\n"
                        + "2. Given a book, know who is the author / are the authors; \n"
                        + "3. How many books has an author written;\n"
                        + "4. Given a Date, what are the books written in that year;\n"
                        + "5. Who is the most prolific author?\n");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        libraryObj.booksByOrder(libraryDetails);
                        break;
                    case 2:
                        libraryObj.listOfAuthorByBookname(libraryDetails);
                        break;
                    case 3:
                        libraryObj.howManyBooks(libraryDetails);
                        break;
                    case 4:
                        libraryObj.booksWrittenByYear(libraryDetails);
                        break;
                    case 5:
                        libraryObj.prolificAuthor(libraryDetails, strarrayList);
                        break;
                    default:
                        System.out.println("Enter valid choice.");
                        break;
                }

                System.out.println("Do you want to continue. If yes enter 0. If not enter any key.");
                num = sc.nextInt();
            }
            while (num == 0);

        }
        catch (InputMismatchException i)
        {
            System.out.println("Input mismatched exception occurs enter valid data.");
        }


    }//Main method ends


//methods for feature
    public void booksByOrder(ArrayList<Author> libraryDetails)
    {
        System.out.println();
        Collections.sort(libraryDetails,new TitleAscComparator());
        System.out.println("1. List all books written by an author.\n");
        System.out.println("1.1 List of all book based on Title(Alphabetical Order)");
        for (int index=0;index<libraryDetails.size();index++) {
            System.out.println("AuthorName: "+libraryDetails.get(index).getName()
                    +",TitleName: "+ libraryDetails.get(index).getBooks().getTitle());
        }
        Collections.sort(libraryDetails,new YearDescComparator());
        System.out.println("**************************************************************************");
        System.out.println("1.2 List of all book based on publication year(Desc order)");
        for (Author bk:libraryDetails) {
            System.out.println("AuthorName: "+(bk).getName()
                    +",TitleName: "+bk.getBooks().getTitle()
                    +", "+"Year: "+bk.getBooks().getYear());
        }
        Collections.sort(libraryDetails,new RatingDescComparator());
        System.out.println("\n**************************************************************************");
        System.out.println("1.3 List of all book based on rating(Desc Order)");
        for (int index=0;index<libraryDetails.size();index++) {
            System.out.println("AuthorName: "+libraryDetails.get(index).getName()
                    +",TitleName: "+ libraryDetails.get(index).getBooks().getTitle()
                    +", "+"Year: "+libraryDetails.get(index).getBooks().getYear()
                    +", "+"Rating: "+libraryDetails.get(index).getBooks().getRating());
        }

    }


    public void listOfAuthorByBookname(ArrayList<Author> libraryDetails)
    {int count;
        System.out.println();
        System.out.println("\n**************************************************************************");
        boolean flag=true;
        System.out.println("2. Enter the input for Library.Library.Book Name, to display the List of Authors");
        String bookName=sc.nextLine();
        count=1;
        System.out.println("Library.Library.Author who write this book: "+bookName);
        for (int index=0;index<libraryDetails.size();index++) {
            if(libraryDetails.get(index).getBooks().getTitle().equalsIgnoreCase(bookName)){
                System.out.println((count++)+" "+libraryDetails.get(index).getName());
                flag=false;
            }
        }
        if (flag)
        {
            System.out.println("This book is not available in Library.Library.");
        }
    }

    public void howManyBooks(ArrayList<Author> libraryDetails)
    {
        boolean flag=true;
        int count;
        System.out.println("\n**************************************************************************");
        System.out.println("3. How many books has an author written");
        flag=true;
        System.out.println("Enter the input for AuthorName, to display count of books written by author.");
        String authorName=sc.nextLine();
        count=0;
        for (int index=0;index<libraryDetails.size();index++) {
            if(libraryDetails.get(index).getName().equalsIgnoreCase(authorName)){
                count++;
                flag=false;
            }
        }
        System.out.println("The countOfBooks, written by "+authorName+" is:"+count);
        if (flag)
        {
            System.out.println("The AuthorName did not written book, Please enter another author name.");
        }
    }

    public void booksWrittenByYear(ArrayList<Author> libraryDetails) {
        boolean flag = true;
        int count;
        System.out.println("\n**************************************************************************");
        System.out.println("4. Enter the input year, to display the List of books written in that year");
        int yearInput=sc.nextInt();
        count=1;
        flag=true;
        System.out.println("Books written in year "+yearInput);
        for (Author bk:libraryDetails) {
            if(bk.getBooks().getYear()==yearInput){
                System.out.println((count++)+" "+bk.getBooks().getTitle());
                flag=false;
            }
        }
        if (flag)
        {
            System.out.println("There is no book written in this year. Please enter valid Year .");
        }
    }

    public void prolificAuthor(ArrayList<Author> libraryDetails, ArrayList<String> strarrayList) {
        boolean flag = true;
        int count;
        System.out.println("\n**************************************************************************");
        System.out.println("5. The most prolific author");
        int countOfAuthor;
        String str;
        String []arr={};
        for(int indexI=0;indexI<libraryDetails.size();indexI++)
        {   countOfAuthor=1;
            String author1=libraryDetails.get(indexI).getName();
            for (int indexJ=indexI+1;indexJ<libraryDetails.size();indexJ++){
                String author2=libraryDetails.get(indexJ).getName();
                if(author1.equalsIgnoreCase(author2))
                {
                    countOfAuthor++;
                }
            }
            str=(Integer.toString(countOfAuthor))+","+author1;
            strarrayList.add(str);
        }
        Collections.sort(strarrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        arr=strarrayList.get(0).split(",");
        System.out.println("AuthorName: "+arr[1]+" is a prolific author. He has written "+arr[0]+" books");






    }
}
