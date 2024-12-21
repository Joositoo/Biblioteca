package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;
import jakarta.persistence.NoResultException;

import java.util.Scanner;

public class Control {

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
