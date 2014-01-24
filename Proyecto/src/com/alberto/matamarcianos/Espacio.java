package com.alberto.matamarcianos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Espacio extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	static boolean bjuego = false;
	public static Screens pantallas;
	@Override
	public void create() {
		batch = new SpriteBatch();
		//Fuente por defecto de libGDX: Arial
		font = new BitmapFont();
		pantallas = new Screens(this);
		pantallas.crearJuego();
		
	}
	public void render() {
		super.render(); //Importante!
	}
	
	public void dispose() {
		super.dispose();
	}
	@Override
	public void pause() {
		super.pause();
		
	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}
	@Override
	public void resume() {
		super.resume();
		
	}

}
