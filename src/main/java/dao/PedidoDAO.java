package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Pedido;

import java.sql.*;
import java.util.List;

public class PedidoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean agregarPedidos(Pedido pedido) throws SQLException {
        connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", SchemaDB.TAB_ORDERS,
                SchemaDB.COL_DESCRIPTION, SchemaDB.COL_TOTALPRICE, SchemaDB.COL_IDPRODUCT);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pedido.getDescripcion());
        preparedStatement.setDouble(2, pedido.getPrecio_total());
        preparedStatement.setInt(3, pedido.getId_producto());

        return preparedStatement.execute();
    }

    public boolean mostrarTodosPedidos() throws SQLException {
        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_ORDERS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String descripcion = resultSet.getString("descripcion");
            double precioTotal = resultSet.getDouble("precio_total");
            int idProducto = resultSet.getInt("id_producto");

            System.out.println("id = " + id);
            System.out.println("descripcion = " + descripcion);
            System.out.println("precioTotal = " + precioTotal);
            System.out.println("idProducto = " + idProducto);
            System.out.println("------------------------------");
        }
        return resultSet.next();
    }
}
