package co.FindFoodApp.dto;

import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;

/**
 * Clase para mapear los datos de las donaciones a la vista.
 * @author Alejandro Herrera Montilla
 * @version 1.0
 */

public class DonacionDTO {
    private String id;
    private String donante;
    private String beneficiario;
    private String fecha;
    private String descripcion;
    // Disponible, Seleccionada, Finalizada
    private String estado;

    public DonacionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonante() {
        return donante;
    }

    public void setDonante(String donante) {
        this.donante = donante;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DonacionDTO{" +
                "id='" + id + '\'' +
                ", donante='" + donante + '\'' +
                ", beneficiario='" + beneficiario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    //    public DonacionDTO getDonacionView(DonacionModel donacion, DonanteModel donante, BeneficiarioModel beneficiario){
//        DonacionDTO donacionDTO = new DonacionDTO();
//        donacionDTO.setId(donacion.getId());
//        donacionDTO.setDonante(donante.getNombre());
//        donacionDTO.setDonante(beneficiario.getNombre());
//        donacionDTO.setFecha(donacion.getFecha());
//        donacionDTO.setDescripcion(donacion.getDescripcion());
//        donacionDTO.setEstado(donacion.getEstado());
//        return donacionDTO;
//    }
}
