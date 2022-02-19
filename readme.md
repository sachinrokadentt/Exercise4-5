
<h1 align="center">Hi 👋, I'm Sachin Ramchandra Rokade</h1>


<h3 align="left">Languages :</h3>
<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> </p>

# Exercise4-5

Exercise4-5 is Java Project which contain 3 exercise on java problems.

## Installation

Use the project build tool [maven](https://maven.apache.org/download.cgi) to manage project, dependencies etc.

## Dependencies

Junit testing tool is required for Testing the class. Add the following dependencies to pom.xml file in Maven Project
```
<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```


##
## Exercise 4
1 A program for library system. Perform the following features:

1. List all books written by an author using following criteria:
   •	alphabetical asc/desc,
   •	publication date asc/desc,
   •	rating asc/desc;
2. Given a book, know who is the author / are the authors;
3. How many books has an author written;
4. Given a Date, what are the books written in that year;
5. ... who is the most prolific author?

<h3 align="left">Run Program:</h3>
Run Library.java class - To print the required output.

##### Classes Details
- Author
- Book
- Compare Object - (Implments interface of Comparator<Object> by overriding compare method.)
- Library (Main method)

1 Code logic for this exercise is in [Library.Library.java] class. Normal and parametrize test are performed on code logic.
- Library.Library.java Location: "src\\main\\java\\Library\\Library.java"

2 Test Cases for logic return in LibraryTest.AuthorTest.java and AuthorTest.AuthorTestParameterize.java.
- AuthorTest Location:"src\\test\\java\\LibraryTest.AuthorTest"
- AuthorTestParameterize Location:"src\\test\\java\\LibraryTest.AuthorTestParameterize"
- BookTest Location:"src\\test\\java\\LibraryTest.BookTest"

##
## Exercise 5
A program in Java for the management of the vehicles of a car rental.

The features that must be available in the application are:
1. Addition of a new rental of a vehicle by a customer, with which the rental start and end dates, customer data and a reference to the rented vehicle are recorded;
2. Return of the vehicle to the car rental;
3. Display of the list of all vehicles belonging to the car rental fleet.
4. View the list of all vehicles available for rental in a specified period of time.
5. Adding a new vehicle to the car rental fleet.
6. Cancellation of a vehicle from the car rental fleet.
7. Importing vehicles from a CSV file, with which it is possible to add new vehicles to the car rental fleet.
8. Export of all car rental vehicles in a CSV file.



<h3 align="left">Run Program:</h3>
Run Customer.java class - To print the required output.

#### Resource
1 rental.csv -(rented vehicle)

2 vehicle.csv- (car-rental-fleet)

##### Classes Details
- Customer -Class (main method)
- Rental -Class (rented-vehicle)
- Vehicle -Class(car-rental-fleet)

1 - Code logic for this exercise is in [Customer.java] class.  Customer is separate class for (main method and logic for problem statement) which access vehicle and Rental Class Member and methods.
- Customer Location: "src\\main\\java\\VehicleRent\\Customer.java"
- Rental Location: "src\\main\\java\\VehicleRent\\Rental.java"
- Vehicle Location: "src\\main\\java\\VehicleRent\\Vehicle.java"

2 -Test Cases for logic return in RentalTestParameterize.java
- RentalTestParameterize Location: "src\\test\\java\\VehicleRentTest\\RentalTestParameterize"


##
[Sachin Rokade](https://www.linkedin.com/in/sachin-%E3%82%B5%E3%83%81%E3%83%B3-rokade-%E3%83%AD%E3%82%AB%E3%83%87-74478518a/)
<h3 align="left">Connect with me:</h3>
<p align="left">
<a href="https://linkedin.com/in/https://www.linkedin.com/in/sachin-%e3%82%b5%e3%83%81%e3%83%b3-rokade-%e3%83%ad%e3%82%ab%e3%83%87-74478518a/" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="https://www.linkedin.com/in/sachin-%e3%82%b5%e3%83%81%e3%83%b3-rokade-%e3%83%ad%e3%82%ab%e3%83%87-74478518a/" height="30" width="40" /></a>
</p>
