package Model;

public class Usuario {

    private int usuarioId;

    private String nombre;

    private String contrasena;

    public Usuario(int usuarioId, String contrasena, String nombre) {
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
