package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ImagenProducto extends Entity {
    String url;

    public ImagenProducto() {
    }

    public ImagenProducto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
