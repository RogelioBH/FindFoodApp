package co.FindFoodApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection="usuario")
public class UsuarioModel {

    @Id
    private String id;
    @NotEmpty(message = "No puede estar vacio.")
    private String nombre;
    @NotEmpty(message = "No puede estar vacio.")
    private String correo;
    @NotEmpty(message = "No puede estar vacio.")
    private String telefono;
    @NotEmpty(message = "No puede estar vacio.")
    private String username;
    private String password;
    private String hash;
    @NotEmpty(message = "No puede estar vacio.")
    private String rol;

    public UsuarioModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
