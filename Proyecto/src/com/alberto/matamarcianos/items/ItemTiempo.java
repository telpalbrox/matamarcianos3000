package com.alberto.matamarcianos.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemTiempo extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen = new Texture(Gdx.files.internal("data/images/tiempo.png"));
	String tipo = "tiempo";
	
	public Texture cargarTextura() {
		return imagen;
	}
	
	public String obtenerTipo() {
		return tipo;
	}
	
	public void dispose() {
		imagen.dispose();
	}

}
