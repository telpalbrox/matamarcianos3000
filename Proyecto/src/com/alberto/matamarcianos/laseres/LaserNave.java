package com.alberto.matamarcianos.laseres;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LaserNave extends Laser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen = new Texture(Gdx.files.internal("data/images/laserNave.png"));
	String tipo = "laserNave";
	int velocidadLaser;
	int velocidad = 400;
	int danio = 1;

	@Override
	public Texture cargarTextura() {
		return imagen;
	}

	@Override
	public String obtenerTipo() {
		return tipo;
	}

	@Override
	public int obtenerVelocidadLaser() {
		return velocidad;
	}

	@Override
	public int obtenerDanio() {
		return danio;
	}

	@Override
	public void dispose() {
		imagen.dispose();
	}

	@Override
	public void fijarDanio(int danio) {
		this.danio = danio;
	}

}
