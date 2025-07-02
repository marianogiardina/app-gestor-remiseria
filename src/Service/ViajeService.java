package Service;

import DAO.AutoDao;
import DAO.ChoferDao;
import DAO.ViajeDao;
import DTO.BalanceMensualDTO;
import DTO.SemanaChoferDTO;
import Model.Chofer;
import Model.EstadoViaje;
import Model.Response;
import Model.Viaje;

import java.time.LocalDateTime;
import java.util.List;

public class ViajeService extends BaseService<Viaje, ViajeDao> {

    public ViajeService( ViajeDao viajeDao ) {
        super(viajeDao);
    }

    @Override
    public Response<Viaje> create(Viaje obj) {

        try{
            Response<Viaje> response = dao.create(obj);

            if(response.isSuccess()){

                actualizarDisponibilidadChofer(obj, "create");

                actualizarKilometrajeAuto(obj.getChofer(), obj.getKilometros());

                return response;

            } else {

                return new Response<>("Error al crear el viaje: " + response.getMensaje(), 500, false);

            }

        } catch (Exception e) {

            return new Response<>("Error al crear el viaje: " + e.getMessage(), 500, false, "ViajeService", "create");

        }


    }

    @Override
    public Response<Viaje> update(Viaje obj) {

        try {
            Response<Viaje> response = dao.update(obj);

            if(response.isSuccess()){

                actualizarDisponibilidadChofer(obj, "update");

                return response;

            } else {

                return new Response<>("Error al actualizar el viaje: " + response.getMensaje(), 500, false);

            }

        } catch (Exception e) {
            return new Response<>("Error al actualizar el viaje: " + e.getMessage(), 500, false, "ViajeService", "update");
        }
    }

    @Override
    public Response<Viaje> delete(int id) {

        try {

            actualizarDisponibilidadChofer(dao.read(id).getObj(), "delete");

            Response<Viaje> response = dao.delete(id);

            if(response.isSuccess()){

                return response;

            }else {

                return new Response<>("Error al eliminar el viaje con ID: " + id, 404, false);

            }

        } catch (Exception e) {
            return new Response<>("Error al eliminar el viaje: " + e.getMessage(), 404, false, "ViajeService", "delete");
        }
    }

    @Override
    public Response<Viaje> read(int id) {

        try{
            Response<Viaje> response = dao.read(id);

            if(response.isSuccess()){

                return response;

            }else{
                return new Response<>("Error al obtener el viaje con ID: " + id, 404, false);
            }

        } catch (Exception e) {
            return new Response<>(e.getMessage(), 404, false, "ViajeService", "read");
        }
    }

    @Override
    public Response<Viaje> readAll() {

        try{
            Response<Viaje> response = dao.readAll();

            if(response.isSuccess()){

                return response;

            }else {

                return new Response<>("Error al obtener los viajes", 404, false);
            }
        }catch(Exception e){

            return new Response<>("Error al obtener los viajes: " + e.getMessage(), 404, false, "ViajeService", "readAll");

        }


    }

    public Response<SemanaChoferDTO> cerrarSemanaChoferes(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            Response<SemanaChoferDTO> response = dao.selectSemanaChofer(fechaInicio, fechaFin);

            if (response.isSuccess()) {
                //Calculo cuanto debe cobrar cada chofer, ajustando el valor por el porcentaje de comision si tiene auto propio
                //corresponde 80% del viaje si no tiene auto propio 60%
                List<SemanaChoferDTO> semanaChofers = response.getObjList();

                float porcentajeDeViaje;

                for( SemanaChoferDTO semanaChofer : semanaChofers) {

                    if (semanaChofer.getChofer().isAutoPropio()) {
                        porcentajeDeViaje = 0.8f; // 80% del viaje
                    } else {
                        porcentajeDeViaje = 0.6f; // 60% del viaje
                    }

                    semanaChofer.setSueldoSemanal(semanaChofer.getSueldoSemanal()* porcentajeDeViaje);
                }

                return new Response<>(response.getMensaje(), response.getCodigo(), response.isSuccess(), semanaChofers);
            } else {
                return new Response<>("Error al cerrar la semana de los choferes: " + response.getMensaje(), 500, false);
            }

        } catch (Exception e) {
            return new Response<>("Error al cerrar la semana de los choferes: " + e.getMessage(), 500, false, "ViajeService", "cerrarSemanaChoferes");
        }


    }

    public Response<BalanceMensualDTO> realizarBalanceMensual() {
        try {
            Response<BalanceMensualDTO> response = dao.selectViajesMensual();

            if (response.isSuccess()) {

                BalanceMensualDTO balanceMensual = procesarDataBalanceMensual(response.getObjList());

                return new Response<>(response.getMensaje(), response.getCodigo(), response.isSuccess(), balanceMensual);
            } else {
                return new Response<>("Error al realizar el balance mensual: " + response.getMensaje(), 500, false);
            }

        } catch (Exception e) {
            return new Response<>("Error al realizar el balance mensual: " + e.getMessage(), 500, false, "ViajeService", "realizarBalanceMensual");
        }
    }

    private BalanceMensualDTO procesarDataBalanceMensual(List<BalanceMensualDTO> listaBalanceMensual) {

        int totalViajes = 0;
        int contadorChoferes = 0;
        float promedioViajePorChofer; //dividiendo totalViajes / contadorChoferes


        int contadorChoferConAutoPropio = 0;
        int contadorChoferSinAutoPropio = 0;
        float contadorSueldoChoferConAutoPropio = 0;
        float contadorSueldoChoferSinAutoPropio = 0;
        float promedioSueldoChoferConAutoPropio;//dividiendo contadorSueldoChoferConAutoPropio / contadorChoferConAutoPropio
        float promedioSueldoChoferSinAutoPropio;//dividiendo contadorSueldoChoferSinAutoPropio / contadorChoferSinAutoPropio

        float gananciaMensualPorChoferConAutoPropio;
        float gananciaMensualPorChoferSinAutoPropio;

        float gananciaMensualTotal;

        for (BalanceMensualDTO dto : listaBalanceMensual) {


            totalViajes += dto.getCantidadViajesChofer();

            contadorChoferes++;

            if(dto.getChofer().isAutoPropio()){

                contadorChoferConAutoPropio++;

                contadorSueldoChoferConAutoPropio += (float) dto.getSueldoMensualChofer();

            }else {

                contadorChoferSinAutoPropio++;

                contadorSueldoChoferSinAutoPropio += (float) dto.getSueldoMensualChofer();

            }

        }

        promedioViajePorChofer = (float) totalViajes / contadorChoferes;

        promedioSueldoChoferConAutoPropio = (float) ((contadorSueldoChoferConAutoPropio * 0.8) / contadorChoferConAutoPropio); // 80% del sueldo mensual si tiene auto propio
        promedioSueldoChoferSinAutoPropio = (float) ((contadorSueldoChoferSinAutoPropio * 0.6) / contadorChoferSinAutoPropio); // 60% del sueldo mensual si no tiene auto propio

        gananciaMensualPorChoferConAutoPropio = (float) (contadorSueldoChoferConAutoPropio * 0.2);
        gananciaMensualPorChoferSinAutoPropio = (float) (contadorSueldoChoferSinAutoPropio * 0.4);

        gananciaMensualTotal = gananciaMensualPorChoferConAutoPropio + gananciaMensualPorChoferSinAutoPropio;

        return new BalanceMensualDTO(totalViajes, gananciaMensualTotal, promedioSueldoChoferSinAutoPropio, promedioSueldoChoferConAutoPropio,
                gananciaMensualPorChoferSinAutoPropio, gananciaMensualPorChoferConAutoPropio, promedioViajePorChofer);
    }

    private void actualizarDisponibilidadChofer(Viaje viaje, String metodo) {

        Chofer chofer = viaje.getChofer();

        ChoferService choferService = new ChoferService(ChoferDao.getInstance());

        switch (metodo) {
            case "create", "update":

                if(viaje.getEstadoViaje() == EstadoViaje.FINALIZADO) {

                    chofer.setDisponible(true);

                    choferService.update(chofer);

                } else if (viaje.getEstadoViaje() == EstadoViaje.ENCURSO) {

                    chofer.setDisponible(false);

                    choferService.update(chofer);

                }
                break;
            case "delete":

                chofer.setDisponible(true);

                choferService.update(chofer);

                break;
        }
    }

    private void actualizarKilometrajeAuto(Chofer chofer, double kilometros) {

        AutoService autoService = new AutoService(AutoDao.getInstance());

        if (chofer.getAutoAlquilado() != null) {
            chofer.getAutoAlquilado().setKilometraje(chofer.getAutoAlquilado().getKilometraje() + kilometros);

           autoService.update(chofer.getAutoAlquilado());
        }
    }
}
