package com.alberto.matamarcianos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemInvulnerabilidad extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen = new Texture(Gdx.files.internal("data/invulnerabilidad.png"));
	String tipo = "invulnerabilidad";
	
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
