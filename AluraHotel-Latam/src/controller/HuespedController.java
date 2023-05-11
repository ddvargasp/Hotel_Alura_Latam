package controller;

import java.time.LocalDate;
import java.util.List;

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
	
	public List<Huesped> listarHuesped(){
		return this.huespedDAO.listarHuesped();
	}
	
	
	public List<Huesped> buscarHuespedId(String id){
		return this.huespedDAO.buscarHuespedId(id);
	}
	
	public void actualizar(String nombre, String apellido, LocalDate fechaNacimiento, 
			String nacionalidad, String telefono, Integer idReserva, Integer id) {
		this.huespedDAO.actualizarHuesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	}
	
	public void eliminarHuesped(Integer id) {
		this.huespedDAO.eliminarHuesped(id);
	}
	
}
