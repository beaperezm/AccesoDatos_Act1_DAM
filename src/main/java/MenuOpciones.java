import dao.EmpleadoDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dao.ProductoFavDAO;
import model.Empleado;
import model.Pedido;
import model.Producto;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuOpciones {

    Scanner sc = new Scanner(System.in);
    int opcion;
    ProductoDAO productoDAO = new ProductoDAO();
    Producto producto = new Producto();
    PedidoDAO pedidoDAO = new PedidoDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    ProductoFavDAO productoFavDAO = new ProductoFavDAO();


    public void menuOpciones() {

        do {
            System.out.println();
            System.out.println("--------------------- MENÚ DE OPCIONES ---------------------");
            System.out.println("Por favor seleccione lo que desea hacer");
            System.out.println("1. Agregar todos los productos que están ubicados en: https://dummyjson.com/products");
            System.out.println("2. Agregar pedidos");
            System.out.println("3. Agregar empleados");
            System.out.println("4. Mostrar todos los productos");
            System.out.println("5. Mostrar todos los pedidos");
            System.out.println("6. Mostrar todos los empleados");
            System.out.println("7. Mostrar productos con precio inferir a 600€");
            System.out.println("8. Insertar en la tabla productos_fav aquellos productos que tengan un valor superior a 1000€");
            System.out.println("9. Finalizar");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        productoDAO.agregarProductosJSON(producto);
                        System.out.println("Productos insertados correctamente");
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al insertar los productos procedentes del JSON");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 2:
                    try {
                        sc.nextLine();
                        System.out.println("Introduce una descripción: ");
                        String descripcion = sc.nextLine();
                        System.out.println("Introduce el precio total: ");
                        double precioTotal = sc.nextDouble();
                        System.out.println("Introduce el id del producto");
                        int idProducto = sc.nextInt();
                        System.out.println("Producto insertado con éxito");
                        pedidoDAO.agregarPedidos(new Pedido(descripcion, precioTotal, idProducto));
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al insertar el pedido");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 3:
                    try {
                        sc.nextLine();
                        System.out.println("Introduce un nombre: ");
                        String nombre = sc.nextLine();
                        System.out.println("Introduce un apellido: ");
                        String apellidos = sc.nextLine();
                        System.out.println("Introduce un correo");
                        String correo = sc.next();
                        System.out.println("Empleado insertado con éxito");
                        empleadoDAO.agregarEmpleados(new Empleado(nombre, apellidos, correo));
                        break;
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al insertar al empleado");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 4:
                    try {
                        productoDAO.mostrarTodosProductos();
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al mostrar los productos");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 5:
                    try {
                        pedidoDAO.mostrarTodosPedidos();
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al mostrar los pedidos");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 6:
                    try {
                        empleadoDAO.mostrarTodosEmpleados();
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al mostrar a los empleados");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 7:
                    try {
                        productoDAO.mostrarProductosPrecioInferior600(600.00);
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al mostrar los productos con precio inferior a 600");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 8:
                    try {
                        productoFavDAO.insertarProductos(1000.00);
                        System.out.println("Productos insertados correctamente");
                    } catch (SQLException e) {
                        System.out.println("Algo ha fallado al insertar los productos con precio superior a 1000");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }
                    break;
                case 9:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Ninguna de las opciones es correcta");
            }

        } while (opcion < 9);

    }
}
