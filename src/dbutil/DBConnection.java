package dbutil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    static{
        try{
<<<<<<< HEAD
            Class.forName("com.mysql.cj.jdbc.Driver");
=======
            Class.forName("com.mysql.jdbc.Driver");
>>>>>>> 59d80e3ffac5be5484c14e8b9a34781e571f2d7a
          conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Medical","root","ananya7566");

        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null, "Problem in Database");
<<<<<<< HEAD
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null, "Class Not Found");

=======
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
>>>>>>> 59d80e3ffac5be5484c14e8b9a34781e571f2d7a
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection(){
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}

