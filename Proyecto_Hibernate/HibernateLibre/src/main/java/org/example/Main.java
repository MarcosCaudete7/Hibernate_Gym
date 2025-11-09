package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.model.Clase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GimnasioPU");
        EntityManager em = emf.createEntityManager();
        Interacciones interacciones = new Interacciones(em);

        Scanner sc = new Scanner(System.in);
        System.out.println("Comandos disponibles");
        System.out.println("Mostrar Entrenador           -> lista entrenadores");
        System.out.println("Mostrar -r Entrenador        -> lista entrenadores y sus clases");
        System.out.println("Añadir Clase                 -> añade una clase con entrenador = null");
        System.out.println("Añadir -r Clase              -> añade una clase y asigna/crea entrenador");
        System.out.println("Salir                        -> salir");
        while (true) {
            String opcion = sc.nextLine();
            if(opcion.equals("Salir")){
                break;
            } else if (opcion.equalsIgnoreCase("Mostrar Entrenador")) {
                interacciones.mostrarEntrenadores(false);
            } else if (opcion.equalsIgnoreCase("Mostrar -r entrenador")) {
                interacciones.mostrarEntrenadores(true);

            } else if (opcion.equalsIgnoreCase("añadir clase")) {
                interacciones.añadirClase(false);
            } else if (opcion.equalsIgnoreCase("añadir -r clase")) {
                interacciones.añadirClase(true);

            } else{
                System.err.println("Opcion no valida");
            }

        }
    }
}