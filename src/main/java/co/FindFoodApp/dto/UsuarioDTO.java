package co.FindFoodApp.dto;

import co.FindFoodApp.models.UsuarioModel;

public class UsuarioDTO {
    private String id;
    private String username;
    private String rol;
    private String hash;

    public UsuarioDTO() {
    }

    public UsuarioDTO getUsuarioLogin(UsuarioModel usuarioModel, String hash){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioModel.getId());
        usuarioDTO.setUsername(usuarioModel.getUsername());
        usuarioDTO.setRol(usuarioModel.getRol());
        usuarioDTO.setHash(hash);
        return usuarioDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", rol='" + rol + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
