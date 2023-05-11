package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import model.Huesped;
import model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservaController reservaController;
	private HuespedController huespedController;
	private ReservasView reservasView;
	
	String reservas;
	String huespedes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		this.reservasView = new ReservasView();
		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("Numero de Reserva");
		modeloReserva.addColumn("Fecha Check In");
		modeloReserva.addColumn("Fecha Check Out");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		MostrarTablaReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		MostrarTablaHuespedes();
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				limpiarTabla();
				if(txtBuscar.getText().equals("")) {
					MostrarTablaReservas();
					MostrarTablaHuespedes();
				}else {
					MostrarTablaReservaId();
					MostrarTablaHuespedId();
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				if(filaReservas >= 0) {
					ActualizarReservas();
					limpiarTabla();
					MostrarTablaReservas();
					MostrarTablaHuespedes();
				}else
					{if(filaHuespedes >= 0) {
						ActualizarHuespedes();
						limpiarTabla();
						MostrarTablaHuespedes();
						MostrarTablaReservas();
					}
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					
					reservas = tbReservas.getValueAt(filaReservas, 0).toString();
					
					int confirmar = JOptionPane.showConfirmDialog(null, "Seguro que desea borrar la reserva?");
					
					if (confirmar == JOptionPane.YES_OPTION) {
						
						String valor = tbReservas.getValueAt(filaReservas, 0).toString();
						reservaController.eliminarReserva(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "El registro de la reserva fue eliminado correctamente!");
						
						limpiarTabla();
						MostrarTablaReservas();	
						MostrarTablaHuespedes();
					}
				}
				else 
					if(filaHuespedes >= 0) {
						
					huespedes = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
					
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el Huésped?");
					
					if (confirmar == JOptionPane.YES_OPTION) {
						String valor = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						huespedController.eliminarHuesped(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Huésped eliminado correctamente!");
						
						limpiarTabla();
						MostrarTablaHuespedes();
						MostrarTablaReservas();	
					}
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	// 		-------- Reservas --------

	private List<Reserva> MostrarReservas() {
		return this.reservaController.listarReservas();
	}

	private List<Reserva> buscarReservaId() {
		return this.reservaController.listarReservas();
	}	

	private void MostrarTablaReservas() {

		List<Reserva> reserva = MostrarReservas();
		modeloReserva.setRowCount(0);

		try {

			for(Reserva reservas : reserva ) {
				modeloReserva.addRow(new Object[] {
						reservas.getId(), 
						reservas.getFechaEntrada(), 
						reservas.getFechaSalida(), 
						reservas.getValor(),
						reservas.getFormaPago()
				});
			}
		}

		catch(Exception ex) {
			throw ex;
		}

	}

	protected void MostrarTablaReservaId() {

		List<Reserva> reserva = buscarReservaId();

		try {
			for(Reserva reservas : reserva ) {
				modeloReserva.addRow(new Object[] {
						reservas.getId(), 
						reservas.getFechaEntrada(), 
						reservas.getFechaSalida(), 
						reservas.getValor(), 
						reservas.getFormaPago()
				});
			}
		}
		catch(Exception ex) {
			throw ex;
		}

	}

	protected void ActualizarReservas() {

		Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(),
				tbReservas.getSelectedColumn())).ifPresent(fila ->{

					LocalDate fechaEntrada;
					LocalDate fechaSalida;

					try {

						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

						fechaEntrada = LocalDate.parse(modeloReserva.getValueAt(
								tbReservas.getSelectedRow(),1).toString(),
								dateTimeFormatter);

						fechaSalida = LocalDate.parse(modeloReserva.getValueAt(
								tbReservas.getSelectedRow(), 2).toString(),
								dateTimeFormatter);

					}

					catch(DateTimeException e) {				
						throw new RuntimeException(e);
					}

					this.reservasView.limpiarValor();

					String valor = ("₡ " + calcularValorHospedaje(fechaEntrada, fechaSalida));
					String formaPago = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);
					Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());

					if(tbReservas.getSelectedColumn()==0) {

						JOptionPane.showMessageDialog(this, "No se pueden editar los ID");
					}

					else {

						this.reservaController.actualizarReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
						JOptionPane.showMessageDialog(this, "Registro de reservas modificado con exito");
					}

				});


	}

	
	//		-------- Huespedes --------

	private List<Huesped> MostrarHuesped(){
		
		return this.huespedController.listarHuesped();
		
	}
	
	private List<Huesped> buscarIdHuesped(){
		
		return this.huespedController.buscarHuespedId(txtBuscar.getText());
		
	}

	private void MostrarTablaHuespedes() {

		List<Huesped> huesped = MostrarHuesped();
		
		modeloHuesped.setRowCount(0);
		try {
			
			for(Huesped huespedes : huesped ) {
				
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(), 
						huespedes.getNombre(), 
						huespedes.getApellido(), 
						huespedes.getFechaNacimiento(), 
						huespedes.getNacionalidad(), 
						huespedes.getTelefono(), 
						huespedes.getIdReserva()
				});
			}
		}
		catch(Exception e) {
			throw e;
		}
	}

	protected void MostrarTablaHuespedId() {

		List<Huesped> huesped = buscarIdHuesped();

		try {
			for(Huesped huespedes : huesped ) {

				modeloHuesped.addRow(new Object[] {
						huespedes.getId(), 
						huespedes.getNombre(),
						huespedes.getApellido(), 
						huespedes.getFechaNacimiento(), 
						huespedes.getNacionalidad(),
						huespedes.getTelefono(), 
						huespedes.getIdReserva()
				});
			}
		}

		catch(Exception e) {
			throw e;
		}

	}

	protected void ActualizarHuespedes() {

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 
				tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(filaHuespedes ->{
			
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
			String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString(); 
			LocalDate fechaNacimiento = LocalDate.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());			
			String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
			String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
			Integer idReservas = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());			
						
			if(tbHuespedes.getSelectedColumn()==0 || tbHuespedes.getSelectedColumn()==6) {
				
				JOptionPane.showMessageDialog(this, "No se pueden editar los ID");
			}
			
			else {
				
				this.huespedController.actualizar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReservas, id);
				JOptionPane.showMessageDialog(this, "Registro modificado con exito");
			}		
		
		}, ()->JOptionPane.showInternalMessageDialog(this, "Algo Salio Mal"));

	}

	private String calcularValorHospedaje(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		if(fechaEntrada != null && fechaSalida != null) {
			
			int dias = (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
			int noche =12000;
			int valor = dias* noche;
			
			return Integer.toString(valor);
		}
		else {
			
			return "";
		}
	}

	protected void limpiarTabla() {
		
		((DefaultTableModel)tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel)tbReservas.getModel()).setRowCount(0);

	}

	

	

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
