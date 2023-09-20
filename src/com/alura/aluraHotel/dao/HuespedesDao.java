package com.alura.aluraHotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alura.aluraHotel.modelo.Huespedes;

public class HuespedesDao {

	private Connection con;

	public HuespedesDao(Connection con) {
		this.con = con;
	}

	/**
	 * Metódo guardar para la tabla de huespedes
	 * 
	 * @param huesped
	 */
	public void guardar(Huespedes huesped) {
		try {
			String sql = "INSERT INTO huespedes "
					+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
				preparedStatement.setString(1, huesped.getNombre());
				preparedStatement.setString(2, huesped.getApellido());
				preparedStatement.setDate(3, huesped.getFechaNacimiento());
				preparedStatement.setString(4, huesped.getNacionalidad());
				preparedStatement.setString(5, huesped.getTelefono());
				preparedStatement.setString(6, huesped.getIdReserva());
				preparedStatement.execute();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {

						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue guardado con éxito el " + "húesped: %s", huesped));
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al realizar el registro.", "Error al guardar los datos.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo para actualizar la tabla de huespedes
	 */
	public int actualizar(Integer idHuesped, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono) {
		try {
			String sql = "UPDATE huespedes " + "SET nombre = ?, apellido = ?, fecha_nacimiento = ?, "
					+ "nacionalidad = ?, telefono = ? " + "WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
				preparedStatement.setString(1, nombre);
				preparedStatement.setString(2, apellido);
				preparedStatement.setDate(3, fechaNacimiento);
				preparedStatement.setString(4, nacionalidad);
				preparedStatement.setString(5, telefono);
				preparedStatement.setInt(6, idHuesped);
				preparedStatement.execute();
				int updateCount = preparedStatement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar los datos.", "Inténtelo más tarde.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo para elminar el registro de un huesped y su reserva
	 * @param idHuesped
	 * @param idReserva
	 * @return
	 */
	public int eliminar(Integer idHuesped, String idReserva) {
		try {
			String sql = "DELETE FROM huespedes WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setInt(1, idHuesped);
				preparedStatement.execute();
				eliminarReserva(idReserva);
				int updateCount = preparedStatement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar los datos.", "Inténtelo más tarde.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo para eliminar la reserva
	 * @param idReserva
	 */
	private void eliminarReserva(String idReserva) {
		String sql = "DELETE FROM reserva WHERE id = ?";
		try {
			try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
				preparedStatement.setString(1, idReserva);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar los datos.", "Inténtelo más tarde.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo para listar los huespedes
	 * @return
	 */
	public List<Huespedes> listar() {
		List<Huespedes> listaHuespedes = new ArrayList<>();
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva "
				+ "FROM huespedes";
		try {
			try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getResultSet();
				while (resultSet.next()) {
					Huespedes fila = new Huespedes(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_NACIMIENTO"),
							resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
							resultSet.getString("ID_RESERVA"));
					listaHuespedes.add(fila);
				}
				return listaHuespedes;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Inténtelo más tarde.", "Error al traer los datos.",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Metodo para buscar por apellido a un huesped
	 * @param apellido
	 * @return
	 */
    public List<Huespedes> listar(String apellido) {
        List<Huespedes> listaHuespedes = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "id, nombre, apellido, fecha_nacimiento, "
                    + "nacionalidad, telefono, id_reserva "
                    + "FROM huespedes "
                    + "WHERE apellido LIKE ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, apellido.concat("%"));
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Huespedes fila = new Huespedes(
                            resultSet.getInt("ID"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("FECHA_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO"),
                            resultSet.getString("ID_RESERVA")
                    );
                    listaHuespedes.add(fila);
                }
                return listaHuespedes;
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

}
