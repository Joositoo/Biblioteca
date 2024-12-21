package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Libro;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Scanner;

public class Control {
    static Scanner scann = new Scanner(System.in);

    public static void existeUsuario(String correo, String password){
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);
        Usuario usuario = null;

        try {
            usuario = daoUsuario.findByUniqueValue("email", correo);
        }
        catch (NoResultException e){
            System.out.println("El usuario no existe");
            System.out.println(e.getMessage());
        }

        if (usuario != null){
            if (usuario.getPassword().equals(password)){
                tipoUsuario(usuario);
            }
            else{
                System.out.println("Contraseña incorrecta");
                Consola.menuInicio();
            }
        }
    }

    public static void tipoUsuario(Usuario u){
        if (u.getTipo().equals("normal")){
            Consola.menuUsuarioNormal(u);
        }
        else{
            Consola.menuAdmin();
        }
    }

    public static void gestionAdmin(int i){
        switch (i){
            case 1:
                Consola.menuLibros();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Opcion no valida");
                Consola.menuAdmin();
        }
    }

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
                Consola.menuAdmin();
                break;
            case 6:
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

    /*public static void registrarEjemplar(){
        int id;
        String isbn;
        String estado;

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        REGISTRAR EJEMPLAR            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        System.out.println("Introduce el id del ejemplar");


    }*/
}
