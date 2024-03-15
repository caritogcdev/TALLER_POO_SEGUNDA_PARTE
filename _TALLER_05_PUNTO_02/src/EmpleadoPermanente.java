public class EmpleadoPermanente extends Empleado{

    private int anosQueLlevaLaborando;

    public EmpleadoPermanente(String nombre, int edad, int idEmpleado, double salario, int anosQueLlevaLaborando) {
        super(nombre, edad, idEmpleado, salario);
        this.anosQueLlevaLaborando = anosQueLlevaLaborando;
    }

    public int getAnosQueLlevaLaborando() {
        return anosQueLlevaLaborando;
    }

    public void setAnosQueLlevaLaborando(int anosQueLlevaLaborando) {
        this.anosQueLlevaLaborando = anosQueLlevaLaborando;
    }
}
