package com.alberto.matamarcianos;

import com.alberto.matamarcianos.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * En esta clase se comprueba que teclas se estan pulsando,
 * ademas de realizar las tareas pertinentes
 * @author alberto
 *
 */
public class Controles {

	public static void escuchaControles() {
		Vector2 touchPos = new Vector2();
		touchPos.set(Gdx.input.getX(), Gdx.input.getY());

		//Controles de la nave
		if(Gdx.input.isKeyPressed(Keys.A)) {
			GameScreen.nave.moverIzquierda();
			GameScreen.nave.x += GameScreen.nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime(); //Si se presiona la tecla izquierda se mueve 200 pixels	
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			GameScreen.nave.moverDerecha();
			GameScreen.nave.x += GameScreen.nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.L) && TimeUtils.nanoTime() - GameScreen.nave.obtenerUltimoDisparo() > GameScreen.nave.obtenerRetardo()) {
			SpawnUtils.spawnLaser();
			GameScreen.laserSonido.play(GameScreen.volumen);
			GameScreen.nave.fijarUltimoDisparo(TimeUtils.nanoTime());
		}
		if(!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)) {
			GameScreen.nave.fijarVelocidadMovimiento(0);
			GameScreen.nave.fijarDireccion(Nave.NORMAL);
		}
			

		//Control del pause
		if((Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.MENU))) {
			GameScreen.game.setScreen(Screens.pause);
		}

		/*
		 * CONTROLES ANDROID
		 */
		
		/*GameScreen.margenIzquierda = (int) (touchPos.x - 64);
		GameScreen.margenDerecha = (int) (touchPos.x + 5);
		
		if(Gdx.input.isTouched() && touchPos.y > 606) { //Si se esta tocando la pantalla
			if(GameScreen.margenIzquierda > GameScreen.nave.x) {
				GameScreen.nave.moverDerecha();
				GameScreen.nave.x += (int) (GameScreen.nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime());
			}
			else if(GameScreen.margenDerecha < GameScreen.nave.x) {
				GameScreen.nave.moverIzquierda();
				GameScreen.nave.x += (int) (GameScreen.nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime());
			}
			else {
				GameScreen.nave.fijarDireccion(Nave.NORMAL);
			}
			
		}
		if(touchPos.y > 606 && Gdx.input.isTouched() && TimeUtils.nanoTime() - GameScreen.nave.obtenerUltimoDisparo() > GameScreen.nave.obtenerRetardo()) { //Si se esta tocando la pantalla
			GameScreen.laserSonido.play(GameScreen.volumen);
			SpawnUtils.spawnLaser();
			GameScreen.nave.fijarUltimoDisparo(TimeUtils.nanoTime());
		}

		GameScreen.touchPos = touchPos;*/
	}

}
