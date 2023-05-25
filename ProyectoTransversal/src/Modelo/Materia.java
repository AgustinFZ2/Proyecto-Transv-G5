/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Sergio
 */
public class Materia {
    private int idMateria;
    private String nombre;
    private int anio;
    private int estado;

    public Materia() {
    }

    public Materia(String nombre, int anio, int estado) {
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    public Materia(int idMateria, String nombre, int anio, int estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        JOptionPane.showMessageDialog(null, "ID: "+ idMateria+ "Nombre: "+ nombre);
        return "Materia{" + "idMateria = " + idMateria + ", nombre=" + nombre + ", anio=" + anio + ", estado=" + estado + '}';
    }
    
    
}
