package com.example.findfoot.models;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    
    private Long id;
    private String nombre;
    private String correo;
    private String tipodonacion;
    private Integer cantidad;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
    this.id = id;
    }   
    
    public String getNombre() {
    return nombre;
    }
    public void setNombre(String nombre) {
    this.nombre = nombre;

    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getTipodonacion() {
        return tipodonacion;
    }
    public void setTipodonacion(String tipodonacion) {
        this.tipodonacion = tipodonacion;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}