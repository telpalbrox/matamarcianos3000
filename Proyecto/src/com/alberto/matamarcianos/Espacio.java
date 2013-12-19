package com.alberto.matamarcianos;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Espacio extends Game {
	SpriteBatch batch;
	BitmapFont font;
	@Override
	public void create() {
		batch = new SpriteBatch();
		//Fuente por defecto de libGDX: Arial
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}
	public void render() {
		super.render(); //Importante!
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
