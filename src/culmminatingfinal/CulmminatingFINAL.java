/**
 * Programmer Name: Haroon Chughtai
 * Program Name: Airline reservation System
 * Program AirlineReservationSystem.java
 */ 
package culmminatingfinal;
import java.util.*;
import java.io.*;
import static java.lang.System.out; // shorten output statement

public class CulmminatingFINAL {

    static public Scanner scanS = new Scanner(System.in); // Global Str Scanner
    static public Scanner scanN = new Scanner(System.in); // Global Num Scanner 
    
    public static void main(String[] args) throws IOException, 
            InterruptedException {

        ArrayList<Passenger> flight1 = new ArrayList<>(); // Morocco passengers
        ArrayList<Passenger> flight2 = new ArrayList<>(); // Banff
        ArrayList<Passenger> flight3 = new ArrayList<>(); // Iceland
        
        boolean gameEnd; // show mainMenu repeatedly
        
        for (int i = 0; i < 3; i++) { 
            
            /*
            This for loop opens each .txt file, reads all the info then stores
            it all into the appropriate Flight (ArrayLists)
            */
            
            String DestinationFile = "Country" +  String.valueOf(i) + ".txt";   
            File file = new File(DestinationFile);
            
            // open file to read from
            File myFile = new File(file.getAbsolutePath());
            Scanner readFile = new Scanner(myFile);
            
            // saves the destination to use later in the program
            String destination = readFile.nextLine(); 
            
            // While file has another line create a new Passenger object
            while (readFile.hasNext()) { 
                
                String name; // name of passenger 
                String email; // email of passenger
                String card; // card of passenger
                String number; // number of passenger
                int seat; // seat of passenger

                String moveLine = readFile.nextLine(); // move to next line
                String[] part = moveLine.split(", ");  // split line into string

                name = part[0]; // stores name from .txt file
                email = part[1]; // stores email from .txt file
                card = part[2]; // stores card number from .txt file
                number = part[3]; // stores phone number from .txt file  
                seat = Integer.parseInt(part[4]); // stores the seat # 
                
                Passenger passengerData;//initialize and create new obj below
                passengerData = new Passenger (name, email, card, number, seat);
                
                switch (destination) { // adds obj to appropriate ArrayList
                    case "morocco":
                        flight1.add(passengerData); // add to flight  (morocco)
                        break;
                    case "banff":
                        flight2.add(passengerData); // add to flight (banff)
                        break;
                    case "iceland":
                        flight3.add(passengerData); // add to flight (Iceland)
                        break;
                }
            }    
            readFile.close(); // closing text file from reading
        } // for loop ends
        
        do { // runs main menu repeatedly until user quits 
            gameEnd = mainMenu(flight1, flight2, flight3); 
        } while (gameEnd == false); 
        
        /*
        Once the user quites the game has offically ended but the following code
        runs and updates the text files with all the new info from the 
        ArrayLists.
        */
        
        for (int i = 0; i < 3; i++) {
            
            // switches files and updates them with the final arrays 
            String DestinationFile = "Country" +  String.valueOf(i) + ".txt";   
            File file = new File(DestinationFile);
            
            // finds the file directy that String DestinationFile is at
            FileWriter fWriter = new FileWriter(file.getAbsolutePath());
            PrintWriter outputFile = new PrintWriter(fWriter);

            switch (i) { 
                case 0: // updates Country1.txt
                    outputFile.println("morocco"); // states destination at top
                    fillTxtF(flight1, outputFile); // updates with arrays
                    break;
                case 1: // updates Country2txt
                    outputFile.println("banff");   // states destination at top
                    fillTxtF(flight2, outputFile); // updates with arrays
                    break;
                case 2: // updates Country3.txt
                    outputFile.println("iceland"); // states destination at top
                    fillTxtF(flight3, outputFile); // updates with arrays
                    break;
            } // switch ends
        } // for loop ends   
        
    } // main method ends    
    
    /**
     * Method Name: mainMenu
     * Description: This is the main menu where the user is able to access all
     * commands needed to utilize the program
     * @param flight1 // Represents flight to Morocco
     * @param flight2 // Represents flight to Banff
     * @param flight3 // Represents flight to Iceland
     * @return gameEnd // boolean that makes loop stop or repeat
     * @throws java.lang.InterruptedException // add delays in time
     */
    public static boolean mainMenu(ArrayList<Passenger> flight1, 
            ArrayList<Passenger> flight2, ArrayList<Passenger> flight3) 
            throws InterruptedException {
        
        String optionPicked; // store option picked
        boolean errorTrap;  // for error trapping
        boolean errorTrap1; // for error trapping
        boolean gameEnd = false; // sends back to main loop to stop it or loop
        String destination; // store destination
        
        do { 
            errorTrap = false; // avoid infinite loop
            out.print("\n   MENU - please select by #"
                    + "\n\n1) Edit existing passenger info"
                    + "\n2) Add passenger"
                    + "\n3) Remove passenger"
                    + "\n4) View Company Info"
                    + "\n5) View all passengers"
                    + "\n6) Exit --> ");
            optionPicked = scanS.nextLine().toLowerCase();// store answer in LC
        
            switch (optionPicked) // looks at what task you want to do
            {
                case "1": // wants to edit passenger info
                    do { // for error trapping
                        errorTrap1 = false;
                        destination = destinationFinder(); // get destination
                        /* 
                        this code sends the appropriate flight depending on 
                        the location sent by the destinationFinder()
                        */
                        switch (destination) { 
                            case "1":
                            case "morocco":
                                editPassInfo(flight1);
                                break;
                            case "2":
                            case "banff":
                                editPassInfo(flight2);
                                break;
                            case "3":
                            case "iceland":
                                editPassInfo(flight3);
                                break;
                            default:
                                errorTrap1 = true;
                                break;
                        }
                    } while (errorTrap1 == true);
                    break;
                case "2": // wants to add a new passenger
                    // calls addPassenger array and sends down the flights
                    addPassenger(flight1, flight2, flight3);
                    break;
                case "3": // wants to delete passenger
                    destination = destinationFinder(); // gets destination
                    switch (destination) {
                        case "1": // destination = morocco
                        case "morocco":
                            deletePassenger(flight1); // delete
                            break;
                        case "2": // destion = banff
                        case "banff":
                            deletePassenger(flight2); // delete
                            break;
                        case "3": // destination = iceLand
                        case "iceland":
                            deletePassenger(flight3); // delete
                            break;
                    }
                    break;
                case "4":
                    companyInfo(flight1, flight2, flight3); // shows finances
                    break;
                case "5":
                    do {
                        errorTrap1 = false;
                        out.println("\nWhich flight do you want to view?"
                                + "\nselect the destination below");
                        destination = destinationFinder();
                        switch (destination) { 
                            case "1":
                            case "morocco":
                                destination = "morocco";
                                displayFlight(flight1, destination);
                                break;
                            case "2":
                            case "banff":
                                destination = "banff";
                                displayFlight(flight2, destination);
                                break;
                            case "3":
                            case "iceland":
                                destination = "iceland";
                                displayFlight(flight3, destination);
                                break;
                            default:
                                errorTrap1 = true;
                                break;
                        }
                    } while (errorTrap1 == true);
                    break;
                case "6":
                    gameEnd = true; // ends game after updating files in main
                    break;
                default:
                    out.println("Invalid option");
                    errorTrap = true; // repeats question
                    break;
            }
        } while (errorTrap == true);
        return gameEnd; // returns boolean that lets the loop repeat or break
    } 
    
    /**
     * Method Name: editPassInfo
     * Description: User has the ability to edit whatever they want of a 
     * specific passenger
     * @param flight // the flight that is sent down
     * @throws java.lang.InterruptedException // for time delay
     */
    public static void editPassInfo(ArrayList<Passenger> flight) throws 
            InterruptedException {
        
        if (flight.isEmpty()) {
            out.println("\nThere are no passengers yet");
        } else {
        
            Passenger updatedPassenger; // holds updated passenger info
            int sNOP = binarySearch(flight); // returns seat Num Of Passenger
            int objI = 0; // set too -1 to avoid any potential mixups
            String val = "4"; // used to add spaces in string card
            String editChoice; // what the user would like to edit

            boolean errorTrap;  // for error trapping                                   // remove later??
            boolean errorTrap2; // for error trapping
            boolean errorTrap3; // for error trapping
            boolean errorTrap4; // for error trapping
            
            for (int i = 0; i < flight.size(); i++) {
                if (flight.get(i).getSeat() == sNOP) {
                    objI = i; 
                    break; // break out of loop we found match
                }
            }

            // saves all the existing code of the passenger
            String name = flight.get(objI).getName(); 
            String email = flight.get(objI).getEmail();
            String card = flight.get(objI).cardInfo();
            String phone = flight.get(objI).getphoneNum();
            int seat = flight.get(objI).getSeat();

            do {
                errorTrap3 = false; // avoid infinite loop
                out.print("\nWhat would you like too edit?"
                        + "\n1. Name"
                        + "\n2. Email"
                        + "\n3. Card #"
                        + "\n4. Phone #"
                        + "\n5. Seat # --> ");
                editChoice = scanS.nextLine().toLowerCase();
                
                switch (editChoice) {
                    case "1":
                        do {
                            errorTrap = false; // avoid infinite loop
                            out.print("Enter a new name -> ");
                            name = scanS.nextLine().toLowerCase();
                            if (name.matches(".*\\d.*")) { // if a int is in string
                                 out.println("\nName can not have numbers!");
                                 errorTrap = true;
                            }
                        } while (errorTrap == true);
                        break;
                    case "2":
                        out.println("\nEnter a new email -> ");
                        email = scanS.nextLine().toLowerCase();
                        break;
                    case "3":
                        do { // for error trapping
                            errorTrap4 = false;
                            out.print("\nCard #: "); 
                            card = scanS.nextLine().toLowerCase();

                            if (card.length() > 16 || card.length() <= 15) {
                                System.out.println("A card has exactly 16 "
                                        + "digits try again");
                                errorTrap4 = true;
                            }
                        } while(errorTrap4 == true);

                        // splits the string every 4 chars to match card # form
                        card = card.replaceAll("(.{" + val + "})", "$1 ").trim();                         
                        break;
                    case "4":
                        do {
                            errorTrap2 = false; // avoid infinite loop
                            out.print("\nEnter a new phone number -> ");
                            phone = scanS.nextLine().toLowerCase();                        

                            if (phone.length() > 10 || phone.length() <= 9) {
                                System.out.println("wrong format"
                                        + "enter entire number with no "
                                        + "spaces or dashs (10 characters)"
                                        + "\nfor example : 1234567890");
                                errorTrap2 = true;
                            } else {
                                phone = phone.replaceFirst("(\\d{3})(\\d{3})"
                                        + "(\\d+)", "($1) $2-$3");
                            }
                        } while (errorTrap2 == true);
                        break;
                    case "5":
                        seat = planeSeats(flight);
                        break;
                    default:
                        out.println("Invalid Input");
                        errorTrap3 = true;
                }

            } while (errorTrap3 == true);
            
            out.println("Processing...");
            Thread.sleep(500); // wait .5 seconds
            out.println("System Updated");
            Thread.sleep(500); // wait .5 seconds

            updatedPassenger = new Passenger (name, email, card, phone, seat);
            flight.set(objI, updatedPassenger);
        }
    }
    
    /**
     * Method Name: deletePassenger
     * Description: This method is able to find a passenger in a flight using 
     * binary search and then will delete him/her from the arrayList.
     * @param flight // represents flight that is passed down
     * @throws java.lang.InterruptedException // for time delays
     */
    public static void deletePassenger(ArrayList<Passenger> flight) throws 
            InterruptedException{
        
        sortPassList(flight); // sorts the ArrayList A-Z before binary search
        int sNOP = binarySearch(flight); // 
        for (int i = 0; i < flight.size(); i++) {
            if (flight.get(i).getSeat() == sNOP) {
                flight.remove(i);  // removes passenger obj at index
                break; // break out of loop we found match
            }
        }
        
        out.println("Processing...");
        Thread.sleep(500); // wait .5 seconds
        out.println("System Updated");
        Thread.sleep(500);
    }
    
    /**
     * Method Name: BinarySearch
     * Description: This method uses binary search to find a passenger in the
     * ArrayList that is sent down from other parts of the program
     * @param flight // can be any flight sent down to find field in
     * @return // this returns the seat of the passenger 
     */
    public static int binarySearch(ArrayList<Passenger> flight) {
        
        /*
        This array stores the objects of all the repeated names in the array
        and allows the user to specificy which passenger they want to accesss
        */
        ArrayList<Passenger> storedRepeats = new ArrayList<>(); 
        
        int fI; // The first index value
        int mI; // The midpoint of the search
        int lI; // The last index value
        int seatNumOfPassenger = 0; // Saves the index of the matching seat
        
        boolean lowRF,highRF; // changes if repeated value below/above found 
        boolean valueFound = false; // if binary search finds the right num
        boolean errorTrap1, errorTrap2; // for error trapping 
        
        do { // loops if the passenger name is not found in the array
            
            fI = 0; // first index value
            lI = flight.size() - 1; // last index value
            int moveD, innerH; // Save values and scans surrounding for repeat
            
            errorTrap2 = false; // avoid infinite loop
            
            out.println("\nWhat is the name of this passenger?");
            String target = scanS.nextLine().toLowerCase();

            /*
            The bianry search is repeatedly looped untill a value is found or
            the firstIndex becomes less than the last index which signals that
            no name was found
            */
            while (fI <= lI && !valueFound) { 

                mI = (fI+lI) / 2; // find midpoint

                moveD = mI; // sets equal to the middle
                innerH = mI; // sets equal to the middle

                if (flight.get(mI).getName().compareToIgnoreCase(target) == 0) {   
                      
                    seatNumOfPassenger = flight.get(mI).getSeat();// saves 
                    valueFound = true; // if value is found or not
                    storedRepeats.add(flight.get(mI)); // gets obj at index

                    do { // check for other names that equal too target 
                        // subtract 1 from the middle to check for duplicates
                        lowRF = true;
                        moveD --; // minus one
                        
                        if (moveD < 0)
                            lowRF = false; //stop loop
                        else if (lowRF == true && flight.get(moveD).getName()
                                .compareTo(target) == 0) 
                            storedRepeats.add(flight.get(moveD));//stores value                  
                        else
                            lowRF = false; //stop loop

                    } while(lowRF == true);

                    do { // check for other names that equal too target
                        // adding 1 from the middle to check for other matches
                        highRF = true;
                        innerH++; // add one
                        
                        if (innerH >= flight.size()) { 
                            highRF = false;
                        } else if (highRF == true && flight.get(innerH).getName()
                                .compareTo(target) == 0) {
                            storedRepeats.add(flight.get(innerH));//stores value
                        } else
                            highRF = false; //stop loop
                    } while(highRF == true);

                } 
                else if (flight.get(mI).getName().compareToIgnoreCase(target)> 0) {
                    // Move boundary to exclude everything from middle higher
                    lI = mI - 1; //  moves the boundry of our search
                } 
                else if (flight.get(mI).getName().compareToIgnoreCase(target)< 0) {
                    // Move boundary to exclude everything from the middle lower
                    fI = mI + 1; // moves the boundry of our search
                }
            } // binary search ends

            if (storedRepeats.size() > 1) { 
                do {
                    errorTrap1 = false; // avoid infinite loop
                    
                    out.println(storedRepeats.toString()); // shows repeats
                    /*
                    Note: displaying names would be more user friendly, however
                    there is no purpose to showing just the names because user 
                    will probably know what user to delete or edit because they
                    are in this section to do just that
                    */
                    out.print("\n\n\nwhich " + target + " do you want to select"
                            + "? \nPlease enter their seat number --> ");
                    int seatPicked = scanS.nextInt();

                    for (int i = 0; i < storedRepeats.size(); i++) {
                        if (storedRepeats.get(i).getSeat() == seatPicked) {
                            seatNumOfPassenger = storedRepeats.get(i).getSeat();
                            errorTrap1 = false;
                            break; // found match now break out of loop
                        }
                        else 
                            errorTrap1 = true; // incorrect seatNumber picked
                    } // loop ending

                    if (errorTrap1 == true)
                        out.println("that seat is not any of the options"
                                + " Plese enter again");
                } while (errorTrap1 == true);
            }

            if (valueFound == false) {
                out.println("\nNo passenger goes by that name!");  
                errorTrap2 = true;
            }
        } while (errorTrap2 == true);
        return (seatNumOfPassenger); // returns seat number
    }
    
    /**
     * Method Name: addPassenger
     * Description: This method is able to add passengers to a flight 
     * @param Flight1 // Represents flight to Morocco
     * @param Flight2 // Represents flight to Banff
     * @param Flight3 // Represents flight to Iceland
     * @throws java.lang.InterruptedException // add pauses
     */
    public static void addPassenger(ArrayList<Passenger> Flight1, 
            ArrayList<Passenger> Flight2, ArrayList<Passenger> Flight3) throws 
            InterruptedException {
        
        /* 
        name of passenger
        email of passenger
        destination of passenger
        card of passenger
        number of passenger
        passenger seat
        */
        String name, email, destination, card, phone; 
        String val = "4"; // this is used to split card into 4 equal parts
        
        int seat; // seat of passenger
        boolean errorTrap;  // for error trapping
        boolean errorTrap2; // for error trapping
        boolean errorTrap3; // for error trapping
        
        out.println("\n--Enter Information below--");
        
        // gets name
        do { // for error trapping loop 
            errorTrap2 = false; // avoid infinite loop
            out.print("\nName: ");
            name = scanS.nextLine().toLowerCase();
            if (name.matches(".*\\d.*")) { // if number is present in the string
                out.println("\nName can not have numbers!");
                errorTrap2 = true;
            }
        } while (errorTrap2 == true);
        
        // gets email
        out.print("\nEmail: "); 
        email = scanS.nextLine().toLowerCase();
        
        do { // for error trapping && gets card #
            errorTrap3 = false;
            out.print("\nCard #: "); 
            card = scanS.nextLine().toLowerCase();

            if (card.length() > 16 || card.length() <= 15) {
                System.out.println("\nA card has exactly 16 digits try again");
                errorTrap3 = true;
            }
        } while(errorTrap3 == true);
        
        // splits the string every 4 digits to resemble a credit card #
        card = card.replaceAll("(.{" + val + "})", "$1 ").trim(); 
        
        do { // gets phone #
            errorTrap = false; // avoid infinite loop
            out.print("\nEnter phone number -> ");
            phone = scanS.nextLine().toLowerCase();                        

            if (phone.length() <= 9) {
                System.out.println("wrong format "
                        + "enter entire number with no "
                        + "spaces or dashs (10 characters)"
                        + "\nfor example : 1234567890");
                errorTrap = true;
            } else {
                // adds brackets on first 3 digits and adds dashes between #'s
                phone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
            }
        } while (errorTrap == true); // errorTrap ends
        
        do { // gets seats and adds new Passenger Object
            // loop is for error trapping
            errorTrap = false; // avoid infinite loop
            destination = destinationFinder();
            
            // assigns seat and adds to correct ArrayList
            switch (destination) { 
                case "1":
                case "morocco":
                    // check seats on flight - morocco, adds passenger to Array
                    seat = planeSeats(Flight1); // seat = to return of method
                    Flight1.add(new Passenger(name, email, card, phone, seat));
                    break;
                case "2":
                case "banff":
                    // check seats on flight - banff, adds passenger to Array
                    seat = planeSeats(Flight2); // seat = to return of method
                    Flight2.add(new Passenger(name, email, card, phone, seat));
                    break;
                case "3":
                case "iceland":
                    // check seats on flight - iceland, adds passenger to Array
                    seat = planeSeats(Flight3); // seat = to return of method
                    Flight3.add(new Passenger(name, email, card, phone, seat));
                    break;
                default:
                    out.println("Invalid choice try again");
                    errorTrap = true; // error redo code
                    break;
            } // switch ending
        } while (errorTrap == true); // loop ends for new seat and new passenger
        
        out.print("\nProcessing.... ");
        Thread.sleep(500); // wait .5 seconds
        out.print("... Data Base Updated");
        Thread.sleep(500); // wait.5 seconds
        out.println("\nPress enter to return to main menu");
        scanS.nextLine();

    } // method ends 
    
    /**
     * Method Name: planeSeats
     * Description: This method takes the array passed down that could be any
     * of the three flights, it checks what seats are taken and creates a matrix
     * of the seats inside and then prints out a GUI that prompts the user to 
     * select one of the seats.
     * @param flight // represents flight Array sent down from AddPassenger
     * @return seat // returns seat that the new passenger has picked.
     */
    public static int planeSeats(ArrayList<Passenger> flight) {
        
        String[][] planeSeats = new String[10][3]; // plane seats matrix
        
        int count = 0; // tracker to cycle through matrix
        int seat; // to return the chosen seat number
        boolean errorTrap; // for error trapping
        
        /*
        The following loop cycles through the array checking which seats are 
        taken and if they are it replaces that seat with an X to repersent it
        is taken. 
        */
        for (String[] planeSeat : planeSeats) { 
            for (int j = 0; j < planeSeat.length; j++) {
                
                planeSeat[j] = String.valueOf(count); // convert to String
                
                for (int f = 0; f < flight.size(); f++) {
                    if (flight.get(f).getSeat() == count) 
                        planeSeat[j] = "X"; // replace # with X
                }
                count++; // adds 1
            }
        }
        
        // the 3 print statements print the matrix in a tidy manner
        
        out.println("\nSeat View on Flight");
        out.println("_____________________\n");
        for (String[] planeSeat1 : planeSeats) { 
            for (String planeSeat : planeSeat1) 
                out.print(planeSeat + "\t");
            out.println("");
        }
        out.println("_____________________\n");
        
        // logic that asks for user to select a seat
        do { // error trapping
            errorTrap = false; // avoid infinite loop
            out.print("\nSelect a seat: ");
            seat = scanN.nextInt(); // storing size
            
            if (seat > 29 || seat < 0) { // seat wasn't between 0-29 so redo 
                System.out.println("\nThat seat isn't listed! Try again");
                errorTrap = true; 
            } else {
                // runs through matrix checking is anyone else has the same seat
                for (int i = 0; i < flight.size(); i++) {
                    if (flight.get(i).getSeat() == seat) {
                        // matching seat found, redo.
                        out.println("\nInvalid Input");
                        errorTrap = true; // run do while again
                        break;
                    } // if ends
                } // for loop ends       
            } // if.. else.. chain ends
        } while (errorTrap == true); // do while loop ends
        return seat; // returns integer of seat user picked
    } 
    
    
    
    // these are all the safe ones reserve for last check
    
    /**
     * Method Name: fillTxtF
     * Description: This method just updates the 3 text files. each Text File
     * represents a flight and stores its passengers, EXISTS to simplify main
     * method which was becoming congested
     * @param flight // the flight array sent down
     * @param outputFile // too output the code
     * @throws IOException // for Java.IO related stuff
     */
    public static void fillTxtF(ArrayList<Passenger> flight, 
            PrintWriter outputFile) throws IOException {
        
        for (int x = 0; x < flight.size(); x++) { // outputs ever field to txtF
            outputFile.println(flight.get(x).getName() + ", "
                    + flight.get(x).getEmail() + ", " 
                    + flight.get(x).cardInfo() + ", " 
                    + flight.get(x).getphoneNum() + ", "
                    + flight.get(x).getSeat());
        }
        
        outputFile.close(); // closes from adding new info aka saves file
    } 
   
    /**
     * Method Name: sortPassList
     * Description: Uses selection sort to sort array array alphabetically, this
     * is useful for the binary search method.
     * @param flight // the flight array that is passed down to sort
     */
    public static void sortPassList(ArrayList<Passenger> flight) {
        
        Passenger minValue; // stores the value at min index
        int minIndex; // stores the mininum index
        
        // uses selection sort to sort array.
        for (int i = 0; i < flight.size()-1; i++) {

            minValue = flight.get(i); // gets name of the object at lowest index
            minIndex = i; // stores this lowest index

            for (int j = i+1; j < flight.size(); j++) { // compares to next val
                // if first string comes first alphabetically...
                if (flight.get(j).getName().compareToIgnoreCase(minValue.getName()) < 0) {
                    minValue = flight.get(j); // saves value at index j
                    minIndex = j; // saves index 
                }
            }
            flight.set(minIndex, flight.get(i)); // switching places
            flight.set(i, minValue); // switching places
        }
    } 
    
    /**
     * Method Name: companyInfo
     * Description: calculates costs and prints out company info
     * @param flight1 // flight to Morocco
     * @param flight2 // flight to Banff
     * @param flight3 // flight to Iceland
     * @throws java.lang.InterruptedException // add time delay
     */
    public static void companyInfo(ArrayList<Passenger> flight1, 
            ArrayList<Passenger> flight2, ArrayList<Passenger> flight3) throws 
            InterruptedException {
            
        int oP = 0; // Operating Costs
        int tR; // total revenue

        int tP; // total cost of all flights
        
        /*
        I could also use for loops here but was not sure if that would matter 
        since I am only initializing 3 variables not familiar with how many I
        would have to initilize before a for loop becomes a better option!
        */
        
        int tP1 = 0; // profit from ticket sales (Morocco)
        int tP2 = 0; // profit from ticket sales (Banff)
        int tP3 = 0; // profit from ticket sales (Iceland)

        int pC1 = 0; // passenger care costs for flight 1
        int pC2 = 0; // passenger care costs for flight 2
        int pC3 = 0; // passenger care costs for flight 3

        int fC1 = 2000; // cost of fuel before passengers for Morocco
        int fC2 = 500;  // cost of fuel before passengers for Banff
        int fC3 = 1500; // cost of fuel before passengers for Iceland
        
        for (Passenger flight11 : flight1) {
            tP1 += 800; // adds $800 for each passenger
            pC1 += 58; // costs $58 to take care of each passenger (morocco)
            fC1 += 25; // each additional passenger adds $35 worth of fuel
        }

        for (Passenger flight21 : flight2) {
            tP2 += 500; // adds $500 for each passenger 
            pC2 += 25; // costs $25 to take care of each passenger (Banff)
            fC2 += 10; // each additional passenger adds $35 worth of fuel
        }

        for (Passenger flight31 : flight3) {
            tP3 += 1200; // adds 1200 for each passenger
            pC3 += 68; // costs $68 to take care of each passenger (Iceland)
            fC3 += 35; // each additional passenger adds $35 worth of fuel
        }
        
        tP = tP1 + tP2 + tP3; // calculate profit generated from flights
        tR = tP - oP; // calculates the total revenue

        // prints everything in a pretty format for the user to interpet
        out.println("\n---------------------------------------------"
                + "------");
        out.println("| Pearson Canada Airline Revenue Report Break down|"
                + "");
        out.println("-----------------------------------------------"
                + "----");
        Thread.sleep(500); // .5 second delay
        out.println("Flight 1: Toronto \t -> \t\t  Morocco |"
                + "\n\t\t\t\t\t\t  |"
                + "\nPassenger care costs: \t$" + pC1 + "\t\t\t  |"
                + "\nFuel costs: \t\t$" + fC1 + "\t\t\t  |"
                + "\nRevenue from flight: \t$" + tP1 + "\t\t\t  |");
        Thread.sleep(500); // .5 second delay
        out.println("-----------------------------------------------"
                + "----");

        out.println("Flight 3: Toronto \t -> \t\t  Banff   |"
                + "\n\t\t\t\t\t\t  |"
                + "\nPassenger care costs: \t$" + pC2 + "\t\t\t  |"
                + "\nFuel costs: \t\t$" + fC2 + "\t\t\t  |"
                + "\nRevenue from flight: \t$" + tP2 + "\t\t\t  |");
        Thread.sleep(500); // .5 second delay
        out.println("-----------------------------------------------"
                + "----");

        out.println("Flight 3: Toronto \t -> \t\t  Iceland |"
                + "\n\t\t\t\t\t\t  |"
                + "\nPassenger care costs: \t$" + pC3 + "\t\t\t  |"
                + "\nFuel costs: \t\t$" + fC3 + "\t\t\t  |"
                + "\nRevenue from flight: \t$" + tP3 + "\t\t\t  |");
        Thread.sleep(500); // .5 second delay
        out.println("-----------------------------------------------"
                + "----");
        out.println("| Your Company's Total Revenue is: $" + tR 
                + "\t  |");
        out.println("-----------------------------------------------"
                + "----");

        out.println("\nPress any key to return to main menu");
        scanS.nextLine();
    }
    
    /**
     * MethodName: destinationFinder
     * Description: This statement was needed 3 times, better to just make a 
     * method that returns the destination, allowing the program to manipulate
     * however it pleases
     * @return destination // returns string of destination user picked
     */
    public static String destinationFinder() {
        out.print("\nSelect destination"
            + "\n1) Morocco"
            + "\n2) Banff"
            + "\n3) Iceland --> ");
        String destination = scanS.nextLine().toLowerCase();
        return destination; // returns the destination in a string format
    }
    
    /**
     * Method Name: displayFlight
     * Description: This method displays all the passengers in a flight
     * @param flight // holds flight array that is sent down from mainMenu
     * @param destination // 
     */
    public static void displayFlight(ArrayList<Passenger> flight, 
            String destination) {
        out.println("\n---------------------------------------------------");
        System.out.println("\tDisplaying Passengers en-route " + destination);
        out.println("---------------------------------------------------\n");        
        /* 
        this for loop gets every field of a single passenger, doing this is 
        longer but avoid brackets improving USER friendliness.
        */
        for (int i = 0; i < flight.size(); i++) {
            System.out.println("\nName: " + flight.get(i).getName());
            System.out.println("Email " + flight.get(i).getEmail());
            System.out.println("Card #: " + flight.get(i).cardInfo());
            System.out.println("Phone #: " + flight.get(i).getphoneNum());
            System.out.println("Assigned Seat: seat " + flight.get(i).getSeat());
        }
        
        System.out.println("\npress any key to return to main menu");
        scanS.nextLine(); // waits for key 
    }
    
}
