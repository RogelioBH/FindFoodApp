package co.FindFoodApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="donacion")
public class DonacionModel {

    @Id
    private String id;

    private DonanteModel donante;
    private BeneficiarioModel Beneficiario;

    private String fecha;
    private String descripcion;

    // Disponible, Seleccionada, Finalizada
    private Boolean estado;

    public DonacionModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DonanteModel getDonante() {
        return donante;
    }

    public void setDonante(DonanteModel donante) {
        this.donante = donante;
    }

    public BeneficiarioModel getBeneficiario() {
        return Beneficiario;
    }

    public void setBeneficiario(BeneficiarioModel beneficiario) {
        Beneficiario = beneficiario;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
