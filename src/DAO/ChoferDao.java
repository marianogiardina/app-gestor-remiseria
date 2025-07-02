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

public class ChoferDao extends BaseDao<Chofer> {

    private String tableName = "chofer";

    private AutoRepository autoRepository;

    private final static ChoferDao instance = new ChoferDao();

    public static ChoferDao getInstance() {
        return instance;
    }

    public ChoferDao() {
        super("chofer");
        this.autoRepository = new AutoRepository(AutoDao.getInstance());
    }


    @Override
    public Response<Chofer> create(Chofer obj) {


        String sql = "INSERT INTO " + this.tableName + " (nombre, dni, celular, auto_propio, disponible, id_auto) " +
                "VALUES (?,?,?,?,?,?)";


        try{
            realizarConsulta(sql, obj);

            return new Response<>("Chofer creado exitosamente", 200, true);

        }catch (SQLException e){

            return new Response<>("Error al crear el chofer: " + e.getMessage(), 500, false);

        }

    }

    @Override
    public Response<Chofer> update(Chofer obj) {

        String sql = "UPDATE " + this.tableName + " SET " +
                "nombre = ?," +
                "dni = ?," +
                "celular = ?," +
                "auto_propio = ?," +
                "disponible = ?," +
                "id_auto = ? " +
                "WHERE id = ?";

        try {
            int rows = realizarConsulta(sql, obj);

            if (rows == 1) {

                return new Response<>("Chofer actualizado exitosamente", 200, true);

            } else if (rows == 0) {

                return new Response<>("No se encontró el chofer para actualizar", 404, false);

            } else {

                return new Response<>("Error: se encontró más de un chofer para actualizar", 500, false);

            }

        } catch (SQLException e) {

            return new Response<>("Error al actualizar el chofer: " + e.getMessage(), 500, false);

        }
    }

    @Override
    public Response<Chofer> read(int id) {

        String sql = "SELECT * FROM " + this.tableName + " WHERE id = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            if(rs.next()) {
                // Si el chofer tiene auto propio, no necesito obtener el auto desde el AutoRepository, por lo que utilizo el constructor de Chofer sin auto.
                if(rs.getBoolean("auto_propio")){

                    Chofer chofer = new Chofer(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("dni"),
                            rs.getLong("celular"),
                            rs.getBoolean("auto_propio"),
                            rs.getBoolean("disponible")
                    );

                    return new Response<>("Chofer encontrado", 200, true, chofer);

                }else{

                    Auto auto = getAutoFromResultSet(rs);

                    Chofer chofer = new Chofer(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("dni"),
                            rs.getLong("celular"),
                            rs.getBoolean("auto_propio"),
                            rs.getBoolean("disponible"),
                            auto
                    );
                    return new Response<>("Chofer encontrado", 200, true, chofer);
                }

            } else {
                return new Response<>("Chofer no encontrado", 404, false);
            }

        }catch (SQLException e) {;
            return new Response<>("Error al leer el chofer: " + e.getMessage(), 500, false);
        }
    }



    @Override
    public Response<Chofer> readAll() {

        String sql = "SELECT * FROM " + this.tableName;

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Chofer> choferes = new ArrayList<>();

            while(rs.next()) {

                // Si el chofer tiene auto propio, no necesito obtener el auto desde el AutoRepository, por lo que utilizo el constructor de Chofer sin auto.

                if (rs.getBoolean("auto_propio")) {

                    Chofer chofer = new Chofer(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("dni"),
                            rs.getLong("celular"),
                            rs.getBoolean("auto_propio"),
                            rs.getBoolean("disponible")
                    );

                    choferes.add(chofer);

                }else{

                    Auto auto = getAutoFromResultSet(rs);

                    Chofer chofer = new Chofer(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("dni"),
                            rs.getLong("celular"),
                            rs.getBoolean("auto_propio"),
                            rs.getBoolean("disponible"),
                            auto
                    );

                    choferes.add(chofer);

                }
            }

            return new Response<>("Choferes encontrados", 200, true, choferes);

        }catch (SQLException e) {
            return new Response<>("Error al leer los choferes: " + e.getMessage(), 500, false);
        }
    }

    //Lo mismo que en el ViajeDao, con este metodo evito repetir código en los métodos read y readAllal momento de buscar el auto en el respository.

    private Auto getAutoFromResultSet(ResultSet rs) throws SQLException {

        int autoId = rs.getInt("id_auto");

        Response<Auto> getAuto = autoRepository.getById(autoId);

        if(getAuto.isSuccess()) {
            return getAuto.getObj();
        } else {
            throw new SQLException("Error al obtener el auto: " + getAuto.getMensaje());
        }

    }

    //Como en el ViajeDao, con este metodo evito repetir código en los métodos create y update.

    private int realizarConsulta(String sql, Chofer obj) throws SQLException{

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, obj.getNombre());
        ps.setInt(2, obj.getDni());
        ps.setLong(3, obj.getCelular());
        ps.setBoolean(4, obj.isAutoPropio());
        ps.setBoolean(5, obj.isDisponible());

        if(!obj.isAutoPropio()){
            ps.setInt(6, obj.getAutoAlquilado().getId());
        } else {
            ps.setNull(6, java.sql.Types.INTEGER);
        }
        if(obj.getId() != 0){

            ps.setInt(7, obj.getId());
        }

        return ps.executeUpdate();
    }

}
