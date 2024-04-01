import controller.AutorController;
import controller.LibroController;
import database.ConfigDB;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Holaaaa desde el Main!");

        //Ensayo de conexión DB
        ConfigDB.openConnection();
        ConfigDB.closeConnection();

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. List Autors.
                    2. Insert Autor.
                    3. Update Autor.
                    4. Delete Autor.
                    5. Get autor by name.
                    6. EXIT AUTOR MENU.
                    
                    Choose an option:
                    """);

            switch (option){
                case "1":
                    //como el controlador tiene métodos estáticos no se instancia entonces se llama de la siguiente manera
                    AutorController.getAll();
                    break;
                case "2":
                    AutorController.create();
                    break;
                case "3":
                    AutorController.update();
                    break;
                case "4":
                    AutorController.delete();
                    break;
                case "5":
                    AutorController.getByName();
                    break;
            }

        } while (!option.equals("6"));

        
        do {
            option = JOptionPane.showInputDialog("""
                    1. List Books.
                    2. Insert Book.
                    3. Update Book.
                    4. Delete Book.
                    5. Get book by name.
                    6. EXIT BOOK MENU.
                    
                    Choose an option:
                    """);

            switch (option){
                case "1":
                    // El controlador tiene métodos estáticos entonces no se instancia y se llama de la siguiente manera
                    LibroController.getAll();
                    break;
                case "2":
                    LibroController.create();
                    break;
                case "3":
                    LibroController.update();
                    break;
                case "4":
                    LibroController.delete();
                    break;
                case "5":
                    LibroController.getByName();
                    break;
            }

        } while (!option.equals("6"));

    }
}