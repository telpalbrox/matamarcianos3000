package com.alberto.matamarcianos;

import com.badlogic.gdx.math.Rectangle;

public class Nave extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean invulnerabilidad = false;
	private boolean naveAcel = false;
	private float tiempoInvencible = 99;
	private float tiempoAcel = 99;
	private long retardo = 100000000;
	int velocidadMovimiento = 800;
	
	private int vida = 10;
	
	public Nave() {
		
	}
	
	public void quitarVida() {
		if(!invulnerabilidad) {
			vida--;
		}
	}
	
	public void sumarVida(int numero) {
		vida += numero;
	}
	
	public int obtenerVida() {
		return vida;
	}
	
	public boolean esInvencible() {
		return invulnerabilidad;
	}
	
	public void fijarInvulnerabilidad(boolean bool) {
		this.invulnerabilidad = bool;
	}
	
	public float obtenerTiempoInvencible() {
		return tiempoInvencible;
	}
	
	public void fijarTiempoInvencible(float tiempo) {
		this.tiempoInvencible = tiempo;
	}
	
	public long obtenerRetardo() {
		return retardo;
	}
	
	public void fijarRetardo(long ret) {
		this.retardo = ret;
	}
	
	public boolean esAcelerada() {
		return naveAcel;
	}
	
	public void fijarNaveAcel(boolean bool) {
		this.naveAcel = bool;
	}
	
	public int obtenerVelocidadMovimiento() {
		return velocidadMovimiento;
	}
	
	public void fijarVelocidadMovimiento(int num) {
		this.velocidadMovimiento = num;
	}
	
	public float obtenerTiempoAcel() {
		return tiempoAcel;
	}
	
	public void fijarTiempoAcel(float tiempo) {
		this.tiempoAcel = tiempo;
	}

}
