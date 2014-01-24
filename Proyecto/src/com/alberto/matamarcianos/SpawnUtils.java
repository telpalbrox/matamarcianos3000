package com.alberto.matamarcianos;

import com.alberto.matamarcianos.enemgos.Enemigo;
import com.alberto.matamarcianos.enemgos.Enemigo2;
import com.alberto.matamarcianos.enemgos.Enemigo3;
import com.alberto.matamarcianos.enemgos.NaveEnemiga;
import com.alberto.matamarcianos.items.Item;
import com.alberto.matamarcianos.items.ItemRetardo;
import com.alberto.matamarcianos.items.ItemTiempo;
import com.alberto.matamarcianos.items.ItemVelocidad;
import com.alberto.matamarcianos.items.ItemVida;
import com.alberto.matamarcianos.laseres.LaserEnemigo2;
import com.alberto.matamarcianos.laseres.LaserEnemigo3;
import com.alberto.matamarcianos.laseres.LaserNave;
import com.alberto.matamarcianos.screens.GameScreen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SpawnUtils {
	static final int RESOLUCIONX = InfoUtils.x();
	static final int RESOLUCIONY = InfoUtils.y();

	/*public static void spawnEnemigo2(Array<NaveEnemiga> enemigos) {
		Enemigo2 enemigo = new Enemigo2();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 64;
		enemigo.width = 64;
		enemigos.add(enemigo);
	}*/

	/*public static void spawnEnemigo3(Array<NaveEnemiga> enemigos) {
		Enemigo3 enemigo = new Enemigo3();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 64;
		enemigo.width = 64;
		enemigos.add(enemigo);
	}*/
	/**
	 * 
	 */
	public static void spawnLaser() {
		LaserNave laser = new LaserNave();
		laser.x = GameScreen.nave.x + 32;
		laser.y = 64;
		laser.width = 8;
		laser.height = 16;
		GameScreen.laseresNave.add(laser);
	}

	/**
	 * Spawn de los laseres de los ememigos segun su tipo
	 * @param enemigo
	 */
	public static void spawnLaserEnemigo(NaveEnemiga enemigo) {
		if(enemigo.obtenerTipo().equals("enemigo2")) {
			spawnLaserEnemigo2(enemigo);
		}
		else if(enemigo.obtenerTipo().equals("enemigo3")) {
			spawnLaserEnemigo3(enemigo);
		}
	}

	/**
	 * Spawn del laser del enemigo2
	 * @param enemigo
	 */
	private static void spawnLaserEnemigo2(NaveEnemiga enemigo) {
		LaserEnemigo2 laser = new LaserEnemigo2(enemigo);
		laser.x = enemigo.x + 32;
		laser.y = enemigo.y;
		laser.width = 8;
		laser.height = 16;
		GameScreen.laseresEnemigos.add(laser);
	}
	/**
	 * Spawn del laser del enemigo3
	 * @param enemigo
	 */
	private static void spawnLaserEnemigo3(NaveEnemiga enemigo) {
		LaserEnemigo3 laser = new LaserEnemigo3(enemigo);
		laser.x = enemigo.x + 32;
		laser.y = enemigo.y;
		laser.width = 8;
		laser.height = 16;
		GameScreen.laseresEnemigos.add(laser);
	}

	/**
	 * Spawnea un enemigo3
	 * @param enemigos
	 */
	public static void spawnEnemigo3(Array<NaveEnemiga> enemigos) {
		boolean coinciden = true;
		Enemigo3 enemigo = new Enemigo3();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 64;
		enemigo.width = 64;
		while(coinciden) {
			for(NaveEnemiga enPantalla : enemigos) {
				if(enPantalla.overlaps(enemigo)) {
					coinciden = true;
					enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
					enemigo.y = RESOLUCIONY - 20;
				}
			}
			coinciden = false;
		}
		enemigos.add(enemigo);
	}

	/**
	 * Spawnea un enemigo normal
	 * @param enemigos
	 */
	public static void spawnEnemigo(Array<NaveEnemiga> enemigos) {
		boolean coinciden = true;
		Enemigo enemigo = new Enemigo();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 32;
		enemigo.width = 32;
		while(coinciden) {
			for(NaveEnemiga enPantalla : enemigos) {
				if(enPantalla.overlaps(enemigo)) {
					coinciden = true;
					enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
					enemigo.y = RESOLUCIONY - 20;
				}
			}
			coinciden = false;
		}
		enemigos.add(enemigo);
	}

	/**
	 * Spawnea un enemigo2
	 * @param enemigos
	 */
	public static void spawnEnemigo2(Array<NaveEnemiga> enemigos) {
		boolean coinciden = true;
		Enemigo2 enemigo = new Enemigo2();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 64;
		enemigo.width = 64;
		while(coinciden) {
			for(NaveEnemiga enPantalla : enemigos) {
				if(enPantalla.overlaps(enemigo)) {
					coinciden = true;
					enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
					enemigo.y = RESOLUCIONY - 20;

				}
			}
			coinciden = false;
		}
		enemigos.add(enemigo);
	}

	/*public static void spawnEnemigo(Array<NaveEnemiga> enemigos) {
		Enemigo enemigo = new Enemigo();
		enemigo.x = MathUtils.random(0, RESOLUCIONX-64);
		enemigo.y = RESOLUCIONY - 20;
		enemigo.height = 32;
		enemigo.width = 32;
		enemigos.add(enemigo);
	}*/

	/**
	 * Spawnea un Item que vuelve invencible a la nave durante 5 segundos
	 * @param items
	 */
	public static void spawnItemInvulnerabilidad(Array<Item> items) {
		ItemRetardo item = new ItemRetardo();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

	/**
	 * Spawnea un item que aumenta la vida de la nave
	 * @param items
	 */
	public static void spawnItemVida(Array<Item> items) {
		ItemVida item = new ItemVida();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

	/**
	 * Spawnea un item que reduce el tiempo entre disparo temporalmente
	 * @param items
	 */
	public static void spawnItemTiempo(Array<Item> items) {
		ItemTiempo item = new ItemTiempo();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

	/**
	 * Spawnea un item que aumenta la velocidad de la nave
	 * @param items
	 */
	public static void spawnItemVelocidad(Array<Item> items) {
		ItemVelocidad item = new ItemVelocidad();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

	/**
	 * Spawnea el ladrillo, de momento no hace nada...
	 * @param items
	 * @param enemigo
	 */
	public static void spawnItemRetardo(Array<Item> items, NaveEnemiga enemigo) {
		ItemRetardo item = new ItemRetardo();
		item.x = enemigo.x + enemigo.width/2;
		item.y = enemigo.y + enemigo.height/2;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

	/**
	 * Metodo encargado de spawnear los items, enemigos y el fondo del escenario.
	 * Todo de forma aleatoria en funcion de la puntuacion
	 */
	public static void spawnAleatorio() {
		if(MathUtils.random(0, 5000/(GameScreen.puntuacion + 1)) == 0) {
			spawnEnemigoAleatorio();
		}
		//Spawneo de items
		if(MathUtils.random(0, (100/(GameScreen.puntuacion + 1)) + 300) == 0) {
			spawnItem();
		}
		//Spawneo las imágenes de fondo si no está en pause y con retardo entre cada spawneo
		if(TimeUtils.nanoTime() - GameScreen.ultimoFondo > 101){
			spawnFondo();
		}
	}

	/**
	 * Spawnea de forma aleatroia un enemigo.
	 * Si la puntuacione es mayor de 150 hay posibilidad de spawneo de un enemigo3
	 */
	private static void spawnEnemigoAleatorio() {
		int numero = MathUtils.random(0, 7);
		if(numero <= 3) {
			SpawnUtils.spawnEnemigo(GameScreen.enemigos);
		}
		if(numero > 3) {
			SpawnUtils.spawnEnemigo2(GameScreen.enemigos);
		}
		if(numero == 7 && GameScreen.puntuacion > 150) {
			int numero2 = MathUtils.random(0, 20);
			if(numero2 == 0) {
				SpawnUtils.spawnEnemigo3(GameScreen.enemigos);
			}
		}
	}

	/**
	 * Spawnea una imagen para formar el fondo del escenario
	 */
	public static void spawnFondo() {
		Rectangle detras = new Rectangle();

		if(GameScreen.nave.obtenerVelocidadMovimiento() != 0) {
			detras.x = MathUtils.random(0, RESOLUCIONX-64);
			detras.y = MathUtils.random(0, RESOLUCIONY);
		}

		else {
			detras.x = MathUtils.random(0, RESOLUCIONX-64);
			detras.y = RESOLUCIONY - 20;
		}

		detras.height = 32;
		detras.width = 32;
		GameScreen.fondos.add(detras);
	}

	/**
	 * Spawnea un item de forma aleatoria
	 */
	private static void spawnItem() {
		int numero = MathUtils.random(0, 4);

		if(numero == 1) {
			spawnItemVida(GameScreen.items);
		}
		if(numero == 0) {
			spawnItemTiempo(GameScreen.items);
		}
		if(numero == 2) {
			spawnItemInvulnerabilidad(GameScreen.items);
		}
		if(numero == 4) {
			spawnItemVelocidad(GameScreen.items);
		}
	}

}
