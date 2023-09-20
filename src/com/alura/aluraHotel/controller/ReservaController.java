package com.alura.aluraHotel.controller;

import java.sql.Date;
import java.util.List;
import com.alura.aluraHotel.dao.ReservaDao;
import com.alura.aluraHotel.factory.ConnectionFactory;
import com.alura.aluraHotel.modelo.Reserva;


public class ReservaController {

	 private final ReservaDao reservaDao;
	 
	 public ReservaController() {
	        this.reservaDao = new ReservaDao(new ConnectionFactory().recuperaConexion());
	    }
	
	 public List<Reserva> listar() {
	        return reservaDao.listar();
	    }
	 
	 public List<Reserva> listar(String idReserva) {
	        return reservaDao.listar(idReserva);
	    }
	 
	 public void guardar(Reserva reserva) {
	        reservaDao.guardar(reserva);
	    }
	 
	 public int actualizar(String idReserva, Date fechaEntrada,
	            Date fechaSalida, double valorReserva, String formaPago) {
	        return reservaDao.actualizar(idReserva, fechaEntrada, fechaSalida, valorReserva, formaPago);
	    }
	 
	 public int obtenerId() {
		return reservaDao.obtenerId();
		
	 }

}
