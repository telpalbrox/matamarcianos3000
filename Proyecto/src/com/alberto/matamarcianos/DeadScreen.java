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
	InfoUtils informacion = new InfoUtils();
	boolean conexion = false;
	int contador = 0;
	
	public DeadScreen(final Espacio gam, String jugador, int pun) {
		game = gam;
		puntuacion = pun;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, InfoUtils.x(), InfoUtils.y());
		if(informacion.hayConexion()) {
			conexion = true;
			if(!jugador.isEmpty()) { //Si le da a cancelar no se envia la puntuacion
				fachada.insertarPuntuacion(jugador, pun);
			}
			puntuaciones = fachada.obtenerPuntuaciones();
		}
		else {
			conexion = false;
		}
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(conexion) {
			camera.update();
			game.batch.setProjectionMatrix(camera.combined);
			game.batch.begin();
			game.font.draw(game.batch, "Mejores Puntuaciones", 25, 650);
			contador = 0;
			for(PuntuacionesDTO puntuacion : puntuaciones) {
				game.font.draw(game.batch, puntuacion.obtenerJugador(), 25, 600-(contador*25));
				game.font.draw(game.batch, String.valueOf(puntuacion.obtenerPuntuacion()), 125, 600-(contador*25));
				if(puntuacion.obtenerVersion() != null)
					game.font.draw(game.batch, puntuacion.obtenerVersion(), 160, 600-(contador*25));
				contador++;
			}
			game.font.draw(game.batch, "Has muerto...", 225, 750);
			game.font.draw(game.batch, "Pulsa R o tecla menu para continuar", 225, 725);
			game.font.draw(game.batch, "Has conseguido: "+puntuacion+" puntos.", 225, 700);
			game.batch.end();
			
			if (Gdx.input.isButtonPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.MENU)) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
		}
		else {
			game.setScreen(new ErrorScreen(game, "No hay conexion con la base de datos :("));	
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
