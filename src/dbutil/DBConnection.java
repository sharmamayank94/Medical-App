package dbutil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
          conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Medical","root","ananya7566");

        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null, "Problem in Database");
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null, "Class Not Found");

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

