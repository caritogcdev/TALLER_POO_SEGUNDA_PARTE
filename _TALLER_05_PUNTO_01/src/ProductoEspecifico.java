public class ProductoEspecifico extends Producto {

    String categoria;
    String marca;

    /*Heredamos todo lo del papá más todo lo que yo ponga entonces debo crear un constructor*/

    public ProductoEspecifico(int id, String nombre, double precio, String categoria, String marca) {
        super(id, nombre, precio); //Esporque eso es lo que necesitga el constructor de Producto
        this.categoria = categoria;
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

   /* @Override
    public String toString() { //para imprimir la información de la clase
        return "ProductoEspecifico{" +
                "categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }*/

    @Override
    public String toString() { //para imprimir la información de la clase
        return super.toString() +
                " categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }


}
