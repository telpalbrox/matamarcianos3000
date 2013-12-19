package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class DeadScreen implements Screen {
	
	final Espacio game;
	int puntuacion;
	
	OrthographicCamera camera;
	
	public DeadScreen(final Espacio gam, int pun) {
		game = gam;
		puntuacion = pun;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Resolucion.x(), Resolucion.y());
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "Has muerto...", 100, 150);
		game.font.draw(game.batch, "Pulsa R para continuar", 100, 100);
		//game.font.draw(game.batch, "Has durado: " +Math.rint(time * 100) / 100, 100, 75);
		game.font.draw(game.batch, "Has conseguido: "+puntuacion+" puntos.", 100, 75);
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.R)) {
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
