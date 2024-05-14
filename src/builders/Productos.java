package builders;

public class Productos {

    private String descripcion;
    private int id;
    private double precio;

    public Productos(int id, String descripcion, double precio){
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters
    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static boolean agregar(int id, String descripcion, double precio){
        return false;
    }

}
