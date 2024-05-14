import builders.*;
import data.FileManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Main {

    static Scanner rc = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Vendedor> vendedores = new ArrayList<>();
    static ArrayList<Productos> productos = new ArrayList<>();

    public static void main(String[] args) {

        clientes = FileManager.loadClientes("src/data/clientes.txt");
        vendedores = FileManager.loadVendedores("src/data/vendedores.txt");
        productos = FileManager.loadProductos("src/data/productos.txt");

        int op;
            while (true) {

                System.out.println("----------------------| FACTURAS |----------------------");
                System.out.println("1. Alta Clientes");
                System.out.println("2. Alta Vendedores");
                System.out.println("3. Alta Productos");
                System.out.println("4. Realizar Venta");
                System.out.println("5. Listar Facturas de Cliente");
                System.out.println("6. Buscar Vendedor");
                System.out.println("0. Salir");
                System.out.print("-> ");
                try {
                    op = rc.nextInt();
                    switch (op) {
                        case 0:
                            System.out.println("--------------------------------------------------------");
                            System.out.println("¡Programa finalizado con éxito!");
                            break;
                        case 1:
                            System.out.println("--------------------| ALTA CLIENTE |--------------------");

                            Persona personaCliente = newPersona();
                            String rfc = null;
                            String domicilio = null;
                            ArrayList<Factura> compras = new ArrayList<>();

                            try {
                                System.out.print("RFC: ");
                                rfc = rc.next();
                                System.out.print("Domicilio: ");
                                domicilio = rc.next();
                            } catch (Exception e) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Por favor, ingrese sus datos correctamente.");
                            }

                            Cliente cliente = new Cliente(rfc, domicilio, personaCliente.getNombre(), personaCliente.getApellidoP(), personaCliente.getApellidoM(), personaCliente.getFechaNacimiento(), compras);
                            clientes.add(cliente);
                            System.out.println("---------------------| Su RFC es: " + cliente.getRfc() + " |-------------------------");
                            break;
                        case 2:
                            System.out.println("-------------------| ALTA VENDEDORES |------------------");

                            Persona personaVendedor = newPersona();
                            int id = 0;
                            String area = null;
                            float comision = 0.0F;
                            int sueldoBase = 0;

                            try {
                                System.out.print("ID: ");
                                id = rc.nextInt();
                                System.out.print("Area: ");
                                area = rc.next();
                                System.out.print("Porcentaje de Comision: ");
                                comision = rc.nextFloat();
                                System.out.print("Sueldo Badse: ");
                                sueldoBase = rc.nextInt();
                            } catch (Exception e) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Por favor, ingrese sus datos correctamente.");
                            }

                            Vendedor vendedor = new Vendedor(id, area, comision, sueldoBase, personaVendedor.getNombre(), personaVendedor.getApellidoP(), personaVendedor.getApellidoM(), personaVendedor.getFechaNacimiento());
                            vendedores.add(vendedor);
                            System.out.println("---------------------| Su ID es: " + vendedor.getId() + " |-------------------------");
                            break;
                        case 3:
                            System.out.println("--------------------| ALTA PRODUCTOS |------------------");
                            int idProducto = 0;
                            String descripcion = null;
                            double precio = 0.0;

                            try {
                                System.out.print("ID: ");
                                idProducto = rc.nextInt();
                                System.out.print("Descripcion: ");
                                descripcion = rc.next();
                                System.out.print("Precio: ");
                                precio = rc.nextDouble();
                            } catch (Exception e) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Por favor, ingrese los datos correctamente.");
                            }
                            Productos producto = new Productos(idProducto, descripcion, precio);
                            System.out.println("---------------------| El ID de : " + producto.getDescripcion() + " es: " + producto.getId() + " |-------------------------");
                            productos.add(producto);
                            break;
                        case 4:
                            System.out.println("-----------------------| VENDER |-----------------------");
                            break;
                        case 5:

                            System.out.println("-------------| LISTAR FACTURAS DE CLIENTE |-------------");
                            try {
                                System.out.print("RFC del cliente: ");
                                String idCliente = rc.next();

                            } catch (Exception e) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Por favor, ingrese los datos correctamente.");
                            }
                            break;
                        case 6:
                            System.out.println("-------------------| BUSCAR VENDEDOR |------------------");
                            try {
                                System.out.print("ID del vendedor: ");
                                int idVendedor = rc.nextInt();


                            } catch (InputMismatchException e) {
                                System.out.println("---------------------------------------------------------");
                                System.out.println("Por favor, ingrese un número entero como ID de vendedor.");
                                rc.nextLine();
                            }
                            break;
                    }
                } finally {
                    rc.close();
                }
            }
    }


    private static Persona newPersona(){
        String nombre = null;
        String apellidoP = null;
        String apellidoM = null;
        int dia = 0;
        int mes= 0;
        int anio = 0;

        try{
            System.out.print("Nombre(s): ");
            while (!rc.hasNext("[a-zA-Z]+")) {
                System.out.println("Por favor, ingrese un nombre válido (solo letras).");
                System.out.print("Nombre(s): ");
                rc.next();
            }
            nombre = rc.next();

            System.out.print("Apellido Paterno: ");
            while (!rc.hasNext("[a-zA-Z]+")) {
                System.out.println("Por favor, ingrese un apellido paterno válido (solo letras).");
                System.out.print("Apellido Paterno: ");
                rc.next();
            }
            apellidoP = rc.next();

            System.out.print("Apellido Materno: ");
            while (!rc.hasNext("[a-zA-Z]+")) {
                System.out.println("Por favor, ingrese un apellido materno válido (solo letras).");
                System.out.print("Apellido Materno: ");
                rc.next();
            }
            apellidoM = rc.next();

            try {
                System.out.print("Dia: ");
                dia = rc.nextInt();
                System.out.print("Mes: ");
                mes = rc.nextInt();
                System.out.print("Año: ");
                anio = rc.nextInt();
            }catch (Exception e){
                System.out.println("--------------------------------------------------");
                System.out.println("Por favor, ingrese una Fecha valida.");
            }

        }catch (Exception e){
            System.out.println("--------------------------------------------------");
            System.out.println("Por favor, ingrese el dato válido.");
        }


        Fecha fechaNacimiento = new Fecha(dia, mes, anio);

        return new Persona(nombre.toUpperCase(), apellidoP.toUpperCase(), apellidoM.toUpperCase(), fechaNacimiento);
    }

}