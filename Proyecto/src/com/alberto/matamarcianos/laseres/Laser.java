package com.alberto.matamarcianos.laseres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Laser extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen;
	String tipo;
	int velocidadLaser;
	int velocidad;
	int danio;
	
	public abstract Texture cargarTextura();
	
	public abstract String obtenerTipo();
	
	public abstract int obtenerVelocidadLaser();
	
	public abstract int obtenerDanio();
	
	public abstract void dispose();
	
	public abstract void fijarDanio(int danio);

}
