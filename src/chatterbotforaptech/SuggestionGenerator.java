/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbotforaptech;

import org.alicebot.ab.Chat;

/**
 *
 * @author Dell
 */
public class SuggestionGenerator {
    
    Chat suggChat;
    
    SuggestionGenerator(Chat chatsession)
    {
        suggChat = chatsession;
    }
    
    String createSuggestions(String userInput)
    {
        String suggestion="suggestion for "+userInput;
        
        
        String response = suggChat.multisentenceRespond(suggestion);
        return response;
    }
}
