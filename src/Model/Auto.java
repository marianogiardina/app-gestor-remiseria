package Model;

public class Auto {

    private int id;
    private double kilometraje;
    private String marca;
    private String modelo;
    private boolean disponible;

    public Auto(int id, double kilometraje, String marca, String modelo, boolean disponible) {
        this.id = id;
        this.kilometraje = kilometraje;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = disponible;
    }

    // Constructor sin id para utilizar cuando se crea el auto, luego para leerlo utilizo el constructor con id

    public Auto(double kilometraje, String marca, String modelo, boolean disponible) {
        this.kilometraje = kilometraje;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = disponible;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return this.marca + this.modelo;
    }
}
