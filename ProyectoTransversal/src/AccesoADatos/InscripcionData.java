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

import Modelo.Materia;
import Modelo.Alumno;
import Modelo.Inscripcion;
import java.awt.Component;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    Connection con= null;
    
    public InscripcionData(){
        con= Conexion.getConexion();
    }
    
 // public void inscripcionMateria(Materia materia, Alumno alumno, float nota) {
 
  public void guardarInscripcion(Inscripcion cursada) {     
  
  //Inscripcion inscripcion= new Inscripcion();      
  String sql = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, (float) cursada.getNota());
            ps.setInt(2, cursada.getIdAlumno());
            ps.setInt(3, cursada.getIdMateria());
            //ResultSet rs = ps.getGeneratedKeys();
            if(ps.executeUpdate()>0){
              // cursada.setIdInscripcion((int) rs.getFloat("idInscripcion"));
                JOptionPane.showMessageDialog(null, "Inscripcion exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "No se puso inscribir.");
            }
            
            
            //ps.setInt(1,alumno.getIdAlumno());
            //ps.setInt(2, materia.getIdMateria());
            //ps.setDouble(3, inscripcion.getNota()); // if reducido
            //ps.setBoolean(4, materia.isEstado());
            
            //ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //if (rs.next()) {
                //inscripcion.setIdInscripcion(rs.getInt(1));
                
              //  inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                //inscripcion.setIdAlumno(alumno.getIdAlumno());
                //inscripcion.setIdMateria(materia.getIdMateria());
                //inscripcion.setNota(null);
                
                //materia.setIdMateria(rs.getInt(1));
               // JOptionPane.showMessageDialog(null, "Inscripcion exitosa.");
            //} else {
              //  JOptionPane.showMessageDialog(null, "No se puso inscribir.");
            //}

            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex.getMessage());
        }
}
    
    /**
     *
     * @param inscripcion
     * @return
     */
/* public boolean verificarInscripcion(Inscripcion inscripcion){
 String sql= "SELECT * FROM inscripcion WHERE idAlumno= ? AND idMateria= ?;";
 try{
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
         ps.setInt(1,inscripcion.getIdAlumno());
         ps.setInt(2,inscripcion.getIdMateria());
         ps.executeUpdate();
         //ResultSet rs= ps.getGeneratedKeys();
         //if(rs.next()){
         if(ps.executeUpdate()>0){
             JOptionPane.showMessageDialog(null, "El alumno ya está inscripto en esa materia.");
             return true;
             //return 1;
         }
         else{
             JOptionPane.showMessageDialog(null, "Alumno no inscripto en esa materia");
             return false;
             //InscripcionData insc= new InscripcionData();
             //insc.inscripcionMateria(inscripcion);
             //return 0;
         }
     }
 
     catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex);
        }
 
 } 
 */ 
 public void obtenerInscripciones(){
  String sql= "SELECT * FROM inscripcion";
  
    try{
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Inscripcion insc= new Inscripcion();
        insc.setIdInscripcion(rs.getInt("idInscripto"));
        insc.setIdAlumno(rs.getInt("idAlumno"));
        insc.setIdMateria(rs.getInt("idMateria"));
        insc.setNota(rs.getFloat("nota"));
        System.out.println(insc.toString());
        JOptionPane.showMessageDialog(null, "Listado de materias");
        }
      ps.close();
      
    }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex.getMessage());
        }
  
  }
 
 public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int id){
  String sql= "SELECT * FROM inscripcion WHERE idAlumno=?";
  ArrayList<Inscripcion> inscriptas= new ArrayList(); //= null;
  boolean flag=false;
    try{
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Inscripcion insc= new Inscripcion();
        insc.setIdInscripcion(rs.getInt("idInscripto"));
        insc.setIdAlumno(rs.getInt("idAlumno"));
        insc.setIdMateria(rs.getInt("idMateria"));
        insc.setNota(rs.getFloat("nota"));
        inscriptas.add(insc);
        //if(flag){
         System.out.println("Mostrar inscripcion metodo obtener insc por alumno "+insc.toString());
         Component cmpnt = null;
         JOptionPane.showMessageDialog(cmpnt, insc.toString());
        //}
        }
      ps.close();
      
    }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex.getMessage());
        }
    return inscriptas;
  
  }
 
 
 public ArrayList<Alumno> obtenerAlumnosXMateria(int idMateria){
  String sql="SELECT alumno.id, alumno.dni, alumno.apellido, alumno.nombre, alumno.fechaNacimiento, alumno.estado\n" +
              "FROM inscripcion JOIN alumno ON(inscripcion.idAlumno= alumno.idAlumno)\n" +
              "WHERE inscripcion.idMateria= ?;";
  ArrayList<Alumno> listaAlumnosMateria= new ArrayList();
  try{
      PreparedStatement ps= con.prepareStatement(sql);
      ps.setInt(1, idMateria);
      ResultSet rs=  ps.executeQuery();
      if(rs.next()){
       Alumno alu= new Alumno();
       alu.setIdAlumno(rs.getInt("idAlumno"));
       alu.setDni(rs.getInt("dni"));
       alu.setApellido(rs.getString("apellido"));
       alu.setNombre(rs.getString("nombre"));
       alu.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
       alu.setEstado(rs.getInt("estado"));
       listaAlumnosMateria.add(alu);
      }
  }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion"+ex.getMessage());
        }
  return listaAlumnosMateria;
  }
 
 
 public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
  String sql="DELETE FROM `inscripcion` \n" +
              "WHERE idAlumno=? AND idMateria=?";
  try{
   PreparedStatement ps= con.prepareStatement(sql);
   ps.setInt(1, idAlumno);
   ps.setInt(2, idMateria);
   int filas= ps.executeUpdate();
   if(filas>0){
    Component cmpnt = null;
    JOptionPane.showMessageDialog(cmpnt, "Inscripcion eliminada");
   }
   else{
       
     JOptionPane.showMessageDialog(null, "No existía tal inscripción");
   }
   ps.close();
   }
  catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion"+ex.getMessage());
        }
 
 }
 
 public void obtenerMateriasCursadas(int id){
  String sql= "SELECT DISTINCT nombre \n" +
              "FROM `inscripcion` JOIN materia ON(materia.idMateria=inscripcion.idMateria)\n" +
              "WHERE idAlumno= ? \n" +
              "ORDER BY materia.nombre;";
  
    try{
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Materia materia= new Materia();
        materia.setNombre(rs.getString("nombre"));
        //insc.setIdAlumno(rs.getInt("idAlumno"));
        //insc.setIdMateria(rs.getInt("idMateria"));
        //insc.setNota(rs.getFloat("nota"));
        //System.out.println(insc.toString());
        Component cmpnt = null;
        JOptionPane.showMessageDialog(cmpnt, materia.getNombre());
        }
      ps.close();
      
    }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex.getMessage());
        }
  
  }
 
 public ArrayList<Materia> obtenerMateriasNoCursadas(int id){
  String sql= "SELECT DISTINCT nombre FROM materia WHERE materia.nombre NOT IN(SELECT nombre FROM inscripcion JOIN materia ON(inscripcion.idMateria= materia.idMateria) WHERE idAlumno= ?);";
  ArrayList<Materia> noCursadas= new ArrayList();
    try{
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        Materia materia= new Materia();
        materia.setNombre(rs.getString("nombre"));
        //insc.setIdAlumno(rs.getInt("idAlumno"));
        //insc.setIdMateria(rs.getInt("idMateria"));
        //insc.setNota(rs.getFloat("nota"));
        //System.out.println(insc.toString());
        noCursadas.add(materia);
        Component cmpnt = null;
        JOptionPane.showMessageDialog(cmpnt, materia.getNombre());
        }
      ps.close();
      
    }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex.getMessage());
        }
   return noCursadas;
  }
 
 
 public void actualizarNota(Inscripcion inscripcion){
 String sql = "UPDATE inscripcion SET nota= ? WHERE idAlumno= ? AND idMateria= ?";
 //UPDATE `inscripcion` SET `nota`=8
//WHERE idAlumno= 16 AND idMateria=12;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
  
            ps.setInt(1, (int) inscripcion.getNota());
            ps.setInt(2, inscripcion.getIdAlumno());
            //ps.setDouble(3, inscripcion.getNota()); // if reducido
            ps.setInt(3, inscripcion.getIdMateria());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setNota(rs.getInt(1));
                
                //materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Carga de nota exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la nota.");
            }

            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex);
        }
 }   
 
 
 /*public void cargarNota(Materia materia, Alumno alumno, Inscripcion inscripcion){
 String sql = "INSERT INTO inscripcion (IdAlumno, IdMateria, nota) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
  
            ps.setInt(1,alumno.getIdAlumno());
            ps.setInt(2, materia.getIdMateria());
            //ps.setDouble(3, inscripcion.getNota()); // if reducido
            ps.setInt(4, materia.isEstado());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setNota(rs.getInt(1));
                //materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Carga de nota exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la nota.");
            }

            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la inscripcion"+ex);
        }
 }  */ 
    
}





/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package AccesoADatos;

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


/*
public class MateriaData {
            

    private Connection con = null;
    

  public MateriaData() {
        
            con = Conexion.getConexion();
            
        
    }

    
    public Materia buscarMateria(int id) {
        Materia materia = new Materia();
        String sql = "SELECT IdMateria, nombre, anio, estado FROM materia WHERE idMateria=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
             
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setAnio(rs.getInt("anio"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getBoolean("estado"));
               

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
            ps.setBoolean(4,(materia.isEstado()));
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

    public List<Materia> listarAlumnos() {

        List<Materia> materias = new ArrayList<Materia>();
        try {
            String sql = "SELECT * FROM Materia WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();

                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(true);
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


*/
