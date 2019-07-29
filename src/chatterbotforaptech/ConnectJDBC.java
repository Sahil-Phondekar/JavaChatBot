
package chatterbotforaptech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//class to connect project with the database
public class ConnectJDBC {
    
    Connection con;
    
    //this method is called to connect with the database 
    void connectToDatabase() throws SQLException
    {
        try 
        {
            //Register drivers
            Class.forName("com.mysql.jdbc.Driver");
            //Establish connection with the created database students
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/students","root","root");
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //this method is used to insert data to database
    void insertToDatabase(String name,String email,String mobno, String location) throws SQLException
    {
        // create a prepared statement to and pass the insert commmand
        PreparedStatement ps;
        ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
        
        //fill in the parameters in above statement
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, mobno);
        ps.setString(4, location);
        ps.setString(5, null);

        // Check for the success of the insert operation
        int status = ps.executeUpdate();
        if(status>0)
        {
            System.out.println("");
        }
        else
        {
            System.out.println("Something went wrong");
        }
    }
    
//    //this method is used to update the database with new entry
//    void updateDatabase(String course, String email) throws SQLException
//    {
//        // create a prepared statement to and pass the update commmand
//        PreparedStatement ps;
//        ps = con.prepareStatement("update student set courses_interested = ? where email_id = ?");
//        
//        //fill in the parameters in above statement
//        ps.setString(1,course);
//        ps.setString(2,email);
//        
//        // Check for the success of the update operation
//        int status = ps.executeUpdate();
//        if(status>0)
//        {
//            System.out.println("");
//        }
//        else
//        {
//            System.out.println("Something went wrong");
//        }
//    }
}
