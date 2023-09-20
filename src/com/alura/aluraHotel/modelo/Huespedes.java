package com.alura.aluraHotel.modelo;

import java.sql.Date;

public class Huespedes {

	private Integer idHuesped;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private String idReserva;
	
	/**
	 * Constructor Huespedes
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param nacionalidad
	 * @param telefono
	 * @param idReserva
	 */
	
	public Huespedes(String nombre, String apellido, java.sql.Date fechaNacimiento, String nacionalidad,
			String telefono, String idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	/**
	 * Huespedes
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param nacionalidad
	 * @param telefono
	 * @param idReserva
	 */
	 public Huespedes(int id, String nombre, String apellido,
	            Date fechaNacimiento, String nacionalidad, String telefono,
	            String idReserva) {
	        this.idHuesped = id;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.fechaNacimiento = fechaNacimiento;
	        this.nacionalidad = nacionalidad;
	        this.telefono = telefono;
	        this.idReserva = idReserva;
	    }

	 
	 /**
	  * Getters & Setters
	  */
	 
	public Integer getId() {
		return idHuesped;
	}
	
	public void setId(Integer id) {
		this.idHuesped = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getIdReserva() {
		return idReserva;
	}
	
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	
	
	/**
	 * Sobrescribiendo el toString() 
	 */
	 @Override
	    public String toString() {
	        return String.format("{ID: %s, Nombre: %s, Apellido: %s, FechaNacimiento: %s, Nacionalidad: %s, Teléfono: %s, ID_Reserva: %s}",
	                this.idHuesped,
	                this.nombre,
	                this.apellido,
	                this.fechaNacimiento,
	                this.nacionalidad,
	                this.telefono,
	                this.idReserva
	        );
	    }
}
