/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericidad;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.*;
import Genericidad.ConexionGen;


public class Operaciondb {
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = ConexionGen.getCon();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    public static void setData(String query, String message) {
        try (Connection con = ConexionGen.getCon();
             Statement st = con.createStatement()) {
            st.executeUpdate(query);
            if (!message.equals("")) {
                JOptionPane.showMessageDialog(null, message);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ResultSet getData(String query) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexionGen.getCon();
            st = con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
    }

    // Método para preparar una consulta SQL
    public static PreparedStatement prepareStatement(String query) {
        try {
            Connection con = ConexionGen.getCon();
            return con.prepareStatement(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para obtener todos los IDs de las personas
    public static ResultSet getAllPersonIds() {
        String query = "SELECT id FROM DatosPersona";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexionGen.getCon();
            st = con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para actualizar la edad de una persona
    public static void actualizarEdad(int personaId) {
        String query = "SELECT TIMESTAMPDIFF(YEAR, s.FechaNacimiento, CURDATE()) AS edad_calculada "
                + "FROM SaludPersona s WHERE s.id = ?";
        try (Connection con = ConexionGen.getCon();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, personaId);
            try (ResultSet rs = pst.executeQuery()) {
                int edad = 0;
                if (rs.next()) {
                    edad = rs.getInt("edad_calculada");
                }
                String updateQuery = "UPDATE DatosPersona SET Edad = ? WHERE id = ?";
                try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                    updatePst.setInt(1, edad);
                    updatePst.setInt(2, personaId);
                    updatePst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try (ResultSet rs = getAllPersonIds()) {
            if (rs != null) {
                while (rs.next()) {
                    int personaId = rs.getInt("id");
                    actualizarEdad(personaId);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
