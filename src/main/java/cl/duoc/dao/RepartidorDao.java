package cl.duoc.dao;

import cl.duoc.conexion.ConexionBD;
import cl.duoc.model.Pedido;
import cl.duoc.model.Repartidor;
import cl.duoc.util.EstadoPedido;
import cl.duoc.util.Tipo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDao {

    public static void crear(String nombre){

        try (Connection connection = ConexionBD.conectar();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO repartidores(nombre) VALUES(?)")){

            ps.setString(1, nombre);
            ps.executeUpdate();

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }

    }

    public static List<Repartidor> listar() throws SQLException{

        List<Repartidor> repartidor = new ArrayList<>();
        try(Connection c = ConexionBD.conectar();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM repartidores")){
            while(rs.next()){
                Repartidor r = new Repartidor();
                r.setIdRepartidor(rs.getInt("id"));
                r.setNombre(rs.getString("nombre"));

                repartidor.add(r);
            }
        }
        return repartidor;
    }


    public static void actualizar(int id, String nombre){
        try(Connection c = ConexionBD.conectar();
            PreparedStatement ps = c.prepareStatement("UPDATE repartidores SET nombre = ? WHERE id = ?")){
            ps.setString(1, nombre);
            ps.setInt(2, id);

            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }
    }

    public static void eliminar(int id) throws SQLException{
        try(Connection c = ConexionBD.conectar();
            PreparedStatement ps = c.prepareStatement("DELETE FROM repartidores WHERE id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL ha tenido un error. " +e);
        }
    }
}
