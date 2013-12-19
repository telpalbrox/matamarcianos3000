package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemigo3 extends NaveEnemiga {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int vida = 10;
	static Texture imagen = new Texture(Gdx.files.internal("data/enemigo3.png"));
	int velocidad = -50;
	int velocidadx = 0;
	String tipo = "enemigo3";
	boolean muerto = false;
	boolean animacion = false;
	boolean disparaLaser  = false;
	float tiempoMuerte = 99;
	
	public void quitarVida() {
		vida--;
		if(vida == 0) {
			muerto = true;
		}
		
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
		this.velocidad = velocidad;
		
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
	
	public int obtenerVelocidadX() {
		return velocidadx;
	}
	
	public void fijarVelocidadX(int velocidad) {
		this.velocidadx = velocidad;
	}
	
	public void moverDerecha(int velocidad) {
		
	}
	
	public void moverIzquierda(int velocidad) {

	}

	public void moverAbajo(int velocidad) {

	}
	
	public void moverArriba(int velocidad) {
		
	}
	
	public void dispose() {
		
	}

}
