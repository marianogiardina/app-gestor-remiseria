package Service;

import DAO.AutoDao;
import Model.Auto;
import Model.Chofer;
import Model.Response;

//En el service del auto no utlice mucha logica mas que las validaciones de las respuestas por parte del DAO, por lo que quedo como un pasamanos entre el DAO y el controller.

public class AutoService extends BaseService<Auto, AutoDao> {

    public AutoService(AutoDao autoDao) {
        super(autoDao);
    }

    @Override
    public Response<Auto> create(Auto obj) {

        try{

            Response<Auto> response = dao.create(obj);

            if(response.isSuccess()){

                return response;

            } else {

                return new Response<>("Error al crear el auto: " + response.getMensaje(), 500, false);
            }

        } catch (Exception e){

            return new Response<>("Error al crear el auto: " + e.getMessage(), 500, false, "AutoService", "create");
        }
    }

    @Override
    public Response<Auto> update(Auto obj) {

        try{

            Response<Auto> response = dao.update(obj);

            if(response.isSuccess()){

                return response;

            } else {

                return new Response<>("Error al actualizar el auto: " + response.getMensaje(), 500, false);
            }

        } catch (Exception e){

            return new Response<>("Error al actualizar el auto: " + e.getMessage(), 500, false, "AutoService", "update");
        }
    }

    @Override
    public Response<Auto> delete(int id) {

        try{

            Response<Auto> response = dao.delete(id);

            if(response.isSuccess()){

                return response;

            } else {

                return new Response<>("Error al eliminar el auto: " + response.getMensaje(), 500, false);
            }

        } catch (Exception e){

            return new Response<>("Error al eliminar el auto: " + e.getMessage(), 500, false, "AutoService", "delete");

        }
    }

    @Override
    public Response<Auto> read(int id) {

        try {

            Response<Auto> response = dao.read(id);

            if (response.isSuccess()) {

                return response;

            } else {

                return new Response<>("Error al obtener el auto: " + response.getMensaje(), 404, false);

            }

        } catch (Exception e) {

            return new Response<>("Error al obtener el auto: " + e.getMessage(), 404, false, "AutoService", "read");

        }
    }

    @Override
    public Response<Auto> readAll() {

        try{

            Response<Auto> response = dao.readAll();

            if(response.isSuccess()){

                return response;

            }else {

                return new Response<>("Error al obtener los autos", 404, false);
            }
        }catch(Exception e){

            return new Response<>("Error al obtener los autos: " + e.getMessage(), 404, false, "AutoService", "readAll");

        }
    }
}

