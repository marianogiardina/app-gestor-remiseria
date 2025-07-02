package DAO;

import Model.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDao<T> implements Crud<T> {

    //Como los DAOs tienen la misma estructura, creo una clase padre que implemente la interfaz Crud<T> y que maneje la conexión a la base de datos.

    protected Connection conn;

    protected String tableName;

    public BaseDao(String tableName) {
        try {
            this.conn = DbConn.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.tableName = tableName;
    }

    @Override
    public abstract Response<T> create(T obj);

    @Override
    public abstract Response<T> update(T obj);

    //Como el metodo delete es muy similar en todas las tablas, lo implemento en la clase base para no repetir código.

    @Override
    public Response<T> delete(int id){
        String sql = "DELETE FROM " + this.tableName + " WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return new Response<>(tableName + " eliminado exitosamente", 200, true);

        } catch (SQLException e) {
            return new Response<>("Error al eliminar el"+tableName+": " + e.getMessage(), 500, false);
        }
    };

    @Override
    public abstract Response<T> read(int id);

    @Override
    public abstract Response<T> readAll();
}
