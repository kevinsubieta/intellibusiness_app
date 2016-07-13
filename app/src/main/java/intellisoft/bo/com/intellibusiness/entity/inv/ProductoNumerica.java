package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class ProductoNumerica extends Entity {
    private int producto;
    private int numerica;
    private double valor;
    private Numerica insNumerica;

    public ProductoNumerica() {
    }

    public ProductoNumerica(int producto, int numerica, double valor, Numerica insNumerica) {
        this.producto = producto;
        this.numerica = numerica;
        this.valor = valor;
        this.insNumerica = insNumerica;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getNumerica() {
        return numerica;
    }

    public void setNumerica(int numerica) {
        this.numerica = numerica;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Numerica getInsNumerica() {
        return insNumerica;
    }

    public void setInsNumerica(Numerica insNumerica) {
        this.insNumerica = insNumerica;
    }
}
