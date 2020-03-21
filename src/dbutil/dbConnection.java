package dbutil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static Connection conn;

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Medical","root","test");
            JOptionPane.showMessageDialog(null,"Connection done successfully");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"cannot load driver");
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL exception");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
                JOptionPane.showMessageDialog(null, "Connection closed successfully");
            }
        }
        catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Problem in closing connection");
                e.printStackTrace();
            }

    }

    public static void main(String[] args) {
        System.out.println("connected");
    }
}
