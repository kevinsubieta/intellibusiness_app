package intellisoft.bo.com.intellibusiness.entity.inv;

/**
 * Created by kevin on 06/06/2016.
 */
public class Suggestion {
    private int id;
    private String nombre;
    private String price;


    public Suggestion(int id, String nombre, String price) {
        this.id = id;
        this.nombre = nombre;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
