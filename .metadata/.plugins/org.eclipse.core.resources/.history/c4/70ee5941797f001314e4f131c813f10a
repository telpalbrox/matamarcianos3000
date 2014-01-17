package com.alberto.matamarcianos;

import com.alberto.matamarcianos.conexion.Facade;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class NombreScreen implements Screen {
	
	final Espacio game;
	int puntuacion;
	OrthographicCamera camera;
	Facade fachada = new Facade();
	MyTextInputListener listener = new MyTextInputListener();
	
	public NombreScreen(final Espacio gam, int pun) {
		this.puntuacion = pun;
		game = gam;
		puntuacion = pun;
		listener.entrada = "";
		Gdx.input.getTextInput(listener, "Se va a enviar tu puntuacion, instroduce tu nombre "
				+ "(no mas de 10 caracteres): ", "Nombre");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, InfoUtils.x(), InfoUtils.y());
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "Pulsa R o tecla menu para continuar", 100, 100);
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.MENU)) {
			game.setScreen(new DeadScreen(game, listener.entrada, puntuacion));
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

class MyTextInputListener implements TextInputListener {
	public String entrada = "";
	@Override
	public void input (String text) {
		this.entrada = text;
	}

	@Override
	public void canceled () {
	}
}
