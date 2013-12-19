package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemigo2 extends NaveEnemiga {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int vida = 3;
	static Texture imagen = new Texture(Gdx.files.internal("data/enemigo2.png"));
	String tipo = "enemigo2";
	int velocidad;
	boolean disparaLaser  = false;
	boolean muerto = false;
	boolean animacion = false;
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
	
	public void fijarDisparaLaser(boolean bool) {
		disparaLaser = bool;
	}
	
	public boolean obtenerDisparaLaser() {
		return disparaLaser;
	}
	
	public void fijarTiempoLaser(float tiempo) {
		tiempoLaser = tiempo;
	}
	
	public float obtenerTiempoLaser() {
		return tiempoLaser;
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
		imagen.dispose();
	}
}
