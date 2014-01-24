package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Nave principal del juego, controlada por el usuario
 * @author alberto
 *
 */

public class Nave extends Rectangle {
	
	//Atributos
	private static final long serialVersionUID = 1L;
	public static final int NORMAL = 0;
	public static final int DERECHA = 1;
	public static final int IZQUIERDA = 2;
	private boolean invulnerabilidad = false;
	private boolean naveAcel = false;
	private boolean laserAcelerado = false;
	private float tiempoInvencible = 99;
	private float tiempoAcel = 99;
	private float tiempoAnim = 0.5f;
	public static final long retardoAcelerado = 100000000/2;
	private float tiempoRetardoAcel;
	private long retardo = 100000000;
	private long ultimoDisparo = 1;
	private int danioLaser = 1;
	private int direccion = 0;
	static Texture imagenNormal = new Texture(Gdx.files.internal("data/images/nave.png"));
	static Texture imagenDerecha = new Texture(Gdx.files.internal("data/images/naved.png"));
	static Texture imagenIzquierda = new Texture(Gdx.files.internal("data/images/navei.png"));
	int velocidadMovimiento = 600;
	private int velocidadMovimientoX = 600;
	
	private int vida = 10;
	
	public Nave() {
		
	}
	
	/**
	 * Se quita un punto de vida a la nave y se vuelve invulnerable durante
	 * 5 segundos
	 * @param tiempo Cuando se ha quitado la vida a la nave
	 */
	public void quitarVida() {
		if(!invulnerabilidad) {
			vida--;
		}
	}
	
	/**
	 * Quita una cantidad de vida a la nave
	 * @param danio Cuanto danio se quiere quitar
	 */
	public void quitarVida(int danio) {
		if(!invulnerabilidad) {
			vida -= danio;
		}
	}
	
	/**
	 * Aumenta la vida de la nave
	 * @param numero Cantidad de vida a sumar
	 */
	public void sumarVida(int numero) {
		vida += numero;
	}
	
	/**
	 * Retorna la vida que tiene la nave
	 * @return
	 */
	public int obtenerVida() {
		return vida;
	}
	
	/**
	 * @return si la nave es invencible
	 */
	public boolean esInvencible() {
		return invulnerabilidad;
	}
	
	/**
	 * Hace invencible a la nave
	 * @param bool es invencible?
	 */
	public void fijarInvulnerabilidad(boolean bool) {
		this.invulnerabilidad = bool;
	}
	
	/**
	 * @return Desde cuando es invencible la nave
	 */
	public float obtenerTiempoInvencible() {
		return tiempoInvencible;
	}
	
	/**
	 * @param tiempo Fija el momente en que se hizo invencible
	 */
	public void fijarTiempoInvencible(float tiempo) {
		this.tiempoInvencible = tiempo;
	}
	
	/**
	 * @return Tiempo entre disparo y disparo
	 */
	public long obtenerRetardo() {
		return retardo;
	}
	
	/**
	 * @param ret Tiempo entre disparo y disparo
	 */
	public void fijarRetardo(long ret) {
		this.retardo = ret;
	}
	
	/**
	 * @return Si la nave se mueve mas rapido de lo normal
	 */
	public boolean esAcelerada() {
		return naveAcel;
	}
	
	/**
	 * @param bool Si se quiere acelerar la nave
	 */
	public void fijarNaveAcel(boolean bool) {
		this.naveAcel = bool;
	}
	
	/**
	 * @return Velocidad a la que se mueve la nave
	 */
	public int obtenerVelocidadMovimiento() {
		return velocidadMovimiento;
	}
	
	/**
	 * @param num Velocidad a la que quieres que se mueva la nave
	 */
	public void fijarVelocidadMovimiento(int num) {
		this.velocidadMovimiento = num;
	}
	
	/**
	 * @return Instante en el que se acelero la nave
	 */
	public float obtenerTiempoAcel() {
		return tiempoAcel;
	}
	
	/**
	 * @param tiempo Instante en el que se acelera la nave
	 */
	public void fijarTiempoAcel(float tiempo) {
		this.tiempoAcel = tiempo;
	}
	
	/**
	 * Fija el momento en el que disparo la nave por ultima vez
	 * @param momeno del disparo
	 */
	public void fijarUltimoDisparo(long disparo) {
		this.ultimoDisparo = disparo;
	}
	
	/**
	 * Retorna el momento en el que dispario la nave por ultima vez
	 * @return momento del disparo
	 */
	public long obtenerUltimoDisparo() {
		return ultimoDisparo;
	}

	public int obtenerDanioLaser() {
		return danioLaser;
	}

	public void fijarDanioLaser(int danioLaser) {
		this.danioLaser = danioLaser;
	}
	
	public Texture cargarTextura() {
		if(direccion == NORMAL) 
			return imagenNormal;
			
		if(direccion == DERECHA)
			return imagenDerecha;
		
		if(direccion == IZQUIERDA)
			return imagenIzquierda;
		
		return imagenNormal;
	}
	
	public void fijarDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	public void moverDerecha() {
		fijarVelocidadMovimiento(velocidadMovimientoX);
		direccion = DERECHA;
	}
	
	public void moverIzquierda() {
		fijarVelocidadMovimiento(-velocidadMovimientoX);
		direccion = IZQUIERDA;
	}

	public boolean esLaserAcelerado() {
		return laserAcelerado;
	}

	public void fijarLaserAcelerado(boolean laserAcelerado) {
		this.laserAcelerado = laserAcelerado;
	}

	public float obtenerTiempoAnim() {
		return tiempoAnim;
	}

	public void fijarTiempoAnim(float tiempoAnim) {
		this.tiempoAnim = tiempoAnim;
	}

	public float obtenerRetardoAcelerado() {
		return retardoAcelerado;
	}

	/*public void fijarRetardoAcelerado(float retardoAcelerado) {
		this.retardoAcelerado = retardoAcelerado;
	}*/

	public float obtenerTiempoRetardoAcel() {
		return tiempoRetardoAcel;
	}

	public void fijarTiempoRetardoAcel(float tiempoRetardoAcel) {
		this.tiempoRetardoAcel = tiempoRetardoAcel;
	}

	public int obtenerVelocidadMovimientoX() {
		return velocidadMovimientoX;
	}

	public void fijarVelocidadMovimientoX(int velocidadMovimientoX) {
		this.velocidadMovimientoX = velocidadMovimientoX;
	}

}
