package intellisoft.bo.com.intellibusiness.entity.inv;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by kevin on 29/05/2016.
 */
public class ValorEscalar extends Entity {
    private int escalar;
    private String valor;
    private Escalar insEscalar;

    public ValorEscalar() {
    }

    public ValorEscalar(int escalar, String valor, Escalar insEscalar) {
        this.escalar = escalar;
        this.valor = valor;
        this.insEscalar = insEscalar;
    }

    public int getEscalar() {
        return escalar;
    }

    public void setEscalar(int escalar) {
        this.escalar = escalar;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Escalar getInsEscalar() {
        return insEscalar;
    }

    public void setInsEscalar(Escalar insEscalar) {
        this.insEscalar = insEscalar;
    }
}
