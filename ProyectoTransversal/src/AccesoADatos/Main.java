/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;
//package Modelo;


import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
//import AccesoADatos.AlumnoData;
//import AccesoADatos.Conexion;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        LocalDate fecha= LocalDate.of(1982,06,15);
        
        Alumno a= new Alumno(26500400,"Riquelme","Roman",fecha,1);
        Connection c = null;
        c= Conexion.getConexion();
        AlumnoData alum= new AlumnoData();
        //alum.guardarAlumno(a);
        MateriaData mate= new MateriaData();
        ArrayList<Materia> materiastabla= mate.listarMaterias();
        materiastabla.forEach(mat -> {
            System.out.println(mat.toString());
        });
        
        InscripcionData insdata= new InscripcionData();
        ArrayList<Inscripcion> inscripto= insdata.obtenerInscripcionesPorAlumno(3);
        inscripto.forEach(insc -> {
            System.out.println("Inscripciones por alumno "+insc.toString());
        });
        
        ArrayList<Materia> matNoCursadas= insdata.obtenerMateriasNoCursadas(3);
        matNoCursadas.forEach(matSinCursar ->{
            System.out.println("Materias no cursadas por el alumno: "+ matSinCursar.getNombre());
        });
        
        insdata.borrarInscripcionMateriaAlumno(16, 12);
        ArrayList<Alumno> alumnosMateria= insdata.obtenerAlumnosXMateria(2);
        alumnosMateria.forEach( alumMat -> {
            System.out.println("Alumnos por Materia: "+ alumMat.toString());
        });
        
        
        Materia m= new Materia("Desarrollo",2,1);
        MateriaData md= new MateriaData();
        //md.guardarMateria(m);
        Materia buscamateria= new Materia();
        buscamateria= md.buscarMateria("Ingles");
        System.out.println(buscamateria.toString());
        buscamateria.toString();
        float nota= 0;
        a= alum.buscarAlumnoPorDni(28350410);
        System.out.println("IdAlumno: "+ a.getIdAlumno());
        System.out.println("Datos: "+ a.toString());
        System.out.println("Alumno: "+ a.toString());
        m= md.buscarMateria("Desarrollo");
        System.out.println("IdMateria: "+ m.getIdMateria());
        /*List<Materia> materias= md.listarMaterias();
        materias.forEach((mat) -> {
            System.out.println(mat.toString());
        });*/
        
        InscripcionData ins= new InscripcionData();
        Inscripcion cursada= new Inscripcion();
        
        
        //cursada.setIdAlumno(a.getIdAlumno());
        //cursada.setIdMateria(m.getIdMateria());
        cursada.setNota(nota);
        cursada.setIdAlumno(3);
        cursada.setIdMateria(5);
        //ins.obtenerInscripciones();
        List<Inscripcion> inscripciones;
        inscripciones = ins.obtenerInscripcionesPorAlumno(3);
        inscripciones.forEach(insc ->{
            insc.toString();
        });
        
        ins.obtenerMateriasCursadas(3);
         //int p= ins.verificarInscripcion(cursada);
        //System.out.println("Valor de p: "+p);
        //JOptionPane.showMessageDialog(null, p);
        /*if(!ins.verificarInscripcion(cursada)){
          ins.guardarInscripcion(cursada);
          JOptionPane.showMessageDialog(null, "El alumno se pudo inscribir en la materia");
        }
        */
        /*if(p==0){
           ins.inscripcionMateria(cursada);
            System.out.println("Se pudo inscribir");
            JOptionPane.showMessageDialog(null, "El alumno se pudo inscribir en esa materia");
        }
        else{
            System.out.println("No se puede inscribir a ese alumno en esa materia");
            JOptionPane.showMessageDialog(null, "El alumno no se pudo inscribir en esa materia");
        }*/
        }
        
        
        
        
        
    
    


    }
    

