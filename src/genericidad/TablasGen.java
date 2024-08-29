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
            String saludTable = "CREATE TABLE `DatosSalud`(id int AUTO_INCREMENT primary key, Nombre varchar(30), Edad int(3), Eps varchar(30), FechaNacimiento DATE )";
            Operaciondb.setData(saludTable, "Tabla creada correctamente");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
