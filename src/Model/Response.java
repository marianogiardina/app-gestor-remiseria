package Model;

import java.util.List;

public class Response<T> {

    private String mensaje;
    private int codigo;
    private boolean success;
    private boolean validado;
    private String nombreClase;
    private String metodo;
    private T obj;
    private List<T> objList;

    //Realizo un contructor que recibe los parametros necesarios para una respuesta general, por ejemplo, los metodos void.

    public Response(String mensaje, int codigo, boolean success) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = success;
    }

    //Realizo otro contructor que recibe los parametros necesarios para una respuesta mas especifica, como el nombre de la clase y el metodo donde se produjo el error

    public Response(String mensaje, int codigo, boolean success, String nombreClase, String metodo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = success;
        this.nombreClase = nombreClase;
        this.metodo = metodo;
    }

    //Realizo otro contructor que recibe los parametros necesarios para una respuesta con un objeto validado, por ejemplo, al validar un usuario en un login

    public Response(String mensaje, int codigo, boolean success, boolean validado) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = success;
        this.validado = validado;

    }

    //Realizo otro contructor que recibe los parametros necesarios para una respuesta con un objeto especifico, por ejemplo, al crear un nuevo objeto en la base de datos

    public Response(String mensaje, int codigo, boolean success, T obj) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = success;
        this.obj = obj;

    }

    //Realizo otro contructor que recibe los parametros necesarios para una respuesta con una lista de objetos, por ejemplo, al obtener una lista de objetos en un readlAll

    public Response(String mensaje, int codigo, boolean success,List<T> objList) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = success;
        this.objList = objList;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }
}
