package Controller;

import DAO.AutoDao;
import Model.Auto;
import Model.Chofer;
import Service.AutoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//Principalmente los controller sirven como un "pasamanos" entre la vista y el service, por lo que no hay mucha logica mas que la validacion de la respuesta que envia el service.
//Para estar cubierto de cualquier tipo de error, englobo esta logica dentro de un try-catch

public class AutoController extends BaseController<Auto>{

    private AutoService autoService;

    public AutoController() {
        this.autoService = new AutoService(AutoDao.getInstance());
    }

    @Override
    public void create(Auto obj) {
        try{

            if(autoService.create(obj).isSuccess()){

                JOptionPane.showMessageDialog(null, "Auto creado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al crear el auto: " + autoService.create(obj).getMensaje());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el auto: " + e.getMessage());
        }
    }

    @Override
    public void update(Auto obj) {

        try{
            if(autoService.update(obj).isSuccess()){

                JOptionPane.showMessageDialog(null, "Auto actualizado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al actualizar el auto: " + autoService.update(obj).getMensaje());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el auto: " + e.getMessage());
        }

    }

    @Override
    public void delete(int id) {

        try {

            if(autoService.delete(id).isSuccess()){

                JOptionPane.showMessageDialog(null, "Auto eliminado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al eliminar el auto: " + autoService.delete(id).getMensaje());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el auto: " + e.getMessage());
        }

    }

    @Override
    public Auto read(int id) {

            Auto auto = null;

        try {

            if(autoService.read(id).isSuccess()){

                auto = autoService.read(id).getObj();

            } else {

                JOptionPane.showMessageDialog(null, "Error al leer el auto: " + autoService.read(id).getMensaje());

            }

        }catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al leer el auto: " + e.getMessage());

        }

        return auto;
    }

    @Override
    public List<Auto> readAll() {
        List<Auto> listadoAutos = new ArrayList<>();

        try{

            if(autoService.readAll().isSuccess()){

                listadoAutos = autoService.readAll().getObjList();

            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el lisado de autos");
            }



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontraron los autos");
        }

        return listadoAutos;
    }
}
