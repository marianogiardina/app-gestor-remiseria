package DTO;

import Model.Chofer;

//Esta clase va a ser utilizada por el service y por la bbdd para transportar informacion de una capa a otra.

public class SemanaChoferDTO {

    private Chofer chofer;

    private int cantidadViajes;

    private double cantidadKilometros;

    private double sueldoSemanal;

    public SemanaChoferDTO(Chofer chofer, int cantidadViajes, double cantidadKilometros, double sueldoSemanal) {
        this.chofer = chofer;
        this.cantidadViajes = cantidadViajes;
        this.cantidadKilometros = cantidadKilometros;
        this.sueldoSemanal = sueldoSemanal;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public int getCantidadViajes() {
        return cantidadViajes;
    }

    public void setCantidadViajes(int cantidadViajes) {
        this.cantidadViajes = cantidadViajes;
    }

    public double getCantidadKilometros() {
        return cantidadKilometros;
    }

    public void setCantidadKilometros(double cantidadKilometros) {
        this.cantidadKilometros = cantidadKilometros;
    }

    public double getSueldoSemanal() {
        return sueldoSemanal;
    }

    public void setSueldoSemanal(double sueldoSemanal) {
        this.sueldoSemanal = sueldoSemanal;
    }
}
