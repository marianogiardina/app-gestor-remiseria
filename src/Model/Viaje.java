package Model;

import java.time.LocalDateTime;

//En esta clase podemos ver una relacion de agregacion con chofer, ya que el viaje depende del chofer, pero el chofer no del viaje

public class Viaje {

    private int id;
    private double kilometros;
    private double valorKm;
    private EstadoViaje estadoViaje;
    private Chofer chofer;
    private String origen;
    private String destino;
    private LocalDateTime fecha;

    //creo un constructor sin id para utilizar cuando se cree el viaje, despues se asignara cuando llegue a la bbdd
    public Viaje( double kilometros, double valorKm, EstadoViaje estadoViaje, Chofer chofer, String origen, String destino, LocalDateTime fecha) {
        this.kilometros = kilometros;
        this.valorKm = valorKm;
        this.estadoViaje = estadoViaje;
        this.chofer = chofer;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public Viaje(int id, double kilometros, double valorKm, EstadoViaje estadoViaje, Chofer chofer, String origen, String destino, LocalDateTime fecha) {
        this.id = id;
        this.kilometros = kilometros;
        this.valorKm = valorKm;
        this.estadoViaje = estadoViaje;
        this.chofer = chofer;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public double getValorKm() {
        return valorKm;
    }

    public void setValorKm(double valorKm) {
        this.valorKm = valorKm;
    }

    public EstadoViaje getEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(EstadoViaje estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "id=" + id +
                ", kilometros=" + kilometros +
                ", valorKm=" + valorKm +
                ", estadoViaje=" + estadoViaje +
                ", chofer=" + chofer +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
