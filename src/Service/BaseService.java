package Service;

import DAO.Crud;
import Model.Response;

public abstract class BaseService<T,D> implements Crud<T> {

    protected D dao;

    public BaseService(D dao) {
        this.dao = dao;
    }

    @Override
    public abstract Response<T> create(T obj);

    @Override
    public abstract Response<T> update(T obj);

    @Override
    public abstract Response<T> delete(int id);

    @Override
    public abstract Response<T> read(int id);

    @Override
    public abstract Response<T> readAll();

}
