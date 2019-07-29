/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbotforaptech;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

/**
 *
 * @author Dell
 */
public class EnquiryForm {
    void enquiryPage()
    {
        JFrame f1 = new JFrame("Enquiry Form");
        f1.setSize(500,700);
        f1.setLayout(null);
        f1.setVisible(true);
        
        JLabel l1 = new JLabel("Name:");
        l1.setBounds(10, 10, 150, 30);
        JTextField t1 = new JTextField();
        t1.setBounds(170, 10, 300, 30);
        
        JLabel l2 = new JLabel("E-mail:");
        l2.setBounds(10, 50, 150, 30);
        JTextField t2 = new JTextField();
        t2.setBounds(170, 50, 300, 30);
        
        JLabel l3 = new JLabel("Mobile-no:");
        l3.setBounds(10, 90, 150, 30);
        JTextField t3 = new JTextField();
        t3.setBounds(170, 90, 300, 30);
        
        JLabel l4 = new JLabel("Location:");
        l4.setBounds(10, 130, 150, 30);
        JTextField t4 = new JTextField();
        t4.setBounds(170, 130, 300, 30);
        
        JButton b = new JButton("SUBMIT");
        b.setBounds(150, 170, 150, 30);
        
        JLabel l5 = new JLabel();
        l5.setBounds(150, 210, 150, 30);
        
        f1.add(l1);
        f1.add(t1);
        
        f1.add(l2);
        f1.add(t2);
        
        f1.add(l3);
        f1.add(t3);
        
        f1.add(l4);
        f1.add(t4);
        
        f1.add(b);
        
        f1.add(l5);
        
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                String name = t1.getText();
                String emailid = t2.getText();
                String mobno = t3.getText();
                String location = t4.getText();
                // Create an object of the validation class to use validation methods
                validation v = new validation();
                
                if(!v.validateEmailid(emailid))
                {
                    l5.setText("enter valid email");
                }
                else if(!v.validateMobNo(mobno))
                {
                    l5.setText("enter valid mobile no");
                }
                else if(name.isEmpty() | location.isEmpty())
                {
                    l5.setText("All fields are compulsary");
                }
                else{
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
                    try 
                    {
                        j.insertToDatabase(name, emailid, mobno, location);
                    } 
                    catch (SQLException ex) 
                    {
                        System.out.println("Error while uploading data");
                        //Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    l5.setText("Form Submitted");
                    
                    
                }
            }
        });
    }
    
    String suggestion = "Hi";
    
    void robot()
    {
        JFrame f2 = new JFrame("Chat");
        f2.setSize(500,700);
        f2.setLayout(null);
        f2.setVisible(true);
        
        JLabel l1 = new JLabel();
        l1.setBounds(10, 10, 300, 100);
        l1.setBorder(BorderFactory.createLineBorder(Color.green));
        
        JLabel l2 = new JLabel();
        l2.setBounds(170, 140, 300, 100);
        l2.setBorder(BorderFactory.createLineBorder(Color.green));
        
        JLabel l3 = new JLabel();
        l3.setBounds(10, 270, 460, 50);
        l3.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        JTextArea T1 = new JTextArea();
        T1.setBounds(10,350,400,50);
        
        JButton b1 = new JButton(">");
        b1.setBounds(420, 350, 50, 50);
        
        f2.add(l1);
        f2.add(l2);
        f2.add(l3);
        f2.add(T1);
        f2.add(b1);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // connect to the chatbot usinf class connect chat bot
        ConnnectChatBot b = new ConnnectChatBot();
        b.connectToAICounsellor();
        
        //get the chatsession using getChatSession getter
        Chat chatsession = b.getChatSession();
        
        // variable to store the user input
        //String textline;
        
        
        SuggestionGenerator g = new SuggestionGenerator(chatsession);
        
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    //take the user inputand store it in the textline
                    //System.out.println("\nSugggestions:\n"+suggestion);
                    suggestion = g.createSuggestions(suggestion);
                    l3.setText("Suggestion: "+suggestion);
                    //System.out.print("\n"+name+" -> ");
                    
                    //textline = IOUtils.readInputTextLine();
                    String textline = T1.getText();
                    if(!textline.isEmpty())
                    {
                        
                        // check for invalid input from user
                        if((textline == null) || (textline.length()<1))
                        {
                            textline = MagicStrings.null_input;
                        }

                        // check for exit request
                        if(textline.equals("q"))
                        {
                            //System.out.println("\nRobo -> "+"Thank you "+name+"! It was a great conversation.");
                            l1.setText("Thank You! It was a great conversation");
                            System.exit(0);
                        }

                        //store the modified user input into new variable
                        String request = textline;
                        l2.setText(request);

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
                        //System.out.println("\nRobo -> "+response);
                        l1.setText(response);
                    }
            }
                    
        });
        
    }
    
    public static void main(String[] args) {
        EnquiryForm ef = new EnquiryForm();
        //ef.enquiryPage();
        ef.robot();
    }
    
}
