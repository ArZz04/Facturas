package builders;

import java.util.ArrayList;

public class Cliente extends Persona {

    private String rfc;
    private String domicilio;
    private ArrayList<Factura> compras;

    public Cliente(String rfc, String domicilio, String nombre, String apellidoP, String apellidoM, Fecha fechaNacimiento, ArrayList<Factura> compras){
        super( nombre, apellidoP, apellidoM, fechaNacimiento);
        this.rfc = rfc;
        this.domicilio = domicilio;
        this.compras = new ArrayList<>();
    }

    // Getters
    public String getRfc() {
        return rfc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public ArrayList<Factura> getCompras() {
        return compras;
    }

    // Setters
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCompras(ArrayList<Factura> compras) {
        this.compras = compras;
    }

    /*
    public static Factura comprar(){
        return new Factura();
    }
    */
}
