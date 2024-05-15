package data;

import builders.Cliente;
import builders.Fecha;
import builders.Productos;

import java.io.*;
import java.util.*;

public class controllerClientes {
    static String FILENAME = "src/data/clientes.txt";

    public static Boolean addCliente(Cliente cliente) {
        File file = new File(FILENAME);

        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                // Si no existe, crear el archivo
                file.createNewFile();
            }

            // Agregar la informacion al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
                writer.write(cliente.getRfc() + " | " + cliente.getNombre() + " | " + cliente.getApellidoP() + " | " + cliente.getApellidoM() + " | " + cliente.getDomicilio() + " | " + returnDate(cliente.getFechaNacimiento()) + " | " + cliente.getCompras());
                writer.newLine();
            } catch (IOException e) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
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
                    String[] fechaNacimientoArr = fechaNacimientoStr.split("/");
                    try {
                        String idFacturas = datos[6];
                    }finally {
                        String idFacturas = null;
                    }

                    try {
                        if (fechaNacimientoArr.length == 3) {
                            int dia = Integer.parseInt(fechaNacimientoArr[0].trim());
                            int mes = Integer.parseInt(fechaNacimientoArr[1].trim());
                            int anio = Integer.parseInt(fechaNacimientoArr[2].trim());

                            // Aquí podrías agregar validaciones adicionales para dia, mes y año si es necesario

                            Fecha fechaNacimiento = new Fecha(dia, mes, anio);
                            clientes.add(new Cliente(rfc, domicilio, nombre, apellidoP, apellidoM, fechaNacimiento, new ArrayList<>()));
                        } else {
                            System.err.println("Error en el formato de la fecha de nacimiento del cliente1: " + linea);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error en el formato de la fecha de nacimiento del cliente2: " + linea );
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

    public static boolean verifyCliente(String rfc) {
        ArrayList<Cliente> clientes = loadClientes(FILENAME);
        for (Cliente cliente : clientes) {
            if (cliente.getRfc().equals(rfc)) {
                return true;
            }
        }
        return false;
    }

    public static Cliente findCliente(String rfc) {
        ArrayList<Cliente> clientes = loadClientes(FILENAME);
        for (Cliente cliente : clientes) {
            if (Objects.equals(cliente.getRfc().trim(), rfc)) {
                return cliente;
            }
        }
        return null;
    }

    public static String returnDate(Fecha date) {
        return (date.getDia() + "/" + date.getMes() + "/" + date.getAnio());
    }
}
