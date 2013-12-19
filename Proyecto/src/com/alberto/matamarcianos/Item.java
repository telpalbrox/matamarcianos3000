package com.alberto.matamarcianos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Item extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Texture imagen;
	String tipo;
	
	public Texture cargarTextura() {
		return imagen;
	}
	
	public String obtenerTipo() {
		return tipo;
	}
	
	public void dispose() {
		
	}

}
