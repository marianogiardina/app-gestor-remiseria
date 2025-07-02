package Repository;

import Model.Response;

import java.util.HashMap;
import java.util.Map;

//Creo la clase BaseRepository que implementa la interfaz Cache y recibe como parámetros el tipo de entidad T y el tipo de DAO D.
//Buscando una estructura común para los repositorios que manejen diferentes tipos de entidades y sus respectivos DAOs.

public abstract class BaseRepository<T, D> implements Cache<T> {

    // Declaro dos tipos de datos genericos T y D.
    // El atributo de tipo D permite que pueda trabajar con diferentes tipos de DAOs.
    // El atributo de tipo T representa la entidad que maneja el repositorio.

    protected D dao;

    //Buscando aun mas la generealizacion, creo HashMap generico que almacena las entidades T en una especie de cache, utilizando su ID como clave.

    protected final Map<Integer, T> cache = new HashMap<>();

    public BaseRepository(D dao) {
        this.dao = dao;
    }

    @Override
    public abstract Response<T> getById(int id);

    @Override
    public abstract Response<T> clearCache();

}
