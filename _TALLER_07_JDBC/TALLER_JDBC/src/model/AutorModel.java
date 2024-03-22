package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el obj que llegó
        Autor objAutor = (Autor) obj;

        try {
            //3. Escribir el SQL
            String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?,?);";

            //4. Preparar el Sratement, además agregar la propiedad RETURN_GENERATED_KEYS que hace que la sentencia SQL nos retorne los id generados por la base de datos
            // Después de insertar en la db, también queremos que nos devuelva las llaves generadas, es decir, que nos devuelva el id que generó la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ? ?
            objPrepare.setString(1, objAutor.getNombre());
            objPrepare.setString(2, objAutor.getNacionalidad());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (LLaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Iterar mientras haya un registro

            while (objRest.next()) {
                //Podemos obtener el valor también con indices
                objAutor.setId(objRest.getInt(1));
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAutor;
    }

    @Override
    public List<Object> findAll() {

        //1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listaAutores = new ArrayList<>();

        // 2. Abrir la conexión con la DB
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query en SQL
            String sql = "SELECT * FROM autores;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta
            // Ejecutar el query y obtener el resultado (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un resultado siguiente, gacer:
            while (objResult.next()){

                //Por cada iteración de la DB vamos a crear un autor que luego lo vamos a llenar

                //6.1 Crear un autor
                Autor objAutor = new Autor();

                //Para llenar el autor hacemos lo del punto 6.2
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));

                //6.3 Agregamos el Autor a la lista
                //cuando el objeto está lleno, lo agregamos a la lista
                listaAutores.add(objAutor);

            }


        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listaAutores;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrir a conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a un autor
        Autor objAutor = (Autor) obj;

        //3. Creamos una variable para conocer el estado de la acción
        boolean isUpdated = false;

        try {
            //4. Crear la sentencia SQL
            String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1, objAutor.getNombre());
            objPrepare.setString(2, objAutor.getNacionalidad());
            objPrepare.setInt(3, objAutor.getId());

            //7. Ejecutar el query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //Paso 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        //1. Convertir el objeto a la entidad
        Autor objAutor = (Autor) obj;

        //2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Crear la variable de estado
        boolean isDeleted = false;

        try {
            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM autores WHERE id = ?;";

            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Dar valor al ?
            // Traemos a objAutor como parámetro para extraerle el ID
            objPrepare.setInt(1, objAutor.getId());

            //7. Ejecutamos el Query (executeUpdate), que devuelve el número de registros afectados
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    //////////////////////////////

    //Método para encontrar a un autor por ID
    public Autor findById(int id){

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2.  Crear el autor que vamos retornar
        Autor objAutor = null;

        try {
            //3,Sentencia SQL
            String sql = "SELECT * FROM autores WHERE id = ?";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parámetro del query
            objPrepare.setInt(1, id);

            // Execute devuelve un booleano
            // Execute query devuelve un resultado

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) { //si viene algo
                objAutor = new Autor(); //reasignando el valor de la variable
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión

        ConfigDB.closeConnection();

        return objAutor;

    }

    // HACER EL MÉTODO PARA ENCONTRAR AUTORES POR EL NOMBRE

    public List<Autor> findByName(String nombre) {

        //Creamos la lista
        List<Autor> listAutor = new ArrayList<>();

        //1. Abrimos la conexión
        Connection objConnection  = ConfigDB.openConnection();

        //2. Crear el autor que vamos a retornar por el nombre
        Autor objAutorByName = null;

        try {
            // 3. Sentencia sql para seleccionar al autor por el nombre
            // con el like hacemos que lo incluya

            String sql = "SELECT * FROM autores WHERE nombre LIKE ?;";

            // 4. Preparamos el statement por buenas prácticas
            //Preparamos la consulta sql que se le realizará a la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle el valor al parámetro del Query, es decir, al signo de interrogación que tenemos inicialmente en nuestro query
            objPrepare.setString(1, "%"+nombre+"%");

            //6. Ejecutar el Query

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //Si viene algo entonces es que el nombre existe
                objAutorByName = new Autor(); //Reasignamos el valor de la variable objAutorByName para retornarlo en el apartado inferior

                objAutorByName.setId(objResult.getInt("id"));
                objAutorByName.setNombre(objResult.getString("nombre"));
                objAutorByName.setNacionalidad(objResult.getString("nacionalidad"));

                listAutor.add(objAutorByName);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Hola desde el catch" + error.getMessage());

        }

        // 7. Cerrar la conexión

        ConfigDB.closeConnection();

        return listAutor;

    }


}
