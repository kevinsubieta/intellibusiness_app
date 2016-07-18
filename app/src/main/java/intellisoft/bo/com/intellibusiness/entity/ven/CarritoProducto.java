package intellisoft.bo.com.intellibusiness.entity.ven;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by kevin on 28/05/2016.
 */
public class CarritoProducto extends Entity {
    private int cliente;
    private int producto;
    private int cantidad;
    private ProductoEmpresa productoEmpresa;

    public CarritoProducto() {
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoEmpresa getProductoEmpresa() {
        return productoEmpresa;
    }

    public void setProductoEmpresa(ProductoEmpresa productoEmpresa) {
        this.productoEmpresa = productoEmpresa;
    }
}
