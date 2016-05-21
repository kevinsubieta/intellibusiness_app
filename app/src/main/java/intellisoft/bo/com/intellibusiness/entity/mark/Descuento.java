package intellisoft.bo.com.intellibusiness.entity.mark;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class Descuento extends Entity {
    String descripcion;
    long inicio;
    long fin;
    int porcentaje;
    boolean baja;

    public Descuento() {
    }

    public Descuento(String descripcion, long inicio, long fin, int porcentaje, boolean baja) {
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.porcentaje = porcentaje;
        this.baja = baja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public long getFin() {
        return fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }
}
