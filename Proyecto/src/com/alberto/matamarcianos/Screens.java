package com.alberto.matamarcianos;

import com.alberto.matamarcianos.screens.GameScreen;
import com.alberto.matamarcianos.screens.Pause2;
import com.alberto.matamarcianos.screens.Splash2;
import com.badlogic.gdx.Screen;

public class Screens {

	final Espacio game;

	public static Screen pause;
	public static GameScreen juego;

	public Screens(final Espacio gam) {
		this.game = gam;
	}

	public void crearJuego() {
		pause = new Pause2(game);
		game.setScreen(new Splash2(game));
	}

}
