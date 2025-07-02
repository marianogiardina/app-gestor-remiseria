package DAO;

import DTO.BalanceMensualDTO;
import DTO.SemanaChoferDTO;
import Model.Chofer;
import Model.EstadoViaje;
import Model.Response;
import Model.Viaje;
import Repository.ChoferRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViajeDao extends BaseDao<Viaje> {

    private final String tableName = "viaje";

    private ChoferRepository choferRepository;

    // Instancia única del DAO para evitar múltiples instancias y utilizar el patrón Singleton(solo en los DAOs y la conexion a la bbdd).

    private final static ViajeDao instance = new ViajeDao();

    public static ViajeDao getInstance() {
        return instance;
    }

    public ViajeDao() {
        super("viaje");
        this.choferRepository = new ChoferRepository(ChoferDao.getInstance());
    }

    @Override
    public Response<Viaje> create(Viaje obj) {

        //Guardo en variable sql la consulta de inserción

        String sql = "INSERT INTO " + this.tableName + " (kilometros, valor_km, estado_viaje, id_chofer, origen, destino, fecha) " +
                "VALUES (?,?,?,?,?,?, ?)";

        // Intento ejecutar la consulta, asegurando que la conexión a la base de datos sea exitosa, en caso de no serlo, se catchea la excepción y se retorna un mensaje de error.

        try {

            realizarConsulta(sql, obj);

            // Retorno un mensaje de éxito si la inserción fue exitosa

            return new Response<>("Viaje creado exitosamente", 200, true);

        } catch (SQLException e) {

            // En caso de error, retorno un mensaje de error con el código 500 y el mensaje de la excepción

            return new Response<>("Error al crear el viaje: " + e.getMessage(), 500, false);
        }
    }

    @Override
    public Response<Viaje> update(Viaje obj) {

        //Guardo en variable sql la consulta de actualización

        String sql = "UPDATE " + this.tableName + " SET " +
                "kilometros = ?," +
                "valor_km = ?," +
                "estado_viaje = ?," +
                "id_chofer = ?," +
                "origen = ?," +
                "destino = ?," +
                "fecha = ?" +
                "WHERE id = ?";

        // Intento ejecutar la consulta, asegurando que la conexión a la base de datos sea exitosa, en caso de no serlo, se catchea la excepción y se retorna un mensaje de error.

        try {

            int rows = realizarConsulta(sql, obj);

            // Verifico cuántas filas encontro la consulta y valido en caso de éxito o error. Generalmente, afectar una sola fila, pero en caso de algun error en la BBDD
            // puede afectar más de una fila, por lo que realizo este if para asegurar.

            if (rows == 1) {
                // Si se actualizó un viaje, retorno un mensaje de éxito

                return new Response<>("Viaje actualizado exitosamente", 200, true);
            } else if (rows == 0) {
                // Si no se actualizó ningún viaje, retorno un mensaje de error indicando que no se encontró el viaje

                return new Response<>("No se encontró el viaje para actualizar", 404, false);
            } else {
                // Si se actualizó más de un viaje, retorno un mensaje de error indicando que hubo un problema

                return new Response<>("Error: se encontró más de un viaje para actualizar", 500, false);
            }

        } catch (SQLException e) {

            // En caso de error, retorno un mensaje de error con el código 500 y el mensaje de la excepción

            return new Response<>("Error al actualizar el viaje: " + e.getMessage(), 500, false);
        }
    }

    @Override
    public Response<Viaje> read(int id) {

        String sql = "SELECT * FROM " + this.tableName + " WHERE id = ?";


        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Chofer chofer = getChoferFromResultSet(rs);

                Viaje viaje = new Viaje(
                        rs.getInt("id"),
                        rs.getDouble("kilometros"),
                        rs.getDouble("valor_km"),
                        EstadoViaje.valueOf(rs.getString("estado_viaje")),
                        chofer,
                        rs.getString("origen"),
                        rs.getString("destino"),
                        //Como el constructor de la clase Viaje espera un parámetro de tipo LocalDateTime y rs.getObject("fecha")
                        //devuelve un objeto que no necesariamente es de LocalDateTime, lo convierto.
                        rs.getObject("fecha", LocalDateTime.class)
                );

                return new Response<>("Viaje encontrado", 200, true, viaje);

            } else {

                return new Response<>("No se encontró el viaje", 404, false);

            }
        } catch (SQLException e) {

            return new Response<>("Error al leer el viaje: " + e.getMessage(), 500, false);

        }
    }

    @Override
    public Response<Viaje> readAll() {

        String sql = "SELECT * FROM " + this.tableName;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Viaje> viajes = new ArrayList<>();

            while (rs.next()) {

                Chofer chofer = getChoferFromResultSet(rs);

                Viaje viaje = new Viaje(
                        rs.getInt("id"),
                        rs.getDouble("kilometros"),
                        rs.getDouble("valor_km"),
                        EstadoViaje.valueOf(rs.getString("estado_viaje")),
                        chofer,
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getObject("fecha", LocalDateTime.class)
                );

                viajes.add(viaje);

            }

            return new Response<>("Viajes leídos exitosamente", 200, true, viajes);

        } catch (SQLException e) {

            return new Response<>("Error al leer los viajes: " + e.getMessage(), 500, false);

        }
    }

    // En este metodo, traigo desde la bbdd la informacion necesaria para realizar la facturacion de la semana del chofer.
    // Retorno esta informacion en un DTO.

    public Response<SemanaChoferDTO> selectSemanaChofer(LocalDateTime fechaInicioSemana, LocalDateTime fechaFinSemana) {

        String sql = "SELECT id_chofer, COUNT(*) AS cantidad_viajes, " +
                "SUM(kilometros) AS cantidad_kilometros, " +
                "SUM(kilometros * valor_km) AS sueldo_semana " +
                "FROM "+ this.tableName +
                " WHERE estado_viaje = 'FINALIZADO' AND fecha BETWEEN ? AND ? " +
                "GROUP BY id_chofer"
        ;

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, fechaInicioSemana);

            ps.setObject(2, fechaFinSemana);

            ResultSet rs = ps.executeQuery();

            List<SemanaChoferDTO> sueldosChoferes = new ArrayList<>();

            while (rs.next()) {

                Chofer chofer = getChoferFromResultSet(rs);

                SemanaChoferDTO semanaChofer = new SemanaChoferDTO(
                        chofer,
                        rs.getInt("cantidad_viajes"),
                        rs.getDouble("cantidad_kilometros"),
                        rs.getDouble("sueldo_semana")

                );

                sueldosChoferes.add(semanaChofer);
            }

            return new Response<>("Viajes encontrados por fecha", 200, true, sueldosChoferes);

        } catch (SQLException e) {
            return new Response<>("Error al buscar viajes por fecha: " + e.getMessage(), 500, false);
        }
    }


    // Este metodo obtiene el balance mensual de viajes por chofer, agrupando los viajes finalizados del mes actual.
    // Creo una clase DTO para transportar la informacion necesaria para realizar el balance mensual en el service.
    public Response<BalanceMensualDTO> selectViajesMensual(){

        String sql = "SELECT id_chofer, COUNT(*) AS cantidad_viajes, " +
                "SUM(kilometros * valor_km) AS total_mes_chofer " +
                "FROM "+ this.tableName +
                " WHERE estado_viaje = 'FINALIZADO' AND fecha BETWEEN ? AND ? " +
                "GROUP BY id_chofer";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, LocalDateTime.now().withDayOfMonth(1)); // Primer día del mes actual
            ps.setObject(2, LocalDateTime.now().withDayOfMonth(LocalDateTime.now().toLocalDate().lengthOfMonth())); // Último día del mes actual

            ResultSet rs = ps.executeQuery();

            List<BalanceMensualDTO> balancesMensuales = new ArrayList<>();

            while (rs.next()) {

                Chofer chofer = getChoferFromResultSet(rs);

                BalanceMensualDTO balanceMensualPorChofer = new BalanceMensualDTO(
                        chofer,
                        rs.getInt("cantidad_viajes"),
                        rs.getDouble("total_mes_chofer")
                );

                balancesMensuales.add(balanceMensualPorChofer);
            }

            return new Response<>("Viajes mensuales encontrados", 200, true, balancesMensuales);

        } catch (SQLException e) {
            return new Response<>("Error al buscar viajes mensuales: " + e.getMessage(), 500, false);
        }

    }

    //Para evitar repetir el código de la consulta de getChofer, creo un metodo privado que recibe un ResultSet y devuelve un objeto Chofer.
    //Este metodo, en caso de fallar con la recuperacion del id, retorna una QLSException que luego atrapara el catch

    private Chofer getChoferFromResultSet(ResultSet rs) throws SQLException {

        int choferId = rs.getInt("id_chofer");

        Response<Chofer> getChofer = choferRepository.getById(choferId);

        if (getChofer.isSuccess()) {
            return getChofer.getObj();
        } else {
            throw new SQLException("Error al obtener el chofer: " + getChofer.getMensaje());
        }

    }


    //Realizo este metodo para evitar repetir el código de la consulta de create y update, ya que ambas consultas son muy parecidas.
    //
    private int realizarConsulta(String sql, Viaje obj) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, obj.getKilometros());
        ps.setDouble(2, obj.getValorKm());
        ps.setString(3, obj.getEstadoViaje().name());
        ps.setInt(4, obj.getChofer().getId());
        ps.setString(5, obj.getOrigen());
        ps.setString(6, obj.getDestino());
        ps.setObject(7, obj.getFecha());

        // Si el id del objeto es 0, significa que es un create (al crear el objeto el id es = 0 hasta llegar a la bbdd donde se asigna), por lo que no se debe setear el id en la consulta.

        if(obj.getId() != 0){

            ps.setInt(8, obj.getId());
        }

        return ps.executeUpdate();
    }
}
