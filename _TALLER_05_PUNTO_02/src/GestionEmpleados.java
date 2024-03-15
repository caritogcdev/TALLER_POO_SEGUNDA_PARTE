import java.util.ArrayList;

public class GestionEmpleados {
    //Lista de empleados
    private ArrayList<Empleado> empleados;

    //Inicialización del constructor para el ArrayList
    public GestionEmpleados() {
        this.empleados = new ArrayList<>();
    }

    //Método para añadir un empleado
    public void agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    //Método para eliminar un empleado
    public boolean eliminarEmpleado(int IdEmpleado){
        // el removeIf es como un filtro
        return empleados.removeIf(empleado -> empleado.getIdEmpleado() == IdEmpleado);
    }

    //Método para mostrar empleados
    public void mostrarEmpleados(){
        //foreach en java
        //Primero debemos recorrer la lista de empleados para mostrarlos
        //   tipo variable : coleccion
        for (Empleado empleado : this.empleados) {
            System.out.println("LISTADO DE EMPLEADOS: \n" +
                    " ID Empleado: " + empleado.getIdEmpleado() +
                    ", Nombre empleado: " + empleado.getNombre() +
                    ", Edad empleado: " + empleado.getEdad() +
                    ", Salario empleado: " + empleado.getSalario());
        }
    }

}
