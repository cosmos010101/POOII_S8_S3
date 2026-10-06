package cl.duoc.model;

import cl.duoc.util.EstadoPedido;
import cl.duoc.util.Tipo;

public class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private Tipo tipo;
    private EstadoPedido estadoPedido;

    public Pedido(int idPedido, String direccionEntrega, Tipo tipo, EstadoPedido estadoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(String direccionEntrega, Tipo tipo, EstadoPedido estadoPedido) {
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(){

    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estadoPedido = nuevoEstado;
    }
}
