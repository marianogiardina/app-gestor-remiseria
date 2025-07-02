package Service;

import DAO.AutoDao;
import DAO.ChoferDao;
import Model.Auto;
import Model.Chofer;
import Model.Response;
import Model.Viaje;

//Este service es casi lo mismo que el AutoService, pero agrego un metodo para actualizar la disponibilidad del auto. Como esta disponibilidad depende del chofer y no-
//del auto mismo, la utilizo en el ChoferService.

public class ChoferService extends BaseService<Chofer, ChoferDao> {

    public ChoferService(ChoferDao choferDao) {
        super(choferDao);
    }

    @Override
    public Response<Chofer> create(Chofer obj) {
        try{
            Response<Chofer> response = dao.create(obj);

            if(response.isSuccess()){

                actualizarDisponibilidadDeAuto(obj, "create");

                return response;

            } else {

                return new Response<>("Error al crear el chofer: " + response.getMensaje(), 500, false);

            }

        } catch (Exception e) {

            return new Response<>("Error al crear el chofer: " + e.getMessage(), 500, false, "ChoferService", "create");

        }
    }

    @Override
    public Response<Chofer> update(Chofer obj) {

        try {

            Response<Chofer> response = dao.update(obj);

            if(response.isSuccess()){

                actualizarDisponibilidadDeAuto(obj, "update");

                return response;

            } else {

                return new Response<>("Error al actualizar el chofer: " + response.getMensaje(), 500, false);

            }

        } catch (Exception e) {
            return new Response<>("Error al actualizar el chofer: " + e.getMessage(), 500, false, "ChoferService", "update");
        }
    }

    @Override
    public Response<Chofer> delete(int id) {
        try {

            actualizarDisponibilidadDeAuto(dao.read(id).getObj(), "delete");

            Response<Chofer> response = dao.delete(id);

            if(response.isSuccess()){

                return response;

            }else {

                return new Response<>("Error al eliminar el chofer", 500, false);

            }

        } catch (Exception e) {

            return new Response<>("Error al eliminar el chofer: " + e.getMessage(), 500, false, "ChoferService", "delete");

        }
    }

    @Override
    public Response<Chofer> read(int id) {

        try{

            Response<Chofer> response = dao.read(id);

            if(response.isSuccess()){

                return response;

            }else{

                return new Response<>("Error al obtener el chofer con ID: " + id, 404, false);

            }

        } catch (Exception e) {
            return new Response<>(e.getMessage(), 404, false, "ChoferService", "read");
        }
    }

    @Override
    public Response<Chofer> readAll() {
        try{
            Response<Chofer> response = dao.readAll();

            if(response.isSuccess()){

                return response;

            }else {

                return new Response<>("Error al obtener los choferes", 500, false);
            }
        }catch(Exception e){

            return new Response<>("Error al obtener los chofer: " + e.getMessage(), 500, false, "ChoferService", "readAll");

        }
    }

    //En este metodo busco actualziar la disponibilidad del auto segun las acciones del chofer, por ejemplo si se edita borra el chofer, el auto se setea en disponible.
    //Si el chofer pasa de tener auto propio a alquilar uno, se setea en no disponible el auto

    private void actualizarDisponibilidadDeAuto(Chofer chofer, String metodo) {

        Auto auto = chofer.getAutoAlquilado();

        AutoService autoService = new AutoService(AutoDao.getInstance());

        switch (metodo) {
            case "create", "update":
                if (auto != null && !chofer.isAutoPropio()) {
                    auto.setDisponible(false);
                    autoService.update(auto);
                }
                break;

            case "delete":
                if (auto != null && !chofer.isAutoPropio()) {
                    auto.setDisponible(true);
                    autoService.update(auto);
                }
                break;

            default:
                break;
        }

    }
}
