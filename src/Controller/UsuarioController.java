package Controller;

import DAO.UsuarioDao;
import Service.UsuarioService;

public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService(UsuarioDao.getInstance());


    public boolean validarUsuario(String nombre, String contrasena) {

        return usuarioService.validarLogin(nombre, contrasena).isValidado();

    }

}
