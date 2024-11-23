package dao;

import database.DBConnection;
import database.SchemaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoFavDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean insertarProductos(double price) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT INTO %s (%s) SELECT %s FROM %s WHERE %s > ?", SchemaDB.TAB_PRODUCTSFAV, SchemaDB.COL_IDPRODUCT, SchemaDB.COL_ID, SchemaDB.TAB_PRODUCTS, SchemaDB.COL_PRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, price);
        return preparedStatement.execute();


    }
}
