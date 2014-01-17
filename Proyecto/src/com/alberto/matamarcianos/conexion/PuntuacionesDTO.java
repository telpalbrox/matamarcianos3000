package com.alberto.matamarcianos.conexion;

public class PuntuacionesDTO {
	private String jugador;
	private int puntuacion;
	private String version;
	
	public String toString() {
		return jugador+" | "+puntuacion;
	}
	
	public void fijarJugador(String jugador) {
		this.jugador = jugador;
	}
	
	public String obtenerJugador() {
		return jugador;
	}
	
	public void fijarPuntuacion(int numero) {
		this.puntuacion = numero;
	}
	
	public int obtenerPuntuacion() {
		return puntuacion;
	}
	
	public void fijarVersion(String version) {
		this.version = version;
	}
	
	public String obtenerVersion() {
		return version;
	}

}
