package DAO;

import Model.Response;

public interface Crud<T> {

    public Response<T> create(T obj);

    public Response<T> update(T obj);

    public Response<T> delete(int id);

    public Response<T> read(int id);

    public Response<T> readAll();

}
