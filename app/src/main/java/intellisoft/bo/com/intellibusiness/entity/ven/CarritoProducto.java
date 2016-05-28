package intellisoft.bo.com.intellibusiness.entity.ven;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;
import intellisoft.bo.com.intellibusiness.entity.inv.ProductoEmpresa;

/**
 * Created by kevin on 28/05/2016.
 */
public class CarritoProducto extends Entity {
    private int idc;
    private int idp;
    private long fecha;
    private ProductoEmpresa productoEmpresa;

    public CarritoProducto() {
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public ProductoEmpresa getProductoEmpresa() {
        return productoEmpresa;
    }

    public void setProductoEmpresa(ProductoEmpresa productoEmpresa) {
        this.productoEmpresa = productoEmpresa;
    }
}
