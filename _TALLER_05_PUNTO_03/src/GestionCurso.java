import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;

public class GestionCurso {

    private ArrayList<Curso> listaCursos;

    public GestionCurso(){
        this.listaCursos = new ArrayList<>();
    }

    //Controlador donde van a estar los métodos
    public void agregarCurso(Scanner objScan){
        System.out.println("Ingresa el nombre del curso");
        String nombre = objScan.next();

        System.out.println("Ingresa el código del curso");
        String codigo = objScan.next();

        //Validamos que el código del curso sea único
        if (this.buscarCursoPorCodigo(codigo) != null) {
            System.out.println("Ya existe un curso con este código");
        } else {
            Curso objCurso = new Curso(codigo, nombre);

            if (this.listaCursos.add(objCurso)) { //validación si lo agregó bien
                System.out.println("Curso agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el curso");
            }

        }

    }

    public void listarTodosLosCursos(){
       /* if ((iterador.getCodigo().equalsIgnoreCase(codigo)) && (iterador.getCodigo().equalsIgnoreCase(codigo))) {

        }*/
        if (this.listaCursos.isEmpty()) {
            System.out.println("No hay cursos registrados\n");
        } else {
            for (Curso  iterador: this.listaCursos){
                System.out.println(iterador.toString());
            }
        }
    }

    public Curso buscarCursoPorCodigo(String codigoBuscar){
        for (Curso temporal: this.listaCursos) {
            if (temporal.getCodigo().equalsIgnoreCase(codigoBuscar)) {
                return temporal;
            }
        }

        //Si no retornó nada mientras hizo el for, entonces que retorne null
        return null;
    }

}
