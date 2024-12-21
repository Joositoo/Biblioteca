package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Libro;
import Biblioteca.Vista.Consola;

import java.util.List;
import java.util.Scanner;

public class ControlLibro {
    static Scanner scann = new Scanner(System.in);
    public static void gestionLibros(int i){
        switch (i){
            case 1:
                registraLibro();
                break;
            case 2:
                modificarLibro();
                break;
            case 3:
                eliminaLibro();
                break;
            case 4:
                verLibros();
                break;
            case 5:
                seleccionarLibro();
                break;
            case 6:
                Consola.menuAdmin();
                break;
            case 7:
                break;
            default:
                System.out.println("Opcion no valida");
                Consola.menuLibros();
        }
    }

    public static void registraLibro(){
        String isbn;
        String titulo;
        String autor;

        System.out.println("*************************************");
        System.out.println("*                                   *");
        System.out.println("*        REGISTRAR LIBRO            *");
        System.out.println("*                                   *");
        System.out.println("*************************************");
        System.out.println("                                     ");

        System.out.print("Introduce el ISBN13 del libro: ");
        isbn = scann.nextLine();

        System.out.print("Introduce el titulo del libro: ");
        titulo = scann.nextLine();

        System.out.print("Introduce el autor del libro: ");
        autor = scann.nextLine();

        Libro libro = new Libro(isbn, titulo, autor);

        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);
        daoLibro.insert(libro);

        Consola.menuLibros();
    }

    public static void modificarLibro(){
        String isbn;
        String titulo;
        String autor;

        System.out.println("*************************************");
        System.out.println("*                                   *");
        System.out.println("*        MODIFICAR LIBRO            *");
        System.out.println("*                                   *");
        System.out.println("*************************************");
        System.out.println("                                     ");

        System.out.print("Introduce el ISBN13 del libro a modificar: ");
        isbn = scann.nextLine();

        System.out.print("Introduce el titulo del libro: ");
        titulo = scann.nextLine();

        System.out.print("Introduce el autor del libro: ");
        autor = scann.nextLine();

        Libro libro = new Libro(isbn, titulo, autor);

        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);
        daoLibro.update(libro);

        Consola.menuLibros();
    }

    public static void eliminaLibro(){
        String isbn;

        System.out.println("*************************************");
        System.out.println("*                                   *");
        System.out.println("*         ELIMINAR LIBRO            *");
        System.out.println("*                                   *");
        System.out.println("*************************************");
        System.out.println("                                     ");

        System.out.print("Introduce el ISBN13 del libro a eliminar: ");
        isbn = scann.nextLine();

        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);
        Libro libro = daoLibro.findByString(isbn);

        daoLibro.delete(libro);

        Consola.menuLibros();
    }

    public static void verLibros(){
        List<Libro> listaLibros = null;

        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);
        listaLibros = daoLibro.findAll();

        System.out.println("**************************************");
        System.out.println("*                                    *");
        System.out.println("*         LISTA DE LIBROS            *");
        System.out.println("*                                    *");
        System.out.println("**************************************");
        System.out.println("                                      ");

        for (Libro libro : listaLibros){
            System.out.println(libro);
        }

        Consola.menuLibros();
    }

    public static void seleccionarLibro(){
        String isbn;
        DAO<Libro, Integer> daoLibro = new DAO<>(Libro.class, Integer.class);

        System.out.println("***************************************");
        System.out.println("*                                     *");
        System.out.println("*         SELECCIONA LIBRO            *");
        System.out.println("*                                     *");
        System.out.println("***************************************");
        System.out.println("                                       ");
        System.out.print("Indica el isbn del libro: ");
        isbn = scann.nextLine();

        Libro libro = daoLibro.findByString(isbn);

        System.out.println("El libro es: \n" +libro+ "\n");

        Consola.menuLibros();
    }
}