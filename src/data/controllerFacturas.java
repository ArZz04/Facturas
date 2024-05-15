package data;

import builders.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerFacturas {
    static String FILENAME = "src/data/facturas.txt";

    public static Boolean addFactura(Factura factura) {
        File file = new File(FILENAME);

        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                // Si no existe, crear el archivo
                file.createNewFile();
            }

            // Agregar la informacion al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
                writer.write(factura.getId() + " | " + factura.getFechaFactura() + " | " + factura.getSubTotal() + " | " + factura.getIva() + " | " + factura.getTotal() + " | " + factura.getProductos() + " | " + factura.getVendedor());
                writer.newLine();
            } catch (IOException e) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<Factura> loadFacturas() {
        ArrayList<Factura> facturas = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("\\|");

                if (datos.length == 6) { // Asegurarse de tener 6 campos
                    int id = Integer.parseInt(datos[0].trim());
                    Fecha fecha = datos[1];
                    float subTotal = Double.parseDouble(datos[2]);
                    float iva = Double.parseDouble(datos[3]);
                    float total = Double.parseDouble(datos[4]);
                    ArrayList<Productos> productos = datos[5];
                    Vendedor vendedor = datos[5];

                    // Crear una nueva instancia de Factura con los datos
                    facturas.add(new Factura(id, fecha, subTotal, iva, total, productos, vendedor));
                } else {
                    System.err.println("Error en el formato de datos de la factura: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de facturas: " + FILENAME);
        }

        return facturas;
    }

    public static boolean verifyFactura(int id) {
        ArrayList<Factura> facturas = loadFacturas();
        for (Factura factura : facturas) {
            if (factura.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
