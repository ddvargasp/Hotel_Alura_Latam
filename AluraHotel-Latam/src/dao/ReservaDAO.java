package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Reserva> listarReserva() {
		List<Reserva> listaReserva = new ArrayList<Reserva>();

		try {
			final PreparedStatement statement = connection
					.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas");
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {

					
					listaReserva.add(new Reserva(resultSet.getInt("id"),
							resultSet.getDate("fecha_entrada").toLocalDate(),
							resultSet.getDate("fecha_salida").toLocalDate(),
							resultSet.getString("valor"),
							resultSet.getString("forma_de_pago")));
				}
			}
	
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		return listaReserva;

	}

	public List<Reserva> buscarReservaId(String id) {
		
		List<Reserva> listaReserva = new ArrayList<Reserva>();

		try {
			final PreparedStatement statement = connection.prepareStatement(
					"SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id =?");

			try (statement) {
				
				statement.setString(1, id);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {

						listaReserva.add(new Reserva(resultSet.getInt("ID"),
								resultSet.getDate("fecha_entrada").toLocalDate().plusDays(1),
								resultSet.getDate("fecha_salida").toLocalDate().plusDays(1),
								resultSet.getString("valor"), 
								resultSet.getString("forma_de_pago")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listaReserva;

	}
	public void actualizarReserva(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id) {
		try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE reservas SET "
                    + " fecha_entrada = ?, "
                    + " fecha_salida = ?,"
                    + " valor = ?,"
                    + "forma_de_pago = ?"
                    + " WHERE id = ?");

            try (statement) {
                statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
                statement.setObject(2, java.sql.Date.valueOf(fechaSalida));
                statement.setString(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);
                statement.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
      }
    }	
	public void eliminarReserva(int id) {
		 try {
	        	
	            final PreparedStatement statement = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?");

	            try (statement) {
	                statement.setInt(1, id);
	                statement.execute();              

	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    
	}
	
	

}
