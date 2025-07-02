package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {

    private final String db = "app_gestor_remiseria_avanzada"; // Aca va el nombre de tu base de datos
    private final String url = "jdbc:mysql://localhost:3306/"+db;
    private final String user = "root";
    private final String password = "";

    private Connection conn;

    private static DbConn instance;

    private DbConn() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC no encontrado", e);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos", e);
        }
    }

    public Connection getConnection(){
        return this.conn;
    }


    public static DbConn getInstance() throws SQLException {
        if(instance == null){
            instance = new DbConn();
        } else if(instance.getConnection() == null || instance.getConnection().isClosed()){
            instance = new DbConn();
        }
        return instance;
    }


}

