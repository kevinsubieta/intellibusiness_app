package intellisoft.bo.com.intellibusiness.entity.adm;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 07/06/2016.
 */
public class Empresa extends Entity {
    private String nombre;
    private String pais;
    private String correo;
    private String logo;


    public Empresa() {
    }

    public Empresa(String nombre, String pais, String correo, String logo) {
        this.nombre = nombre;
        this.pais = pais;
        this.correo = correo;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
