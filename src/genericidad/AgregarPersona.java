package genericidad;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Genericidad.ConexionGen;


public class AgregarPersona {

    // Método para agregar una persona a la base de datos
    public static void agregarPersona(String nombre, Date fechaNacimiento, String eps) {
        Connection con = null;
        PreparedStatement psDatosPersona = null;
        PreparedStatement psSaludPersona = null;
        try {
            con = Operaciondb.getConnection();

            // Insertar en la tabla DatosPersona
            String queryDatosPersona = "INSERT INTO DatosPersona (Nombre) VALUES (?)";
            psDatosPersona = con.prepareStatement(queryDatosPersona, PreparedStatement.RETURN_GENERATED_KEYS);
            psDatosPersona.setString(1, nombre);
            psDatosPersona.executeUpdate();

            // Obtener el ID generado para la nueva persona
            ResultSet rs = psDatosPersona.getGeneratedKeys();
            int personaId = 0;
            if (rs.next()) {
                personaId = rs.getInt(1);
            }

            // Insertar en la tabla SaludPersona
            String querySaludPersona = "INSERT INTO SaludPersona (id, Eps, FechaNacimiento) VALUES (?, ?, ?)";
            psSaludPersona = con.prepareStatement(querySaludPersona);
            psSaludPersona.setInt(1, personaId);
            psSaludPersona.setString(2, eps);
            psSaludPersona.setDate(3, fechaNacimiento);
            psSaludPersona.executeUpdate();

            JOptionPane.showMessageDialog(null, "Persona agregada correctamente.");

            Operaciondb.actualizarEdad(personaId);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la persona: " + e.getMessage());
        } finally {
            try {
                if (psDatosPersona != null) psDatosPersona.close();
                if (psSaludPersona != null) psSaludPersona.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
