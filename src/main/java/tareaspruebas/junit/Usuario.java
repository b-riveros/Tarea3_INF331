package tareaspruebas.junit;

public class Usuario {
    private String id;
    private String nombre;
    private String departamento;
    private String descripcion;

    public Usuario(String id, String nombre, String departamento, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.descripcion = descripcion;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

