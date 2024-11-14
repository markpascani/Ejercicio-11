/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class Alumno implements Serializable{
    @JsonProperty
    private int nia;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellidos;
    @JsonProperty
    private char genero;
    @JsonProperty
    private LocalDate fechaNacimiento;
    @JsonProperty
    private String ciclo;
    @JsonProperty
    private String curso;
    @JsonProperty
    private String grupo;

    public Alumno(int nia, String nombre, String apellidos, char genero, LocalDate fechaNacimiento, String ciclo, String curso, String grupo) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
        this.curso = curso;
        this.grupo = grupo;
    }
    
    
}
