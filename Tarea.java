public class Tarea {
    private String id;
    private String nombre;
    private String descripcion;
    private String estado;
    private Boolean esAlta;

    public Tarea() {
        this.id = java.util.UUID.randomUUID().toString().substring(0, 5);
        this.esAlta = true;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Boolean getEsAlta() { return esAlta; }
    public void setEsAlta(Boolean esAlta) { this.esAlta = esAlta; }

    @Override
    public String toString() {
        return "Tarea [" +
               "código=" + id +
               ", nombre='" + nombre + '\'' +
               ", descripción='" + descripcion + '\'' +
               ", estado=" + estado +
               ", esAlta=" + esAlta +
               ']';
    }
}
