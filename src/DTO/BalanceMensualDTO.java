package DTO;

import Model.Chofer;

//Creo esta clase como un DTO para transportar datos de una capa a la otra.

public class BalanceMensualDTO {

    //Estos tres atributos son para crear el constructor de la informacion que recibo desde el DAO
    private Chofer chofer;
    private int cantidadViajesChofer;
    private double sueldoMensualChofer;


    //Estos atributos son para crear el constructor de la informacion que envio a la vista
    private int cantidadViajesTotal;
    private float promedioSueldoMensualChoferSinAuto;
    private float promedioSueldoMensualChoferConAuto;
    private float gananciaMensualChoferSinAuto;
    private float gananciaMensualChoferConAuto;
    private float gananciaMensualRemiseria;
    private float promedioViajesPorChofer;

    public BalanceMensualDTO(Chofer chofer, int cantidadViajesChofer, double sueldoMensualChofer) {
        this.chofer = chofer;
        this.cantidadViajesChofer = cantidadViajesChofer;
        this.sueldoMensualChofer = sueldoMensualChofer;
    }

    public BalanceMensualDTO(int cantidadViajesTotal, float gananciaMensualRemiseria, float promedioSueldoMensualChoferSinAuto,
                             float promedioSueldoMensualChoferConAuto, float gananciaMensualChoferSinAuto, float gananciaMensualChoferConAuto, float promedioViajesPorChofer) {
        this.cantidadViajesTotal = cantidadViajesTotal;
        this.gananciaMensualRemiseria = gananciaMensualRemiseria;
        this.promedioSueldoMensualChoferSinAuto = promedioSueldoMensualChoferSinAuto;
        this.promedioSueldoMensualChoferConAuto = promedioSueldoMensualChoferConAuto;
        this.gananciaMensualChoferSinAuto = gananciaMensualChoferSinAuto;
        this.gananciaMensualChoferConAuto = gananciaMensualChoferConAuto;
        this.promedioViajesPorChofer = promedioViajesPorChofer;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public int getCantidadViajesChofer() {
        return cantidadViajesChofer;
    }

    public void setCantidadViajesChofer(int cantidadViajesChofer) {
        this.cantidadViajesChofer = cantidadViajesChofer;
    }

    public double getSueldoMensualChofer() {
        return sueldoMensualChofer;
    }

    public void setSueldoMensualChofer(double sueldoMensualChofer) {
        this.sueldoMensualChofer = sueldoMensualChofer;
    }

    public int getCantidadViajesTotal() {
        return cantidadViajesTotal;
    }

    public void setCantidadViajesTotal(int cantidadViajesTotal) {
        this.cantidadViajesTotal = cantidadViajesTotal;
    }

    public float getPromedioSueldoMensualChoferSinAuto() {
        return promedioSueldoMensualChoferSinAuto;
    }

    public void setPromedioSueldoMensualChoferSinAuto(float promedioSueldoMensualChoferSinAuto) {
        this.promedioSueldoMensualChoferSinAuto = promedioSueldoMensualChoferSinAuto;
    }

    public float getPromedioSueldoMensualChoferConAuto() {
        return promedioSueldoMensualChoferConAuto;
    }

    public void setPromedioSueldoMensualChoferConAuto(float promedioSueldoMensualChoferConAuto) {
        this.promedioSueldoMensualChoferConAuto = promedioSueldoMensualChoferConAuto;
    }

    public float getGananciaMensualChoferSinAuto() {
        return gananciaMensualChoferSinAuto;
    }

    public void setGananciaMensualChoferSinAuto(float gananciaMensualChoferSinAuto) {
        this.gananciaMensualChoferSinAuto = gananciaMensualChoferSinAuto;
    }

    public float getGananciaMensualChoferConAuto() {
        return gananciaMensualChoferConAuto;
    }

    public void setGananciaMensualChoferConAuto(float gananciaMensualChoferConAuto) {
        this.gananciaMensualChoferConAuto = gananciaMensualChoferConAuto;
    }

    public float getGananciaMensualRemiseria() {
        return gananciaMensualRemiseria;
    }

    public void setGananciaMensualRemiseria(float gananciaMensualRemiseria) {
        this.gananciaMensualRemiseria = gananciaMensualRemiseria;
    }

    public float getPromedioViajesPorChofer() {
        return promedioViajesPorChofer;
    }

    public void setPromedioViajesPorChofer(float promedioViajesPorChofer) {
        this.promedioViajesPorChofer = promedioViajesPorChofer;
    }
}
