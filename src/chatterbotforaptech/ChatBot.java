
package chatterbotforaptech;

import java.sql.SQLException;
import java.util.Scanner;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

//Main class to run the chatbot
public class ChatBot 
{
    
    //main function
    public static void main(String[] args)
    {
        // variables to store the user data -> name, emailid 
        String name;
        String emailid;
        String mobno;
        String location;
        Scanner sc = new Scanner(System.in);
        
        // some welcome text
        System.out.println("*******************************");
        System.out.println("With Aptech Learning, "
                + "\nget job oriented programs in"
                + "\n1. Digital and IT"
                + "\n2. Hardware and networking"
                + "\n3. Banking and Finance"
                + "\n4. English and Communication");
        System.out.println("-------------------------------");
        
        System.out.println("Confused on selecting the right course?"
                + "\nFill the enquiry details and our counsellors will get in touch with you.");
        System.out.println("-------------------------------");
        
        
        //  establish connection with the database used class ConnectJDBC
        ConnectJDBC j = new ConnectJDBC();
        try 
        {
            j.connectToDatabase();
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error Connnecting Database.");
            //Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create an object of the validation class to use validation methods
        validation v = new validation();
        
        // ask the user for name and store in variable 
        System.out.print("Please enter your name: ");
        name = sc.next();
        
        do
        {
            // ask the user for email and store in variable
            System.out.print("Please enter your Email ID: ");
            emailid = sc.next();
        }
        while(!v.validateEmailid(emailid));//  validate the email id until user enters correct one
        
        do
        {
            // ask the user for mobile no and store in variable
            System.out.print("Please enter your mobile no: ");
            mobno = sc.next();
        }
        while(!v.validateMobNo(mobno)); //  validate the mobile no until user enters correct one
        
        // ask the user for location and store in variable
        System.out.print("Please enter your location: ");
        location = sc.next();
        
        // insert the data into the table of connected database using the insertToDatabase function
        try 
        {
            j.insertToDatabase(name, emailid, mobno, location);
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error while uploading data");
            //Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // some message
        System.out.println("Thank you "+name);
        System.out.println("Now we will connect you with our AI Counsellor....");
        
        try {
            //create a delay for readability
            Thread.sleep(3000);
            } catch (InterruptedException ex) {
            ///Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("something went wrong");
        }
        
        
        // connect to the chatbot usinf class connect chat bot
        ConnnectChatBot b = new ConnnectChatBot();
        b.connectToAICounsellor();
        
        //get the chatsession using getChatSession getter
        Chat chatsession = b.getChatSession();
        
        // variable to store the user input
        String textline;
        
        
        //some text
        System.out.println("--------------------------------");
        System.out.println("Hi "+name+"!!"+"Ask something!"+" enter 'q' to exit");
        
        // flag for suggestions status
        //int chatcount = 1;
        String suggestion = "Hi";
        SuggestionGenerator g = new SuggestionGenerator(chatsession);
        
        while(true)
        {
            
            //take the user inputand store it in the textline
            System.out.println("\nSugggestions:\n"+suggestion);
            suggestion = g.createSuggestions(suggestion);
            System.out.print("\n"+name+" -> ");
            textline = IOUtils.readInputTextLine();
            
            // check for invalid input from user
            if((textline == null) || (textline.length()<1))
            {
                textline = MagicStrings.null_input;
            }
            
            // check for exit request
            if(textline.equals("q"))
            {
                System.out.println("\nRobo -> "+"Thank you "+name+"! It was a great conversation.");
                break;
            }
            
            //store the modified user input into new variable
            String request = textline;
            
            if(MagicBooleans.trace_mode)
            {
                System.out.println("STATE="+request+":THAT="+((History)
                        chatsession.thatHistory.get(0)).get(0)+":TOPIC="+
                        chatsession.predicates.get("topic"));
            }
            // get response from the chat bot and store it in the response String
            String response = chatsession.multisentenceRespond(request);
            
            // filter the response for entities
            while(response.contains("&lt;"))
            {
                response = response.replace("&lt;", "<");
            }
            while(response.contains("&gt;"))
            {
                response = response.replace("&gt;", ">");
            }
            
            //print the response
            System.out.println("\nRobo -> "+response);
            
        }
    }
}
