import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*
        * 1. Agregar
        * 2. ELiminar
        * 3. Buscar por nombre
        * 4. Buscar por categoría
        * 5. Listar inventario
        * 6. Salir
        * */

        String menuOpciones = "";

        do {
            menuOpciones = JOptionPane.showInputDialog("1. Agregar\n" +
                    "2. ELiminar\n" +
                    "3. Buscar por nombre\n" +
                    "4. Buscar por categoría\n" +
                    "5. Listar inventario\n" +
                    "6. Salir");

            switch (menuOpciones) {
                case "1":
                    System.out.println("Agregando");
                    break;
                case "2":
                    System.out.println("Eliminando");
                    break;
            }


        } while (!menuOpciones.equals("6"));


        ProductoEspecifico objProducto1 = new ProductoEspecifico(1, "lapiz", 2000, "Papeleria", "Big");
        ProductoEspecifico objProducto2 = new ProductoEspecifico(2, "cuaderno", 6000, "Cuaderno", "Norma");

        Inventario objInventario = new Inventario();
        objInventario.agregarProducto(objProducto1);
        objInventario.agregarProducto(objProducto2);

        objInventario.listarProductos();

        System.out.println("Antes de eliminar");
        objInventario.listarProductos();

        objInventario.eliminarProducto(2);
        System.out.println("Después de eliminar");
        objInventario.listarProductos();

        //System.out.println(objInventario.buscarPorNombre("lapiz"));

        /*Inventario objInventario = new Inventario();
        int option = 0;

        do {
            JOptionPane.showMessageDialog(null, "Menu Principal");
            option = Integer.parseInt(JOptionPane.showInputDialog("1. Agregar Producto\n" +
                    "2. Eliminar producto\n" +
                    "3. Buscar productos\n" +
                    "4. Listar todos los productos\n" +
                    "5. Salir del menu"));

            switch (option){
                case 1:
                    Producto product;
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del producto"));
                    String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto");
                    double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio del producto"));
                    product = new Producto(id, nombre, precio);
                    objInventario.addProducto(product);
                    break;
                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog())
                    break;
            }
        }while(option != 5);*/

    }
}