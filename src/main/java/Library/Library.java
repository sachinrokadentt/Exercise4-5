package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Comparator;

public class Library{   // Separate class for (main method and logic) which access Book and Author class.
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
            //parsing a Books CSV file into scanner Object
            Scanner scanner = new Scanner(new File("src\\main\\resources\\Books.csv"));
            Library libraryObj = new Library();
            scanner.useDelimiter(",");

            //local variable for taking csv content.
            String author;
            String title;
            String year;
            String rating;
            String lineInResultFile;

            ArrayList<String> strarrayList = new ArrayList<>(); //store author name and count of each author in String Type arraylist
            //arraylist of type class, which separately store books.csv file content
            ArrayList<Book> bookArrayList = new ArrayList<>();
            ArrayList<Author> authorArrayList = new ArrayList<>();  //store authorName and Book class Object.
            String header = scanner.nextLine();
            int count = 0;

            //Reading csv line by line using scanner.readline()
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

                //adding object of author class in arraylist named authorArrayList
                for (int i = 0; i < authorArray.length; i++) {
                    authorArrayList.add(new Author(authorArray[i], new Book(title, year, rating)));
                }
            }//while ends

            //Composition Achieve using author class which store Book class Object.
            Library library = new Library(authorArrayList);
            ArrayList<Author> libraryDetails = library.getAuthor();
            String c;
            int num;

            //Preview message for feature implemented in program.
            do {
                System.out.println("Enter choice:-\n"
                        + "1. List all books written by an author by alphabetical asc, publicationDate desc, rating desc\n"
                        + "2. Given a book, know who is the author / are the authors; \n"
                        + "3. How many books has an author written;\n"
                        + "4. Given a Date, what are the books written in that year;\n"
                        + "5. Who is the most prolific author?\n");
                int choice = sc.nextInt();
                sc.nextLine();

                //As per choice selected, call the feature implemented method.
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
                        //if enter choice other than given.
                        System.out.println("Enter valid choice.");
                        break;
                }

                System.out.println("Do you want to continue. If yes enter 0. If not enter any key.");
                num = sc.nextInt();
            }
            while (num == 0); //Loop ends which displays choices.

        }
        catch (InputMismatchException i)
        {
            System.out.println("Input mismatched exception occurs enter valid data.");
        }// Input exception get handled.


    }//Main method ends


//methods for feature
    //List of Book, ordered by title.
    public void booksByOrder(ArrayList<Author> libraryDetails)
    {
        System.out.println();
        //Ordering the arrayList of type-class by year. Using comparator interface implemented by TitleAscComparator which has compare method.
        Collections.sort(libraryDetails,new TitleAscComparator());
        System.out.println("1. List all books written by an author.\n");
        System.out.println("1.1 List of all book based on Title(Alphabetical Order)");
        for (int index=0;index<libraryDetails.size();index++) {
            System.out.println("AuthorName: " + libraryDetails.get(index).getName()
                    + ",TitleName: " + libraryDetails.get(index).getBooks().getTitle());
        }

        //Ordering the arrayList of type-class by year. Using comparator interface implemented by YearDescComparator which has compare method.
        Collections.sort(libraryDetails,new YearDescComparator());
        System.out.println("**************************************************************************");
        System.out.println("1.2 List of all book based on publication year(Desc order)");
        for (Author bk:libraryDetails) {
            System.out.println("AuthorName: "+(bk).getName()
                    +",TitleName: "+bk.getBooks().getTitle()
                    +", "+"Year: "+bk.getBooks().getYear());
        }
        //Ordering the arrayList of type-class by rating. Using comparator interface implemented by RatingDescComparator which has compare method.
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

//By given bookName or title. Shows the list of author
    public void listOfAuthorByBookname(ArrayList<Author> libraryDetails)
    {int count;
        System.out.println();
        System.out.println("\n**************************************************************************");
        boolean flag=true;
        System.out.println("2. Enter the input for Book Name, to display the List of Authors");
        String bookName=sc.nextLine();
        count=1;
        System.out.println("Author who write this book: "+bookName);
        for (int index=0;index<libraryDetails.size();index++) {
            //check this book is available in library or not
            if(libraryDetails.get(index).getBooks().getTitle().equalsIgnoreCase(bookName)){
                //if available, prints the name of author
                System.out.println((count++)+" "+libraryDetails.get(index).getName());
                flag=false;
            }
        }
        if (flag)
        {
            System.out.println("This book is not available in Library.Library.");
        }
    }

    //Take userInput as author, Display countOfBooks written by author
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
            //check this authorName present  in library or not
            if(libraryDetails.get(index).getName().equalsIgnoreCase(authorName)){
                count++;  //author is available, count by increment by one.
                flag=false;
            }
        }
        //print number of books written
        System.out.println("The countOfBooks, written by "+authorName+" is:"+count);
        if (flag)
        {
            System.out.println("The AuthorName did not written book, Please enter another author name.");
        }
    }

    //accept year as userInput
    public void booksWrittenByYear(ArrayList<Author> libraryDetails) {
        boolean flag = true;
        int count;
        System.out.println("\n**************************************************************************");
        System.out.println("4. Enter the input year, to display the List of books written in that year");
        int yearInput=sc.nextInt();
        count=1;
        flag=true;
        System.out.println("Books written in year "+yearInput);
        for (Author bk:libraryDetails) { //iterate over author arraylist
            if(bk.getBooks().getYear()==yearInput){  //check for exact year given
                //print the name of book published in given year
                System.out.println((count++)+" "+bk.getBooks().getTitle());
                flag=false;
            }
        }
        if (flag)
        {
            System.out.println("There is no book written in this year. Please enter valid Year .");
        }
    }

    //Displays the authorName and countOfBook published by author
    public void prolificAuthor(ArrayList<Author> libraryDetails, ArrayList<String> strarrayList) {
        boolean flag = true;
        int count;
        System.out.println("\n**************************************************************************");
        System.out.println("5. The most prolific author");
        int countOfAuthor;
        String str;
        String []arr={};
        //count how many times each author name is there in list.
        for(int indexI=0;indexI<libraryDetails.size();indexI++)
        {   countOfAuthor=1;                                       //Take first occurance of authorName
            String author1=libraryDetails.get(indexI).getName();
            for (int indexJ=indexI+1;indexJ<libraryDetails.size();indexJ++){
                String author2=libraryDetails.get(indexJ).getName();
                if(author1.equalsIgnoreCase(author2))              // check for same authorname
                {
                    countOfAuthor++;
                }
            }
            //
            str=(Integer.toString(countOfAuthor))+","+author1;
            //store author name and count of each author in String Type arraylist
            strarrayList.add(str);
        }

        //order string arrayList by descending
        Collections.sort(strarrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        //take first string from arrayList which has most prolific author
        arr=strarrayList.get(0).split(",");
        System.out.println("AuthorName: "+arr[1]+" is a prolific author. He has written "+arr[0]+" books");



    }
}
