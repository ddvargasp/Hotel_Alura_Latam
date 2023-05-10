package controller;

import dao.UsuarioDAO;
import factory.ConnectionFactory;

public class UsuarioController {

	private UsuarioDAO  usuarioDAO;
	
	public UsuarioController() {
		ConnectionFactory  connectionFactory = new ConnectionFactory();
		this.usuarioDAO = new UsuarioDAO(connectionFactory.recuperaConexion());
	}
	
	public boolean VerificarUsuario(String nombre, String contraseña) {
		return usuarioDAO.verificarUsuario(nombre, contraseña);
	}
}
