package Biblioteca.Vista;

import Biblioteca.Controlador.*;
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

    public static void menuLibros() {
        int eleccion;

        System.out.println("**************************************");
        System.out.println("*                                     *");
        System.out.println("*               LIBROS                *");
        System.out.println("*                                     *");
        System.out.println("***************************************");
        System.out.println("*  1. Añadir un  libro                *");
        System.out.println("*  2. Modificar un libro              *");
        System.out.println("*  3. Eliminar un libro               *");
        System.out.println("*  4. Ver todos los libros            *");
        System.out.println("*  5. Seleccionar un libro            *");
        System.out.println("*  6. Volver al menu admin.           *");
        System.out.println("*  7. Cerrar sesión                   *");
        System.out.println("***************************************");

        System.out.println("¿Qué quieres hacer? ");
        eleccion = scann.nextInt();

        ControlLibro.gestionLibros(eleccion);
    }

    public static void menuEjemplares() {
        int eleccion;

        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*               EJEMPLARES                *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*  1. Añadir un  ejemplar                 *");
        System.out.println("*  2. Modificar un ejemplar               *");
        System.out.println("*  3. Eliminar un ejemplar                *");
        System.out.println("*  4. Ver todos los ejemplares            *");
        System.out.println("*  5. Seleccionar un ejemplar             *");
        System.out.println("*  6. Ver el stock disponible             *");
        System.out.println("*  7. Volver al menu admin.               *");
        System.out.println("*  8. Cerrar sesión                       *");
        System.out.println("*******************************************");

        System.out.println("¿Qué quieres hacer? ");
        eleccion = scann.nextInt();

        ControlEjemplar.gestionEjemplares(eleccion);
    }

    public static void menuGestionUsuarios() {
        int eleccion;

        System.out.println("*************************************************");
        System.out.println("*                                               *");
        System.out.println("*               USUARIOS                        *");
        System.out.println("*                                               *");
        System.out.println("*************************************************");
        System.out.println("*  1. Añadir un usuario                         *");
        System.out.println("*  2. Modificar un usuario                      *");
        System.out.println("*  3. Eliminar un usuario                       *");
        System.out.println("*  4. Ver todos los usuario                     *");
        System.out.println("*  5. Seleccionar un usuario                    *");
        System.out.println("*  6. Ver préstamos de un usuario               *");
        System.out.println("*  7. Volver al menu admin.                     *");
        System.out.println("*  8. Cerrar sesión                             *");
        System.out.println("*************************************************");

        System.out.println("¿Qué quieres hacer? ");
        eleccion = scann.nextInt();

        ControlUsuario.gestionUsuarios(eleccion);
    }

    public static void menuPrestamos() {
        int eleccion;

        System.out.println("*******************************************");
        System.out.println("*                                         *");
        System.out.println("*               PRÉSTAMOS                 *");
        System.out.println("*                                         *");
        System.out.println("*******************************************");
        System.out.println("*  1. Añadir un  préstamo                 *");
        System.out.println("*  2. Modificar un préstamo               *");
        System.out.println("*  3. Eliminar un préstamo                *");
        System.out.println("*  4. Ver todos los préstamos             *");
        System.out.println("*  5. Seleccionar un préstamo             *");
        System.out.println("*  6. Volver al menu admin.               *");
        System.out.println("*  7. Cerrar sesión                       *");
        System.out.println("*******************************************");

        System.out.println("¿Qué quieres hacer? ");
        eleccion = scann.nextInt();

        ControlPrestamo.gestionPrestamo(eleccion);
    }
}
