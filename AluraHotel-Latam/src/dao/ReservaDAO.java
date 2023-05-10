package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Reserva;

public class ReservaDAO {
	
	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public void guardarReserva(Reserva reserva) {
		try {
			PreparedStatement statement;
			statement = connection.prepareStatement(
					"INSERT INTO RESERVAS" + "(fecha_entrada, fecha_salida, valor, forma_de_pago)" + "VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setObject(1, reserva.getFechaEntrada());
				statement.setObject(2, reserva.getFechaSalida());
				statement.setObject(3, reserva.getValor());
				statement.setObject(4, reserva.getFormaPago());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
