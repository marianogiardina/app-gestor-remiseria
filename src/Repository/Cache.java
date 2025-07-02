package Repository;

import Model.Response;

public interface Cache<T> {

    public abstract Response<T> getById(int id);

    public abstract Response<T> clearCache();



}
