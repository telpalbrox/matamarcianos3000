package com.alberto.matamarcianos.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Esta es la clase abstracta de la que heredan todos los items
 * Unicamente carga la textura del item y define su tipo
 * @author alberto
 */
public abstract class Item extends Rectangle {
	private static final long serialVersionUID = 1L;
	static Texture imagen;
	String tipo;
	
	/**
	 * Retora la textura del item
	 * @return textura
	 */
	public abstract Texture cargarTextura();
	
	/**
	 * Retorna el el tipo de item, es decir lo que hace
	 * @return
	 */
	public abstract String obtenerTipo();
	
	/**
	 * Libera los recursos usados por el item
	 */
	public abstract void dispose();

}
