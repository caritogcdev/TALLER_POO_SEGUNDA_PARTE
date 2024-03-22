package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Método para conectarnos desde Java a la DB
    public static Connection openConnection(){

        try {
            // Importamos el driver que acabamos de llamar
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creramos las variables de conexión
            /* String url = "jdbc:mysql://localhost:3306/libreria";
            String user = "root";
            String password = "Rlwl2023.";*/

            //CONEXIÓN A LA DB REMOTA
            String url = "jdbc:mysql://bbffrjw9gmjyknucrwph-mysql.services.clever-cloud.com:3306/bbffrjw9gmjyknucrwph";
            String user = "uscml8jwrqqaxcct";
            String password = "oHL860tbms8kIBQhO3Qf";

            //Establecer la cnexión con la DB
            /*
            * Lo casteamos porque queremos que lo que devuelva getConnection sea de tipo Connection y por eso lo ponemos entre paréntesis
            */

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfully established!!!");

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR >> Driver no Instalado " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERROR >> error al conectar con la base de datos" + e.getMessage());
        }

        return objConnection;
    }

    // Método para finalizar la coneción desde Java a la DB
    public static void closeConnection(){
        try {
            //Si hay una conexión activa entonces la cerramos
            if (objConnection != null) objConnection.close();
            System.out.println("Connection successfully completed!");
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

}
