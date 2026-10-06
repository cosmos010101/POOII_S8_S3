package cl.duoc.conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final String URL = "JDBC:MYSQL://LOCALHOST:3306/speedfast_db?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Lilgodanubis8.";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void inicializarTabla() {
        String sqlRepartidor = """
                CREATE TABLE repartidores (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL
                );
                """;
        String sqlPedido = """
                CREATE TABLE pedidos (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    direccion VARCHAR(100) NOT NULL,
                    tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS'),
                    estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO')
                );
                """;
        String sqlEntrega = """
                CREATE TABLE entregas (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_pedido INT,
                    id_repartidor INT,
                    fecha DATE,
                    hora TIME,
                    FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
                    FOREIGN KEY (id_repartidor) REFERENCES repartidores(id)
                );
                """;
        try (Connection conexion = conectar();
             Statement statement = conexion.createStatement();
        ){
            statement.execute(sqlRepartidor);
            statement.execute(sqlPedido);
            statement.execute(sqlEntrega);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
