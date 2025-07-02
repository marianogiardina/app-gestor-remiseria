package DAO;

import Model.Response;
import Model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDao extends BaseDao<Usuario>{

    private String tableName = "usuario";

    private final static UsuarioDao instance = new UsuarioDao();

    public static UsuarioDao getInstance() {
        return instance;
    }

    public UsuarioDao() {
        super("usuario");
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
    public Response<Usuario> read(int id) {
        return null;
    }

    @Override
    public Response<Usuario> readAll() {
        return null;
    }

    public Response<Usuario> readByLogin(String nombre, String contrasena) {

        String sql = "SELECT * FROM " + this.tableName + " WHERE nombre = ? AND contrasena = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            //Si el resultSet no envia un usuario, siginifca que esta mal la contraseña o el nombre

            if (rs.next()) {

                Usuario usuario = new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena")
                );

                return new Response<>("Usuario encontrado", 200, true, usuario);

            } else {
                return new Response<>("Usuario no encontrado", 404, false);
            }

        } catch (SQLException e) {
            return new Response<>("Error al buscar el usuario: " + e.getMessage(), 500, false);
        }


    }
}
