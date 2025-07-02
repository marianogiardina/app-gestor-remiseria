package Controller;

import DAO.Crud;
import Model.Response;

import java.util.List;

public abstract class BaseController<T> {

    public abstract void create(T obj);

    public abstract void update(T obj);

    public abstract void delete(int id);

    public abstract T read(int id);

    public abstract List<T> readAll();

}
