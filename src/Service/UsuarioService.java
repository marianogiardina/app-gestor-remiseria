package Service;

import DAO.UsuarioDao;
import Model.Response;
import Model.Usuario;

public class UsuarioService extends BaseService<Usuario, UsuarioDao> {

    public UsuarioService(UsuarioDao usuarioDao) {
        super(usuarioDao);
    }

    @Override
    public Response<Usuario> create(Usuario obj) {
        return null;
    }

    @Override
    public Response<Usuario> update(Usuario obj) {
       return null;
    }

    @Override
    public Response<Usuario> delete(int id) {
       return null;
    }

    @Override
    public Response<Usuario> read(int id) {
       return null;
    }

    @Override
    public Response<Usuario> readAll() {
        return null;
    }

    public Response<Usuario> validarLogin(String nombre, String contrasena) {

        Boolean usuarioValido = false;

        try{

            //Si la respuesta del metodo readByLogin es positiva, envia al controller un booleando de que el usuario esta validado

            Response<Usuario> response = dao.readByLogin(nombre, contrasena);

            if(response.isSuccess()){

                usuarioValido = true;

                return new Response<Usuario>("Usuario validado correctamente", 200, true, usuarioValido);


            } else {

                return new Response<>("Error al validar el usuario: " + response.getMensaje(), 500, false);

            }
        }catch(Exception e){


            return new Response<>("Error al validar el usuario: " + e.getMessage(), 500, false, "UsuarioService", "validarLogin");

        }
    }
}
