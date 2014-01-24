package com.alberto.matamarcianos.laseres;

import com.alberto.matamarcianos.enemgos.NaveEnemiga;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LaserEnemigo3 extends Laser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen = new Texture(Gdx.files.internal("data/images/laser.png"));
	String tipo = "laserEnemigo2";
	int velocidadLaser = 50;
	int velocidad;
	int danio = 2;
	
	public LaserEnemigo3(NaveEnemiga enemigo) {
		this.velocidad = enemigo.obtenerVelocidad() + velocidadLaser;
	}
	
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

	@Override
	public void fijarDanio(int danio) {}
}
