package intellisoft.bo.com.intellibusiness.entity.adm;

import intellisoft.bo.com.intellibusiness.entity.Principal.Entity;

/**
 * Created by Subieta on 15/05/2016.
 */
public class Usuario extends Entity {
    private String loggin;
    private String pass;
    private int ci;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private Cliente cliente;

    public Usuario(String loggin, String pass, int ci, String nombres, String apellidos, String email,
                   String telefono, String direccion, Cliente cliente) {
        this.loggin = loggin;
        this.pass = pass;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cliente = cliente;
    }

    public String getLoggin() {
        return loggin;
    }

    public void setLoggin(String loggin) {
        this.loggin = loggin;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
