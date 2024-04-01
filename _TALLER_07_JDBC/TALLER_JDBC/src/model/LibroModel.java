package model;

import database.CRUD;
import database.ConfigDB;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertir el obj que llegó
        Libro objLibro = (Libro) obj;

        try {
            //3. Escribir el SQL
            String sql = "INSERT INTO libros (titulo, anio_publicacion, precio, id_autor) VALUES (?,?,?,?);";

            //4. Preparar el Sratement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ? ? ?
            objPrepare.setString(1, objLibro.getTitulo());
            objPrepare.setInt(2, objLibro.getAnioPublicacion());
            objPrepare.setDouble(3, objLibro.getPrecio());
            objPrepare.setDouble(4, objLibro.getIdAutor());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (LLaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();


            // Mientras haya un registro, iterar

            while (objRest.next()) {
                //Podemos obtener el valor también con indices
                objLibro.setId(objRest.getInt(1));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();

        return objLibro;
    }

    @Override
    public List<Object> findAll() {
        //1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listaLibros = new ArrayList<>();

        // 2. Abrir la conexión con la DB
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos el query en SQL
            String sql = "SELECT * FROM libros;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta
            // Ejecutar el query y obtener el resultado (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un resultado siguiente, hacer:
            while (objResult.next()){
                //6.1 Crear un libro
                Libro objLibro = new Libro();

                //6.2 Llenar el libro
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnioPublicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setIdAutor(objResult.getInt("id_autor"));

                //6.3 Agregar el libro a la lista
                listaLibros.add(objLibro);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listaLibros;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrir a conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a un libro
        Libro objLibro = (Libro) obj;

        //3. Creamos una variable para conocer el estado de la acción
        boolean isUpdated = false;

        try {
            //4. Crear la sentencia SQL
            String sql = "UPDATE libros SET titulo = ?, anio_publicacion = ?, precio = ?, id_autor = ? WHERE id = ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1, objLibro.getTitulo());
            objPrepare.setInt(2, objLibro.getAnioPublicacion());
            objPrepare.setDouble(3, objLibro.getPrecio());
            objPrepare.setInt(4, objLibro.getIdAutor());

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
        Libro objLibro = (Libro) obj;

        //2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Crear la variable de estado
        boolean isDeleted = false;

        try {
            // 4. Escribir la sentencia SQL
            String sql = "DELETE FROM libros WHERE id = ?;";

            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Dar valor al ?, y nos  traemos a objLibro como parámetro para extraerle el ID
            objPrepare.setInt(1, objLibro.getId());

            //7. Ejecutamos el Query (executeUpdate), que devuelve el número de registros afectados
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si se eliminó algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
        
    }

    // Método para encontrar un libro por ID

    public Libro findById(int id){

        // 1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Creamos el libro que vamos a retornar
        Libro objLibro = null;

        try {
            // 3. Sentencia SQL
            String sql = "SELECT * FROM libros WHERE id = ?;";

            // 4. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Darle valor al parámetro del query
            objPrepare.setInt(1, id);

            // 6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            // Validamos si viene algún resultado
            if (objResult.next()) {
                objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnioPublicacion(objResult.getInt("anioPublicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setIdAutor(objResult.getInt("idAutor"));
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión

        ConfigDB.closeConnection();

        return objLibro;
    }

    // Método para encontrar un libro por nombre

    public List<Libro> findByName(String nombre) {

        // Creamos la lista
        List<Libro> listBook = new ArrayList<>();

        // 1. Abrimos la conexión
        Connection objConnection  = ConfigDB.openConnection();

        // 2. Crear el libro que vamos a retornar por el nombre
        Libro objBookByName = null;

        try {
            // 3. Sentencia sql para seleccionar el libro por el nombre y con el like hacemos que lo incluya
            String sql = "SELECT * FROM libros WHERE nombre LIKE ?;";

            // 4. Preparamos el statement
            //Preparamos la consulta sql que se le realizará a la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Darle el valor al parámetro del Query, es decir, al signo de interrogación que tenemos inicialmente en nuestro query
            objPrepare.setString(1, "%"+nombre+"%");

            //6. Ejecutar el Query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // Validamos si viene algo entonces es que el nombre del libro existe

                //Reasignamos el valor de la variable objLibroByName para retornarlo en el apartado inferior
                objBookByName = new Libro();

                objBookByName.setId(objResult.getInt("id"));
                objBookByName.setTitulo(objResult.getString("titulo"));
                objBookByName.setAnioPublicacion(objResult.getInt("anioPublicacion"));
                objBookByName.setPrecio(objResult.getDouble("precio"));
                objBookByName.setIdAutor(objResult.getInt("idAutor"));

            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Hola desde el catch del libro" + error.getMessage());

        }

        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listBook;
    }

}
