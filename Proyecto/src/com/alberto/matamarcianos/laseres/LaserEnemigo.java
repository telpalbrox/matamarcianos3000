package com.alberto.matamarcianos.laseres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class LaserEnemigo extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen;
	String tipo;
	int velocidadLaser;
	int velocidad;
	int danio;
	
	public Texture cargarTextura() {
		return imagen;
	}
	
	public String obtenerTipo() {
		return tipo;
	}
	
	public int obtenerVelocidadLaser() {
		return velocidadLaser;
	}
	
	public int obtenerDanio() {
		return danio;
	}
	
	public void dispose() {
		imagen.dispose();
	}

}
