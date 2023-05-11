package controller;

import java.time.LocalDate;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(connectionFactory.recuperaConexion());
	}

	public void guardarReserva(Reserva reserva) {
		this.reservaDAO.guardarReserva(reserva);
	}
	
	public List<Reserva> listarReservas() {
		return this.reservaDAO.listarReserva();
	}

	public List<Reserva> buscarReservaId(String id) {
		return this.reservaDAO.buscarReservaId(id);
	}

	public void actualizarReserva(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id) {
		this.reservaDAO.actualizarReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
	

	public void eliminarReserva(Integer id) {
    	this.reservaDAO.eliminarReserva(id);
    }
	
}
