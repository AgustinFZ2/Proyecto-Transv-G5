/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package AccesoADatos;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;*/

/*
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
*/
/**
 *
 * @author Sergio
 */
//public class Prueba {

    /**
     * @param args the command line arguments
     */
 /*   public static void main(String[] args){
        try {
            //Class.forName("org.mariadb.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad","root", "");
            //String sql="INSERT INTO alumno(nombre,apellido,dni,fechaNacimiento,estado) VALUES ('Ricardo','Fort',22222220,2000-01-19,1)";
            
            //Prueba con otro driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad","root","");
            String sql="INSERT INTO alumno(nombre,apellido,dni,fechaNacimiento,estado) VALUES ('Ricardo','Fort',28350410,'2000-01-19',1)";
            
            PreparedStatement ps= con.prepareStatement(sql);
            
            int resultado;
            resultado = ps.executeUpdate();
            if(resultado>0){
            JOptionPane.showMessageDialog(null,"Alumno agregado");
            }
            else{
            JOptionPane.showMessageDialog(null,"El alumno ya estaba cargado en la BD");
            }
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los Drivers");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la BD");
        }
        
    }
    
}
*/