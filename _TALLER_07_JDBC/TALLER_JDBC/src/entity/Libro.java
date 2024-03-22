package entity;

public class Libro {

    //Atributos
    private int id;
    private String titulo;
    private int anioPublicacion;
    private double precio;
    private int idAutor;

    //Constructor vacío por si se requiere
    public Libro() {
    }

    //Constructor
    public Libro(int id, String titulo, int anioPublicacion, double precio, int idAutor) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.precio = precio;
        this.idAutor = idAutor;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    //toString
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", precio=" + precio +
                ", idAutor=" + idAutor +
                '}';
    }
}
