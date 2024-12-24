package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Ejemplar;
import Biblioteca.Modelo.Libro;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;

import java.util.List;
import java.util.Scanner;

public class ControlUsuario {
    static Scanner scann = new Scanner(System.in);

    public static void gestionUsuarios(int i){
        switch (i){
            case 1:
                registraUsuario();
                break;
            case 2:
                modificaUsuario();
                break;
            case 3:
                eliminaUsuario();
                break;
            case 4:
                verUsuarios();
                break;
            case 5:
                seleccionaUsuario();
                break;
            case 6:
                registraPenaliz();
                break;
            case 7:
                Consola.menuAdmin();
                break;
            case 8:
                break;
        }
    }

    public static void registraUsuario(){
        String dni;
        String nombre;
        String email;
        String password;
        String tipo;

        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        REGISTRAR USUARIO            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");
        System.out.print("Introduce el dni del usuario: ");
        dni = scann.nextLine();

        System.out.print("Introduce el nombre del usuario: ");
        nombre = scann.nextLine();

        System.out.print("Introduce el email del usuario: ");
        email = scann.nextLine();

        System.out.print("Introduce la contraseña del usuario: ");
        password = scann.nextLine();


        do {
            System.out.print("Indica el tipo del usuario (normal | administrador): ");
            tipo = scann.nextLine();
        }
        while (!tipo.equals("normal") && !tipo.equals("administrador"));

        Usuario usuario = new Usuario(dni, nombre, email, password, tipo);

        daoUsuario.insert(usuario);

        Consola.menuGestionUsuarios();
    }

    public static void modificaUsuario(){
        int id;
        String dni;
        String nombre;
        String email;
        String password;
        String tipo;
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        MODIFICAR USUARIO             *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        System.out.print("Introduce el id del usuario a modificar: ");
        id = scann.nextInt();
        scann.nextLine();

        System.out.print("Introduce el dni del usuario: ");
        dni = scann.nextLine();

        System.out.print("Introduce el nombre del usuario: ");
        nombre = scann.nextLine();

        System.out.print("Introduce el email del usuario: ");
        email = scann.nextLine();

        System.out.print("Introduce la contraseña del usuario: ");
        password = scann.nextLine();


        do {
            System.out.print("Indica el tipo del usuario (normal | administrador): ");
            tipo = scann.nextLine();
        }
        while (!tipo.equals("normal") && !tipo.equals("administrador"));

        Usuario usuario = daoUsuario.findById(id);

        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipo(tipo);

        daoUsuario.update(usuario);

        Consola.menuGestionUsuarios();
    }

    public static void eliminaUsuario(){
        int id;
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*         ELIMINAR USUARIO             *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        System.out.print("Introduce el id del usuario a eliminar: ");
        id = scann.nextInt();

        Usuario usuario = daoUsuario.findById(id);

        daoUsuario.delete(usuario);

        Consola.menuGestionUsuarios();
    }

    public static void verUsuarios(){
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);
        List<Usuario> listaUsuarios;

        listaUsuarios = daoUsuario.findAll();

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*         LISTA DE USUARIOS            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");

        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }

        Consola.menuGestionUsuarios();
    }

    public static void seleccionaUsuario(){
        int id;
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);

        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("*         SELECCIONA USUARIO             *");
        System.out.println("*                                        *");
        System.out.println("******************************************");
        System.out.println("                                          ");
        System.out.print("Indica el id del ejemplar: ");
        id = scann.nextInt();

        Usuario usuario = daoUsuario.findById(id);
        System.out.println(usuario+ "\n");

        Consola.menuGestionUsuarios();
    }

    public static void registraPenaliz(){

    }

}
