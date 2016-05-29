package intellisoft.bo.com.intellibusiness.entity.mark;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ProductoDescuento extends Entity {
    private int producto;
    private int descuento;
    private Descuento insDescuento;

    public ProductoDescuento() {
    }

    public ProductoDescuento(int producto, int descuento, Descuento insDescuento) {
        this.producto = producto;
        this.descuento = descuento;
        this.insDescuento = insDescuento;
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

    public Descuento getInsDescuento() {
        return insDescuento;
    }

    public void setInsDescuento(Descuento insDescuento) {
        this.insDescuento = insDescuento;
    }
}
