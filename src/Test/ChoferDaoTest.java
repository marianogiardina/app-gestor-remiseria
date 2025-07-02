package Test;

import DAO.ChoferDao;
import Model.Auto;
import Model.Chofer;
import Model.Response;
import Model.Viaje;

public class ChoferDaoTest {

    protected ChoferDao choferDao;

    public ChoferDaoTest() {
        this.choferDao = ChoferDao.getInstance();
    }


    public void testCreateChofer(Chofer chofer) {

        Response<Chofer> response = choferDao.create(chofer);

        if (response.isSuccess()) {
            System.out.println("Chofer creado: " + response.getMensaje());
        } else {
            System.out.println("Error al leer el chofer: " + response.getMensaje());
        }

    }

    public void testUpdateChofer(Chofer chofer) {

        chofer.setCelular(91218);

        Response<Chofer> response = choferDao.update(chofer);

        if (response.isSuccess()) {
            System.out.println("Chofer actualizado: " + response.getMensaje());
        } else {
            System.out.println("Error al actualizar el chofer: " + response.getMensaje());
        }

    }

    public void testDeleteChofer(int id) {

        Response<Chofer> response = choferDao.delete(id);

        if (response.isSuccess()) {
            System.out.println("Chofer eliminado: " + response.getMensaje());
        } else {
            System.out.println("Error al eliminar el chofer: " + response.getMensaje());
        }

    }

    public void testReadChofer(int id) {

        Response<Chofer> response = choferDao.read(id);

        if (response.isSuccess()) {
            System.out.println("Chofer encontrado: " + response.getObj().toString());
        } else {
            System.out.println("Error al leer el chofer: " + response.getMensaje());
        }

    }

    public void testReadAllChofer() {

        /*Response<Chofer> choferResponse = choferDao.readAll();

        if (choferResponse.isSuccess()) {

            for(Chofer chofer : choferResponse.getObjList()) {
                System.out.println("Chofer encontrado: " + chofer.toString());
            }
        } else {
            System.out.println("Error al leer los viajes: " + choferResponse.getMensaje());
        }*/

    }

    public void testAsignarAuto(int choferId, int autoId) {

        /*Response<Chofer> response = choferDao.asignarAuto(choferId, autoId);

        if (response.isSuccess()) {
            System.out.println("Auto asignado al chofer: " + response.getMensaje());
        } else {
            System.out.println("Error al asignar el auto: " + response.getMensaje());
        }*/

    }

}
