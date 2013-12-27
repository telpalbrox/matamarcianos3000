package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen{
	
	final Espacio game;
	
	OrthographicCamera camera;
	
	public MainMenuScreen(final Espacio gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Resolucion.x(), Resolucion.y());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, "Bienvenido a esa cosa rara",100 ,790);
		game.font.draw(game.batch, "Pulsa A para moverte a la derecha", 100, 750);
		game.font.draw(game.batch, "Pulsa D para moverte a la izquierda", 100, 700);
		game.font.draw(game.batch, "Pulsa L para disparar", 100, 650);
		game.font.draw(game.batch, "VERSION 20.12.2013 ALPHA ESCRITORIO 0.0.3", 100, 600);
		game.font.draw(game.batch, "Toca en cualquier lugar o pulsa k...", 100, 550);
		game.batch.end();
		
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.K)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
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
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
