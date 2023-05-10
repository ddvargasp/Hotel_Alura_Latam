package controller;

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
}
