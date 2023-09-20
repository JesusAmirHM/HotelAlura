package com.alura.aluraHotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.aluraHotel.modelo.*;
import com.alura.aluraHotel.controller.*;
import com.alura.aluraHotel.dao.*;

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
import java.sql.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private HuespedesController huespedesController;
	private ReservaController reservaController;

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
		this.huespedesController = new HuespedesController();
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
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtBuscarKeyTyped(e);
			}
		});
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(280, 63, 477, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbReservasMouseClicked(e);
			}
		});
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane tableReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tableReservas,
				null);
		tableReservas.setVisible(true);
		cargarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbHuespedesMouseClicked(e);
			}
		});
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
		JScrollPane tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				tableHuespedes, null);
		tableHuespedes.setVisible(true);
		cargarTablaHuespedes();

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
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
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

		JPanel btnBuscar = new JPanel();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 btnBuscarMouseClicked(e);
			}
		});
		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnBuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEditarMouseClicked(e);
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
				btnEliminarMouseClicked(e);
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
		
		JPanel btnInicio = new JPanel();
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnInicioMouseClicked(e);
			}

		});
		btnInicio.setLayout(null);
		btnInicio.setBackground(new Color(12, 138, 199));
		btnInicio.setBounds(20, 508, 122, 35);
		btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnInicio);
		
		JLabel lblInicio = new JLabel("INICIO");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblInicio.setBounds(0, 0, 122, 35);
		btnInicio.add(lblInicio);
		setResizable(false);
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

	/**
	 * Cargar tabla huespedes
	 */
	private void cargarTablaHuespedes() {
		List<Huespedes> listaHuespedes = this.huespedesController.listar();
		listaHuespedes.forEach((huesped) -> {
			modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getIdReserva() });
		});
	}

	/**
	 * Buscar el huesped mediante su apellido
	 * @param campoBusqueda
	 */
	private void cargarTablaHuespedes(JTextField campoBusqueda) {
		String apellido = campoBusqueda.getText();
		List<Huespedes> listaHuespedes = this.huespedesController.listar(apellido);
		listaHuespedes.forEach((huesped) -> {
			modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getIdReserva() });
		});
	}

	/**
	 * Cargar las reservas
	 */
	private void cargarTablaReservas() {
		List<Reserva> listaReservas = this.reservaController.listar();
		listaReservas.forEach((reserva) -> {
			modelo.addRow(new Object[] { reserva.getIdReserva(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValorReserva(), reserva.getFormaPago() });
		});
	}

	/**
	 * Cargar la reserva con id proporcionado
	 * @param campoBusqueda
	 */
	private void cargarTablaReservas(JTextField campoBusqueda) {
		String idReserva = campoBusqueda.getText();
		List<Reserva> listaReservas = this.reservaController.listar(idReserva);
		listaReservas.forEach((reserva) -> {
			modelo.addRow(new Object[] { reserva.getIdReserva(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValorReserva(), reserva.getFormaPago() });
		});
	}

	/**
	 * Limpiar la tabla de huespedes
	 */
	private void limpiarTablaHuespedes() {
		modeloHuesped.getDataVector().clear();
		tbHuespedes.clearSelection();
	}

	/**
	 * Limpiar la tabla de huespedes
	 */
	private void limpiarTablaReservas() {
		modelo.getDataVector().clear();
		tbReservas.clearSelection();
	}

	/**
	 * Editar la tabla de huespedes
	 */
	private void actualizarHuesped() {
		int fila = tbHuespedes.getSelectedRow();
		if (fila < 0) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
		} else {
			Integer idHuesped = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
			String nombre = String.valueOf(tbHuespedes.getValueAt(fila, 1));
			String apellido = String.valueOf(tbHuespedes.getValueAt(fila, 2));
			Date fechaNac = Date.valueOf(tbHuespedes.getValueAt(fila, 3).toString());
			String nacionalidad = String.valueOf(tbHuespedes.getValueAt(fila, 4));
			String telefono = String.valueOf(tbHuespedes.getValueAt(fila, 5));

			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresent(row -> {
						int lineasActualizadas;
						lineasActualizadas = this.huespedesController.actualizar(idHuesped, nombre, apellido, fechaNac,
								nacionalidad, telefono);
						JOptionPane.showMessageDialog(null,
								lineasActualizadas + " " + "registro actualizado éxitosamente.",
								"Actualización éxitosa.", JOptionPane.INFORMATION_MESSAGE);
					});
		}
	}
	

	/**
	 * Eliminar el huesped y su reserva
	 */
	 private void eliminarHuesped() {
	        int fila = tbHuespedes.getSelectedRow();
	        if (fila < 0) {
	            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
	        } else {
	            Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                    .ifPresent(row -> {
	                        Integer idHuesped = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
	                        String idReserva = String.valueOf(tbHuespedes.getValueAt(fila, 6));
	                        
	                        int cantidadEliminada;
	                        cantidadEliminada = this.huespedesController.eliminar(idHuesped, idReserva);
	                        JOptionPane.showMessageDialog(
	                                null,
	                                cantidadEliminada + " " + "registro eliminado éxitosamente.",
	                                "Eliminación de registro éxitosa.",
	                                JOptionPane.INFORMATION_MESSAGE
	                        );
	                    });
	        }
	    }
	
	 /**
	  * Confirmar la eliminación del huesped y su reserva
	  * @param evt
	  */
	 private void confirmarEliminacionHuesped(java.awt.event.MouseEvent evt) {
	        Object[] opciones = {"Aceptar", "Cancelar"};
	        int eleccion = JOptionPane.showOptionDialog(
	                this,
	                "¿Realmente desea eliminar el registro?\n"
	                + "El registro será eliminado definitivamente.",
	                "Confirmar eliminación de registro.",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.WARNING_MESSAGE,
	                null,
	                opciones,
	                "Aceptar"
	        );
	        if (eleccion == JOptionPane.YES_OPTION) {
	            evt.consume();
//	            reestablecerCampos();
	            eliminarHuesped();
	            limpiarTablaHuespedes();
	            limpiarTablaReservas();
	            cargarTablaHuespedes();
	            cargarTablaReservas();
	        }
	    }

	 /**
	  * Editar la reserva
	  */
	 private void actualizarReserva() {
	        int fila = tbReservas.getSelectedRow();
	        if (fila < 0) {
	            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
	        } else {
	            String idReserva = String.valueOf(tbReservas.getValueAt(fila, 0));
	            Date fechaEntrada = Date.valueOf(tbReservas.getValueAt(fila, 1).toString());
	            Date fechaSalida = Date.valueOf(tbReservas.getValueAt(fila, 2).toString());
	            String valorReservaStringTabla = String.valueOf(tbReservas.getValueAt(fila, 3));
	            double valorReservaToDouble = Double.parseDouble(valorReservaStringTabla);
	            String seleccionPago = String.valueOf(tbReservas.getValueAt(fila, 4));
	                Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                        .ifPresent(row -> {
	                            int lineasActualizadas;
	                            lineasActualizadas = this.reservaController.actualizar(idReserva, fechaEntrada, fechaSalida,
	                                    valorReservaToDouble, seleccionPago);
	                            JOptionPane.showMessageDialog(
	                                    null,
	                                    lineasActualizadas + " " + "registro actualizado éxitosamente.",
	                                    "Actualización éxitosa.",
	                                    JOptionPane.INFORMATION_MESSAGE);
	                        });
			}
		}

	 
	 /**
	  * Al presionar el boton BUSCAR se ejecutara
	  * @param e
	  */
	 private void btnBuscarMouseClicked(java.awt.event.MouseEvent e) {
	        e.consume();
	        if (tbHuespedes.isShowing()) {
	            limpiarTablaHuespedes();
	            cargarTablaHuespedes(txtBuscar);
	        } else {
	            limpiarTablaReservas();
	            cargarTablaReservas(txtBuscar);
	        }
	    }

	 /**
	  * Configuración del boton ELIMINAR
	  * @param e
	  */
	 private void btnEliminarMouseClicked(java.awt.event.MouseEvent e) {
	        if (tbHuespedes.isShowing()) {
	            confirmarEliminacionHuesped(e);
	        } 
	 }
	 
	 /**
	  * Configuracion del boton EDITAR
	  * @param e
	  */
	 private void btnEditarMouseClicked(java.awt.event.MouseEvent e) {
	        e.consume();
	        if (tbHuespedes.isShowing()) {
	            actualizarHuesped();
	            limpiarTablaHuespedes();
	            cargarTablaHuespedes();
	        } else if (tbReservas.isShowing()) {
	            actualizarReserva();
	            limpiarTablaReservas();
	            cargarTablaReservas();
	        } 
	    }

	 /**
	  * Al seleccionar una fila de la tabla de huespedes
	  * @param e
	  */
	 private void tbHuespedesMouseClicked(java.awt.event.MouseEvent e) {
	        if (e.getClickCount() == 1) {
	            int fila = tbHuespedes.getSelectedRow();
	            String nombre = String.valueOf(tbHuespedes.getValueAt(fila, 1));
	            String apellido = String.valueOf(tbHuespedes.getValueAt(fila, 2));
	            String fecha = String.valueOf(tbHuespedes.getValueAt(fila, 3));
	            String nacionalidad = String.valueOf(tbHuespedes.getValueAt(fila, 4));
	            String telefono = String.valueOf(tbHuespedes.getValueAt(fila, 5));
	        }
	    }
	 
	 /**
	  * AL seleccionar una fila de la tabla de reservas
	  */
	 private void tbReservasMouseClicked(java.awt.event.MouseEvent e) {
	        if (e.getClickCount() == 1) {
	            int fila = tbReservas.getSelectedRow();
	            String fechaEntradaOfTablaReservas = String.valueOf(tbReservas.getValueAt(fila, 1));
	            String fechaSalidaOfTablaReservas = String.valueOf(tbReservas.getValueAt(fila, 2));
	            String valor = String.valueOf(tbReservas.getValueAt(fila, 3));
	            String formaPago = String.valueOf(tbReservas.getValueAt(fila, 4));
	        }
	    }
	 
	 /**
	  * Coomportamiento del textbox Buscar
	  * @param e
	  */
	 private void txtBuscarKeyTyped(java.awt.event.KeyEvent e) {
		 char comodin = '%';
	        if (e.getKeyChar() == comodin) {
	            e.consume();
	        }
	    }
	 
	 /**
	  * COnfiguración del boton INICIO
	  * @param e
	  */
	 private void btnInicioMouseClicked(MouseEvent e) {
		 limpiarTablaHuespedes();
         limpiarTablaReservas();
         cargarTablaHuespedes();
         cargarTablaReservas();
		}
}
