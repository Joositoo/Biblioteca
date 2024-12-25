package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Ejemplar;
import Biblioteca.Modelo.Prestamo;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;

import java.time.LocalDate;
import java.util.List;
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
            case 3:
                eliminaPrestamo();
                break;
            case 4:
                verPrestamos();
                break;
            case 5:
                seleccionaPrestamo();
                break;
            case 6:
                Consola.menuAdmin();
                break;
            case 7:
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

        if (usuario.getPenalizacionHasta().isAfter(LocalDate.now())) {
            System.out.println("No puedes realizar ningún prestamo, tienes una penalización hasta el día " +usuario.getPenalizacionHasta());
            Consola.menuPrestamos();
            return;
        }

        if (!ejemplar.getEstado().equals("Prestado")){
            ejemplar.setEstado("Prestado");

            if (usuario.getPrestamos().size() < 3){
                Prestamo prestamo = new Prestamo(usuario, ejemplar, fechaInicio, fechaDevolucion);
                usuario.getPrestamos().add(prestamo);

                daoEjemplar.update(ejemplar);
                daoPrestamo.insert(prestamo);
            }
            else{
                System.out.println(usuario.getNombre()+ " ya cuenta con 3 prestamos");
            }
        }
        else {
            System.out.println("El libro ya está prestado");
        }

        Consola.menuPrestamos();
    }

    public static void modificaPrestamo() {
        int id;
        int idEjemplar;

        DAO<Prestamo, Integer> daoPrestamo = new DAO<>(Prestamo.class, Integer.class);
        DAO<Ejemplar, Integer> daoEjemplar = new DAO<>(Ejemplar.class, Integer.class);

        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("*        MODIFICAR PRÉSTAMO             *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.println("                                         ");
        System.out.println("Introduce el id del préstamo a modificar: ");
        id = scann.nextInt();

        System.out.println("Introduce el id del ejemplar nuevo: ");
        idEjemplar = scann.nextInt();

        Ejemplar ejemplar = daoEjemplar.findById(idEjemplar);
        ejemplar.setEstado("Prestado");
        daoEjemplar.update(ejemplar);

        Prestamo prestamo = daoPrestamo.findById(id);
        prestamo.getEjemplar().setEstado("Disponible");
        daoPrestamo.update(prestamo);

        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(15));

        daoPrestamo.update(prestamo);

        Consola.menuPrestamos();
    }

    public static void eliminaPrestamo() {
        int id;
        LocalDate fechaDevolucion = LocalDate.now();
        DAO<Prestamo, Integer> daoPrestamo = new DAO<>(Prestamo.class, Integer.class);
        DAO<Usuario, Integer> daoUsuario = new DAO<>(Usuario.class, Integer.class);

        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("*         ELIMINAR PRÉSTAMO             *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.println("                                         ");
        System.out.print("Indica el id del préstamo a eliminar: ");
        id = scann.nextInt();

        Prestamo prestamo = daoPrestamo.findById(id);

        prestamo.getEjemplar().setEstado("Disponible");

        daoPrestamo.update(prestamo);

        if (fechaDevolucion.isAfter(prestamo.getFechaDevolucion())){

            System.out.println(prestamo.getUsuario().getNombre()+ "Tiene una penalización de 15 días");
            if (prestamo.getUsuario().getPenalizacionHasta() == null){
                prestamo.getUsuario().setPenalizacionHasta(LocalDate.now().plusDays(15));
            }
            else {
                LocalDate penalizacion = prestamo.getUsuario().getPenalizacionHasta();
                prestamo.getUsuario().setPenalizacionHasta(penalizacion.plusDays(15));
            }
            daoUsuario.update(prestamo.getUsuario());
            daoPrestamo.update(prestamo);
        }
        prestamo.getUsuario().getPrestamos().remove(prestamo);

        daoUsuario.update(prestamo.getUsuario());
        daoPrestamo.delete(prestamo);

        Consola.menuPrestamos();
    }

    public static void verPrestamos() {
        DAO<Prestamo, Integer> daoPrestamo = new DAO<>(Prestamo.class, Integer.class);
        List<Prestamo> listaPrestamos;

        listaPrestamos = daoPrestamo.findAll();

        System.out.println("*****************************************");
        System.out.println("*                                       *");
        System.out.println("*         LISTA DE PRÉSTAMOS            *");
        System.out.println("*                                       *");
        System.out.println("*****************************************");
        System.out.println("                                         ");

        for (Prestamo p : listaPrestamos) {
            System.out.println(p);
        }

        Consola.menuPrestamos();
    }

    public static void seleccionaPrestamo() {
        int id;
        DAO<Prestamo, Integer> daoPrestamo = new DAO<>(Prestamo.class, Integer.class);

        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("*         SELECCIONA PRÉSTAMO            *");
        System.out.println("*                                        *");
        System.out.println("******************************************");
        System.out.println("                                          ");
        System.out.print("Indica el id del préstamo: ");
        id = scann.nextInt();

        Prestamo prestamo = daoPrestamo.findById(id);

        System.out.println(prestamo);

        Consola.menuPrestamos();
    }
}
