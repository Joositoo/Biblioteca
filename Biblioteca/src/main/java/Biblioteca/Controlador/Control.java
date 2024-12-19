package Biblioteca.Controlador;

import Biblioteca.Modelo.DAO;
import Biblioteca.Modelo.Usuario;
import Biblioteca.Vista.Consola;
import jakarta.persistence.NoResultException;

public class Control {
    static Consola consola = new Consola();

    public void existeUsuario(String correo, String password){
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
                consola.menuInicio();
            }
        }
    }

    public void tipoUsuario(Usuario u){
        Consola consola = new Consola();

        if (u.getTipo().equals("normal")){
            consola.menuUsuarioNormal(u);
        }
        else{
            consola.menuAdmin(u);
        }
    }
}
