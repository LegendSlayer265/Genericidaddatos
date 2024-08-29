/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericidad;

import static Genericidad.ConexionGen.getCon;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Andrés
 */
public class Operaciondb {
    public static void setData(String Query, String message){
        try{
            Connection con = getCon();
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            if(!message.equals(""));
            JOptionPane.showMessageDialog(null,message);
        }
        catch(HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static ResultSet getData(String Query){
        try{
            Connection con = getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            return rs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    } 
     public static Connection getConnection(){
        try{
            return getCon();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static PreparedStatement prepareStatement(String Query){
        try{
            Connection con = getConnection();
            return con.prepareStatement(Query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}