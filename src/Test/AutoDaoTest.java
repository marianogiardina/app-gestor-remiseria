package Test;

import DAO.AutoDao;
import Model.Auto;
import Model.Response;

public class AutoDaoTest {
    
    protected AutoDao autoDao;

    public AutoDaoTest() {
        this.autoDao = new AutoDao();
    }



    public void testCreateAuto(Auto auto) {
        /*
        Response<Auto> response = autoDao.create(auto);

        if (response.isSuccess()) {
            System.out.println("Auto creado: " + response.getMensaje() + " con ID: " + response.getObjId());
        } else {
            System.out.println("Error al crear el auto: " + response.getMensaje());
        }*/

    }

    public void testUpdateAuto(Auto auto) {

        /*auto.setKilometraje(50.000);
        Response<Auto> response = autoDao.update(auto);

        if (response.isSuccess()) {
            System.out.println("Auto actualizado: " + response.getMensaje());
        } else {
            System.out.println("Error al actualizar el auto: " + response.getMensaje());
        }*/

    }

    public void testReadAuto(int id) {

        /*Response<Auto> response = autoDao.read(id);

        if (response.isSuccess()) {
            System.out.println("Auto encontrado: " + response.getObj().toString());
        } else {
            System.out.println("Error al leer el auto: " + response.getMensaje());
        }*/

    }

    public void testDeleteAuto(Auto auto) {

        /*Response<Auto> response = autoDao.delete(auto.getId());

        if (response.isSuccess()) {
            System.out.println("Auto eliminado: " + response.getMensaje());
        } else {
            System.out.println("Error al eliminar el auto: " + response.getMensaje());
        }*/

    }

    public void testReadAllAutos() {

        /*Response<Auto> response = autoDao.readAll();

        if (response.isSuccess()) {
            for (Auto auto : response.getObjList()) {
                System.out.println("Auto encontrado: " + auto.toString());
            }
        } else {
            System.out.println("Error al leer los autos: " + response.getMensaje());
        }*/

    }

    public void testAsignarChofer(int autoId, int choferId) {

        /*Response<Auto> response = autoDao.asignarAuto(autoId);

        if (response.isSuccess()) {
            System.out.println("Chofer asignado al auto: " + response.getMensaje());
        } else {
            System.out.println("Error al asignar el chofer: " + response.getMensaje());
        }*/

    }

}
