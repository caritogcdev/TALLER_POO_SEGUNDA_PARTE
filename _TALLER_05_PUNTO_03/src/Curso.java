import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    private String codigo;
    private String nombre;

    //Metodo de acceso
    private ArrayList<Estudiante> listaEstudiantes;

/*    un metodo estatico es que el puedo llamar desde cualquier parte del programa sin tener que instanciar la clase
    uansdo un metodo es estatico debe ser solo uno en todo el programa*/
    //Un método estático es el que se puede sin tener que instanciar la clase
    //Puedo acceder directamente a el.
    //Basicamente termina siendo una variable global
    private static int index = 1; //Creamos un index en la posición 1 para que abajo, otra forma era con el tamaño de la lista, y otra un random con validacion de que no se repita

    // El constructor es lo primero que se ejecuta al yo inicializar una clase?
    public Curso(String codigo, String nombre){
        this.nombre = nombre;
        this.codigo = codigo;
        //Estamos inicializando la lista de estudiantes
        //Necesitamos que el constructor cree la lista, no que nos la pida, por eso no autogeneramos el constructor ya que no necesitamos que nos lo pida como parámetro
        this.listaEstudiantes = new ArrayList<>(); //por cada instancia que creemos,
    }

    //Metodo para agregar un estudiante al curso
    public void agregarEstudiante(Scanner objScan){
        objScan.nextLine(); // Para limpiar el buffer, es decir, la consola, si no lo ponemos, se pasa al siguiente next Line
        System.out.println("Ingresa el nombre del estudiante");
        String nombre = objScan.nextLine();
        System.out.println("Ingresa el nombre del estudiante");
        String email = objScan.nextLine();

        Estudiante objEstudiante = new Estudiante(1, nombre, email);
        index++;

        this.listaEstudiantes.add(objEstudiante);
        System.out.println("Estudiante agregado correctamente!");
    }

    public void listarEstudiantes(){


        if (this.listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes en el curso ".concat(this.nombre));
        } else {
            System.out.println("Lista de estudiantes del curso ".concat(this.nombre)); //con el .concat es otra forma de concatenar un string. Este this hace referencia al nombre de la clase, es decir, al nombre del curso

            for (Estudiante temporal1 : this.listaEstudiantes){
                System.out.println(temporal1.toString());
            }
        }

    }

    public void eliminarEstudiante(Scanner objScan){
        //hay que saber primero que es lo que vamos a eliminar
        // es decir que hay que listarle todos los estudiantes
        //arriba hay una función que ya lo hace

        this.listarEstudiantes();

        System.out.println("Ingrese el id del estudiante a eliminar");
        int idEliminar = objScan.nextInt();

        //Eliminamos el estudiante si su ID coincide con el id que se desea eliminar

        boolean eliminado = this.listaEstudiantes.removeIf(estudiante -> estudiante.getId() == idEliminar);

        System.out.println(!eliminado
                ? "No se pudo eliminar el estudiante"
                : "Estudiante eliminado correctamente");
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", listaEstudiantes=" + listaEstudiantes +
                '}';
    }
}
