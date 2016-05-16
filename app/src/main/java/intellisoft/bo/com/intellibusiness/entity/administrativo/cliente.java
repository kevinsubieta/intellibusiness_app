package intellisoft.bo.com.intellibusiness.entity.administrativo;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 15/05/2016.
 */
public class Cliente extends Entity {
    String gcm;

    public Cliente() {
    }

    public Cliente(String gcm) {
        this.gcm = gcm;
    }

    public String getGcm() {
        return gcm;
    }

    public void setGcm(String gcm) {
        this.gcm = gcm;
    }
}
