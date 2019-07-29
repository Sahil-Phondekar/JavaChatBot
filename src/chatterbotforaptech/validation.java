
package chatterbotforaptech;

import java.util.regex.Pattern;

//class to validate the user entered information
public class validation {
    
    //this method is called to validate the email id
    //returns true if the email is valid
    public boolean validateEmailid(String email)
    {
        //String variable to store the pattern of the email using regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                +"[a-zA-Z0-9_+&*-]+)*@"+
                "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                "A-Z]{2,7}$";
        
        //Now we will define the above pattern in the Pattern object using compile()
        Pattern pat = Pattern.compile(emailRegex);
        //check if the user entered email matches with pattern and returns boolean true if matches 
        if(pat.matcher(email).matches())
        {
            return true;
        }
        else
        {
            System.out.println("Invalid email entered!!!");
            return false;
        }
    }
    
    //this method is called to validate the mobile no
    //returns true if the mob no is valid
    // rules-> first digit ->0 to 9
    //         remaining nine -> 0 to 9 
    //         before first digit there may be -> 0 or 91
    //         total no of digits ->10 or 11 or 12
    public boolean validateMobNo(String mob)
    {
        //String variable to store the pattern of the mob no using regular expression
        String mobRegex = "(0/91)?[7-9][0-9]{9}";
        
        //Now we will define the above pattern in the Pattern object using compile()
        Pattern pat = Pattern.compile(mobRegex);
        
        //check if the user entered number matches with pattern and returns boolean true if matches 
        if(pat.matcher(mob).matches())
        {
            return true;
        }
        else
        {
            System.out.println("Invalid mobile number!!!");
            return false;
        }
    }
    
}
