
package chatterbotforaptech;

import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicBooleans;

//Java Class to add custom aiml fils to the chat bots brain
public class ChatterBotForAptech {

    private static final boolean TRACE_MODE = false ;
    
    public static void main(String[] args) 
    {
        try
        {
            //path for the aiml and chatbot files
            String resource_path = "E:\\MY FILES\\APTECH COURSE\\JAVA\\ChatterBotForAptech\\resources";
            MagicBooleans.trace_mode = TRACE_MODE;
            //create a bot with name super which has all its files and aiml docs at the resource_path location
            Bot bot = new Bot("super",resource_path);
            /*once the bot is created if there is any user defined aiml file in the aiml folder we need convert 
            that file into other aiml formats (like .aiml.csv). This is done by following line */
            bot.writeAIMLFiles();
        }
        catch(Exception e)
        {
            System.out.println("error ading memory");
        }
    }
    
}
