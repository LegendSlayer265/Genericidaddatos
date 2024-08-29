/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package genericidad;

import java.sql.Connection;
import java.sql.Date;
import genericidad.Operaciondb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import genericidad.ModeloPair.Pair;

public class Genericidad {

    public static void main(String[] args) {

        Connection con = null;
        try {
            con = Operaciondb.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM DatosPersona");

            while (rs.next()) {
                int personaId = rs.getInt("id");
                Operaciondb.actualizarEdad(personaId);
            }

            String nombre = "Paula Gomez";
            Date fechaNacimiento = Date.valueOf("2001-09-11");
            String eps = "La Nueva";
            AgregarPersona.agregarPersona(nombre, fechaNacimiento, eps);

            stmt = con.createStatement();
            rs = stmt.executeQuery(
                "SELECT dp.Nombre, dp.Edad, sp.Eps " +
                "FROM DatosPersona dp " +
                "JOIN SaludPersona sp ON dp.id = sp.id");

            while (rs.next()) {
                String nombrePersona = rs.getString("Nombre");
                int edad = rs.getInt("Edad");
                String epsObtenido = rs.getString("Eps");
                ModeloPair modeloPair = new ModeloPair();
                Pair<Integer, String> Nombre = modeloPair.new Pair<>(edad, nombrePersona);
                Pair<String, String> DatosPersona = modeloPair.new Pair<>(epsObtenido, nombrePersona);
                System.out.println("Persona: " + Nombre.toString() + ", Datos adicionales: " + DatosPersona.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}