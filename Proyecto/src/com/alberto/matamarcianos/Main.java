package com.alberto.matamarcianos;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		InfoUtils info = new InfoUtils();
		cfg.title = "Matamarcianos 3000 | " + info.version();
		cfg.useGL20 = true;
		cfg.width = InfoUtils.x();
		cfg.height = InfoUtils.y();
		cfg.resizable = false;
		
		new LwjglApplication(new Espacio(), cfg);
	}
}