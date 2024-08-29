/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericidad;

import javax.swing.JOptionPane;

/**
 *
 * @author Andrés
 */
public class TablasGen {
    public static void main(String[] args) {
        try{
            String datosTable = "CREATE TABLE `DatosPersona`(id int AUTO_INCREMENT primary key, Nombre varchar(30), Edad int(3))";
            Operaciondb.setData(datosTable, "Tabla DatosPersona creada correctamente");
            
            String saludTable = "CREATE TABLE `SaludPersona`(id int AUTO_INCREMENT primary key, Eps varchar(30), FechaNacimiento DATE, " +
                                "FOREIGN KEY (id) REFERENCES DatosPersona(id) )";
            Operaciondb.setData(saludTable, "Tabla SaludPersona creada correctamente");
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}