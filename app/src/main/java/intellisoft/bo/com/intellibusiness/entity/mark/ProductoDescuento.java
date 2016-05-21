package intellisoft.bo.com.intellibusiness.entity.mark;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ProductoDescuento extends Entity {
    int producto;
    int descuento;
    Descuento objDescuento;

    public ProductoDescuento() {
    }

    public ProductoDescuento(int producto, int descuento, Descuento objDescuento) {
        this.producto = producto;
        this.descuento = descuento;
        this.objDescuento = objDescuento;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Descuento getObjDescuento() {
        return objDescuento;
    }

    public void setObjDescuento(Descuento objDescuento) {
        this.objDescuento = objDescuento;
    }
}
