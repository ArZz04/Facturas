package data;

import builders.Cliente;
import builders.Productos;
import builders.Vendedor;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static data.controllerClientes.loadClientes;

public class controllerProductos {
    static String FILENAME = "src/data/productos.txt";

    public static Boolean addProducto(Productos productos) {
        File file = new File(FILENAME);

        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                // Si no existe, crear el archivo
                file.createNewFile();
            }

            // Agregar la informacion al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
                writer.write(productos.getId() + " | " + productos.getDescripcion() + " | " + productos.getPrecio() );
                writer.newLine();
            } catch (IOException e) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<Productos> loadProductos(String nombreArchivo) {
        ArrayList<Productos> productos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("\\|");

                if (datos.length == 3) { // Asegurarse de tener 3 campos
                    int id = Integer.parseInt(datos[0].trim());
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

    public static Productos findProducto(int id) {
        ArrayList<Productos> productos = loadProductos(FILENAME);
        for (Productos producto1 : productos) {
            if (Objects.equals(producto1.getId(), id)) {
                return producto1;
            }
        }
        return null;
    }

    public static boolean verificarProducto(int id) {
        ArrayList<Productos> productos = loadProductos(FILENAME);
        for (Productos producto : productos) {

            if (Integer.valueOf(producto.getId()).equals(id)) {
                return true;
            }
        }
        return false;
    }

}
