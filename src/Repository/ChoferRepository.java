package Repository;

import DAO.ChoferDao;
import Model.Chofer;
import Model.Response;

public class ChoferRepository extends BaseRepository<Chofer, ChoferDao> {

    public ChoferRepository(ChoferDao choferDao) {
        super(choferDao);
    }

    @Override
    public Response<Chofer> getById(int id) {

        try{
            if(!cache.containsKey(id)){

                Response<Chofer> getChofer = dao.read(id);

                if(getChofer.isSuccess()){

                    Chofer chofer = getChofer.getObj();

                    cache.put(id, chofer);
                }else{

                    return getChofer;

                }
            }

            return new Response<>("Producto encontrado en cache", 200, true, cache.get(id));

        }catch(Exception e){

            return new Response<>("Internal Server Error: " + e.getMessage(), 500,false , "ChoferRepository", "getById");

        }
    }

    @Override
    public Response<Chofer> clearCache() {

        try{

            cache.clear();

            return new Response<>("Cache de choferes borrado exitosamente", 200, true);

        }catch(Exception e){

            return new Response<>("Internal Server Error", 500, false, "ChoferRepository", "clearCache");

        }
    }

}
