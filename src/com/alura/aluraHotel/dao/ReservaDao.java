package com.alura.aluraHotel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.alura.aluraHotel.modelo.Reserva;

public class ReservaDao {

	private Connection con;

	/**
	 * Contructor ReservaDao
	 * @param con
	 */
	public ReservaDao(Connection con) {
		this.con = con;
	}

	/**
	 * Metodo guardar, preparedStatement, MySQL
	 * @param reserva
	 */
	 public void guardar(Reserva reserva) {
	        try {
	            String sql = "INSERT INTO reserva (fechaEntrada, fechaSalida, valor, formaPago) "
	                    + "VALUES (?, ?, ?, ?)";
	            try (
	            		
		            	PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
		                preparedStatement.setDate(1, reserva.getFechaEntrada());
		                preparedStatement.setDate(2, reserva.getFechaSalida());
		                preparedStatement.setBigDecimal(3, reserva.getValorReserva());
		                preparedStatement.setString(4, reserva.getFormaPago());
		                preparedStatement.execute();
		                try ( ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
		                    while (resultSet.next()) {
		                        System.out.println(
		                                String.format("Fue insertada la reserva: %s", reserva)
	                        );
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al realizar el registro.", "Error al guardar los datos.", JOptionPane.ERROR_MESSAGE);
	            throw new RuntimeException(e);
	        }
	    }
	 
	/**
	 * Metodo para actualizar la reserva
	 * @param idReserva
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @param valorReserva
	 * @param formaPago
	 * @return
	 */
	 public int actualizar(String idReserva, Date fechaEntrada,
	            Date fechaSalida, double valorReserva, String formaPago) {
	        try {
	            String sql = "UPDATE reserva "
	                    + "SET fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? "
	                    + "WHERE id = ?";
	            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
	                preparedStatement.setDate(1, fechaEntrada);
	                preparedStatement.setDate(2, fechaSalida);
	                preparedStatement.setDouble(3, valorReserva);
	                preparedStatement.setString(4, formaPago);
	                preparedStatement.setString(5, idReserva);
	                preparedStatement.execute();
	                int updateCount = preparedStatement.getUpdateCount();
	                return updateCount;
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al actualizar el registro.");
	            throw new RuntimeException(e);
	        }
	    }
		
		/**
		 * Metodo para listar las reservas
		 * @return
		 */
	    public List<Reserva> listar() {
	        List<Reserva> listarReservas = new ArrayList<>();
	        try {
	            String sql = "SELECT id, fechaEntrada, fechaSalida, valor, formaPago "
	                    + "FROM reserva";
	            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
	                preparedStatement.execute();
	                ResultSet resultSet = preparedStatement.getResultSet();
	                while (resultSet.next()) {
	                    Reserva fila = new Reserva(
	                            resultSet.getInt("ID"),
	                            resultSet.getDate("FECHAENTRADA"),
	                            resultSet.getDate("FECHASALIDA"),
	                            resultSet.getBigDecimal("VALOR"),
	                            resultSet.getString("FORMAPAGO")
	                    );
	                    listarReservas.add(fila);
	                }
	                return listarReservas;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
		/**
		 * Metodo para buscar una reserva por id
		 * @param idReserva
		 * @return
		 */
	    public List<Reserva> listar(String idReserva) {
	        List<Reserva> listaReservas = new ArrayList<>();
	        String sql = "SELECT\n"
	                + "id, fechaEntrada, fechaSalida, valor, formaPago\n"
	                + "FROM reserva\n"
	                + "WHERE id LIKE ?";
	        try {
	            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
	                preparedStatement.setString(1, idReserva.concat("%"));
	                preparedStatement.execute();
	                ResultSet resultSet = preparedStatement.getResultSet();
	                while (resultSet.next()) {
	                    Reserva fila = new Reserva(
	                            resultSet.getInt("id"),
	                            resultSet.getDate("fechaEntrada"),
	                            resultSet.getDate("FECHASALIDA"),
	                            resultSet.getBigDecimal("VALOR"),
	                            resultSet.getString("FORMAPAGO")
	                    );
	                    listaReservas.add(fila);
	                }
	                return listaReservas;
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(
	                    null,
	                    "Inténtelo más tarde.",
	                    "Error al traer los datos.",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            throw new RuntimeException(e);
	        }
	        
	    }

		/**
		 * Metodo para obtener la ultima reserva
		 * @return
		 */
	    public int obtenerId() {
	    	int maxId = 0;
	    	try {
	    		String sql = "SELECT MAX(id) AS max_id FROM reserva";
	    		try (PreparedStatement preparedStatement = con.prepareStatement(sql);){
	    			ResultSet resultSet = preparedStatement.executeQuery();
	    			while(resultSet.next()) {
	    				maxId = resultSet.getInt("max_id");
	    			}
	    			
	    		}
	    	} catch (SQLException e) {
	            JOptionPane.showMessageDialog(
	                    null,
	                    "Inténtelo más tarde.",
	                    "Error al traer los datos.",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            throw new RuntimeException(e);
	    		}
			return maxId;
	    }
	    
}

