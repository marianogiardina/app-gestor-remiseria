package DAO;

import Model.Auto;
import Model.Chofer;
import Model.Response;
import Model.Viaje;
import Repository.AutoRepository;
import Repository.ChoferRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoDao extends BaseDao<Auto> {

    private String tableName = "auto";

    private final static AutoDao instance = new AutoDao();

    public static AutoDao getInstance() {
        return instance;
    }

    public AutoDao() {
        super("auto");
    }


    @Override
    public Response<Auto> create(Auto obj) {

        String sql = "INSERT INTO " + this.tableName + " (kilometraje, marca, modelo, disponible) " +
                "VALUES (?,?,?,?)";

        try {

            realizarConsulta(sql, obj);

            return new Response<>("Auto creado exitosamente", 200, true);

        }catch (SQLException e) {

            return new Response<>("Error al crear el registro: " + e.getMessage(), 500, false);

        }
    }

    @Override
    public Response<Auto> update(Auto obj) {

        String sql = "UPDATE " + this.tableName + " SET " +
                "kilometraje = ?," +
                "marca = ?," +
                "modelo = ?," +
                "disponible = ? " +
                "WHERE id = ?";
        try {

            int rows = realizarConsulta(sql, obj);

            if (rows == 1) {

                return new Response<>("Auto actualizado exitosamente", 200, true);

            } else if (rows == 0) {

                return new Response<>("No se encontró el auto para actualizar", 404, false);

            } else {

                return new Response<>("Error: se encontró más de un auto para actualizar", 500, false);

            }

        } catch (SQLException e) {

            return new Response<>("Error al actualizar el registro: " + e.getMessage(), 500, false);
        }
    }

    @Override
    public Response<Auto> read(int id) {

        String sql = "SELECT * FROM " + this.tableName + " WHERE id = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getDouble("kilometraje"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getBoolean("disponible")
                );

                return new Response<>("Auto entonctrado", 200, true, auto);
            } else {
                return new Response<>("No se encontró el auto", 404, false);
            }

        } catch (SQLException e) {
            return new Response<>("Error al leer el auto: " + e.getMessage(), 500, false);
        }
    }

    @Override
    public Response<Auto> readAll() {

        String sql = "SELECT * FROM " + this.tableName;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Auto> autos = new ArrayList<>();

            while(rs.next()) {

                Auto auto = new Auto(
                        rs.getInt("id"),
                        rs.getDouble("kilometraje"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getBoolean("disponible")
                );

                autos.add(auto);
            }
            return new Response<>("Autos encontrados", 200, true, autos);

        } catch (SQLException e) {

            return new Response<>("Error al leer los autos: " + e.getMessage(), 500, false);

        }
    }

    private int realizarConsulta(String sql, Auto obj) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, obj.getKilometraje());
        ps.setString(2, obj.getMarca());
        ps.setString(3, obj.getModelo());
        ps.setBoolean(4, obj.isDisponible());

        if(obj.getId() != 0){

            ps.setInt(5, obj.getId());
        }

        return ps.executeUpdate();
    }
}
