
package chatterbotforaptech;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;

//class to connect to chat bot
public class ConnnectChatBot {
    
    private static final boolean TRACE_MODE = false ;
    Bot bot;
    Chat chatSession;


    public Chat getChatSession() {
        return chatSession;
    }
     
    //this method is called when the user request ai counsellor
    void connectToAICounsellor()
    {
        try
        {
            //path for the aiml and chatbot files
            String resource_path = "E:\\MY FILES\\APTECH COURSE\\JAVA\\ChatterBotForAptech\\resources";
            MagicBooleans.trace_mode = TRACE_MODE;
            //create a bot with name super which has all its files and aiml docs at the resource_path location
            bot = new Bot("super",resource_path);
            //Creates a chatsession object with the created bot
            chatSession = new Chat(bot);
            // load the brain of the chat bot with available aiml files
            bot.brain.nodeStats();
            //bot.writeAIMLFiles();
        }
        catch(Exception e)
        {
            System.out.println("Error connecting with AI Counsellor");
        }
    }
}
