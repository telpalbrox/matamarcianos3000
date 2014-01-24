package com.alberto.matamarcianos.screens;

import com.alberto.matamarcianos.Controles;
import com.alberto.matamarcianos.Escenario;
import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.InfoUtils;
import com.alberto.matamarcianos.Nave;
import com.alberto.matamarcianos.SpawnUtils;
import com.alberto.matamarcianos.enemgos.Enemigo;
import com.alberto.matamarcianos.enemgos.Enemigo2;
import com.alberto.matamarcianos.enemgos.NaveEnemiga;
import com.alberto.matamarcianos.items.Item;
import com.alberto.matamarcianos.laseres.Laser;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	public static Espacio game;

	static final int RESOLUCIONX = InfoUtils.x();
	static final int RESOLUCIONY = InfoUtils.y();

	/*
	 * Interfaz de usuario
	 */
	private static HudScreen ui;
	public static Vector2 touchPos;
	public static int margenDerecha;
	public static int margenIzquierda;

	//Cargo las imagenes
	Texture naveImagen;
	Texture laserImagen;
	Texture fondoImagen;

	//Creo la camara y la ejecucion del render
	static OrthographicCamera camera;
	SpriteBatch batch;

	//Creo la nave
	Rectangle fondo;
	public static Nave nave;
	Enemigo enemigo;
	Enemigo2 enemigo2;
	public static Sound laserSonido;
	public static Sound explosionSonido;
	public static Music musica;
	Button boton;

	//Declaro variables de control
	long retardo, momentoUltimoEnemigo, ultimoDisparo, retardoAcel, retardoFondo;
	public static float tiempo2, tiempoLaser, tiempoSonido, tiempoPause, volumen, tiempo, tiempoActual;
	public static long ultimoFondo;
	public static int pause, velocidad, puntuacion;
	public static boolean itemTiempo, pauseBool;

	//Creo los arrays de imagenes y de enemigos
	public static Array<Rectangle> laseres;
	public static Array<Rectangle> fondos;
	public static Array<NaveEnemiga> enemigos;
	public static Array<Item> items;
	public static Array<Laser> laseresEnemigos;
	public static Array<Laser> laseresNave;

	//ANIMACION
	private static final int        FRAME_COLS = 4;         // #1
	private static final int        FRAME_ROWS = 5;         // #2

	Animation                       explosionAnimacion;          // #3
	Texture                         explosionFolio;              // #4
	TextureRegion[]                 explosionFoto;             // #5
	TextureRegion                   fotoActual;           // #7

	float tiempoEstado;                     

	@SuppressWarnings("static-access")
	public GameScreen(final Espacio gam) {
		
		touchPos = new Vector2();
		touchPos.x = 0;
		
		this.game = gam;
		//Cargo las imagenes
		laserImagen = new Texture(Gdx.files.internal("data/images/laserNave.png"));
		fondoImagen = new Texture(Gdx.files.internal("data/images/fondo.png"));
		laserSonido = Gdx.audio.newSound(Gdx.files.internal("data/sound/laser.mp3"));
		explosionSonido = Gdx.audio.newSound(Gdx.files.internal("data/sound/explosion.mp3"));
		musica = Gdx.audio.newMusic(Gdx.files.internal("data/sound/musica.mp3"));
		musica.setLooping(true);
		musica.play();

		//Creo la camara en la que se ve el juego
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESOLUCIONX, RESOLUCIONY);

		//Creo el SpriteBatch
		batch = new SpriteBatch();

		//Creo el rectangulo que almacena la nave
		nave = new Nave();
		nave.x = RESOLUCIONX / 2 - 64 / 2;
		nave.y = 40;
		nave.width = 64;
		nave.height = 64;

		//Inicializo los arrays
		laseres = new Array<Rectangle>();
		fondos = new Array<Rectangle>();
		enemigos = new Array<NaveEnemiga>();
		items = new Array<Item>();
		laseresEnemigos = new Array<Laser>();
		laseresNave = new Array<Laser>();

		//Inicializo las variables de control
		ultimoDisparo = 1;
		ultimoFondo = 1;
		tiempo = 0;
		tiempo2 = 0;
		tiempoActual = 0;
		tiempoSonido = 0;
		retardo = 100000000;
		retardoFondo = 100000000/2;
		retardoAcel = 0;
		puntuacion = 0;
		itemTiempo = false;
		velocidad = 0;
		volumen = 0.4f;
		pause = 1;
		pauseBool = false;

		//Spawneo un enemigo siempre
		SpawnUtils.spawnAleatorio();

		//ANIMACION
		explosionFolio = new Texture(Gdx.files.internal("data/images/explosion_folio.png"));     // #9
		TextureRegion[][] tmp = TextureRegion.split(explosionFolio, explosionFolio.getWidth() / FRAME_COLS, explosionFolio.getHeight() / FRAME_ROWS);                                // #10
		explosionFoto = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				explosionFoto[index++] = tmp[i][j];
			}
		}
		explosionAnimacion = new Animation(0.025f, explosionFoto);              // #11
		tiempoEstado = 0f;

		SpawnUtils.spawnFondo();
		SpawnUtils.spawnEnemigo(enemigos);
		SpawnUtils.spawnEnemigo2(enemigos);
		SpawnUtils.spawnItemVelocidad(items);
		ultimoFondo = TimeUtils.nanoTime();
	}


	@Override
	public void render(float Delta) {
		tiempo += Math.rint(Gdx.graphics.getDeltaTime() * 100) / 100;
		tiempoEstado += Gdx.graphics.getDeltaTime(); //Tiempo para controlar las animaciones
		
		touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		
		fotoActual = explosionAnimacion.getKeyFrame(tiempoEstado, true); //Según el tiempo se selecciona un fotograma
		//Limpio la pantalla y la coloreo de negro
		Gdx.gl.glClearColor(0, 0, 0, 1);

		//Me aseguro de que la camara este actualizada
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		//Le digo al SpriteBatch que use las coordenadas especificadas por la camara
		game.batch.setProjectionMatrix(camera.combined);
		
		touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		
		game.batch.begin();
		//Dibujo los fondos detrás de todo
		for(Rectangle fondo : fondos) {
			game.batch.draw(fondoImagen, fondo.x, fondo.y);
		}
		//Dibujo en pantalla el array de enemigos
		for(NaveEnemiga enemigo : enemigos) {
			game.batch.draw(enemigo.cargarTextura(), enemigo.x, enemigo.y);
			if(enemigo.esAnimacion()) {
				Vector2 vector = new Vector2();
				enemigo.getCenter(vector);
				game.batch.draw(fotoActual, vector.x-110, vector.y-95);
			} else {
				game.batch.draw(enemigo.cargarTextura(), enemigo.x, enemigo.y);
			}
		}
		//Dobujo en pantalla la nave
		game.batch.draw(nave.cargarTextura(), nave.x, nave.y);
		//Dibujo en pantalla el array de laseres
		for(Laser laser : laseresNave) {
			game.batch.draw(laserImagen, laser.x, laser.y);
		}
		//Dibujo en pantalla el array de items
		for(Item item : items) {
			game.batch.draw(item.cargarTextura(), item.x, item.y);
		}
		//Dibujo en pantalla el array de laseres enemigos
		for(Laser laser : laseresEnemigos) {
			game.batch.draw(laser.cargarTextura(), laser.x, laser.y);
		}
		//Dibujo el estado de la nave
		game.font.draw(game.batch, "Vidas: "+nave.obtenerVida(), RESOLUCIONX-125, RESOLUCIONY-100);
		if(nave.esInvencible()) {
			game.font.draw(game.batch, "Eres invencible!", RESOLUCIONX-125, RESOLUCIONY-75);
		}
		if(nave.esAcelerada()) {
			game.font.draw(game.batch, "Nave acelerada!", RESOLUCIONX-125, RESOLUCIONY-125);
		}
		game.font.draw(game.batch, "Puntuacion: "+puntuacion, RESOLUCIONX-125, RESOLUCIONY-50);
		game.batch.end();
		ui.render(Delta);

		//La velocidad aumenta en función de la puntuación
		velocidad = -1 * (puntuacion + 50);

		Controles.escuchaControles();
		SpawnUtils.spawnAleatorio();
		Escenario.calcularChoques();
		Escenario.calculaEstadoNave();

	}

	@Override
	public void dispose() {
		naveImagen.dispose();
		laserImagen.dispose();
		laserSonido.dispose();
		explosionFolio.dispose();
		for(NaveEnemiga enemigo : enemigos) {
			enemigo.dispose();
		}
		for(Item item : items) {
			item.dispose();
		}
		for(Laser laser : laseresEnemigos) {
			laser.dispose();
		}
		batch.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		
		ui = new HudScreen(game);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}

