package pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperaConexion();

       System.out.println("Conexion Exitosa");
        
       connection.close();
        System.out.println("Conexion cerrada correctamente");
    }
}
