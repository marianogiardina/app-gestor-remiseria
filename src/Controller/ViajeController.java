package Controller;

import DAO.ViajeDao;
import DTO.BalanceMensualDTO;
import DTO.SemanaChoferDTO;
import Model.Response;
import Model.Viaje;
import Service.ViajeService;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Como en todos los controller, oficia de pasamanos entre la peticion de la vista y el service.

public class ViajeController extends BaseController<Viaje> {

    private ViajeService viajeService;

    public ViajeController() {
        this.viajeService = new ViajeService(ViajeDao.getInstance());
    }

    @Override
    public void create(Viaje obj) {

        try{

            Response<Viaje> response = viajeService.create(obj);

            if(response.isSuccess()){

                JOptionPane.showMessageDialog(null, "Viaje creado exitosamente");

            }else{

                JOptionPane.showMessageDialog(null, "Error al crear el viaje: " + response.getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al crear el viaje: " + e.getMessage());

        }

    }

    @Override
    public void update(Viaje obj) {

        try{

            Response<Viaje> response = viajeService.update(obj);

            if(response.isSuccess()){

                JOptionPane.showMessageDialog(null, "Viaje actualizado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al actualizar el viaje: " + response.getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al actualizar el viaje: " + e.getMessage());

        }

    }

    @Override
    public void delete(int id) {
        try {

            Response<Viaje> response = viajeService.delete(id);

            if (response.isSuccess()) {

                JOptionPane.showMessageDialog(null, "Viaje eliminado exitosamente");

            } else {

                JOptionPane.showMessageDialog(null, "Error al eliminar el viaje: " + response.getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar el viaje: " + e.getMessage());

        }
    }

    @Override
    public Viaje read(int id) {

        Viaje viaje = null;

       try{

           if(viajeService.read(id).isSuccess()){

               viaje = viajeService.read(id).getObj();

           } else {

               JOptionPane.showMessageDialog(null, viajeService.read(id).getMensaje());

           }

       }catch(Exception e){

              JOptionPane.showMessageDialog(null, "Error al obtener el viaje: " + e.getMessage());

       }

        return viaje;
    }

    @Override
    public List<Viaje> readAll() {

        List<Viaje> viajes = new ArrayList<>();

        try{

            if(viajeService.readAll().isSuccess()){

                viajes = viajeService.readAll().getObjList();

            }else{

                JOptionPane.showMessageDialog(null, viajeService.readAll().getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No se encontraron los viajes" + e.getMessage());

        }

        return viajes;

    }

    public List<SemanaChoferDTO> getSemanaChofer(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        List<SemanaChoferDTO> semanaChofers = new ArrayList<>();

        try {
            Response<SemanaChoferDTO> response = viajeService.cerrarSemanaChoferes(fechaInicio, fechaFin);

            if (response.isSuccess()) {

                semanaChofers = response.getObjList();

            } else {

                JOptionPane.showMessageDialog(null, "Error al obtener la semana de choferes: " + response.getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener la semana de choferes: " + e.getMessage());

        }

        return semanaChofers;

    }

    public BalanceMensualDTO getBalanceMensual(){

        BalanceMensualDTO balanceMensual = null;

        try {
            Response<BalanceMensualDTO> response = viajeService.realizarBalanceMensual();

            if (response.isSuccess()) {

                balanceMensual = response.getObj();

            } else {

                JOptionPane.showMessageDialog(null, "Error al obtener el balance mensual: " + response.getMensaje());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener el balance mensual: " + e.getMessage());

        }

        return balanceMensual;
    }
}
