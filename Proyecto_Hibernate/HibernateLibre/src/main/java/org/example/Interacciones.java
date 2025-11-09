package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Dao.ClaseDao;
import org.example.Dao.EntrenadorDao;
import org.example.model.Clase;
import org.example.model.Entrenador;
import org.example.model.Socio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interacciones {
    private final EntityManager em;
    private final ClaseDao claseDao;
    private final EntrenadorDao entrenadorDao;

    public Interacciones(EntityManager em) {
        this.em = em;
        this.claseDao = new ClaseDao(em);
        this.entrenadorDao = new EntrenadorDao(em);
    }

    public void mostrarEntrenadores(boolean esRecursivo) {
        List<Entrenador> listarEntrenadores = entrenadorDao.listarEntrenadores();
        if (listarEntrenadores.isEmpty()) {
            System.err.println("No se han encontrado entrenadores");
            return;
        }
        for (Entrenador entrenador : listarEntrenadores) {
            System.out.printf("Id: %d - Nombre: %s - Especialidad: %s\n", entrenador.getIdEntrenador(), entrenador.getNombre(), entrenador.getEspecialidad());
            if (esRecursivo) {
                if (entrenador.getClases().isEmpty()) {
                    System.err.println("No se han encontrado clases");
                }else {
                    for (Clase clase : entrenador.getClases()) {
                        System.out.println("Clase: " + clase.getNombre() + "Horario: " + clase.getHorario());
                    }
                }
            }
        }
    }

    public void añadirClase(boolean asignaEntrenador) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre de la clase");
        String nombre = sc.nextLine();
        System.out.println("Introduce el horario de la clase");
        String horario = sc.nextLine();
        System.out.println("Introduce el cupo");
        int cupo = Integer.parseInt(sc.nextLine());

        Clase clase = new Clase();
        clase.setNombre(nombre);
        clase.setHorario(horario);
        clase.setCupo(cupo);

        if (!asignaEntrenador) {
            claseDao.insertar(clase);
            System.out.println("Clase insertada sin entrenador con Id: " + clase.getIdClase());
            return;
        }

        List<Entrenador> entrenadores = new ArrayList<>();
        System.out.println("Introduce el id de entrenador. Introduce 0 para asignar un nuevo entrenador");
        if (entrenadores.isEmpty()) {
            System.err.println("No se han encontrado entrenadores");
        } else {
            for (Entrenador entrenador : entrenadores) {
                System.out.println("Id: " + entrenador.getIdEntrenador() + " Nombre: " + entrenador.getNombre() + "Especialidad: " + entrenador.getEspecialidad());
            }
        }
        Long elegido = Long.parseLong(sc.nextLine());
        Entrenador entrenador = new Entrenador();
        if (elegido == 0){
            System.out.println("Creacion de entrenador");
            System.out.println("Introduce el nombre del entrenador");
            String nombreEntrenador = sc.nextLine();
            System.out.println("Introduce la especialidad del entrenador");
            String especialidadEntrenador = sc.nextLine();
            System.out.println("Introduce el telefono del entrenador");
            String telefonoEntrenador = sc.nextLine();
            entrenador = new Entrenador(nombreEntrenador, especialidadEntrenador, telefonoEntrenador);
            entrenadorDao.crearEntrenador(entrenador);
            System.out.println("Entrenador creado con id: " + entrenador.getIdEntrenador());
        }else{
            entrenador = entrenadorDao.buscarEntrenadorPorId(elegido);
            if (entrenador == null) {
                System.err.println("No se encontro entrenador");
                return;
            }
        }
        clase.setEntrenador(entrenador);
        claseDao.insertar(clase);
        System.out.println("Clase creada con ID: " + clase.getIdClase() + " y asignada al entrenador " + entrenador.getNombre());
    }
}
