package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import model.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;
	
	public HuespedController() {		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(connectionFactory.recuperaConexion());		
	}
	
	public void guardarHuesped(Huesped huesped) {
		this.huespedDAO.guardarHuesped(huesped);
	}
	
	
}
