package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean agregarEmpleados(Empleado empleado) throws SQLException {
        connection = new DBConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", SchemaDB.TAB_EMPLOYESS,
                SchemaDB.COL_NAME, SchemaDB.COL_SURNAME, SchemaDB.COL_MAIL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, empleado.getNombre());
        preparedStatement.setString(2, empleado.getApellidos());
        preparedStatement.setString(3, empleado.getCorreo());

        return preparedStatement.execute();

    }

    public boolean mostrarTodosEmpleados() throws SQLException {
        connection = new DBConnection().getConnection();

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_EMPLOYESS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            String correo = resultSet.getString("correo");

            System.out.println("id = " + id);
            System.out.println("nombre = " + nombre);
            System.out.println("apellidos = " + apellidos);
            System.out.println("correo = " + correo);
            System.out.println("------------------------------");
        }
        return resultSet.next();

    }
}
