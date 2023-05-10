package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

private Connection connection;
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
			
	}
	
	public boolean verificarUsuario(String nombre, String contraseña) {

		try {
			final PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, contraseña);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {

					while (resultSet.next()) {
						if (resultSet.getString("nombre").equals(nombre)
								&& resultSet.getString("contraseña").equals(contraseña)) {

							return true;

						}
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}
