/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

/**
 *
 * @author Sergio
 */

//package universidadejemplo.AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


    
public class Conexion {
  ///////////////////////////////// "jdbc:mysql://localhost:3306/"
    private static final String URL="mariadb:mysql://localhost:3306/";
    private static final String DB="universidad";
    private static final String USUARIO="root";
    private static final String PASSWORD="";

    private static Connection connection;
   
    
    //Metodo constructor
   
    private Conexion(){}
    
    
public static Connection getConexion() {
        //TENES LA LIBRERIA IMPORTADA? el mariadb.cliente.jar?
       
        if (connection == null) {
            
            try {
//                Class.forName("org.mariadb.jdbc.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
               // connection= DriverManager.getConnection("jdbc:mariadb:mysql://localhost/universidad", "root", "");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
                //connection = DriverManager
                    //    .getConnection(URL+DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                      //          + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la BD con el nuevo driver" + ex);
            }catch(ClassNotFoundException ex){
                
                JOptionPane.showMessageDialog(null, "Error al cargar los Drivers" + ex);
            }
        }
        return connection;
    }

    
}

