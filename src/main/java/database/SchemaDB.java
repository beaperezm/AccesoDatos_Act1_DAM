package database;

public interface SchemaDB {

    String HOST = "127.0.0.1";
    String PORT = "3360";
    String DBNAME = "almacen";
    String USER = "root";
    String PASS = "";

    String TAB_PRODUCTS = "productos";
    String TAB_ORDERS = "pedidos";
    String TAB_EMPLOYESS = "empleados";
    String TAB_PRODUCTSFAV = "productos_fav";
    String COL_ID = "id";
    String COL_NAME = "nombre";
    String COL_DESCRIPTION = "descripcion";
    String COL_STOCK = "cantidad";
    String COL_PRICE = "precio";
    String COL_TOTALPRICE = "precio_total";
    String COL_IDPRODUCT = "id_producto";
    String COL_SURNAME = "apellidos";
    String COL_MAIL = "correo";

}
