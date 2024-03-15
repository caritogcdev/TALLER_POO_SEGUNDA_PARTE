import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Instancias

        Scanner objScan = new Scanner(System.in);

        //Instancia de la clase GestionCurso para utilizar y probar lo metodos creados, asì que hay que utilizar primero la clase y cuando la utilizamos a ello le llamamos instancia
        GestionCurso objGestion = new GestionCurso();


        int option = 0;

        do {
            //De la versión de Java 11 hacia arriba, con triple comillas dobles se pede escribir texto sin tener que concatenar cada uno
            System.out.println("""
                    MENU DE OPCIONES
                    1. Administrar Estudiantes
                    2. Administrar Cursos
                    3. Salir
                    
                    Ingrese una opción:
                    """);
            option = objScan.nextInt();

            switch (option){ // se coloca la variable que evalúa la condición
                case 1:
                    int option3 = 0;

                    do {
                        System.out.println("""
                                MENU DE ESTUDIANTES
                                1. Agregar estudiante a un curso
                                2. Listar todos los estudiantes de un curso
                                3. Eliminar estudiantes de un curso
                                4. Salir
                                
                                Ingresa una opción:
                                """);
                        option3 = objScan.nextInt();

                        switch (option3){
                            case 1:
                                //Hay que listarle todos los cursos
                                //Preguntar a cual curso lo va  agregar
                                // y luego agregarlo

                                objGestion.listarTodosLosCursos();

                                System.out.println("Ingresa el código del curso donde ingresarás el nuevo estudiante");
                                String codigo = objScan.next();

                                Curso objCurso = objGestion.buscarCursoPorCodigo(codigo); // busco el codigo que tengo en esa variable y lo guardo en la variable objCurso

                                if (objCurso == null){
                                    System.out.println("El código ingresado no es válido");
                                }else {
                                    objCurso.agregarEstudiante(objScan);
                                }

                                break;
                            case 2:

                                objGestion.listarTodosLosCursos();

                                System.out.println("Ingresa el código del curso donde ingresarás el nuevo estudiante");

                                codigo = objScan.next();

                                objCurso = objGestion.buscarCursoPorCodigo(codigo); // busco el codigo que tengo en esa variable y lo guardo en la variable objCurso

                                if (objCurso == null){
                                    System.out.println("El código ingresado no es válido");
                                }else {
                                    objCurso.listarEstudiantes();
                                }
                                break;

                            case 3: // Eliminar estudiantes de un curso en específico

                                //1. Listar los cursos
                                objGestion.listarTodosLosCursos();

                                //2. Preguntar el código del curso
                                System.out.println("Ingresa el código del curso donde deseas eliminar el estudiante");
                                codigo = objScan.next();

                                //3. Buscar el curso que tenga ese código
                                Curso objcurso = objGestion.buscarCursoPorCodigo(codigo);
                                if (objcurso == null) {
                                    System.out.println("El código ingresado no coincide con ningún curso");
                                } else {
                                    //4. Eliminar al estudiante de ese curso encontrado
                                    objcurso.eliminarEstudiante(objScan);
                                }
                                break;
                        }

                    }while (option3 != 4);

                case 2:
                    int option2 = 0;
                    do {
                        System.out.println("""
                                MENU CURSOS
                                1. Agregar curso
                                2. Listar cursos
                                3. Buscar por código
                                4. Salir
                                
                                Ingrese una opción:
                                """);
                        option2 = objScan.nextInt();

                        switch (option2){
                            case 1:
                                objGestion.agregarCurso(objScan);
                                break;
                            case 2:
                                objGestion.listarTodosLosCursos();
                                break;
                            case 3:
                                System.out.println("Ingresa el código del curso a buscar: ");
                                String codigo = objScan.next();
                               /*
                                if (objGestion.buscarCursoPorCodigo(codigo) == null) {
                                    System.out.println("No existe ningún curso con ese código");
                                } else {
                                    System.out.println();
                                }*/

                                Curso objCurso = objGestion.buscarCursoPorCodigo(codigo);

                                if (objCurso == null) {
                                    System.out.println("No existe ningún curso con ese código");
                                } else {
                                    System.out.println(objCurso.toString());
                                }
                        }

                    } while (option2 != 4);

            }

        } while (option != 3);


        //polimorfismo es cambiarle la funcionalidad a un metodo sin cambiar el original
    }
}