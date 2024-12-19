package Biblioteca.Vista;

import Biblioteca.Controlador.Control;
import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Prestamo;
import Biblioteca.Modelo.Usuario;

import java.util.List;
import java.util.Scanner;

public class Consola {
    public void menuInicio() {
        Scanner scann = new Scanner(System.in);
        Control control = new Control();

        String correo;
        String password;

        System.out.println("*** INICIO DE SESIÓN ***");
        System.out.print("Introduce tu correo electrónico: ");
        correo = scann.nextLine();

        System.out.print("Introduce tu password: ");
        password = scann.nextLine();

        control.existeUsuario(correo, password);
    }

    public void menuUsuarioNormal(Usuario usuario) {
        DAO<Usuario, Integer> daoUsuario = new DAO<Usuario, Integer>(Usuario.class, Integer.class);

        System.out.println("======USUARIO======");
        System.out.println("¡Hola! " + usuario.getNombre()+ ", estos son tus prestamos:");

        List<Usuario> listaPrestamos = daoUsuario.findPrestamos(usuario.getId());
        for (Usuario prestamo : listaPrestamos) {
            System.out.println(prestamo);
        }
    }

    public void menuAdmin(Usuario usuario) {
        System.out.println("======Administrador======");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar ejemplar de libro");
        System.out.println("3. Registrar usuario");
        System.out.println("4. Registrar un prestamo");

    }
}
