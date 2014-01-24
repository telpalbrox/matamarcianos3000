package com.alberto.matamarcianos;

import java.util.Iterator;

import com.alberto.matamarcianos.enemgos.NaveEnemiga;
import com.alberto.matamarcianos.items.Item;
import com.alberto.matamarcianos.laseres.Laser;
import com.alberto.matamarcianos.screens.EnviarPuntuacion;
import com.alberto.matamarcianos.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * En esta clase se comprueba los choques de los elementos que hay en pantall
 * ademas de realizar las acciones pertinentes.
 * @author alberto
 *
 */
public class Escenario {

	static final int RESOLUCIONX = InfoUtils.x();
	static final int RESOLUCIONY = InfoUtils.y();

	public static void calcularChoques() {
		//Muevo los disparos
		Iterator<Laser> iterLaser = GameScreen.laseresNave.iterator();
		while(iterLaser.hasNext()) {
			Laser laser = iterLaser.next();
			laser.y += laser.obtenerVelocidadLaser() * Gdx.graphics.getDeltaTime();
			for(NaveEnemiga enemigo : GameScreen.enemigos) {
				//Control de choques contra el laser
				if(enemigo.obtenerVida() >= 0 && enemigo.overlaps(laser)) {
					enemigo.quitarVida();
					iterLaser.remove();
				}
			}
		}
		//Muevo los enemigos
		Iterator<NaveEnemiga> iterEnemigo = GameScreen.enemigos.iterator();
		while(iterEnemigo.hasNext()){
			NaveEnemiga enemigo = iterEnemigo.next();
			//Segun la puntuacion llevan una velocidad, excepto Enemigo3 que tiene un movimiento especial
			if(!enemigo.obtenerTipo().equals("enemigo3")) {
				enemigo.fijarVelocidad(GameScreen.velocidad);
				enemigo.y += enemigo.obtenerVelocidad() * Gdx.graphics.getDeltaTime();
			}
			//Quito vida si los enemigos se chocan cotra la nave
			if(enemigo.overlaps(GameScreen.nave) && !enemigo.esAnimacion()) {
				GameScreen.nave.quitarVida();
				GameScreen.explosionSonido.play(GameScreen.volumen);
				enemigo.fijarTiempoMuerte(GameScreen.tiempo);
				enemigo.fijarAnimacion(true);
			}
			//Elimino los enemigos sin vida
			if(enemigo.obtenerVida()<= 0 && !enemigo.esAnimacion()) {
				GameScreen.puntuacion += enemigo.obtenerVidaInicial();
				if(enemigo.obtenerTipo().equals("enemigo2")) {
					if(MathUtils.random(0, 4) == 0) {
						SpawnUtils.spawnItemRetardo(GameScreen.items, enemigo);
					}
				}
				GameScreen.explosionSonido.play();
				enemigo.fijarTiempoMuerte(GameScreen.tiempo);
				enemigo.fijarAnimacion(true);
			}
			if(enemigo.esAnimacion() && enemigo.obtenerTiempoMuerte() + 0.25f <= GameScreen.tiempo) {
				iterEnemigo.remove();
			}
			//Quito vida si los enemigos llegan al final de la pantalla
			if(enemigo.y <= -64) {
				GameScreen.nave.quitarVida();
				iterEnemigo.remove();
			}
			//Si es enemigo de tipo 2 o 3 dispara laser
			if(enemigo.obtenerTipo().equals("enemigo2") || enemigo.obtenerTipo().equals("enemigo3")) {
				if(!enemigo.obtenerDisparaLaser()) {
					enemigo.fijarTiempoLaser(GameScreen.tiempo); //Fijo el momento en que disparó
					enemigo.fijarDisparaLaser(true);
				}
				if(enemigo.obtenerTiempoLaser() + 2.5 <= GameScreen.tiempo) {
					//Si han pasado más de dos segundos desde el último disparo
					SpawnUtils.spawnLaserEnemigo(enemigo);
					enemigo.fijarTiempoLaser(GameScreen.tiempo);
				}
				/*if(enemigo.obtenerTiempoLaser() + 2 - 1 <= tiempo && puntuacion >= 150) {
							spawnLaserEnemigo2(enemigo);
							enemigo.fijarTiempoLaser(tiempo);
						}*/
			}
			//Si es enemigo3
			if(enemigo.obtenerTipo().equals("enemigo3")) {
				System.out.println("PosicionX enemigo3: "+enemigo.x);
				System.out.println("PosicionY enemigo3: "+enemigo.y);
				//Movimientos en pantalla
				if(enemigo.y < RESOLUCIONY - 200) {
					if(enemigo.y <= RESOLUCIONY - 400) {
						enemigo.fijarVelocidad(GameScreen.velocidad);
					}
					if(enemigo.y >= RESOLUCIONY - 250 && enemigo.y > 246) {
						enemigo.moverIzquierda(GameScreen.velocidad);
						if(enemigo.x < 10) {
							enemigo.moverAbajo(GameScreen.velocidad);
						}
					}
					if(enemigo.y < RESOLUCIONY - 400) {
						enemigo.moverDerecha(GameScreen.velocidad);
						if(enemigo.x > RESOLUCIONX - 70) {
							enemigo.moverArriba(GameScreen.velocidad);
						}
					}
				}
				//Muevo la nave
				enemigo.y += enemigo.obtenerVelocidad() * Gdx.graphics.getDeltaTime() * GameScreen.pause;
				enemigo.x += enemigo.obtenerVelocidadX() * Gdx.graphics.getDeltaTime() * GameScreen.pause;
			}
		}

		//Muevo los items
		Iterator<Item> iterItem = GameScreen.items.iterator();
		while(iterItem.hasNext()) {
			Item item = iterItem.next();
			item.y += (GameScreen.velocidad - 50) * Gdx.graphics.getDeltaTime() * GameScreen.pause;
			//Si el item choca contra la nave se gasta y recibe los beneficios
			if(item.overlaps(GameScreen.nave)) {
				if(item.obtenerTipo().equals("vida")) {
					GameScreen.nave.sumarVida(1);
				}
				if(item.obtenerTipo().equals("tiempo")) {
					GameScreen.nave.fijarLaserAcelerado(true);
					GameScreen.nave.fijarRetardo(Nave.retardoAcelerado);
					GameScreen.nave.fijarTiempoRetardoAcel(GameScreen.tiempo);
				}
				if(item.obtenerTipo().equals("invulnerabilidad")) {
					GameScreen.nave.fijarTiempoInvencible(GameScreen.tiempo);
					GameScreen.nave.fijarInvulnerabilidad(true);
				}
				if(item.obtenerTipo().equals("velocidad")) {
					GameScreen.nave.fijarVelocidadMovimientoX(1600);
					GameScreen.nave.fijarTiempoAcel(GameScreen.tiempo);
					GameScreen.nave.fijarNaveAcel(true);
				}
				GameScreen.puntuacion++;
				iterItem.remove();
			}
			//Si llega abajo se elimina
			if(item.y <= -64) iterItem.remove();
		}

		//Muevo los laseres enemigos
		Iterator<Laser> iterLaserEnemigo = GameScreen.laseresEnemigos.iterator();
		while(iterLaserEnemigo.hasNext()) {
			Laser laser = iterLaserEnemigo.next();
			laser.y += (GameScreen.velocidad - laser.obtenerVelocidadLaser()) * Gdx.graphics.getDeltaTime() * GameScreen.pause;
			//Si llegan abajo se eliminan
			if(laser.y <= -16) {
				iterLaserEnemigo.remove();
			}
			if(laser.overlaps(GameScreen.nave)) {
				GameScreen.nave.quitarVida(laser.obtenerDanio());
				iterLaserEnemigo.remove();
			}

		}
		//Muevo el fondo
		Iterator<Rectangle> iterFondo = GameScreen.fondos.iterator();
		while(iterFondo.hasNext()) {
			Rectangle fondo = iterFondo.next();
			fondo.y += (GameScreen.velocidad - 500) * Gdx.graphics.getDeltaTime();
			fondo.x += -(GameScreen.nave.obtenerVelocidadMovimiento()) * Gdx.graphics.getDeltaTime();
			if(fondo.y < -64) {
				iterFondo.remove();
			}

		}


	}

	public static void calculaEstadoNave() {
		//Si la nave se sale de los limites
		if(GameScreen.nave.x < 0) GameScreen.nave.x = 0;
		if(GameScreen.nave.x > RESOLUCIONX - 64) GameScreen.nave.x = RESOLUCIONX - 64;
		
		//Si se acaban las vidas se muestra la pantalla de muerte
		if(GameScreen.nave.obtenerVida() <= 0) {
			GameScreen.musica.stop();
			GameScreen.game.setScreen(new EnviarPuntuacion(GameScreen.game, GameScreen.puntuacion));
		}
		
		if(GameScreen.nave.esInvencible() && GameScreen.nave.obtenerTiempoInvencible() + 5 <= GameScreen.tiempo) {
			GameScreen.nave.fijarInvulnerabilidad(false);
		}
		
		if(GameScreen.nave.esAcelerada() && GameScreen.nave.obtenerTiempoAcel() + 5 <= GameScreen.tiempo) {
			GameScreen.nave.fijarVelocidadMovimientoX(600);
			GameScreen.nave.fijarNaveAcel(false);
		}
		
		if(GameScreen.nave.esLaserAcelerado() && GameScreen.nave.obtenerTiempoRetardoAcel() + 5 <= GameScreen.tiempo) {
			GameScreen.nave.fijarRetardo(100000000);
			GameScreen.nave.fijarLaserAcelerado(false);
		}
	}

}
