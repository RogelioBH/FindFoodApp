package co.FindFoodApp.enums;

public enum EstadoDonacion {

    DISPONIBLE("1","Disponible"),
    SELECCIONADA("2","Seleccionada"),
    FINALIZADA("3","Finalizada"),
    CANCELADA("4","Cancelada");

    private String codigo;
    private String nombre;

    EstadoDonacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
