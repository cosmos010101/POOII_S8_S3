package cl.duoc.dao;

import cl.duoc.conexion.ConexionBD;
import cl.duoc.model.Pedido;
import cl.duoc.util.EstadoPedido;
import cl.duoc.util.Tipo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public PedidoDao() {
        try (Connection c = ConexionBD.conectar();
             Statement st = c.createStatement()) {

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void crear(String direccion, String tipo, String estadoPedido){

            try (Connection connection = ConexionBD.conectar();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pedidos(direccion, tipo, estado) VALUES(?, ?, ?)")){

                ps.setString(1, direccion);
                ps.setString(2, tipo);
                ps.setString(3, estadoPedido);
                ps.executeUpdate();

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
            }

        }

    public static List<Pedido> listar() throws SQLException{

            List<Pedido> pedido = new ArrayList<>();
            try(Connection c = ConexionBD.conectar();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos")){
                while(rs.next()){
                     Pedido p = new Pedido();
                     p.setIdPedido(rs.getInt("id"));
                     p.setDireccionEntrega(rs.getString("direccion"));
                     p.setTipo(Tipo.valueOf(rs.getString("tipo")));
                     p.setEstado(EstadoPedido.valueOf(rs.getString("estado")));

                     pedido.add(p);
                }
            }
            return pedido;
    }


    public static void actualizar(int id, String direccion, String tipo, String estadoPedido){
        try(Connection c = ConexionBD.conectar();
            PreparedStatement ps = c.prepareStatement("UPDATE pedidos SET direccion = ?, tipo = ?, estado = ? WHERE id = ?")){

            ps.setString(1, direccion);
            ps.setString(2, tipo);
            ps.setString(3, estadoPedido);
            ps.setInt(4, id);

            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }
    }

    public static void eliminar(int id) throws SQLException{
        try(Connection c = ConexionBD.conectar();
        PreparedStatement ps = c.prepareStatement("DELETE FROM pedidos WHERE id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }
    }
}
