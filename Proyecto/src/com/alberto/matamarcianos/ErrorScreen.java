package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ErrorScreen implements Screen {
	
	final Espacio game;
	OrthographicCamera camera;
	String error;
	
	public ErrorScreen(final Espacio gam, String error) {
		this.error = error;
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, InfoUtils.x(), InfoUtils.y());
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "FALLO GORDO: "+error, 100, 150);
		game.font.draw(game.batch, "Pulsa R o tecla menu para continuar", 100, 100);
		game.batch.end();
		
		if (Gdx.input.isButtonPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.MENU)) {
			game.setScreen(new MainMenuScreen(game));
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
