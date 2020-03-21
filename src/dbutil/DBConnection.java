package dbutil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    static{
        try{
//            Class.forName("com.mysql.jdbc.Driver");
          conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Medical","root","test");

        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Problem in Database");
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

