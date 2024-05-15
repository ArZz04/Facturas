package data;

import builders.Fecha;
import builders.Productos;
import builders.Vendedor;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class controllerVendedores {
    static String FILENAME = "src/data/vendedores.txt";

    public static Vendedor buscarVendedor(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId() == id) {
                return vendedor;
            }
        }
        return null;
    }

    public static Boolean addVendedor(Vendedor vendedor) {
        File file = new File(FILENAME);

        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                // Si no existe, crear el archivo
                file.createNewFile();
            }

            // Agregar la informacion al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
                writer.write(vendedor.getId() + " | " + vendedor.getArea() + " | " + vendedor.getPorcentajeComision() + " | " + vendedor.getSueldoBase() + " | " + vendedor.getNombre() + " | " + vendedor.getApellidoP() + " | " + vendedor.getApellidoM() + " | " + returnDate(vendedor.getFechaNacimiento()) );
                writer.newLine();
            } catch (IOException e) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<Vendedor> loadVendedores(String nombreArchivo) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("\\|");

                if (datos.length == 8) { // Asegurarse de tener 8 campos
                    int id = Integer.parseInt(datos[0].trim());
                    String area = datos[1];
                    float comision = Float.parseFloat(datos[2]);
                    int sueldoBase = Integer.parseInt(datos[3].trim());
                    String nombre = datos[4];
                    String apellidoP = datos[5];
                    String apellidoM = datos[6];
                    Fecha fechaNacimiento = new Fecha(Integer.parseInt(datos[7].split("/")[0].trim()),
                            Integer.parseInt(datos[7].split("/")[1]),
                            Integer.parseInt(datos[7].split("/")[2]));
                    // Crear una nueva instancia de Vendedor con los datos
                    vendedores.add(new Vendedor(id, area, comision, sueldoBase, nombre, apellidoP, apellidoM, fechaNacimiento));
                } else {
                    System.err.println("Error en el formato de datos del vendedor: " + linea );
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de vendedores: " + nombreArchivo);
        }

        return vendedores;
    }

    public static Vendedor findVendedor(int id) {
        ArrayList<Vendedor> vendedores = loadVendedores(FILENAME);
        for (Vendedor vendedor1 : vendedores) {
            if (Objects.equals(vendedor1.getId(), id)) {
                return vendedor1;
            }
        }
        return null;
    }

    public static boolean verificarVendedor(int id) {
        ArrayList<Vendedor> vendedores = loadVendedores(FILENAME);
        for (Vendedor vendedor : vendedores) {
            if (Integer.valueOf(vendedor.getId()).equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static String returnDate(Fecha date) {
        return (date.getDia() + "/" + date.getMes() + "/" + date.getAnio());
    }
}

