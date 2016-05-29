package intellisoft.bo.com.intellibusiness.entity.inv;

import java.util.List;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class Producto extends Entity {
    private String nombre;
    private List<ProductoEscalar> lstProductoEscalar;
    private List<ProductoNumerica> lstProductoNumerica;

    public Producto() {
    }

    public Producto(String nombre, List<ProductoEscalar> lstProductoEscalar, List<ProductoNumerica> lstProductoNumerica) {
        this.nombre = nombre;
        this.lstProductoEscalar = lstProductoEscalar;
        this.lstProductoNumerica = lstProductoNumerica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProductoEscalar> getLstProductoEscalar() {
        return lstProductoEscalar;
    }

    public void setLstProductoEscalar(List<ProductoEscalar> lstProductoEscalar) {
        this.lstProductoEscalar = lstProductoEscalar;
    }

    public List<ProductoNumerica> getLstProductoNumerica() {
        return lstProductoNumerica;
    }

    public void setLstProductoNumerica(List<ProductoNumerica> lstProductoNumerica) {
        this.lstProductoNumerica = lstProductoNumerica;
    }
}
