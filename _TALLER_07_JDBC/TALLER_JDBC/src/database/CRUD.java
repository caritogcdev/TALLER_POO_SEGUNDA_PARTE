package database;

import java.util.List;

public interface CRUD {

    //Esta interfaz nos va a servir para cualquier entidad que retornemos

    public Object insert(Object obj);

    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);
}
