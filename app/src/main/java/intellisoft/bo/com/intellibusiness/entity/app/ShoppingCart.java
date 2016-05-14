package intellisoft.bo.com.intellibusiness.entity.app;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ShoppingCart extends Entity{
    private String image;
    private String nombre;
    private double precio;

    public ShoppingCart() {
    }

    public ShoppingCart(String image, String nombre, double precio) {
        this.image = image;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
