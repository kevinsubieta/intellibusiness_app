package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class Escalar extends Entity {
    private String nombre;

    public Escalar() {
    }

    public Escalar(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
