package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Ejemplar;
import Biblioteca.Modelo.Prestamo;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;

import java.time.LocalDate;
import java.util.Scanner;

public class ControlPrestamo {
    static Scanner scann = new Scanner(System.in);

    public static void gestionPrestamo(int i) {
        switch (i){
            case 1:
                registraPrestamo();
                break;
            case 2:
                modificaPrestamo();
                break;
        }
    }

    public static void registraPrestamo() {
        int idUsuario;
        int idEjemplar;
        LocalDate fechaInicio;
        LocalDate fechaDevolucion;
        DAO<Prestamo, Integer> daoPrestamo = new DAO<>(Prestamo.class, Integer.class);
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);
        DAO< Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*        REGISTRAR PRÉSTAMO            *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.println("                                        ");
        System.out.print("Introduce el id del usuario asociado: ");
        idUsuario = scann.nextInt();

        System.out.print("Introduce el id del ejemplar asociado: ");
        idEjemplar = scann.nextInt();

        fechaInicio = LocalDate.now();
        fechaDevolucion = fechaInicio.plusDays(15);

        Usuario usuario = daoUsuario.findById(idUsuario);
        Ejemplar ejemplar = daoEjemplar.findById(idEjemplar);


        Prestamo prestamo = new Prestamo(usuario, ejemplar, fechaInicio, fechaDevolucion);

        daoPrestamo.insert(prestamo);

        Consola.menuPrestamos();
    }

    public static void modificaPrestamo() {}
}
