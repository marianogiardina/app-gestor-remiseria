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

    //En este metodo cargo con los datos necesarios del dto para cerrar la semana del chofer y los envio al controller

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

    //En este metodo hago algo similar en el que cierro la semana, pero llamo a otra funcion que procese la data que traigo desde el DAO

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

    //En esta funcion lo que hago es utilizar el primer constructor del DTO para procesaro los datos que voy a necesitar en la vista del balance mensual
    //Recibo la lista con los balances mensuales de los choferes, y a partir de eso creo el DTO con la data que se mostrara en la vista.

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

        promedioViajePorChofer = contadorChoferes == 0 ? 0 : (float) totalViajes / contadorChoferes;

        promedioSueldoChoferConAutoPropio = contadorChoferConAutoPropio == 0 ? 0 : (float) ((contadorSueldoChoferConAutoPropio * 0.8) / contadorChoferConAutoPropio); // 80% del sueldo mensual si tiene auto propio
        promedioSueldoChoferSinAutoPropio = contadorChoferSinAutoPropio == 0 ? 0 : (float) ((contadorSueldoChoferSinAutoPropio * 0.6) / contadorChoferSinAutoPropio); // 60% del sueldo mensual si no tiene auto propio

        gananciaMensualPorChoferConAutoPropio = (float) (contadorSueldoChoferConAutoPropio * 0.2);
        gananciaMensualPorChoferSinAutoPropio = (float) (contadorSueldoChoferSinAutoPropio * 0.4);

        gananciaMensualTotal = gananciaMensualPorChoferConAutoPropio + gananciaMensualPorChoferSinAutoPropio;

        return new BalanceMensualDTO(totalViajes, gananciaMensualTotal, promedioSueldoChoferSinAutoPropio, promedioSueldoChoferConAutoPropio,
                gananciaMensualPorChoferSinAutoPropio, gananciaMensualPorChoferConAutoPropio, promedioViajePorChofer);
    }

    //En esta funcion actualizo la disponibilidad de los choferes, dependiendo del metodo que la envie, voy a evaluar si pasa desde 'en curso' a 'finalizado',-
    //o si se borro un viaje en curso, para que el estado del chofer no quede en curso

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
