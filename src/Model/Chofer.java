package Model;

//En esta clase podemos ver una relacion de asociacion, un auto puede existir sin chofer, y el chofer puede existir sin el auto

public class Chofer {

    private int id;
    private String nombre;
    private int dni;
    private long celular;
    private boolean autoPropio;
    private boolean disponible;
    private Auto autoAlquilado;

    public Chofer(int id, String nombre, int dni, long celular, boolean autoPropio, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.autoPropio = autoPropio;
        this.disponible = disponible;
        this.autoAlquilado = null; // Auto alquilado inicialmente es null
    }

    public Chofer(int id, String nombre, int dni, long celular, boolean autoPropio, boolean disponible, Auto autoAlquilado) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.autoPropio = autoPropio;
        this.disponible = disponible;
        this.autoAlquilado = autoAlquilado;
    }

    //creo dos constructores sin id para utilizar cuando se crea, ya que no tienen id hasta no llegar a la bbdd
    public Chofer(String nombre, int dni, long celular, boolean autoPropio, boolean disponible) {
        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.autoPropio = autoPropio;
        this.disponible = disponible;
        this.autoAlquilado = null; // Auto alquilado inicialmente es null
    }

    public Chofer(String nombre, int dni, long celular, boolean autoPropio, boolean disponible, Auto autoAlquilado) {

        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.autoPropio = autoPropio;
        this.disponible = disponible;
        this.autoAlquilado = autoAlquilado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public Auto getAutoAlquilado() {
        return autoAlquilado;
    }

    public void setAutoAlquilado(Auto autoAlquilado) {
        this.autoAlquilado = autoAlquilado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public long getCelular() {
        return celular;
    }

    public boolean isAutoPropio() {
        return autoPropio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setAutoPropio(boolean autoPropio) {
        this.autoPropio = autoPropio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


    //Utilizo el toString solo devolviendo el nombre del chofer, para que se muestre en la vista en el comboBox
    @Override
    public String toString() {
        return this.nombre;
    }
}
