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
//public class MateriaData {
//}


//package AccesoADatos;

/**
 *
 * @author AGUSTIN
 */

import Modelo.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MateriaData {
            

    private Connection con = null;
    

  public MateriaData() {
        
            con = Conexion.getConexion();
            
        
    }

    public void guardarMateria(Materia materia) {

        String sql = "INSERT INTO materia (nombre, anio, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
  
            //ps.setInt(1, materia.getIdMateria());
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio()); // if reducido
            ps.setInt(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "La materia se agrego con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no fue agregada.");
            }

            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la materia"+ex);
        }

}

    public Materia buscarMateria(int id) {
        Materia materia = new Materia();
        String sql = "SELECT idMateria, nombre, anio, estado FROM materia WHERE idMateria = ? AND estado = 1";
        //PreparedStatement ps = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
             
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAnio(rs.getInt("anio"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getInt("estado"));
               

            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
        }

        return materia;
    }

    public Materia buscarMateria(String nombre) {
        Materia materia = new Materia();
        String sql = "SELECT idMateria, nombre, anio, estado FROM materia WHERE nombre = ? AND estado = 1";
        //PreparedStatement ps = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
             
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAnio(rs.getInt("anio"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getInt("estado"));
               

            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la materia");
        }

        return materia;
    }

    public Materia modificarMateria(Materia materia) {

        String sql = "UPDATE materia SET IdMateria = ? , anio = ?, nombre = ?, estado = ? WHERE  idMateria = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, materia.getIdMateria());
            ps.setInt(2, materia.getAnio());
            ps.setString(3, materia.getNombre());
            ps.setInt(4,(materia.isEstado()));
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder la materia");
        }
        return materia;
    }

    public ArrayList<Materia> listarMaterias() {

        ArrayList<Materia> materias = new ArrayList();
        boolean flag;
        try {
            String sql = "SELECT * FROM Materia WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(rs.getInt("estado"));
                System.out.println(materia.toString());
                materias.add(materia);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder la materia");
        }
        return materias;
    }

    public void eliminarMateria(int id) {

        try {
            String sql = "UPDATE materia SET estado = 0 WHERE idMateria = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila=ps.executeUpdate();
            ps.close();
            if(fila==1){
                JOptionPane.showMessageDialog(null, " Se eliminÃ³ la materia");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla de materias");
        }
    }

    public void activarMateria (int id) {

        try {
            String sql = "UPDATE materia SET estado = 1 WHERE idMateria = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila=ps.executeUpdate();
            ps.close();
            if(fila==1){
                JOptionPane.showMessageDialog(null, " Se dio de alta la materia");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla de materias");
        }
    }

}

