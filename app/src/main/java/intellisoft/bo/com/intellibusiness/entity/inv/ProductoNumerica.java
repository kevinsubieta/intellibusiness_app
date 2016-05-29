package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class ProductoNumerica extends Entity {
    private int producto;
    private int propiedadnumerica;
    private double valor;
    private Numerica numerica;

    public ProductoNumerica() {
    }

    public ProductoNumerica(int producto, int propiedadnumerica, double valor, Numerica numerica) {
        this.producto = producto;
        this.propiedadnumerica = propiedadnumerica;
        this.valor = valor;
        this.numerica = numerica;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getPropiedadnumerica() {
        return propiedadnumerica;
    }

    public void setPropiedadnumerica(int propiedadnumerica) {
        this.propiedadnumerica = propiedadnumerica;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Numerica getNumerica() {
        return numerica;
    }

    public void setNumerica(Numerica numerica) {
        this.numerica = numerica;
    }
}
