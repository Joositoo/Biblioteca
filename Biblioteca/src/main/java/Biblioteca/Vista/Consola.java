package Biblioteca.Vista;

import Biblioteca.Controlador.Control;
import Biblioteca.Controlador.ControlLibro;
import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Prestamo;
import Biblioteca.Modelo.Usuario;

import java.util.Scanner;
import java.util.Set;

public class Consola {
    static Scanner scann = new Scanner(System.in);

    public static void menuInicio() {

        String correo;
        String password;

        System.out.println("*** INICIO DE SESIÓN ***");
        System.out.print("Introduce tu correo electrónico: ");
        correo = scann.nextLine();

        System.out.print("Introduce tu password: ");
        password = scann.nextLine();

        Control.existeUsuario(correo, password);
    }

    public static void menuUsuarioNormal(Usuario usuario) {
        DAO<Usuario, Integer> daoUsuario = new DAO<Usuario, Integer>(Usuario.class, Integer.class);

        System.out.println("*************************************");
        System.out.println("*                                   *");
        System.out.println("*             USUARIO               *");
        System.out.println("*                                   *");
        System.out.println("*************************************");
        System.out.println("                                     ");
        System.out.println("¡Hola! " + usuario.getNombre()+ ", estos son tus prestamos:\n");

        Set<Prestamo> listaPrestamos = usuario.getPrestamos();
        for (Prestamo prestamo : listaPrestamos) {
            System.out.println(prestamo);
        }


    }

    public static void menuAdmin() {
        int eleccion;

        System.out.println("*************************************");
        System.out.println("*                                   *");
        System.out.println("*          ADMINISTRADOR            *");
        System.out.println("*                                   *");
        System.out.println("*************************************");
        System.out.println("*  1. Gestionar libros              *");
        System.out.println("*  2. Gestionar ejemplares          *");
        System.out.println("*  3. Gestionar usuarios            *");
        System.out.println("*  4. Gestionar préstamos           *");
        System.out.println("*  5. Cerrar sesión                 *");
        System.out.println("*************************************");

        System.out.print("¿Con qué quieres trabajar? Indica un número: ");
        eleccion = scann.nextInt();

        Control.gestionAdmin(eleccion);
    }

    public static void menuLibros(){
        int eleccion;

        System.out.println("**************************************");
        System.out.println("*                                     *");
        System.out.println("*               Libros                *");
        System.out.println("*                                     *");
        System.out.println("***************************************");
        System.out.println("*  1. Añadir un  libro                *");
        System.out.println("*  2. Modificar un libro              *");
        System.out.println("*  3. Eliminar un libro               *");
        System.out.println("*  4. Ver todos los libros            *");
        System.out.println("*  5. Volver al menu admin.           *");
        System.out.println("*  6. Cerrar sesión                   *");
        System.out.println("***************************************");

        System.out.println("¿Qué quieres hacer? ");
        eleccion = scann.nextInt();

        ControlLibro.gestionLibros(eleccion);
    }
}
