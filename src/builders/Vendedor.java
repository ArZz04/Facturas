package builders;

public class Vendedor extends Persona {

    // ID, NOMBRE, APELLIDO PATERNO, APELLIDO MATERNO, domicilio
    private String area;
    private float porcentajeComision;
    private int id, sueldoBase;

    public Vendedor( int id,String area, float porcentajeComision, int sueldoBase, String nombre, String apellidoP, String apellidoM, Fecha fechaNacimiento){
        super(nombre, apellidoP, apellidoM, fechaNacimiento);
        this.id = id;
        this.area = area;
        this.porcentajeComision = porcentajeComision;
        this.sueldoBase = sueldoBase;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public int getSueldoBase() {
        return sueldoBase;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public void setSueldoBase(int sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public static float calcularComision(){
        return 0.0F;
    }
}
