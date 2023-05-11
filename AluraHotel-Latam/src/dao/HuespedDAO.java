package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Huesped;

public class HuespedDAO {

	private Connection connection;

	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	// Conexion a base de datos para guardar huesped
	public void guardarHuesped(Huesped huesped) {

		try {
			PreparedStatement statement;
			statement = connection.prepareStatement("INSERT INTO HUESPEDES "
					+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setObject(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Huesped> listarHuesped() {
		
		List<Huesped> listaHuesped = new ArrayList<Huesped>();
		
		try {
			
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva "
                    		+ "FROM HUESPEDES");
    
            try (statement) {
            	
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        listaHuesped.add(new Huesped(
                        		resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento").toLocalDate(),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva")
                                ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHuesped;
    }
	
	public List<Huesped> buscarHuespedId(String id){
		
		List<Huesped> listaHuesped = new ArrayList<Huesped>();

        try {
        	
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva "
                    		+ "FROM huespedes WHERE id =?");
    
            try (statement) {
            	
            	statement.setString(1, id);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        listaHuesped.add(new Huesped(
                        		resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento").toLocalDate(),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva")
                                ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHuesped;
	}
	
	public void actualizarHuesped(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		try (
	             PreparedStatement statement = connection.prepareStatement(
	            		"UPDATE huespedes SET "
	            		+ "nombre = ?, "
	            		+ "apellido = ?, "
	            		+ "fecha_nacimiento = ?, "
	            		+ "nacionalidad = ?, "
	            		+ "telefono = ?, "
	            		+ "id_reserva = ? "
	            		+ "WHERE id = ?")){           
	            	
	                statement.setString(1, nombre);
	                statement.setString(2, apellido);
	                statement.setObject(3, fechaNacimiento);
	                statement.setString(4, nacionalidad);
	                statement.setString(5, telefono);
	                statement.setInt(6, idReserva);
	                statement.setInt(7, id);
	                
	                statement.execute();

	            }catch (SQLException e) {
	            	
	                throw new RuntimeException(e);
	        } 
	}
	
	public void eliminarHuesped(int id) {
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
