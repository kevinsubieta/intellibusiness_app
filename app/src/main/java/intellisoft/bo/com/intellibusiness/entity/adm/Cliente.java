package intellisoft.bo.com.intellibusiness.entity.adm;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

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
