package intellisoft.bo.com.intellibusiness.entity.mark;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 27/05/2016.
 */
public class Inbox extends Entity {
    private int idc;
    private int idn;
    private boolean eliminado;
    private Notificacion notification;

    public Inbox(int idc, int idn, boolean eliminado, Notificacion notification) {
        this.idc = idc;
        this.idn = idn;
        this.eliminado = eliminado;
        this.notification = notification;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Notificacion getNotification() {
        return notification;
    }

    public void setNotification(Notificacion notification) {
        this.notification = notification;
    }
}
