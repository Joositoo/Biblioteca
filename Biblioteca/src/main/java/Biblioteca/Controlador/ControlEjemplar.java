package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Ejemplar;
import Biblioteca.Modelo.Libro;
import Biblioteca.Vista.Consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlEjemplar {
    static Scanner scann = new Scanner(System.in);

    public static void gestionEjemplares(int i) {
        switch (i) {
            case 1:
                registraEjemplar();
                break;
            case 2:
                modificaEjemplar();
                break;
            case 3:
                eliminaEjemplar();
                break;
            case 4:
                verEjemplares();
                break;
            case 5:
                seleccionaEjemplar();
                break;
            case 6:
                controlarStock();
                break;
            case 7:
                Consola.menuAdmin();
                break;
            case 8:
                break;
            default:
                System.out.println("Opcion no valida");
                Consola.menuLibros();
        }
    }

    public static void registraEjemplar() {
        String isbn;
        String estado;
        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        REGISTRAR EJEMPLAR            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");
        System.out.print("Introduce el isbn del ejemplar: ");
        isbn = scann.nextLine();

        Libro libro = daoLibro.findByString(isbn);

        do {
            System.out.print("Indica el estado del ejemplar (Disponible | Prestado | Dañado): ");
            estado = scann.nextLine();
        }
        while (!estado.equals("Disponible") && !estado.equals("Prestado") && !estado.equals("Dañado"));

        Ejemplar ejemplar = new Ejemplar(libro, estado);

        daoEjemplar.insert(ejemplar);

        Consola.menuEjemplares();
    }

    public static void modificaEjemplar() {
        int id;
        String estado;
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        MODIFICAR EJEMPLAR            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        System.out.print("Introduce el id del ejemplar a modificar: ");
        id = scann.nextInt();
        scann.nextLine();

        do {
            System.out.print("Indica el estado del ejemplar (Disponible | Prestado | Dañado): ");
            estado = scann.nextLine();
        }
        while (!estado.equals("Disponible") && !estado.equals("Prestado") && !estado.equals("Dañado"));

        Ejemplar ejemplar = daoEjemplar.findById(id);
        ejemplar.setEstado(estado);
        daoEjemplar.update(ejemplar);

        Consola.menuEjemplares();
    }

    public static void eliminaEjemplar() {
        int id;
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*         ELIMINAR EJEMPLAR            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        System.out.print("Introduce el id del ejemplar a eliminar: ");
        id = scann.nextInt();

        Ejemplar ejemplar = daoEjemplar.findById(id);

        daoEjemplar.delete(ejemplar);

        Consola.menuLibros();
    }

    public static void verEjemplares() {
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);
        List<Ejemplar> listaEjemplares;

        listaEjemplares = daoEjemplar.findAll();

        System.out.println("**************************************");
        System.out.println("*                                    *");
        System.out.println("*         LISTA DE LIBROS            *");
        System.out.println("*                                    *");
        System.out.println("**************************************");
        System.out.println("                                      ");

        for (Ejemplar e : listaEjemplares) {
            System.out.println(e);
        }

        Consola.menuEjemplares();
    }

    public static void seleccionaEjemplar() {
        int id;
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("*         SELECCIONA EJEMPLAR            *");
        System.out.println("*                                        *");
        System.out.println("******************************************");
        System.out.println("                                          ");
        System.out.print("Indica el id del ejemplar: ");
        id = scann.nextInt();

        Ejemplar ejemplar = daoEjemplar.findById(id);
        System.out.println(ejemplar+ "\n");

        Consola.menuEjemplares();
    }

    public static void controlarStock() {
        int contador = 0;
        List<Ejemplar> ejemplares;
        List<Ejemplar> ejemplaresDisponibles = new ArrayList<>();
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("*           STOCK EJEMPLARES             *");
        System.out.println("*                                        *");
        System.out.println("******************************************");
        System.out.println("                                          ");

        ejemplares = daoEjemplar.findAll();

        for (Ejemplar e : ejemplares) {
            if (e.getEstado().equals("Disponible")) {
                contador++;
                ejemplaresDisponibles.add(e);
            }
        }

        System.out.println("Hay " +contador+ " ejemplares disponibles: ");

        for (Ejemplar e : ejemplaresDisponibles) {
            System.out.println(e);
        }

        Consola.menuEjemplares();
    }
}