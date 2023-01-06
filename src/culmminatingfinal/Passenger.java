/**
 * Programmer Name: Rayaan Khan
 * Date: 2020-10-22
 * Project: CulminatingFinal
 * Class: Passenger
 * Description: Creates object that holds attributes for a person on  a flight
 */

package culmminatingfinal;

public class Passenger {
    
    //fields
    private String name;
    private String email;
    private String cardInfo;
    private String phoneNum;
    private int seat;
    
    //no-arg constructor (default settings)
    public Passenger() 
    {
        name = "No Name"; // default name
        email = "noe@mail.com"; // deafault email
        cardInfo = "0000-0000-0000-0000"; // default card 3
        phoneNum = "000-000-0000"; // default number
        seat = -1; // avoid any errors with seat 0
    }

    //arg constructor
    public Passenger(String n, String e, String c, String p, int s) 
    {
        name = n;
        email = e;
        cardInfo = c;
        phoneNum = p;
        seat = s;
    }

    //Accessors
    // returns the name field
    public String getName() {
        return name;
    }

    // returns the email field
    public String getEmail() {
        return email;
    }

    // returns the cardInfo field
    public String cardInfo() {
        return cardInfo;
    }
    
    // returns the phoneNum field
    public String getphoneNum() {
        return phoneNum;
    }
    
    // returns the sseat field
    public int getSeat() {
        return seat;
    }
    
    //Mutator
    // assigns a value 'n' field
    public void setName(String n) {
        name = n;
    }
    
    // assigns a value 'e' field
    public void setEmail(String e) {
        email = e;
    }
    
    // assigns a value 'c' field
    public void setCardInfo(String c) {
        cardInfo = c;
    }
    
    // assigns a value 'p' field
    public void setPhoneNum(String p) {
        phoneNum = p;
    }
    
    // assigns a value 's' field
    public void setSeat(int s) {
        seat = s;
    }

    /**
     * Method Name: toString
     * Description: creates a String representing object state  
     * @return str // returns string which can be printed to show all info
     */
    @Override
    public String toString() {
        
        String str = "\n\nName: " + name + "\nEmail: " + email + "\nCard: " 
                + cardInfo + "\nNumber: " + phoneNum + "\nSeat: " + seat;
        return str;
    }
}
    
