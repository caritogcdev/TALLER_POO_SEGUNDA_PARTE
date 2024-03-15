import java.util.ArrayList;

public class Inventario {
   // private ArrayList<ProductoEspecifico> listaProductos = new ArrayList<>();
    private ArrayList<ProductoEspecifico> listaProductos;

    // el constructor se puede para inicializar variables o para aplicar lógica
    public Inventario(){
        //Creamos una instacia de arraylist para poder implementar los metodos que nos pide el ejercicio
        this.listaProductos = new ArrayList<>();

    }

    //Todas las clases se terminan convirtiendo en objetos y también en tipos de datos
    //por eso colocamos ProductoEspecifico como tipo de dato de la variable producto
    public void agregarProducto(ProductoEspecifico producto){
        this.listaProductos.add(producto);
    }

    public boolean eliminarProducto(int id){
        //recorrer toda la lista y donde encontramos ese id colocar lista.remove
        //entonces desde Java 11 ya se hace de otra manera, como el filter de javaScript
        //que recibe un callback por dentro, y tiene un iterador

        return this.listaProductos.removeIf(producto -> producto.getId() == id);

    }

    public void listarProductos(){
        //for each en java para recorrer una lista
        for (ProductoEspecifico iterador : this.listaProductos) {
            //se va a ir recorriendo y se va a imprimir lo que tiene en ese metodo toString
            System.out.println(iterador.toString());
        }
    }

    public ProductoEspecifico buscarPorNombre(String nombreBuscar){
        for (ProductoEspecifico iterador : this.listaProductos) {
            if (iterador.getNombre().equalsIgnoreCase(nombreBuscar)) {
                return iterador;
            }
        }
        //En caso de que no retorne nada, que retorne null, eso es equivalente al undefined en JavaScript
        // entonces si no encuentra nada, que devuelva un null
        return null;
    }

    public ProductoEspecifico buscarPorCategoria(String categoriaBuscar){
        for (ProductoEspecifico iterador : this.listaProductos) {
            if (iterador.getCategoria().equalsIgnoreCase(categoriaBuscar)) {
                return iterador;
            }
        }
        //En caso de que no retorne nada, que retorne null, eso es equivalente al undefined en JavaScript
        // entonces si no encuentra nada, que devuelva un null
        return null;
    }

}
