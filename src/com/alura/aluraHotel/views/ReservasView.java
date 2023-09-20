package com.alura.aluraHotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.aluraHotel.util.ConvertirFecha;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import com.alura.aluraHotel.controller.*;
import com.alura.aluraHotel.modelo.*;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	public static JComboBox<String> txtHabitacion;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private JTextField textValorReserva;
	private ReservaController reservaController;
	private static Reserva reserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");
		this.reservaController = new ReservaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// Código que crea los elementos de la interfáz gráfica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 154, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 226, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(SystemColor.textHighlight);
		separator_1_4.setBackground(SystemColor.textHighlight);
		separator_1_4.setBounds(68, 372, 289, 2);
		panel.add(separator_1_4);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 100, 248, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 167, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		JLabel lblTipoDeHabitacin = new JLabel("TIPO DE HABITACIÓN");
		lblTipoDeHabitacin.setForeground(SystemColor.textInactiveText);
		lblTipoDeHabitacin.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTipoDeHabitacin.setBounds(68, 248, 289, 14);
		panel.add(lblTipoDeHabitacin);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(90, 47, 248, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(68, 312, 289, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 299, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);

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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);

		// Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 121, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 192, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// Activa el evento, después del usuario seleccionar las fechas se debe calcular
				// el valor de la reserva
			}
		});
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		textValorReserva = new JTextField();
		textValorReserva.setBounds(68, 337, 232, 36);
		textValorReserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(textValorReserva);
		textValorReserva.setColumns(10);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		txtHabitacion = new JComboBox();
		txtHabitacion.setModel(new DefaultComboBoxModel(
				new String[] { "1 cama", "2 camas", "3 camas", "Lujo - 1 cama", "Lujo - 2 camas" }));
		txtHabitacion.setBounds(68, 263, 289, 38);
		txtHabitacion.setBackground(SystemColor.text);
		txtHabitacion.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtHabitacion.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(txtHabitacion);

		/**
		 * Configuración del boton siguiente
		 */
		JPanel btnsiguiente = new JPanel();
		btnsiguiente.setToolTipText("");
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					guardar();
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(208, 493, 152, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblBotonSig = new JLabel("SIGUIENTE");
		lblBotonSig.setForeground(new Color(255, 255, 255));
		lblBotonSig.setHorizontalAlignment(SwingConstants.CENTER);
		lblBotonSig.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBotonSig.setBounds(0, 0, 152, 35);
		btnsiguiente.add(lblBotonSig);

		/**
		 * Configuracion del boton calcular
		 */
		JButton btnCalcular = new JButton("OK");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					calcularValorReserva(txtFechaEntrada, txtFechaSalida);
					System.out.println(ConvertirFecha.convertirDateALocalDate(txtFechaEntrada.getDate()));
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnCalcular.setBounds(297, 337, 60, 37);
		panel.add(btnCalcular);

	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
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
	 * Valor total de la reserva por X dias 
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public BigDecimal calcularValorReserva(JDateChooser fechaEntrada, JDateChooser fechaSalida) {
		BigDecimal valorReserva = new BigDecimal("0.0");

		BigDecimal valorReservaDia;
		switch (txtHabitacion.getSelectedIndex()) {
		case 0:
			valorReservaDia = new BigDecimal("200");
			break;
		case 1:
			valorReservaDia = new BigDecimal("250");
			break;
		case 2:
			valorReservaDia = new BigDecimal("300");
			break;
		case 3:
			valorReservaDia = new BigDecimal("500");
			break;
		case 4:
			valorReservaDia = new BigDecimal("600");
			break;
		default:
			valorReservaDia = new BigDecimal("200");
		}

		long numeroDias;
		if ((fechaEntrada.getDate() != null) && (fechaSalida.getDate() != null)) {
			LocalDate fechaIn = ConvertirFecha.convertirDateALocalDate(txtFechaEntrada.getDate());
			LocalDate fechaOut = ConvertirFecha.convertirDateALocalDate(txtFechaSalida.getDate());
			numeroDias = ChronoUnit.DAYS.between(fechaIn, fechaOut);
			System.out.println(numeroDias);
			if (numeroDias > 0) {
				BigDecimal diasReservados = new BigDecimal(numeroDias);
				System.out.println(numeroDias);
				valorReserva = diasReservados.multiply(valorReservaDia);
				textValorReserva.setText(String.valueOf(valorReserva));
				return valorReserva;
			} else {
				textValorReserva.setText(String.valueOf(valorReserva));
				return valorReserva;
			}
		} else {
			textValorReserva.setText(String.valueOf(valorReserva));
			return valorReserva;
		}
	}

	/**
	 * Guardar la reserva
	 */
	private void guardar() {
		if (textValorReserva == null) {
			JOptionPane.showMessageDialog(this, "Todos los campos son requeridos.");
			return;
		}
		Date dateCheckIn = Date.valueOf(ConvertirFecha.convertirDateALocalDate(txtFechaEntrada.getDate()));
		Date dateCheckOut = Date.valueOf(ConvertirFecha.convertirDateALocalDate(txtFechaSalida.getDate()));
		String valorReservaString = textValorReserva.getText();
		BigDecimal valorReservaToBigDecimal = new BigDecimal(valorReservaString);

		Reserva reserva = new Reserva(dateCheckIn, dateCheckOut, valorReservaToBigDecimal,
				txtFormaPago.getSelectedItem().toString());

		this.reservaController.guardar(reserva);
		
		this.dispose();
		RegistroHuesped registrarHuesped = new RegistroHuesped();
		registrarHuesped.setVisible(true);
	}


}
