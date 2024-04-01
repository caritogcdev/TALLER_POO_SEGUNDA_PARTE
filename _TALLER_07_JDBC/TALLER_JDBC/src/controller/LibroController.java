package controller;

import entity.Libro;
import model.LibroModel;

import javax.swing.*;

public class LibroController {
    public static void getAll(){
        LibroModel objModel = new LibroModel();
        String listLibros = " BOOKS LIST \n";

        for (Object iterador: objModel.findAll()) {

            //Aquí convertimos del Object a Libro

            Libro objLibro = (Libro) iterador;

            listLibros += objLibro.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listLibros);
    }

    // Método para retornar la lista de libros

    public static String getAllString(){
        LibroModel objModel = new LibroModel();
        String listLibros =  " BOOKS LIST \n";

        for (Object iterador: objModel.findAll()) {

            //Aquí convertimos del Object a Libro

            Libro objLibro = (Libro) iterador;

            listLibros += objLibro.toString() + "\n";
        }

        // Devolvemos la lista en String para poderlo listar en el método delete y se le añade el otro String para que el usuario pueda escoger
        return listLibros;
    }

    public static void create(){
        //Usamos el modelo
        LibroModel objLibroModel = new LibroModel();

        //Pedimos los datos al usuario
        String titulo = JOptionPane.showInputDialog("Inset book title");
        int anioPublicacion = Integer.parseInt(JOptionPane.showInputDialog("Insert book year"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Insert book price"));
        //////
        int idAutor = Integer.parseInt(JOptionPane.showInputDialog("Insert Author ID"));

        // Creamos una instancia de libro
        Libro objLibro = new Libro();
        objLibro.setTitulo(titulo);
        objLibro.setAnioPublicacion(anioPublicacion);
        objLibro.setPrecio(precio);
        objLibro.setIdAutor(idAutor);

        //  Luego llamamos al método de inserción y guardamos el objeto que retorna en autor previamente instanciado, debemos castearlo
        objLibro = (Libro) objLibroModel.insert(objLibro);

        JOptionPane.showMessageDialog(null, objLibro.toString());
    }

    public static void delete(){
        // Utilizamos el modelo (se debe utilizar para interactuar con la DB)
        LibroModel objLibroModel = new LibroModel();

        String listLibros = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listLibros + "\n Enter ID of the book to delete"));

        // Aquì no es necesario castearlo porque ya el método findById devuelve un libro y no un objeto
        Libro objLibro = objLibroModel.findById(idDelete);

        if (objLibro == null) {
            JOptionPane.showMessageDialog(null, "Book not found");
        } else{
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this book? \n" + objLibro.toString());
            if (confirmDelete == 0) {
                //Acá podemos omitir las llaves del if para que sea un if de una sola línea
                objLibroModel.delete(objLibro);
            }
        }
    }

    public static void getByName(){
        String nameBook = JOptionPane.showInputDialog("Insert name (title) book");

        LibroModel objLibro = new LibroModel();

        String listaString = "COINCIDENCIAS DE LIBROS \n";

        for (Libro iterador: objLibro.findByName(nameBook)) {
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);
    }

    public static void update(){
        LibroModel objLibroModel = new LibroModel();

        String listBooks = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks + "\n Enter the ID of the book to edit: "));

        // Aquí podremos obtener el libro por el ID que ingresemos
        Libro objLibro = objLibroModel.findById(idUpdate);

        // Validamos que exista el libro
        if (objLibro == null) {
                JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            String title = JOptionPane.showInputDialog(null, "Enter new title: ", objLibro.getTitulo());
            int anioPublicacion = Integer.parseInt(JOptionPane.showInputDialog("Enter new book year", objLibro.getAnioPublicacion()));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Enter new book price", objLibro.getPrecio()));
            int idAutor = Integer.parseInt(JOptionPane.showInputDialog("Enter new Author ID", objLibro.getIdAutor()));

            objLibro.setTitulo(title);
            objLibro.setAnioPublicacion(anioPublicacion);
            objLibro.setPrecio(precio);
            objLibro.setIdAutor(idAutor);

            objLibroModel.update(objLibro);

        }
    }
}
