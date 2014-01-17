package com.alberto.matamarcianos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConexion {
	
	private static Connection con = null;
	
	public static Connection obtenerConexion() {
		try {
			if(con == null) {
				//con esto determino cuando finaliza el programa
				Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
				
				/*ResourceBundle rb = ResourceBundle.getBundle("mysql");
				String driver = rb.getString("driver");
				String url = rb.getString("url");
				String usr = rb.getString("usr");
				//String pwd = rb.getString("pwd");*/
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://db4free.net/albertopruebas", "telpalbrox", "sinior");
			}
			
			return con;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("NOOOO");
			throw new RuntimeException("Error al crear la conexión", ex);
		}
	}
	static class MiShDwnHook extends Thread {
		//justo antes de finalizar el programa la JVM invoca
		//a este método donde podemos cerrar la conexión
		public void run() {
			try {
				Connection con = MysqlConexion.obtenerConexion();
				con.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}
}
