/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class GestionFichero {

    public static final File FICHERO_ALUMNOS_BINARIO = new File("alumnos.dat");
    public static final File FICHERO_ALUMNOS_JSON = new File("alumnos.json");

    public GestionFichero(boolean binario) {
        iniciarFicheroBinario(binario);
    }

    private void iniciarFicheroBinario(boolean binario) {
        if (binario) {

            try {
                if (FICHERO_ALUMNOS_BINARIO.exists()) {
                    System.out.println("Fichero binario inciado correctamente.");
                } else {
                    FICHERO_ALUMNOS_BINARIO.createNewFile();
                    System.out.println("Fichero no existe, se crea un nuevo fichero binario con éxito.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                if (FICHERO_ALUMNOS_JSON.exists()) {
                    System.out.println("Fichero json inciado correctamente.");
                } else {
                    FICHERO_ALUMNOS_JSON.createNewFile();
                    System.out.println("Fichero no existe, se crea un nuevo fichero json con éxito.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Alumno> leerAlumnosDelFicheroBinario() {
        List<Alumno> alumnos = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO_ALUMNOS_BINARIO))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Alumno) {
                        alumnos.add((Alumno) obj);
                    } else {
                        Logger.getLogger(GestionFichero.class.getName()).log(Level.WARNING,
                                "Objeto no esperado en el archivo: " + obj.getClass().getName());
                    }
                } catch (EOFException e) {
                    // Fin del archivo alcanzado, se rompe el bucle
                    break;
                } catch (ClassNotFoundException e) {
                    Logger.getLogger(GestionFichero.class.getName()).log(Level.SEVERE,
                            "Clase 'Alumno' no encontrada durante la deserialización.", e);
                }
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(GestionFichero.class.getName()).log(Level.SEVERE,
                    "Archivo no encontrado: " + FICHERO_ALUMNOS_BINARIO, e);
        } catch (IOException e) {
            Logger.getLogger(GestionFichero.class.getName()).log(Level.SEVERE,
                    "Error de entrada/salida al leer el archivo: " + FICHERO_ALUMNOS_BINARIO, e);
        }

        // Verificar si se cargó algún alumno
        if (alumnos.isEmpty()) {
            Logger.getLogger(GestionFichero.class.getName()).log(Level.WARNING,
                    "No se pudo leer ningún objeto de tipo 'Alumno' desde el archivo: " + FICHERO_ALUMNOS_BINARIO);
        }

        return alumnos;
    }

    public void escribirFicheroBinario(List<Alumno> alumnos) {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(FICHERO_ALUMNOS_BINARIO))) {
            for (Alumno alumno : alumnos) {
                ous.writeObject(alumno);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionFichero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribirFicheroJson(List<Alumno> alumnos) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Soporte para LocalDate y otros tipos de fecha de Java 8
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Opcional: para escribir fechas en formato ISO
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(FICHERO_ALUMNOS_JSON, alumnos);
            System.out.println("Archivo JSON llenado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir archivo Json" + e.getMessage());
        }

    }

}
