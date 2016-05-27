package intellisoft.bo.com.intellibusiness.entity.mark;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 27/05/2016.
 */
public class Notificacion extends Entity {
    private String texto;
    private String imagen;

    public Notificacion(String texto, String imagen) {
        this.texto = texto;
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
