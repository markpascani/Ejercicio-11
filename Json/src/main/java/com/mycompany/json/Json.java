/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alumno
 */
public class Json {

    public static void main(String[] args) {
        
        //Binario
        GestionFichero gf = new GestionFichero(true);
        List<Alumno> alumnos =new  ArrayList<>();
        Alumno alumn1 = new Alumno(1, "Mihai", "Stinga", 'H', LocalDate.of(2000, 1, 12), "DAM", "2","B" );
        Alumno alumn2= new Alumno(2, "Mihai2", "Stinga2", 'H', LocalDate.of(2000, 7, 12), "DAM", "2","B" );
        Alumno alumn3 = new Alumno(3, "Mihai3", "Stinga3", 'H', LocalDate.of(2000, 6, 12), "DAM", "2","A" );
        Alumno alumn4 = new Alumno(4, "Mihai4", "Stinga4", 'H', LocalDate.of(2000, 5, 12), "DAM", "2","A" );
        
        
        alumnos.add(alumn1);
        alumnos.add(alumn2);
        alumnos.add(alumn3);
        alumnos.add(alumn4);
        
        gf.escribirFicheroBinario(alumnos);
        
        //Json
        GestionFichero gf1 = new GestionFichero(false);
        gf1.leerFicheroJson();
      
        
        
    }
}
