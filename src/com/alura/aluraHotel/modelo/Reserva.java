package com.alura.aluraHotel.modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserva {

	
	private Integer idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private BigDecimal valorReserva;
    private String formaPago;

    /**
     * Constructor Reserva
     * @param fechaEntrada
     * @param fechaSalida
     * @param valorReserva
     * @param formaPago
     */
    public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }
    
    /**
     * Constructor Reserva
     * @param idReserva
     * @param fechaEntrada
     * @param fechaSalida
     * @param valorReserva
     * @param formaPago
     */
    public Reserva(Integer idReserva, Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }

    
    /**
     * Getters & Setters
     */
	public Integer getIdReserva() {
		return idReserva;
	}
	
	public void setIdReserva(Integer id) {
		this.idReserva = id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public BigDecimal getValorReserva() {
		return valorReserva;
	}
	
	public void setValorReserva(BigDecimal valorReserva) {
		this.valorReserva = valorReserva;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
    
	
	/**
	 * Sobrescribiendo toString()
	 */
	 @Override
	    public String toString() {
	        return String.format("{ID: %s, FechaEntrada: %s, FechaSalida: %s, Total: %f, FormaPago: %s}",
	                this.idReserva,
	                this.fechaEntrada,
	                this.fechaSalida,
	                this.valorReserva,
	                this.formaPago
	        );
	    }


}
