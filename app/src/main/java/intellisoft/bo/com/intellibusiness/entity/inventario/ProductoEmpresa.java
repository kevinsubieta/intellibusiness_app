package intellisoft.bo.com.intellibusiness.entity.inventario;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;
import intellisoft.bo.com.intellibusiness.entity.marketing.ProductoDescuento;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ProductoEmpresa extends Entity {

    String producto;
    String empresa;
    String nombre;
    int cantidad;
    double precio;
    int estado;
    String detalle;
    List<ProductoDescuento> lstProductoDescuentos;
    List<ImagenProducto> lstImagenProducto;


    public ProductoEmpresa() {
    }

    public ProductoEmpresa(String producto, String empresa, String nombre, int cantidad, double precio,
                           int estado, String detalle,
                           List<ProductoDescuento> lstProductoDescuentos, List<ImagenProducto> lstImagenProducto) {
        this.producto = producto;
        this.empresa = empresa;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.estado = estado;
        this.detalle = detalle;
        this.lstProductoDescuentos = lstProductoDescuentos;
        this.lstImagenProducto = lstImagenProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<ProductoDescuento> getLstProductoDescuentos() {
        return lstProductoDescuentos;
    }

    public void setLstProductoDescuentos(List<ProductoDescuento> lstProductoDescuentos) {
        this.lstProductoDescuentos = lstProductoDescuentos;
    }

    public List<ImagenProducto> getLstImagenProducto() {
        return lstImagenProducto;
    }

    public void setLstImagenProducto(List<ImagenProducto> lstImagenProducto) {
        this.lstImagenProducto = lstImagenProducto;
    }
}
