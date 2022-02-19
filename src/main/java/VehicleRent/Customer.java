package VehicleRent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;

public class Customer {

    public Customer(){}
    //static scanner accessible outside main also
    static Scanner scannerObj=new Scanner(System.in);

    //parsing a rental CSV file into scanner Object
    static Scanner fileReader;

    static {
        try {
            fileReader = new Scanner(new File("src\\main\\resources\\rental.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //file writer object to write data in rental.csv
    static FileWriter writer;

    static {
        try {
            writer = new FileWriter("src\\main\\resources\\rental.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //parsing a vehicle CSV file into scanner Object
    static Scanner fileReaderVehicle;

    static {
        try {
            fileReaderVehicle = new Scanner(new File("src\\main\\resources\\vehicle.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //file writer object to write data in vehicle.csv
    static FileWriter writerVehicle;

    static {
        try {
            writerVehicle = new FileWriter("src\\main\\resources\\vehicle.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InputMismatchException {
        try{
            ArrayList<Vehicle> vehicleArrayList=new ArrayList<>(); //ArrayList for car-rental-fleet
            ArrayList<Rental> rentalArrayList=new ArrayList<>();   //Store rented vehicle and customer data.

            //Preview message for feature implemented in program with multiple choice
            show(vehicleArrayList,rentalArrayList); //take ArrayList as parameter which has data required for car-rental-fleet and rented vehicle data.
            }// All exception Handled
            catch (InputMismatchException i){
                System.out.println("Input Mismatch Exception Occurs: Please enter input in correct return type");

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
    }// Main method ends

    ////take ArrayList as parameter which has data required for car-rental-fleet and rented vehicle data.
    public static void show(ArrayList<Vehicle> vehicleArrayList,ArrayList<Rental> rentalArrayList) throws IOException, ParseException {
        //local variable to take user input.
        String name;
        String vehicleName;
        long charges;
        long identificationCode;
        String brand;
        String model;
        int numberOfSeat;
        String licensePlate;
        String startDate;
        String endDate;
        long iCode;
        boolean flag;
        int count;

        System.out.println("Enter choice:\n"
                +"1. Addition of a new rental of a vehicle by a customer, with which the rental start and end dates, customer data etc.\n"
                +"2. Return of the vehicle to the car rental\n"
                +"3. Display of the list of all vehicles belonging to the car rental fleet.\n"
                +"4. View the list of all vehicles available for rental in a specified period of time\n"
                +"5. Adding a new vehicle to the car rental fleet.\n"
                +"6. Cancellation of a vehicle from the car rental fleet.\n"
                +"7. [Exit with Vehicle data]Importing vehicles from a CSV file.\n"
                +"8. Export of all car rental vehicles in a CSV file.\n"
                +"9. [Exit with rental data] End the program and Display final rental.csv content \n"
                +"0. Exit\n");

        int number=scannerObj.nextInt();
        scannerObj.nextLine();
        int index;
        //For implementing the multiple feature in choice
        switch (number)
        {
            case 0:
                System.out.println("Exit");
                writer.close();
                break;

            case 1:
                checkVehicle(vehicleArrayList,rentalArrayList);
//user input
                System.out.println("Choice one: Enter customer Details");
                System.out.println("Enter customer name:");
                name=scannerObj.nextLine();
                System.out.println("Enter VehicleName you want to rent:");
                vehicleName=scannerObj.nextLine();
                scannerObj.nextLine();
                System.out.println("Enter User Start date for rent duration  in (dd-mm-yyyy) date format:");
                startDate=scannerObj.nextLine();
                System.out.println("Enter User End date for rent duration in (dd-mm-yyyy) date format:");
                endDate=scannerObj.nextLine();
                SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy"); //date format as string
                Date userStartDate;
                Date userEndDate;
                userStartDate = sdf.parse(startDate);
                userEndDate = sdf.parse(endDate);
                flag=false;
                //iterate over list,find exact vehicle name in list
                for(index=0;index<vehicleArrayList.size();index++) { //take AvailableDate from list
                    Date availableStartDate = sdf.parse(vehicleArrayList.get(index).getStartDate());
                    Date availableEndDate = sdf.parse(vehicleArrayList.get(index).getEndDate());
                    //check userDate is within Available date
                    //check vehicle is available in list
                    if ((vehicleArrayList.get(index).getBrand().equalsIgnoreCase(vehicleName))&&(((userStartDate.getTime() - availableStartDate.getTime()) > 0) && ((availableEndDate.getTime() - userEndDate.getTime()) > 0))) {
                    flag=true;
                    //if yes then continue with input
                    }
                }
                if(!flag)
                {//invalid message
                    System.out.println("Enter valid vehicleName and Date.");
                    show(vehicleArrayList,rentalArrayList);
                    break;
                }
                Date date1=sdf.parse(startDate);
                Date date2=sdf.parse(endDate);
                //calculate day difference
                long timeDifference = (date2.getTime() - date1.getTime());
                long daysDifference = (timeDifference
                        / (1000 * 60 * 60 * 24))
                        % 365;
                Rental r=new Rental();
                //calculate charge or rent for vehicle as per days
                charges=r.getRental(daysDifference);
                for (index=0;index<vehicleArrayList.size();index++) {
                    //iterate over list,find exact vehicle name in list
                    if(vehicleArrayList.get(index).getBrand().equalsIgnoreCase(vehicleName))
                    {
                        iCode=vehicleArrayList.get(index).getIdentificationCode();
                        model=vehicleArrayList.get(index).getModel();
                        numberOfSeat=vehicleArrayList.get(index).getNumberOfSeat();
                        licensePlate=vehicleArrayList.get(index).getLicensePlate();
                        //given the vehicle on rent and remove it from car-rental-fleet
                        rentalArrayList.add(new Rental(name,vehicleName,iCode,model,numberOfSeat,licensePlate,startDate,endDate,charges,daysDifference));
                        //print message for successful process.
                        System.out.println(vehicleArrayList.get(index).getBrand()+" is given on rent. Removed from car-rental-fleet. ");
                        vehicleArrayList.remove(index);
                        break;
                    }
                }
                      show(vehicleArrayList,rentalArrayList);
                    break;

            case 2:
                count=1;
                flag=false;
                System.out.println("Choice two:-Return of the vehicle to the car rental");
                //check the list is empty or not
                //if empty display message
                for (Rental rental:rentalArrayList) {
                    System.out.println((count++)+" "+rental.getVehicleName());
                    flag=true;
                }
                if(!flag){
                    System.out.println("No vehicle on rent yet. Empty List");
                    show(vehicleArrayList,rentalArrayList);
                    break;
                }
//take user data
                flag=false;
                System.out.println("Enter rented vehicleName given to customer. ");
                vehicleName=scannerObj.nextLine();

                //iterate over list,find exact vehicle name in list
                for(index=0;index<rentalArrayList.size();index++){
                    //iterate over list,find exact vehicle name in list
                    if(rentalArrayList.get(index).getVehicleName().equalsIgnoreCase(vehicleName))
                    {
                        iCode=rentalArrayList.get(index).getIdentificationCode();
                        vehicleName=rentalArrayList.get(index).getVehicleName();
                        model=rentalArrayList.get(index).getModel();
                        numberOfSeat=rentalArrayList.get(index).getNumberOfSeat();
                        licensePlate=rentalArrayList.get(index).getLicensePlate();
                        startDate=rentalArrayList.get(index).getStartDate();
                        endDate=rentalArrayList.get(index).getEndDate();

                        //add the vehicle again in car-rental-fleet list
                        vehicleArrayList.add(new Vehicle(iCode,vehicleName,model,numberOfSeat,licensePlate,startDate,endDate));
                       //after adding,now remove it from rented list
                        rentalArrayList.remove(index);
                        flag=true;
                    }
                }
                if(flag)
                {
                    System.out.println("Canceled the rented vehicle and now added to car-rental-fleet ");
                }
                else
                {
                    System.out.println("This vehicle is not rented yet. Not given on rent.");
                }
                show(vehicleArrayList,rentalArrayList);
                break;

            case 3:
                index=1;
                flag=false;
                System.out.println("Choice Three- The list of all vehicles belonging to the car rental Fleet");
                //iterate over list, and display whole list
                for (Vehicle vehicle:vehicleArrayList) {
                    System.out.println((index++)+" "+vehicle.getBrand()+" between "+vehicle.getStartDate()+" and "+vehicle.getEndDate()+" date range.");
                    flag=true; //check flag for list empty or not
                }
                if (!flag)
                {
                    System.out.println("No vehicle found");
                }
                show(vehicleArrayList,rentalArrayList);
                break;

            case 4:
                System.out.println("List of vehicle:");
                checkVehicle(vehicleArrayList,rentalArrayList);
                System.out.println("Find the list of all vehicles available for rental in a specified period of time");
                System.out.println("Enter timePeriod in which you want to check availability of Vehicle. User Start date in (dd-mm-yyyy) date format:");
                startDate=scannerObj.nextLine();
                System.out.println("Enter User End date in (dd-mm-yyyy) date format:");
                endDate=scannerObj.nextLine();
                sdf= new SimpleDateFormat("dd-MM-yyyy");
                userStartDate = sdf.parse(startDate);
                userEndDate = sdf.parse(endDate);

                //check date, end should be bigger than start date
                if((userEndDate.getTime()-userStartDate.getTime())<0)
                {
                    System.out.println("Wrong date entered. Enter valid date range. Select choice again. ");
                    show(vehicleArrayList,rentalArrayList);
                }
                count=1;
                flag=false;
                for(index=0;index<vehicleArrayList.size();index++)
                {
                    //iterate over list, get all available date oneByone.
                    Date availableStartDate = sdf.parse(vehicleArrayList.get(index).getStartDate());
                    Date availableEndDate = sdf.parse(vehicleArrayList.get(index).getEndDate());

                    //check userDate is within Available date
                    if((( userStartDate.getTime()-availableStartDate.getTime() )>0)&&((availableEndDate.getTime() - userEndDate.getTime())>0)){
                       //print list of vehicle in within given user date
                        System.out.println((count++)+" "+vehicleArrayList.get(index).getBrand()+" is available between "+vehicleArrayList.get(index).getStartDate()+" and "+vehicleArrayList.get(index).getEndDate()+" date range.");
                        flag=true;
                    }
                }
                if(!flag)
                {
                    System.out.println("This range of date is not available. Please enter valid date.");
                }
                show(vehicleArrayList,rentalArrayList);
                break;

            case 5:
                //Adding vehicle to car rental fleet
                //take user input
                System.out.println("Choice five: Adding a new vehicle to the car rental fleet.");
                System.out.println("Enter Vehicle identification code(Digits):");
                identificationCode=scannerObj.nextLong();
                scannerObj.nextLine();
                System.out.println("Enter VehicleName (Brand):");
                brand=scannerObj.nextLine();
                System.out.println("Enter Model:");
                model=scannerObj.nextLine();
                System.out.println("Enter Number of Seat(Digit):");
                numberOfSeat=scannerObj.nextInt();
                scannerObj.nextLine();
                System.out.println("Enter Vehicle License Plate:");
                licensePlate=scannerObj.nextLine();
                System.out.println("Enter vehicle available Start date in (dd-mm-yyyy) date format:");
                startDate=scannerObj.nextLine();
                System.out.println("Enter vehicle available End date in (dd-mm-yyyy) date format:");
                endDate=scannerObj.nextLine();
                sdf= new SimpleDateFormat("dd-MM-yyyy");
                userStartDate = sdf.parse(startDate);
                userEndDate = sdf.parse(endDate);
                //check date, end should be bigger than start date
                if((userEndDate.getTime()-userStartDate.getTime())<0)
                {
                    System.out.println("Wrong date entered. Enter valid date range. Select choice again. ");
                    show(vehicleArrayList,rentalArrayList);
                }

                //add vehicle in arraylist by take Vehicle Object with userParameters
                vehicleArrayList.add(new Vehicle(identificationCode,brand,model,numberOfSeat,licensePlate,startDate,endDate));
                System.out.println("Vehicle added successfully");
                System.out.println("Total number of vehicle in car rental fleet-"+vehicleArrayList.size());
                show(vehicleArrayList,rentalArrayList);
                break;

            case 6:  System.out.println("Choice six");
                checkVehicle(vehicleArrayList,rentalArrayList);
                flag=false;
                System.out.println("Enter vehicle name which you want to remove from car-rental-fleet. :");
                vehicleName=scannerObj.nextLine();

                for(index=0;index<vehicleArrayList.size();index++){
                    //check user vehicleName in list
                    if(vehicleArrayList.get(index).getBrand().equalsIgnoreCase(vehicleName))
                    {
                        // found vehicle name
                        //take for loop index - get location of vehicle stored in list
                        vehicleArrayList.remove(index); //Using remove(), delete the vehicle details from list
                        flag=true;
                    }
                }
                if(flag)
                {
                    System.out.println("Vehicle removed successfully.");
                }
                else
                {
                    System.out.println("Vehicle not found in car-rental-fleet.");
                }
                show(vehicleArrayList,rentalArrayList);
                break;

            case 7:
                flag=false;
                for (Vehicle vehicle:vehicleArrayList) {
                      flag=true;
                }
                if (!flag) {
                    System.out.println("No vehicle found. First insert the vehicle, choose option 5.");

                }
                System.out.println("Importing vehicles from a CSV file.");
                for (index=0;index<vehicleArrayList.size();index++)
                {
                    iCode=vehicleArrayList.get(index).getIdentificationCode();
                    brand=vehicleArrayList.get(index).getBrand();
                    model=vehicleArrayList.get(index).getModel();
                    numberOfSeat=vehicleArrayList.get(index).getNumberOfSeat();
                    licensePlate=vehicleArrayList.get(index).getLicensePlate();
                    startDate=vehicleArrayList.get(index).getStartDate();
                    endDate=vehicleArrayList.get(index).getEndDate();
                    //write final content in list in vehicle csv after performing various operation
                    //Date stored in ", " comma separated pattern
                    writerVehicle.write(iCode+ "," +brand+ "," +model+ "," +numberOfSeat+ "," +licensePlate+ "," +startDate+ "," +endDate+"\n");
                }
                writerVehicle.close();
                //file writer object need to be close to write
                while(fileReaderVehicle.hasNext())
                {
                    String line=fileReaderVehicle.nextLine();
                    String[] vehicle=line.split(",");
                    System.out.println("IdentificationCode-"+vehicle[0]
                            +", VehicleRented-"+vehicle[1]
                            +", Vehicle Model-"+vehicle[2]
                            +", NumberOfSeat-"+vehicle[3]
                            +", LicencePlate-"+vehicle[4]
                            +", Available StartDate-"+vehicle[5]
                            +", Available EndDate-"+vehicle[6]
                            );
                }
                break;

            case 8:
                System.out.println("Export of all car rental vehicles in a CSV file.");
                flag=false;
                for(index=0;index<rentalArrayList.size();index++) {
                    name=rentalArrayList.get(index).getCustomerName();
                    iCode = rentalArrayList.get(index).getIdentificationCode();
                    vehicleName = rentalArrayList.get(index).getVehicleName();
                    model = rentalArrayList.get(index).getModel();
                    numberOfSeat = rentalArrayList.get(index).getNumberOfSeat();
                    licensePlate = rentalArrayList.get(index).getLicensePlate();
                    startDate = rentalArrayList.get(index).getStartDate();
                    endDate = rentalArrayList.get(index).getEndDate();
                    charges = rentalArrayList.get(index).getCharges();
                    daysDifference=rentalArrayList.get(index).getNumberOfDays();

                    //write final content in list in rental csv after performing various operation
                    //Date stored in ", " comma separated pattern
                    writer.write(name + "," + vehicleName + "," + model  + "," + iCode  + "," + numberOfSeat + "," + licensePlate  + "," + startDate + "," + endDate + "," + charges + "," + daysDifference + "\n");
                    //file writer object need to be close to write
                    flag=true;
                }
                if (flag)
                {
                    System.out.println("Export successful");
                }
                else
                {
                    System.out.println("No vehicle on rent yet. Empty List");
                    writer.close();
                }
                show(vehicleArrayList,rentalArrayList);
                break;

            case 9:
                System.out.println("Choice 9-\n Reading rental.csv");
                writer.close();
                writerVehicle.close();
                flag=false;
                //by iterate over loop. read csv line by line.
                while(fileReader.hasNext())
                {
                    String line=fileReader.nextLine();
                    String[] rental=line.split(","); //separate in array by comma
                   // System.out.println(line);
                    //print data in csv
                    System.out.println("CustomerName-"+rental[0]
                            +", VehicleRented-"+rental[1]
                            +", VehicleRented-"+rental[2]
                            +", IdentificationCode-"+rental[3]
                            +", NumberOfSeat-"+rental[4]
                            +", LicencePlate-"+rental[5]
                            +", StartDate-"+rental[6]
                            +", EndDate-"+rental[7]
                            +", RentalCharge-"+rental[8]
                            +", DurationOfRent(Days)-"+rental[9]);
                    flag=true;

                }
                if(!flag)
                {
                    System.out.println("No vehicle on rent yet. Empty List");
                }
                fileReader.close();
                writerVehicle.close();
                fileReaderVehicle.close();
                break;

            default:
                //if enter choice other than given.
                System.out.println("Please enter valid choice.");

                //close all file reader, writer class.
                writer.close();
                fileReader.close();
                writerVehicle.close();
                writerVehicle.close();


        }
    }// Preview show method ends

    //checkVehicle list is empty or not
    public static void checkVehicle(ArrayList<Vehicle> vehicleArrayList,ArrayList<Rental> rentalArrayList) throws IOException, ParseException {
        boolean flag=false;
        int index=1;
        for (Vehicle vehicle:vehicleArrayList) {
            System.out.println((index++)+"- Vehicle "+vehicle.getBrand()+" is available between "+vehicle.getStartDate()+" and "+vehicle.getEndDate()+" date range.");
            flag=true;

        }
        if (!flag)
        {
            System.out.println("No vehicle found. First insert the vehicle, choose option 5.");
            show(vehicleArrayList,rentalArrayList);

        }
    }

    //checkRentedCustomer list is empty or not
    public static void checkRentle(ArrayList<Vehicle> vehicleArrayList,ArrayList<Rental> rentalArrayList) throws IOException, ParseException {
        int count=1;
        boolean flag=false;
        System.out.println("Choice two:-Return of the vehicle to the car rental");
        for (Rental rental:rentalArrayList) {
              flag=true;
            break;
        }
        if(!flag){
            System.out.println("No vehicle on rent yet. Empty List");
            show(vehicleArrayList,rentalArrayList);
        }
    }


}//class bracket ends.

