package com.alberto.matamarcianos;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class SpawnUtils {
	static final int RESOLUCIONX = Resolucion.x();
	static final int RESOLUCIONY = Resolucion.y();
	
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
	
	public static void spawnItemInvulnerabilidad(Array<Item> items) {
		ItemRetardo item = new ItemRetardo();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}
	
	public static void spawnItemVida(Array<Item> items) {
		ItemVida item = new ItemVida();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}
	
	public static void spawnItemTiempo(Array<Item> items) {
		ItemTiempo item = new ItemTiempo();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}
	
	public static void spawnItemVelocidad(Array<Item> items) {
		ItemVelocidad item = new ItemVelocidad();
		item.x = MathUtils.random(0, RESOLUCIONX-64);
		item.y = RESOLUCIONY - 20;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}
	
	public static void spawnItemRetardo(Array<Item> items, NaveEnemiga enemigo) {
		ItemRetardo item = new ItemRetardo();
		item.x = enemigo.x + enemigo.width/2;
		item.y = enemigo.y + enemigo.height/2;
		item.height = 32;
		item.width = 32;
		items.add(item);
	}

}
