package com.alberto.matamarcianos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class NaveEnemiga extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int vida;
	static Texture imagen;
	int velocidad;
	int velocidadx;
	String tipo;
	float tiempoLaser, tiempoMuerte;
	boolean disparaLaser, animacion, muerto;
	
	public void quitarVida() {
		
	}
	
	public Texture cargarTextura() {
		return imagen;
	}
	
	public String obtenerTipo() {
		return tipo;
	}
	
	public int obtenerVida() {
		return vida;
	}
	
	public int obtenerVelocidad() {
		return velocidad;
	}
	
	public void fijarVelocidad(int velocidad) {
		
	}
	
	public void fijarTiempoLaser(float tiempo) {
		tiempoLaser = tiempo;
	}
	
	public float obtenerTiempoLaser() {
		return tiempoLaser;
	}
	
	public void fijarDisparaLaser(boolean bool) {
		disparaLaser = bool;
	}
	
	public boolean obtenerDisparaLaser() {
		return disparaLaser;
	}
	
	public boolean esMuerto() {
		return muerto;
	}
	
	public void fijarMuerto(boolean bol) {
		this.muerto = bol;
	}
	
	public void fijarTiempoMuerte(float tiempo) {
		tiempoMuerte = tiempo;
	}
	
	public float obtenerTiempoMuerte() {
		return tiempoMuerte;
	}
	
	public boolean esAnimacion() {
		return animacion;
	}
	
	public void fijarAnimacion(boolean bol) {
		this.animacion = bol;
	}
	
	public void dispose() {
		
	}
	
	public int obtenerVelocidadX() {
		return velocidadx;
	}
	
	public void fijarVelocidadX(int velocidad) {
		this.velocidadx = velocidad;
	}
	

}
