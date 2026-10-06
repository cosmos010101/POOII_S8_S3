package cl.duoc.model;

import cl.duoc.util.EstadoPedido;

import static cl.duoc.util.EstadoPedido.EN_REPARTO;

public class Repartidor{

    private String nombre;
    private int idRepartidor;

    public Repartidor(int idRepartidor, String nombre) {
        this.nombre = nombre;
        this.idRepartidor = idRepartidor;
    }

    public Repartidor(){}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }



}
