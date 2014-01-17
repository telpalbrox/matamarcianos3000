package com.alberto.matamarcianos.conexion;

import java.util.Collection;

public class Facade {
	
	public Collection<PuntuacionesDTO> obtenerPuntuaciones() {
		PuntuacionesDAO puntuaciones = new PuntuacionesDAO();
		return puntuaciones.obtenerPuntuaciones();
	}
	
	public void insertarPuntuacion(String jugador, int puntuacion) {
		InsertarPuntuacion.jugador = jugador;
		InsertarPuntuacion.puntuacion = puntuacion;
		InsertarPuntuacion.insertarPuntuacion().start();
	}

}