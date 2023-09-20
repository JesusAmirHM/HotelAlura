package com.alura.aluraHotel.controller;

import java.sql.Date;
import java.util.List;
import com.alura.aluraHotel.dao.HuespedesDao;
import com.alura.aluraHotel.factory.ConnectionFactory;
import com.alura.aluraHotel.modelo.Huespedes;

public class HuespedesController {


	private final HuespedesDao huespedesDao;
	
	 public HuespedesController() {
	        this.huespedesDao = new HuespedesDao(new ConnectionFactory().recuperaConexion());
	    }
	
	 public List<Huespedes> listar() {
	        return huespedesDao.listar();
	    }
	 
	 public List<Huespedes> listar(String apellido) {
	        return huespedesDao.listar(apellido);
	    }
	 
	 public void guardar(Huespedes huesped) {
	        huespedesDao.guardar(huesped);
	    }
	 
	 public int actualizar(Integer idHuesped, String nombre, String apellido, Date fechaNacimiento,
	            String nacionalidad, String telefono) {
	        return huespedesDao.actualizar(idHuesped, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
	    }
	 
	 public int eliminar(Integer idHuesped, String idReserva) {
	        return huespedesDao.eliminar(idHuesped, idReserva);
	    }
}
