package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LaserEnemigo2 extends LaserEnemigo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen = new Texture(Gdx.files.internal("data/laser.png"));
	String tipo = "laserEnemigo2";
	int velocidad;
	int danio;
	
	public LaserEnemigo2(NaveEnemiga enemigo) {
		this.velocidad = enemigo.obtenerVelocidad() + 50;
	}
	
	public Texture cargarTextura() {
		return imagen;
	}
	
	public String obtenerTipo() {
		return tipo;
	}
	
	public int obtenerVelocidad() {
		return velocidad;
	}
	
	public int obtenerDanio() {
		return danio;
	}
	
	public void dispose() {
		imagen.dispose();
	}

}
