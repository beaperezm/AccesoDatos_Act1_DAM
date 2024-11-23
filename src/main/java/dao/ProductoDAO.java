package dao;

import com.google.gson.Gson;
import database.DBConnection;
import database.SchemaDB;
import model.Producto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void agregarProductosJSON(Producto producto) throws SQLException {

      String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String linea = bufferedReader.readLine();
            JSONObject response = new JSONObject(linea);
            JSONArray results = response.getJSONArray("products");

            for (Object product : results) {
                if (product instanceof JSONObject) {
                    int id = ((JSONObject) product).getInt("id");
                    String title = ((JSONObject) product).getString("title");
                    String description = ((JSONObject) product).getString("description");
                    int stock = ((JSONObject) product).getInt("stock");
                    double price = ((JSONObject) product).getDouble("price");

                    Gson gson = new Gson();
                    producto = gson.fromJson(((JSONObject) product).toString(), Producto.class);

                    connection = new DBConnection().getConnection();
                    preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)", SchemaDB.TAB_PRODUCTS,
                            SchemaDB.COL_ID, SchemaDB.COL_NAME, SchemaDB.COL_DESCRIPTION, SchemaDB.COL_STOCK, SchemaDB.COL_PRICE));
                    preparedStatement.setInt(1, producto.getId());
                    preparedStatement.setString(2, producto.getNombre());
                    preparedStatement.setString(3, producto.getDescripcion());
                    preparedStatement.setInt(4, producto.getCantidad());
                    preparedStatement.setDouble(5, producto.getPrecio());

                }
                preparedStatement.execute();
            }

        } catch (MalformedURLException e) {
            System.out.println("La url indicada no es válida");
        } catch (IOException e) {
            System.out.println("Error en la conexión");
        }
    }

    public boolean mostrarTodosProductos() throws SQLException {
        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_PRODUCTS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
           int id = resultSet.getInt("id");
           String nombre = resultSet.getString("nombre");
           String descripcion = resultSet.getString("descripcion");
           int cantidad = resultSet.getInt("cantidad");
           double precio = resultSet.getDouble("precio");

            System.out.println("PRODUCTO: " + id);
            System.out.println("nombre = " + nombre);
            System.out.println("descripción = " + descripcion);
            System.out.println("cantidad = " + cantidad);
            System.out.println("precio = " + precio);
            System.out.println("------------------------------");
        }
        return resultSet.next();
    }

    public boolean mostrarProductosPrecioInferior600(double price) throws SQLException {

        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s WHERE %s < ?", SchemaDB.TAB_PRODUCTS, SchemaDB.COL_PRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, price);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre  = resultSet.getString("nombre");
            String descripcion  = resultSet.getString("descripcion");
            int cantidad = resultSet.getInt("cantidad");
            double precio = resultSet.getDouble("precio");

            System.out.println("id = " + id);
            System.out.println("nombre = " + nombre);
            System.out.println("descripcion = " + descripcion);
            System.out.println("cantidad = " + cantidad);
            System.out.println("precio = " + precio);
            System.out.println("------------------------------");

        }
        return resultSet.next();
    }

}
