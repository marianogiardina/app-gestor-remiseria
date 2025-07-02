package Repository;

import DAO.AutoDao;
import DAO.ChoferDao;
import Model.Auto;
import Model.Response;

public class AutoRepository extends BaseRepository<Auto, AutoDao> {

    public AutoRepository(AutoDao autoDao) {
        super(autoDao);
    }

    @Override
    public Response<Auto> getById(int id) {

        try {

            if (!cache.containsKey(id)) {

                Response<Auto> getAuto = dao.read(id);

                if (getAuto.isSuccess()) {

                    Auto auto = getAuto.getObj();

                    cache.put(id, auto);

                } else {

                    return getAuto;

                }
            }

            return new Response<>("Auto encontrado en cache", 200, true, cache.get(id));

        } catch (Exception e) {

            return new Response<>("Internal Server Error", 500, false, "AutoRepository", "getById");

        }
    }

    @Override
    public Response<Auto> clearCache() {

        try{

            cache.clear();

            return new Response<>("Cache de autos borrado exitosamente", 200, true);

        }catch(Exception e){

            return new Response<>("Internal Server Error", 500, false, "AutoRepository", "clearCache");

        }
    }
}
