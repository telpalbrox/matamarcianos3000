package com.alberto.matamarcianos.enemgos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemigo extends NaveEnemiga{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int vida;
	int vidaInicial = 1;
	static Texture imagen = new Texture(Gdx.files.internal("data/images/enemigo.png"));
	String tipo = "enemigo";
	int velocidad;
	boolean muerto = false;
	boolean animacion = false;
	float tiempoMuerte = 99;
	
	public Enemigo() {
		vida = vidaInicial;
	}

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
	
	public int obtenerVidaInicial() {
		return vidaInicial;
	}

	@Override
	public void fijarTiempoLaser(float tiempo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float obtenerTiempoLaser() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fijarDisparaLaser(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int obtenerVelocidadX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fijarVelocidadX(int velocidad) {
		// TODO Auto-generated method stub
		
	}

}
