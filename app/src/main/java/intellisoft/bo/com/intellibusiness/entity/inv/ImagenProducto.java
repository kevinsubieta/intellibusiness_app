package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 13/05/2016.
 */
public class ImagenProducto extends Entity {
    private int producto;
    private String url;

    public ImagenProducto() {
    }

    public ImagenProducto(int producto, String url) {
        this.producto = producto;
        this.url = url;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
