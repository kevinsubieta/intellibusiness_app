package intellisoft.bo.com.intellibusiness.entity.app;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 14/05/2016.
 */
public class Notifications extends Entity {
    private String titulo;
    private String fecha;
    private String imagen;

    public Notifications(String titulo, String fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public Notifications(String titulo, String fecha, String imagen) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
