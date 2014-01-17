package com.alberto.matamarcianos.enemgos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase abstracta de la que heredan todos los enemigos
 * Contiene todos los metodos de lo enemigos
 * @author alberto
 *
 */
public abstract class NaveEnemiga extends Rectangle {
	
	//Atributos
	private static final long serialVersionUID = 1L;
	int vida, vidaInicial;
	static Texture imagen;
	int velocidad;
	int velocidadx;
	String tipo;
	float tiempoLaser, tiempoMuerte;
	boolean disparaLaser, animacion, muerto;
	
	/**
	 * Quita uno de vida al enemigo
	 */
	public abstract void quitarVida();
	
	/**
	 * @return La textura del enemigo
	 */
	public abstract Texture cargarTextura();
	
	/**
	 * @return El tipo de enemigo (enemigo, enemigo2...)
	 */
	public abstract String obtenerTipo();
	
	/**
	 * @return La vida actual del enemigo
	 */
	public abstract int obtenerVida();
	
	/**
	 * Retorna la velocidad del enemigo
	 * @return Velocidad enemigo
	 */
	public abstract int obtenerVelocidad();
	
	/**
	 * Fija la velocidad a la que se mueve el enemigo
	 * @param velocidad
	 */
	public abstract void fijarVelocidad(int velocidad);
	
	/**
	 * Fija el tiempo en el que se disparo el ultimo laser
	 * @param tiempo
	 */
	public abstract void fijarTiempoLaser(float tiempo);
	
	/**
	 * Retorna el instante en que se disparo el ultimo laser
	 * @return en que se disparo el ultimo laser
	 */
	public abstract float obtenerTiempoLaser();
	
	/**
	 * Fija si este enemigo dispara laser
	 * @param bool true o false
	 */
	public abstract void fijarDisparaLaser(boolean bool);
	
	/**
	 * Retorna si este enemigo dispara laser
	 * @return true si disparo o false si no
	 */
	public boolean obtenerDisparaLaser() {
		return disparaLaser;
	}
	
	/**
	 * Retorna si el enemmigo esta muerto, si lo esta se dibuja la animacion
	 * de la explosion
	 * @return si esta muerto
	 */
	public abstract boolean esMuerto();
	
	/**
	 * Fija si el enemigo a muerto o no
	 * @param si ha muerto true
	 */
	public abstract void fijarMuerto(boolean bol);
	
	/**
	 * Fija el momento de la muerte del enemigo
	 * @param cuando ha muerto
	 */
	public abstract void fijarTiempoMuerte(float tiempo);
	
	/**
	 * Retorna el momento de la muerte del enemigo
	 * @return cuado ha muerto
	 */
	public abstract float obtenerTiempoMuerte();
	
	/**
	 * Retorna si se esta ejecutando la animacion de la explosion para
	 * este enemigo
	 * @return si esta explotando
	 */
	public abstract boolean esAnimacion();
	
	/**
	 * Fija si se va a ejecutar la animacion de la explosion para
	 * este enemigo
	 * @param bol si va a empezar la animacion
	 */
	public abstract void fijarAnimacion(boolean bol);
	
	/**
	 * Libera la memoria de los recursos utilizados por la NaveEnemiga
	 */
	public abstract void dispose();
	
	/**
	 * Retorna la velocidad que tiene en X el enemigo
	 * @return velocidad a la que se desplaza
	 */
	public abstract int obtenerVelocidadX();
	
	/**
	 * Fija la velocidad a la que se movera en X el enemigo
	 * @param velocidad
	 */
	public abstract void fijarVelocidadX(int velocidad);
	
	/**
	 * Metodo para el movimiento de Enemigo3
	 * @param velocidad
	 */
	public void moverDerecha(int velocidad) {
		this.velocidad = 0;
		this.velocidadx = -velocidad;
	}
	
	/**
	 * Metodo para el movimiento de Enemigo3
	 * @param velocidad
	 */
	public void moverIzquierda(int velocidad) {
		this.velocidad = 0;
		this.velocidadx = velocidad;
	}
	
	/**
	 * Metodo para el movimiento de Enemigo3
	 * @param velocidad
	 */
	public void moverAbajo(int velocidad) {
		this.velocidad = velocidad;
		this.velocidadx = 0;
	}
	
	/**
	 * Metodo para el movimiento de Enemigo3
	 * @param velocidad
	 */
	public void moverArriba(int velocidad) {
		this.velocidad = -velocidad;
		this.velocidadx = 0;
	}
	
	/**
	 * Retorna la vida inicial del enemigo
	 * @return vida
	 */
	public abstract int obtenerVidaInicial();

}