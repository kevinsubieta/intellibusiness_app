package intellisoft.bo.com.intellibusiness.entity.inv;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class ProductoEscalar extends Entity {
    private int producto;
    private int escalar;
    private int valorEscalar;
    private List<ValorEscalar> insValorEscalar;

    public ProductoEscalar() {
    }

    public ProductoEscalar(int producto, int escalar, int valorEscalar, List<ValorEscalar> insValorEscalar) {
        this.producto = producto;
        this.escalar = escalar;
        this.valorEscalar = valorEscalar;
        this.insValorEscalar = insValorEscalar;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getEscalar() {
        return escalar;
    }

    public void setEscalar(int escalar) {
        this.escalar = escalar;
    }

    public int getValorEscalar() {
        return valorEscalar;
    }

    public void setValorEscalar(int valorEscalar) {
        this.valorEscalar = valorEscalar;
    }

    public List<ValorEscalar> getInsValorEscalar() {
        return insValorEscalar;
    }

    public void setInsValorEscalar(List<ValorEscalar> insValorEscalar) {
        this.insValorEscalar = insValorEscalar;
    }
}
