import builders.*;
import data.controllerClientes;
import data.controllerProductos;
import data.controllerVendedores;

import java.util.*;


public class Main {

    static Scanner rc = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Vendedor> vendedores = new ArrayList<>();
    static ArrayList<Productos> productos = new ArrayList<>();

    public static void main(String[] args) {

        clientes = controllerClientes.loadClientes("src/data/clientes.txt");
        vendedores = controllerVendedores.loadVendedores("src/data/vendedores.txt");
        productos = controllerProductos.loadProductos("src/data/productos.txt");

        int op;
        try {
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

                                if (!controllerClientes.verifyCliente(rfc)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El RFC ya existe, por favor ingrese otro.");
                                    break;
                                }

                                System.out.print("Domicilio: ");
                                domicilio = rc.next();
                            } catch (Exception e) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Por favor, ingrese sus datos correctamente.");
                            }

                            Cliente cliente = new Cliente(rfc, domicilio, personaCliente.getNombre(), personaCliente.getApellidoP(), personaCliente.getApellidoM(), personaCliente.getFechaNacimiento(), compras);
                            clientes.add(cliente);
                            controllerClientes.addCliente(cliente);
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

                                if (controllerVendedores.verificarVendedor(id)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El ID de producto ya existe, por favor ingrese otro.");
                                    break;
                                }

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
                            controllerVendedores.addVendedor(vendedor);
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

                                if (controllerProductos.verificarProducto(idProducto)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El ID de producto ya existe, por favor ingrese otro.");
                                    break;
                                }

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
                            controllerProductos.addProducto(producto);
                            break;
                        case 4:
                            Random rand = new Random();
                            System.out.println("-----------------------| VENDER |-----------------------");
                            System.out.print("RFC del cliente: ");
                            String rfcClienteV = rc.next();
                            Cliente clienteV = controllerClientes.findCliente(rfcClienteV);

                            if (Objects.isNull(clienteV)){
                                System.out.println("--------------------------------------------------");
                                System.out.println("El cliente no existe, por favor ingrese otro.");
                                break;
                            }
                            System.out.println("------------------| AGREGAR PRODUCTO |------------------");
                            System.out.print("ID del producto: ");
                            int idProductoV = rc.nextInt();
                            Productos productoV = controllerProductos.findProducto(idProductoV);
                            if (Objects.isNull(productoV)){
                                System.out.println("--------------------------------------------------");
                                System.out.println("El producto no existe, por favor ingrese otro.");
                                break;
                            }
                            System.out.print("---------------| " + productoV.getDescripcion() +" agregado a la factura | ");
                            System.out.println("--------------------------------------------------");
                            System.out.println("¿Desea agregar otro producto?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            System.out.print("-> ");
                            int op2 = rc.nextInt();
                            while (op2 == 1){
                                System.out.println("------------------| AGREGAR PRODUCTO |------------------");
                                System.out.print("ID del producto: ");
                                idProductoV = rc.nextInt();
                                productoV = controllerProductos.findProducto(idProductoV);
                                if (Objects.isNull(productoV)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El producto no existe, por favor ingrese otro.");
                                    break;
                                }
                                System.out.print("---------------| " + productoV.getDescripcion() +" agregado a la factura | ");
                                System.out.println("--------------------------------------------------");
                                System.out.println("¿Desea agregar otro producto?");
                                System.out.println("1. Si");
                                System.out.println("2. No");
                                System.out.print("-> ");
                                op2 = rc.nextInt();
                            }
                            System.out.println("--------------------------------------------------");
                            System.out.println("Ingrese el usuario del vendedor: ");
                            int idVendedor = rc.nextInt();
                            Vendedor vendedorV = controllerVendedores.findVendedor(idVendedor);
                            if (Objects.isNull(vendedorV)){
                                System.out.println("--------------------------------------------------");
                                System.out.println("El vendedor no existe, por favor ingrese otro.");
                                break;
                            }
                            System.out.println("--------------------------------------------------");
                            int nFactura = rand.nextInt(10000); // Genera un número aleatorio entre 0 y 9999

                            //Factura factura = new Factura(nFactura, new Fecha(Date.dai), clienteV, vendedorV, new ArrayList<>());

                            System.out.println("---------------| " + vendedorV.getNombre() + " le realizo la factura " + nFactura + " | ");

                            break;
                        case 5:

                            System.out.println("-------------| LISTAR FACTURAS DE CLIENTE |-------------");
                                System.out.print("RFC del cliente: ");
                                String rfcCliente = rc.next();

                                Cliente cliente1 = controllerClientes.findCliente(rfcCliente);
                                if (Objects.isNull(cliente1)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El cliente no existe, por favor ingrese otro.");
                                    break;
                                }

                                System.out.println("-------------| FACTURAS DEL USUARIO |-------------");
                                ArrayList<Factura> facturas = cliente1.getCompras();
                                if (facturas.isEmpty()) {
                                    System.out.println("------------------| No hay facturas |------------------");
                                } else {
                                    for (Factura factura : facturas) {
                                        System.out.println(factura);
                                    }
                                }
                                System.out.println("--------------------------------------------------");

                            break;
                        case 6:
                            System.out.println("-------------------| BUSCAR VENDEDOR |------------------");
                            try {
                                System.out.print("ID del vendedor: ");
                                int idVendedorr = rc.nextInt();
                                Vendedor vendedorr = controllerVendedores.findVendedor(idVendedorr);
                                if (Objects.isNull(vendedorr)){
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("El vendedor no existe, por favor ingrese otro.");
                                    break;
                                }
                                System.out.println("--------------------------------------------------");
                                //listar sus ventas
                                //mostrar comision y sueldo neto
                                System.out.println("Vendedor: " + vendedorr.getNombre() + " " + vendedorr.getApellidoP() + " " + vendedorr.getApellidoM());
                                System.out.println("Area: " + vendedorr.getArea());
                                System.out.println("Porcentaje de Comision: " + vendedorr.getPorcentajeComision());
                                System.out.println("Sueldo Base: " + vendedorr.getSueldoBase());
                                System.out.println("Sueldo Neto: " + vendedorr.getSueldoBase() * vendedorr.getPorcentajeComision() / 100);
                                System.out.println("--------------------------------------------------");

                            } catch (InputMismatchException e) {
                                System.out.println("---------------------------------------------------------");
                                System.out.println("Por favor, ingrese un número entero como ID de vendedor.");
                                rc.nextLine();
                            }
                            break;
                        default:
                            System.out.println("Opción no válida, por favor ingrese una opción correcta.");
                            break;
                }
            }
        }catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese una opción válida.");
        } finally {
            rc.close(); // Cerrar Scanner después de salir del bucle
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