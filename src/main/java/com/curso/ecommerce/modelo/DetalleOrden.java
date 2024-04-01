package com.curso.ecommerce.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleOrdenes")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleOrden;
    private String nombre;
    private double cantidad;
    private double precion;
    private double total;

    @OneToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;
    public DetalleOrden() {

    }

    public DetalleOrden(Integer idDetalleOrden, String nombre, double cantidad, double precion, double total) {
        this.idDetalleOrden = idDetalleOrden;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precion = precion;
        this.total = total;
    }

    public Integer getIdDetalleOrden() {
        return idDetalleOrden;
    }

    public void setIdDetalleOrden(Integer idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecion() {
        return precion;
    }

    public void setPrecion(double precion) {
        this.precion = precion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "idDetalleOrden=" + idDetalleOrden +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precion=" + precion +
                ", total=" + total +
                '}';
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
