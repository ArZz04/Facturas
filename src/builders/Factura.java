package builders;

import java.util.ArrayList;

public class Factura {

    private static int id;
    private static float subTotal;
    private static float iva;
    private static float total;
    private ArrayList<Productos> productos;
    private Fecha fechaFactura;
    private Vendedor vendedor;

    public Factura(int id, Fecha fechaFactura, float subTotal, float iva, float total, ArrayList<Productos> productos, Vendedor vendedor) {
        this.id = id;
        this.fechaFactura = fechaFactura;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.productos = productos;
        this.vendedor = vendedor;
    }
    public Factura(){}

    // Getters
    public int getId() {
        return id;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public float getIva() {
        return iva;
    }

    public float getTotal() {
        return total;
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public Fecha getFechaFactura() {
        return fechaFactura;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    public void setFechaFactura(Fecha fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    /*
    public static Productos agregarProducto(int id){
        return new Productos();
    }
    */

    public static void eliminarProducto(int id){
        return;
    }

    public static void calcularSubTotal(){
        return;
    }
}
