package data;

import builders.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static boolean addClienteToFile(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/clientes.txt", true))) {
            writer.write(cliente.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addVendedorToFile(Vendedor vendedor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/vendedores.txt", true))) {
            writer.write(vendedor.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addProductoToFile(Productos producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/productos.txt", true))) {
            writer.write(producto.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addFacturaToFile(Factura factura) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/facturas.txt", true))) {
            writer.write(factura.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Cliente> loadClientes(String nombreArchivo) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("\\|");

                if (datos.length == 7) { // Asegurarse de tener 7 campos
                    String rfc = datos[0];
                    String domicilio = datos[1];
                    String nombre = datos[2];
                    String apellidoP = datos[3];
                    String apellidoM = datos[4];
                    String fechaNacimientoStr = datos[5];
                    String[] fechaNacimientoArr = fechaNacimientoStr.split("-");
                    try {
                        String idFacturas = datos[6];
                        System.out.println(idFacturas);
                    }finally {
                        String idFacturas = null;
                    }

                    if (fechaNacimientoArr.length == 3) { // Asegurarse de tener una fecha válida
                        try {
                            Fecha fechaNacimiento = new Fecha(Integer.parseInt(fechaNacimientoArr[0]),
                                    Integer.parseInt(fechaNacimientoArr[1]),
                                    Integer.parseInt(fechaNacimientoArr[2]));
                            // Crear una nueva instancia de Cliente con el id
                            clientes.add(new Cliente(rfc, domicilio, nombre, apellidoP, apellidoM, fechaNacimiento, new ArrayList<>()));
                        } catch (NumberFormatException e) {
                            System.err.println("Error en el formato de fecha de nacimiento del cliente: " + linea);
                        }
                    } else {
                        System.err.println("Error en el formato de fecha de nacimiento del cliente: " + linea);
                    }


                } else {
                    System.err.println("Error en el formato de datos del cliente: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de clientes: " + nombreArchivo);
        }

        return clientes;
    }
    public static ArrayList<Vendedor> loadVendedores(String nombreArchivo) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                if (datos.length == 8) { // Asegurarse de tener 8 campos
                    int id = Integer.parseInt(datos[0]);
                    String area = datos[1];
                    float comision = Float.parseFloat(datos[2]);
                    int sueldoBase = Integer.parseInt(datos[3]);
                    String nombre = datos[4];
                    String apellidoP = datos[5];
                    String apellidoM = datos[6];
                    Fecha fechaNacimiento = new Fecha(Integer.parseInt(datos[7].split("-")[0]),
                            Integer.parseInt(datos[7].split("-")[1]),
                            Integer.parseInt(datos[7].split("-")[2]));
                    // Crear una nueva instancia de Vendedor con los datos
                    vendedores.add(new Vendedor(id, area, comision, sueldoBase, nombre, apellidoP, apellidoM, fechaNacimiento));
                } else {
                    System.err.println("Error en el formato de datos del vendedor: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de vendedores: " + nombreArchivo);
        }

        return vendedores;
    }

    public static ArrayList<Productos> loadProductos(String nombreArchivo) {
        ArrayList<Productos> productos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                if (datos.length == 3) { // Asegurarse de tener 3 campos
                    int id = Integer.parseInt(datos[0]);
                    String descripcion = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    // Crear una nueva instancia de Producto con los datos
                    productos.add(new Productos(id, descripcion, precio));
                } else {
                    System.err.println("Error en el formato de datos del producto: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de productos: " + nombreArchivo);
        }

        return productos;
    }

}
