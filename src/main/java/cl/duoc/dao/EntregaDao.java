package cl.duoc.dao;

import cl.duoc.conexion.ConexionBD;
import cl.duoc.model.Entrega;
import cl.duoc.model.Pedido;
import cl.duoc.util.EstadoPedido;
import cl.duoc.util.Tipo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDao {

    public EntregaDao(){
        try(Connection c = ConexionBD.conectar();
            Statement st = c.createStatement()){

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error." + e);
        }
    }

    public static void crear(int id_pedido, int id_repartidor){
        String sql = "INSERT INTO entregas(id_pedido, id_repartidor, fecha, hora) VALUES(?, ?, ?, ?)";
        String sql2 = "UPDATE pedidos SET estado = 'EN_REPARTO' WHERE id = ?";
        try (Connection connection = ConexionBD.conectar();
             PreparedStatement ps = connection.prepareStatement(sql);
             PreparedStatement ps2 = connection.prepareStatement(sql2)){

            ps.setInt(1, id_pedido);
            ps.setInt(2, id_repartidor);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setTime(4, new java.sql.Time(System.currentTimeMillis()));

            ps.executeUpdate();

            ps2.setInt(1, id_pedido);

            ps2.executeUpdate();

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }

    }

    public static List<Entrega> listar() throws SQLException {
        List<Entrega> entrega = new ArrayList<>();

        try (Connection c = ConexionBD.conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM entregas")) {
            while (rs.next()) {
                Entrega en = new Entrega();
                en.setId(rs.getInt("id"));
                en.setIdPedido(rs.getInt("id_pedido"));
                en.setIdRepartidor(rs.getInt("id_repartidor"));
                en.setFecha(rs.getString("fecha"));
                en.setHora(rs.getString("hora"));

                entrega.add(en);
            }
        }
        return entrega;
    }

    public static void actualizar(int idPedido, int idRepartidor) {

        try (Connection c = ConexionBD.conectar();
             PreparedStatement ps = c.prepareStatement("UPDATE entregas SET id_pedido = ?, id_repartidor = ? WHERE id = ?")) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idRepartidor);

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de SQL. " + e);
        }

    }

    public static void eliminar(int id) throws SQLException{
        try (Connection c = ConexionBD.conectar();
        PreparedStatement ps = c.prepareStatement("DELETE FROM entregas WHERE id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " + e);
        }
    }

}


