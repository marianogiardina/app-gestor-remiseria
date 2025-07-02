package Test;

import DAO.ChoferDao;
import DAO.ViajeDao;
import Model.Chofer;
import Model.EstadoViaje;
import Model.Response;
import Model.Viaje;

import java.time.LocalDateTime;

public class ViajeDaoTest {

    protected ViajeDao viajeDao;

    public ViajeDaoTest() {
        this.viajeDao = ViajeDao.getInstance();
    }

    public void testCreateViaje(Viaje viaje) {


        /*Response<Viaje> response = viajeDao.create(viaje);

        if (response.isSuccess()) {
            System.out.println("Viaje creado: " + response.getMensaje());
        } else {
            System.out.println("Error al leer el viaje: " + response.getMensaje());
        }*/

    }

    public void testUpdateViaje(Viaje viaje) {

        /*viaje.setOrigen("Esta es la modificación del origen");

        viajeDao.update(viaje);*/

    }

    public void testReadViaje(int id) {

        /*Response<Viaje> viaje = viajeDao.read(id);

        if (viaje.isSuccess()) {
            System.out.println("Viaje encontrado: " + viaje.getObj().toString());
        } else {
            System.out.println("Error al leer el viaje: " + viaje.getMensaje());
        }*/

    }

    public void testReadAllViajes() {

        /*// Ahora sí hacer el readAll
        Response<Viaje> viajes = viajeDao.readAll();

        if (viajes.isSuccess()) {
            for (Viaje viaje : viajes.getObjList()) {
                System.out.println("Viaje encontrado: " + viaje.toString());
            }
        } else {
            System.out.println("Error al leer los viajes: " + viajes.getMensaje());
        }*/
    }
}
