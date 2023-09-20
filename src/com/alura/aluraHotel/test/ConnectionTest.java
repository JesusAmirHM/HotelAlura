package com.alura.aluraHotel.test;

import java.sql.Connection;
import java.sql.SQLException;
import com.alura.aluraHotel.factory.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) {

		try(Connection con = new ConnectionFactory().recuperaConexion();){
            System.out.println("Conectado");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
	

}
