package controller;

import entity.Autor;
import model.AutorModel;

import javax.swing.*;

public class AutorController {
    public static void getAll(){
        AutorModel objModel = new AutorModel();
        String listAutores = " AUTORS LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convertimos del Object a Autor

            Autor objAutor = (Autor) iterador;
            listAutores += objAutor.toString() + "\n";

        }
        JOptionPane.showMessageDialog(null, listAutores);
    }

    //Método que hace lo mismo getAll porque ese no se puede sobreescribir por ser static

    public static String getAllString(){
        AutorModel objModel = new AutorModel();
        String listAutores = " AUTORS LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convertimos del Object a Autor

            Autor objAutor = (Autor) iterador;
            listAutores += objAutor.toString() + "\n";
        }

        // Aquí queremos que nos devuelva la lista en String para poderlo listar en el delete y se le añade el otro String para que pueda escoger
        return listAutores;
    }

    public static void create(){
        //Usamos el modelo
        AutorModel objAutorModel = new AutorModel();

        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Insert name");
        String nacionalidad = JOptionPane.showInputDialog("Insert nationality");

        // Creamos una instancia de autor
        Autor objAutor = new Autor();
        objAutor.setNombre(nombre);
        objAutor.setNacionalidad(nacionalidad);

        // Llamamos al método de inserción y guardamos el objeto que retorna en autor previamente instanciado, debemos castearlo.

        objAutor = (Autor) objAutorModel.insert(objAutor);

        JOptionPane.showMessageDialog(null, objAutor.toString());

    }

    public static void delete(){
        //siempre debemos utilizar el modelo porque es el que tiene todos los modelos para interactuar con la base de datos
        AutorModel objAutorModel = new AutorModel();

        String listAutores = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAutores + "\n Enter the Id the autor to delete"));

        Autor objAutor = objAutorModel.findById(idDelete);// aca no lo casteamos porque ya findById devuelve un autor no un objeto

        if (objAutor == null) {
            JOptionPane.showMessageDialog(null, "Autor not found");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the autor ? \n" + objAutor.toString());

            if (confirm == 0) objAutorModel.delete(objAutor);
        }
    }

    public static void getByName(){
        String name = JOptionPane.showInputDialog("Insert name ");

        AutorModel objAutor = new AutorModel();

        String listaString = "COINCIDENCIAS \n";

        for (Autor iterador: objAutor.findByName(name)){
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);
    }

    public static void update(){
        AutorModel objAutorModel = new AutorModel();

        String listAutors= getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAutors+ "\n Enter the ID of the autor to edit: "));

        //Obteniendo un autor por el id ingresado
        Autor objAutor = objAutorModel.findById(idUpdate);

        //Validamos es que exista un autor
        if (objAutor == null) {
            JOptionPane.showMessageDialog(null, "Autor not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new name: ",objAutor.getNombre());

            String nationality = JOptionPane.showInputDialog(null, "Enter new nationality: ",objAutor.getNacionalidad());

            objAutor.setNombre(name);
            objAutor.setNacionalidad(nationality);

            objAutorModel.update(objAutor);

        }
    }
}
