//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Instanciamos la clase que tiene la lógica para agregar, eliminar y mostrar a los empleados
        GestionEmpleados gestionEmpleados = new GestionEmpleados();

        Empleado empleadoTemporal  = new EmpleadoTemporal("Pepito", 20, 1, 3500000.00, "15/03/2024");
        Empleado empleadoPermanente = new EmpleadoPermanente("Fulanita", 27, 2, 5000000.00, 15);

        gestionEmpleados.agregarEmpleado(empleadoTemporal);
        gestionEmpleados.agregarEmpleado(empleadoPermanente);

        gestionEmpleados.mostrarEmpleados();

        gestionEmpleados.eliminarEmpleado(1);

        System.out.println("Mostrar los empleados después de eliminar a uno de ellos por id");
        gestionEmpleados.mostrarEmpleados();

    }
}