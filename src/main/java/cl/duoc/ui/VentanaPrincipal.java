package cl.duoc.ui;

import cl.duoc.dao.EntregaDao;
import cl.duoc.dao.PedidoDao;
import cl.duoc.dao.RepartidorDao;
import cl.duoc.model.Entrega;
import cl.duoc.model.Pedido;
import cl.duoc.model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;


public class VentanaPrincipal extends JFrame {

    //private final PedidoDao pedidoDao = new PedidoDao();
    private final RepartidorDao repartidorDao = new RepartidorDao();
    //private final EntregaDao entregaDao = new EntregaDao();
    private DefaultTableModel tm = new DefaultTableModel(new String[] {"Id_entrega", "id_pedido", "id_repartidor", "direccion", "nombre", "fecha", "hora"}, 0);
    private final JTable tabla = new JTable(tm);
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    private final JTextField txtDireccion = new JTextField();
    private JComboBox cbTipo = new JComboBox();
    PedidoDao p = new PedidoDao();

    public VentanaPrincipal(){

        JFrame ventana = new JFrame("SPEEDFAST");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        ventana.setContentPane(panel);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Pedido", tabPedido());
        tabs.addTab("Repartidor", tabRepartidor());
        tabs.addTab("Entrega", tabEntrega());




        tabs.addChangeListener(e ->
            tabs.getSelectedIndex());
        panel.add(tabs, BorderLayout.NORTH);



        ventana.pack();
        ventana.setVisible(true);

    }
    public Component tabPedido() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 1, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del pedido"));
        try {

            JTextField txtDireccion = new JTextField();
            JComboBox cbTipo = new JComboBox<>(new String[] {"Comida", "Encomienda", "Express"});
            JComboBox cbEstado = new JComboBox<>(new String[] {"Pendiente", "En camino", "Entregado"});
            JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 6));
            JButton btnAgregar = new JButton("Agregar");
            JButton btnActualizar = new JButton("Actualizar");
            JButton btnEliminar = new JButton("Eliminar");
            JButton btnListar = new JButton("Listar");

            panel.add(new JLabel("Direccion"));
            panel.add(txtDireccion);
            panel.add(new JLabel("Tipo"));
            panel.add(cbTipo);
            panel.add(new JLabel("Estado"));
            panel.add(cbEstado);


            panel.add(btnAgregar);
            panel.add(btnActualizar);
            panel.add(btnEliminar);
            panel.add(btnListar);

            panel.add(tabla);

            btnAgregar.addActionListener(e -> PedidoDao.crear(txtDireccion.getText(), cbTipo.getSelectedItem().toString(), cbEstado.getSelectedItem().toString()));
            btnActualizar.addActionListener(e -> {
                        int fila = tabla.getSelectedRow();
                        if (fila < 0) {
                            return;
                        }
                        try {
                            int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                            PedidoDao.actualizar(id, txtDireccion.getText(), cbTipo.getSelectedItem().toString(), cbEstado.getSelectedItem().toString());
                            limpiar();
                            JOptionPane.showMessageDialog(this, "Pedido actualizado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception er) {
                            error(er);
                        }
                    })  ;

            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if(fila < 0) {
                    return;
                }
                try{
                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    PedidoDao.eliminar(id);
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Pedido Eliminado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                }catch (Exception er){
                    error(er);
                }
            });

            btnListar.addActionListener(e -> {
                try {
                    DefaultTableModel modeloPedidos = new DefaultTableModel(
                            new String[]{"Id", "Direccion", "Tipo", "Estado"},
                            0
                    );

                    for (Pedido pedido : PedidoDao.listar()) {
                        modeloPedidos.addRow(new Object[]{
                                pedido.getIdPedido(),
                                pedido.getDireccionEntrega(),
                                pedido.getTipo(),
                                pedido.getEstadoPedido()
                        });
                    }

                    tabla.setModel(modeloPedidos);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            });

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return panel;
    }

    private void limpiar() {
        txtDireccion.setText("");
    }



    private Component tabRepartidor() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 1, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del repartidor"));
        try {

            JTextField txtNombre = new JTextField();
            JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 6));
            JButton btnAgregar = new JButton("Agregar");
            JButton btnActualizar = new JButton("Actualizar");
            JButton btnEliminar = new JButton("Eliminar");
            JButton btnListar = new JButton("Listar");

            panel.add(new JLabel("Nombre"));
            panel.add(txtNombre);

            panel.add(btnAgregar);
            panel.add(btnActualizar);
            panel.add(btnEliminar);
            panel.add(btnListar);

            panel.add(tabla);

            btnAgregar.addActionListener(e -> RepartidorDao.crear(txtNombre.getText()));
            btnActualizar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila < 0) {
                    return;
                }
                try {
                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    RepartidorDao.actualizar(id, txtNombre.getText());
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Repartidor actualizado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception er) {
                    error(er);
                }
            })  ;

            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if(fila < 0) {
                    return;
                }
                try{
                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    RepartidorDao.eliminar(id);
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Repartidor Eliminado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                }catch (Exception er){
                    error(er);
                }
            });

            btnListar.addActionListener(e -> {
                try {
                    DefaultTableModel modeloRepartidor = new DefaultTableModel(
                            new String[]{"Id", "Nombre"},
                            0
                    );

                    for (Repartidor r : RepartidorDao.listar()) {
                        modeloRepartidor.addRow(new Object[]{
                                r.getIdRepartidor(),
                                r.getNombre()
                        });
                    }

                    tabla.setModel(modeloRepartidor);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            });

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return panel;

    }

    private Component tabEntrega() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 1, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Datos de entrega"));
        try {

            JTextField txtId = new JTextField();
            JTextField txtIdPedido = new JTextField();
            JTextField txtIdRepartidor = new JTextField();
            JComboBox cbTipo = new JComboBox<>(new String[] {"Comida", "Encomienda", "Express"});
            JComboBox cbEstado = new JComboBox<>(new String[] {"Pendiente", "En camino", "Entregado"});
            JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 6));
            JButton btnAgregar = new JButton("Agregar");
            JButton btnActualizar = new JButton("Actualizar");
            JButton btnEliminar = new JButton("Eliminar");
            JButton btnListar = new JButton("Listar");

            panel.add(new JLabel("Id Entrega"));
            panel.add(txtId);
            panel.add(new JLabel("Id Pedido"));
            panel.add(txtIdPedido);
            panel.add(new JLabel("Id Repartidor"));
            panel.add(txtIdRepartidor);


            panel.add(btnAgregar);
            panel.add(btnActualizar);
            panel.add(btnEliminar);
            panel.add(btnListar);

            panel.add(tabla);

            btnAgregar.addActionListener(e -> EntregaDao.crear(Integer.parseInt(txtIdPedido.getText()), Integer.parseInt(txtIdRepartidor.getText())));
            btnActualizar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila < 0) {
                    return;
                }
                try {
                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    EntregaDao.actualizar(Integer.parseInt(txtIdPedido.getText()), Integer.parseInt(txtIdRepartidor.getText()));
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Entrega actualizada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception er) {
                    error(er);
                }
            })  ;

            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if(fila < 0) {
                    return;
                }
                try{
                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    EntregaDao.eliminar(id);
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Entrega Eliminada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                }catch (Exception er){
                    error(er);
                }
            });

            btnListar.addActionListener(e -> {
                try {
                    DefaultTableModel modeloEntregas = new DefaultTableModel(
                            new String[]{"Id", "Id_Pedido", "Id_Repartidor", "Fecha", "Hora"},
                            0
                    );

                    for (Entrega en : EntregaDao.listar()) {
                        modeloEntregas.addRow(new Object[]{
                                en.getId(),
                                en.getIdPedido(),
                                en.getIdRepartidor(),
                                en.getFecha(),
                                en.getHora()
                        });
                    }

                    tabla.setModel(modeloEntregas);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            });

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return panel;
    }

    private void error(Exception er){
        JOptionPane.showMessageDialog(null, "Error" + er.getMessage());
    }

}
