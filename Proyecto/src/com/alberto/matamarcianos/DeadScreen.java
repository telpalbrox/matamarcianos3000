package com.alberto.matamarcianos;

import java.util.Collection;

import com.alberto.matamarcianos.conexion.Facade;
import com.alberto.matamarcianos.conexion.PuntuacionesDTO;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class DeadScreen implements Screen {
	
	final Espacio game;
	int puntuacion;
	OrthographicCamera camera;
	Facade fachada = new Facade();
	Collection<PuntuacionesDTO> puntuaciones = null;
	int contador = 0;
	
	public DeadScreen(final Espacio gam, String jugador, int pun) {
		game = gam;
		puntuacion = pun;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Resolucion.x(), Resolucion.y());
		if(!jugador.isEmpty()) {
			fachada.insertarPuntuacion(jugador, pun);
		}
		puntuaciones = fachada.obtenerPuntuaciones();
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "Mejores Puntuaciones", 25, 750);
		contador = 0;
		for(PuntuacionesDTO puntuacion : puntuaciones) {
			game.font.draw(game.batch, puntuacion.obtenerJugador(), 25, 700-(contador*25));
			game.font.draw(game.batch, String.valueOf(puntuacion.obtenerPuntuacion()), 125, 700-(contador*25));
			contador++;
		}
		game.font.draw(game.batch, "Has muerto...", 225, 150);
		game.font.draw(game.batch, "Pulsa R o tecla menu para continuar", 225, 100);
		game.font.draw(game.batch, "Has conseguido: "+puntuacion+" puntos.", 225, 75);
		game.batch.end();
		
		if (Gdx.input.isButtonPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.MENU)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
	}
	
	public void resume() {
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		
	}

}
