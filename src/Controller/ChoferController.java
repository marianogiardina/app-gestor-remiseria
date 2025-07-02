package Controller;

import DAO.ChoferDao;
import Model.Chofer;
import Model.Response;
import Model.Viaje;
import Service.ChoferService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferController extends BaseController<Chofer> {

    private ChoferService choferService;

    public ChoferController() {
        this.choferService = new ChoferService(ChoferDao.getInstance());
    }

    @Override
    public void create(Chofer obj) {
        try{

            Response<Chofer> response = choferService.create(obj);

            if(response.isSuccess()){

                JOptionPane.showMessageDialog(null, "Chofer creado exitosamente");

            }else{

                JOptionPane.showMessageDialog(null, "Error al crear el chofer: " + response.getMensaje());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el chofer: " + e.getMessage());
        }
    }

    @Override
    public void update(Chofer obj) {
        try{

            Response<Chofer> response = choferService.update(obj);

            if(response.isSuccess()){

                JOptionPane.showMessageDialog(null, "Chofer actualizado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al actualizar el chofer: " + response.getMensaje());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el chofer: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {

            Response<Chofer> response = choferService.delete(id);

            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, "Chofer eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el chofer: " + response.getMensaje());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el chofer: " + e.getMessage());
        }
    }

    @Override
    public Chofer read(int id) {

        Chofer chofer = null;

        if(choferService.read(id).isSuccess()){

            chofer = choferService.read(id).getObj();

        }

        return chofer;
    }

    @Override
    public List<Chofer> readAll() {
        List<Chofer> listadoChofer = new ArrayList<>();

        try{

            if(choferService.readAll().isSuccess()){

                listadoChofer = choferService.readAll().getObjList();

            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el listado de choferes");
            }



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontraron los choferes: " + e.getMessage());
        }

        return listadoChofer;
    }
}
