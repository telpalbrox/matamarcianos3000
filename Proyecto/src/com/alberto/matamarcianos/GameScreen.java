package com.alberto.matamarcianos;

import java.util.Iterator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final Espacio game;
	
	static final int RESOLUCIONX = Resolucion.x();
	static final int RESOLUCIONY = Resolucion.y();
	
	//Cargo las imagenes
	Texture naveImagen;
	Texture laserImagen;
	Texture fondoImagen;
	
	//Creo la camara y la ejecucion del render
	OrthographicCamera camera;
	SpriteBatch batch;
	
	//Creo la nave
	Rectangle fondo;
	Nave nave;
	Enemigo enemigo;
	Enemigo2 enemigo2;
	Sound laserSonido;
	Sound explosionSonido;
	Music musica;
	
	//Declaro variables de control
	long retardo, momentoUltimoEnemigo, ultimoDisparo, retardoAcel, ultimoFondo, retardoFondo;
	float tiempo, tiempo2, tiempoActual, tiempoLaser, tiempoSonido, volumen, tiempoPause;
	int puntuacion, velocidad, pause;
	boolean itemTiempo, pauseBool;
	
	//Creo los arrays de imÃ¡genes y de enemigos
	Array<Rectangle> laseres;
	Array<Rectangle> fondos;
	Array<NaveEnemiga> enemigos;
	Array<Item> items;
	Array<LaserEnemigo> laseresEnemigos;
	
	//ANIMACION
	private static final int        FRAME_COLS = 4;         // #1
	private static final int        FRAME_ROWS = 5;         // #2
	   
	Animation                       explosionAnimacion;          // #3
    Texture                         explosionFolio;              // #4
    TextureRegion[]                 explosionFoto;             // #5
    TextureRegion                   fotoActual;           // #7
	    
	float tiempoEstado;                     
	
	public GameScreen(final Espacio gam) {
		this.game = gam;
		//Cargo las imagenes
		naveImagen = new Texture(Gdx.files.internal("data/nave.png"));
		laserImagen = new Texture(Gdx.files.internal("data/laserNave.png"));
		fondoImagen = new Texture(Gdx.files.internal("data/fondo.png"));
		laserSonido = Gdx.audio.newSound(Gdx.files.internal("data/laser.mp3"));
		explosionSonido = Gdx.audio.newSound(Gdx.files.internal("data/explosion.mp3"));
		musica = Gdx.audio.newMusic(Gdx.files.internal("data/musica.mp3"));
		musica.setLooping(true);
		musica.play();
		
		//Creo la camara en la que se ve el juego
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESOLUCIONX, RESOLUCIONY);
				
		//Creo el SpriteBatch
		batch = new SpriteBatch();
		
		//Creo el rectï¿½ngulo que almacena la nave
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
		laseresEnemigos = new Array<LaserEnemigo>();
				
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
		puntuacion = 1;
		itemTiempo = false;
		velocidad = 0;
		volumen = 0.4f;
		pause = 1;
		pauseBool = false;
				
		//Spawneo un enemigo siempre
		spawnEnemigo0(enemigos);
		SpawnUtils.spawnItemVelocidad(items);
		
		//ANIMACION
		explosionFolio = new Texture(Gdx.files.internal("data/explosion_folio.png"));     // #9
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
        
        spawnFondo();
        SpawnUtils.spawnEnemigo3(enemigos);
        SpawnUtils.spawnEnemigo2(enemigos);
        ultimoFondo = TimeUtils.nanoTime();
 }

	@Override
	public void render(float Delta) {
		if(!pauseBool) {
			tiempo += Math.rint(Gdx.graphics.getDeltaTime() * 100) / 100;
			tiempoEstado += Gdx.graphics.getDeltaTime();
		}
		tiempo2 += Math.rint(Gdx.graphics.getDeltaTime() * 100) / 100;
		fotoActual = explosionAnimacion.getKeyFrame(tiempoEstado, true);
		//Limpio la pantalla y la coloreo de negro
		Gdx.gl.glClearColor(0, 0, 0, 1);
				
		//Me aseguro de que la cï¿½mara estï¿½ actualizada
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
				
		//Le digo al SpriteBatch que use las coordenadas especificadas por la cï¿½mara
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
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
			} else{
				game.batch.draw(enemigo.cargarTextura(), enemigo.x, enemigo.y);
			}
		}
		//Dobujo en pantalla la nave
		game.batch.draw(naveImagen, nave.x, nave.y);
		//Dibujo en pantalla el array de laseres
		for(Rectangle laser : laseres) {
			game.batch.draw(laserImagen, laser.x, laser.y);
		}
		for(Item item : items) {
			game.batch.draw(item.cargarTextura(), item.x, item.y);
		}
		for(LaserEnemigo laser : laseresEnemigos) {
			game.batch.draw(laser.cargarTextura(), laser.x, laser.y);
		}
		game.font.draw(game.batch, "Vidas: "+nave.obtenerVida(), RESOLUCIONX-125, RESOLUCIONY-100);
		if(nave.esInvencible()) {
			game.font.draw(game.batch, "Â¡Eres invencible!", RESOLUCIONX-125, RESOLUCIONY-75);
		}
		if(nave.esAcelerada()) {
			game.font.draw(game.batch, "Â¡Nave acelerada!", RESOLUCIONX-125, RESOLUCIONY-125);
		}
		game.font.draw(game.batch, "Puntuacion: "+puntuacion, RESOLUCIONX-125, RESOLUCIONY-50);
		if(pauseBool) {
			game.font.draw(game.batch, "PAUSE", RESOLUCIONX-RESOLUCIONX/2, RESOLUCIONY-RESOLUCIONY/2);
		}
		game.batch.end();
				
		//Controles de la nave
		if(Gdx.input.isKeyPressed(Keys.A)) nave.x -= nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime() * pause; //Si se presiona la tecla izquierda se mueve 200 pixels
		if(Gdx.input.isKeyPressed(Keys.D)) nave.x += nave.obtenerVelocidadMovimiento() * Gdx.graphics.getDeltaTime() * pause;
		if(Gdx.input.isKeyPressed(Keys.L) && TimeUtils.nanoTime() - ultimoDisparo > nave.obtenerRetardo() && !pauseBool) {
			spawnLaser();
			laserSonido.play(volumen);
			ultimoDisparo = TimeUtils.nanoTime();
		}
		
		if((Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.MENU)) && !pauseBool && tiempoPause + 0.5 <= tiempo2) {
			System.out.println("Pause false");
			pause = 0;
			tiempoPause = tiempo2;
			pauseBool = true;
		}
		if((Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.MENU)) && pauseBool && tiempoPause + 0.5 <= tiempo2) {
			System.out.println("Pause true");
			pause = 1;
			tiempoPause = tiempo2;
			pauseBool = false;
		}
		
		if(TimeUtils.nanoTime() - ultimoFondo > 100 && !pauseBool){
			
			spawnFondo();
		}
		
		
		/*
		 * CONTROLES ANDROID
		 */
		if(Gdx.input.isTouched() && pause == 1) { //Si se estÃ¯Â¿Â½ tocando la pantalla
			Vector2 touchPos = new Vector2(); //Transformo las coordenadas del ratÃ¯Â¿Â½n al sistema de coordenadas de la cÃ¯Â¿Â½mara
			touchPos.set(Gdx.input.getX(), Gdx.input.getY());
			nave.x = touchPos.x - 64 /2;
		}
		if(Gdx.input.isTouched() && TimeUtils.nanoTime() - ultimoDisparo > retardo && pause == 1) { //Si se estÃ¯Â¿Â½ tocando la pantalla
			laserSonido.play(volumen);
			spawnLaser();
			ultimoDisparo = TimeUtils.nanoTime();
		}
		
		if(MathUtils.random(0, 5000/(puntuacion + 1)) == 0 && pause == 1) {
			spawnEnemigo0(enemigos);
		}
		//Spawneo de items
		if(MathUtils.random(0, (100/(puntuacion+1)) + 300) == 0 && pause == 1) {
			spawnItem();
		}
				
		//Si se sale de los lï¿½mites
		if(nave.x < 0) nave.x = 0;
		if(nave.x > RESOLUCIONX - 64) nave.x = RESOLUCIONX - 64;
		
		//Muevo los disparos
		Iterator<Rectangle> iterLaser = laseres.iterator();
		while(iterLaser.hasNext()) {
			Rectangle laser = iterLaser.next();
			laser.y += 400 * Gdx.graphics.getDeltaTime() * pause;
			for(NaveEnemiga enemigo : enemigos) {
				//Control de choques contra el laser
				if(enemigo.obtenerVida() >= 0 && enemigo.overlaps(laser)) {
					enemigo.quitarVida();
					iterLaser.remove();
				}
			}
		}
		//Muevo los enemigos
		Iterator<NaveEnemiga> iterEnemigo = enemigos.iterator();
		while(iterEnemigo.hasNext()){
			NaveEnemiga enemigo = iterEnemigo.next();
			//SegÃºn la puntuaciÃ³n lllevan una velocidad
			velocidad = -1 * (puntuacion + 50);
			if(!enemigo.obtenerTipo().equals("enemigo3")) {
				enemigo.fijarVelocidad(velocidad);
				enemigo.y += enemigo.obtenerVelocidad() * Gdx.graphics.getDeltaTime() * pause;
			}
			if(enemigo.obtenerTipo().equals("enemigo3")) {
				System.out.println("Enemigo3 y: "+enemigo.y);
				System.out.println("Enemigo3 x: "+enemigo.x);
				if(enemigo.y < RESOLUCIONY - 200) {
					if(enemigo.y <= RESOLUCIONY - 400) {
						enemigo.fijarVelocidad(50);
					}
					if(enemigo.y >= RESOLUCIONY - 250 && enemigo.y > 248) {
						enemigo.fijarVelocidad(0);
						enemigo.fijarVelocidadX(-50);
						if(enemigo.x < 10) {
							enemigo.fijarVelocidad(-50);
							enemigo.fijarVelocidadX(0);
						}
					}
					if(enemigo.y < RESOLUCIONY - 400 && enemigo.y > 398) {
						enemigo.fijarVelocidad(0);
						enemigo.fijarVelocidadX(50);
						if(enemigo.x > RESOLUCIONX - 70) {
							enemigo.fijarVelocidad(50);
							enemigo.fijarVelocidadX(0);
						}
					}
				}
				enemigo.y += enemigo.obtenerVelocidad() * Gdx.graphics.getDeltaTime() * pause;
				enemigo.x += enemigo.obtenerVelocidadX() * Gdx.graphics.getDeltaTime() * pause;
			}
			//Quito vida si los enemigos se chocan cotra la nave
			if(enemigo.overlaps(nave) && !enemigo.esAnimacion()) {
				nave.quitarVida();
				explosionSonido.play(volumen);
				enemigo.fijarTiempoMuerte(tiempo);
				enemigo.fijarAnimacion(true);
			}
			//Elimino los enemigos sin vida
			if(enemigo.obtenerVida()<= 0 && !enemigo.esAnimacion()) {
				if(enemigo.obtenerTipo().equals("enemigo")) puntuacion += 1;
				if(enemigo.obtenerTipo().equals("enemigo2")) {
					puntuacion += 3;
					if(MathUtils.random(0, 4) == 0) {
						SpawnUtils.spawnItemRetardo(items, enemigo);
					}
				}
				explosionSonido.play();
				enemigo.fijarTiempoMuerte(tiempo);
				enemigo.fijarAnimacion(true);
			}
			if(enemigo.esAnimacion() && enemigo.obtenerTiempoMuerte() + 0.25f <= tiempo) {
				iterEnemigo.remove();
			}
			//Quito vida si los enemigos llegan al final de la pantalla
			if(enemigo.y <= -64) {
				nave.quitarVida();
				iterEnemigo.remove();
			}
			//Si es enemigo de tipo 2 dispara laser
			if(enemigo.obtenerTipo().equals("enemigo2")) {
				if(!enemigo.obtenerDisparaLaser()) {
					enemigo.fijarTiempoLaser(tiempo);
					enemigo.fijarDisparaLaser(true);
				}
				if(enemigo.obtenerTiempoLaser() + 2 - (puntuacion/75) <= tiempo && puntuacion < 150) {
					spawnLaserEnemigo2(enemigo);
					enemigo.fijarTiempoLaser(tiempo);
				}
				if(enemigo.obtenerTiempoLaser() + 2 - 1 <= tiempo && puntuacion >= 150) {
					spawnLaserEnemigo2(enemigo);
					enemigo.fijarTiempoLaser(tiempo);
				}
			}
		}
		
		//Muevo los items
		Iterator<Item> iterItem = items.iterator();
		while(iterItem.hasNext()) {
			Item item = iterItem.next();
			item.y += (velocidad - 50) * Gdx.graphics.getDeltaTime() * pause;
			//Si el item choca contra la nave se gasta y recibe los beneficios
			if(item.overlaps(nave)) {
				if(item.obtenerTipo().equals("vida")) {
					nave.sumarVida(1);
				}
				if(item.obtenerTipo().equals("tiempo")) {
					itemTiempo = true;
					tiempoActual = tiempo;
				}
				if(item.obtenerTipo().equals("invulnerabilidad")) {
					nave.fijarTiempoInvencible(tiempo);
					nave.fijarInvulnerabilidad(true);
				}
				if(item.obtenerTipo().equals("velocidad")) {
					nave.fijarVelocidadMovimiento(1600);
					nave.fijarTiempoAcel(tiempo);
					nave.fijarNaveAcel(true);
				}
				puntuacion++;
				iterItem.remove();
			}
			//Si llega abajo se elimina
			if(item.y <= -64) iterItem.remove();
		}
		
		//Muevo los lÃ¡seres enemigos
		Iterator<LaserEnemigo> iterLaserEnemigo = laseresEnemigos.iterator();
		while(iterLaserEnemigo.hasNext()) {
			LaserEnemigo laser = iterLaserEnemigo.next();
			laser.y += (velocidad - 50) * Gdx.graphics.getDeltaTime() * pause;
			//Si llegan abajo se eliminan
			if(laser.y <= -16) {
				iterLaserEnemigo.remove();
			}
			if(laser.overlaps(nave)) {
				nave.quitarVida();
				iterLaserEnemigo.remove();
			}
			
		}
		
		Iterator<Rectangle> iterFondo = fondos.iterator();
		while(iterFondo.hasNext()) {
			Rectangle fondo = iterFondo.next();
			fondo.y += (velocidad - 500) * Gdx.graphics.getDeltaTime() * pause;
			if(fondo.y < 64) {
				iterFondo.remove();
			}
			
		}
		
		//Si se acaban las vidas se muestra la pantalla de muerte
		/*if(nave.obtenerVida() <= 0) {
			musica.stop();
			game.setScreen(new DeadScreen(game, puntuacion));
		}*/
		
		//Si se ha recogido el item del tiempo se disminiye el retardo
		if(itemTiempo) {
			retardoAcel = 100000000/2;
			nave.fijarRetardo(retardoAcel);
			//Durante 5 segundos
			if(tiempoActual + 5 <= tiempo) {
				nave.fijarRetardo(retardo);
				itemTiempo = false;
			}
			
		}
		if(nave.esInvencible() && nave.obtenerTiempoInvencible() + 5 <= tiempo) {
			nave.fijarInvulnerabilidad(false);
		}
		if(nave.esAcelerada() && nave.obtenerTiempoAcel() + 5 <= tiempo) {
			nave.fijarVelocidadMovimiento(800);
			nave.fijarNaveAcel(false);
		}
		
	}
		
	
	private void spawnLaser() {
		Rectangle laser = new Rectangle();
		laser.x = nave.x + 32;
		laser.y = 64;
		laser.width = 8;
		laser.height = 16;
		laseres.add(laser);
	}
	
	private void spawnLaserEnemigo2(NaveEnemiga enemigo) {
		LaserEnemigo2 laser = new LaserEnemigo2(enemigo);
		laser.x = enemigo.x + 32;
		laser.y = enemigo.y;
		laser.width = 8;
		laser.height = 16;
		laseresEnemigos.add(laser);
	}
	
	private void spawnEnemigo0(Array<NaveEnemiga> enemigos2) {
		int numero = MathUtils.random(0, 6);
		if(numero <= 3) {
			SpawnUtils.spawnEnemigo(enemigos);
		}
		if(numero > 3) {
			SpawnUtils.spawnEnemigo2(enemigos);
		}
		
	}
	
	private void spawnFondo() {
		Rectangle detras = new Rectangle();
		detras.x = MathUtils.random(0, RESOLUCIONX-64);
		detras.y = RESOLUCIONY - 20;
		detras.height = 32;
		detras.width = 32;
		fondos.add(detras);
	}
	
	private void spawnItem() {
		int numero = MathUtils.random(0, 4);
		
		if(numero == 1) {
			SpawnUtils.spawnItemVida(items);
		}
		if(numero == 0) {
			SpawnUtils.spawnItemTiempo(items);
		}
		if(numero == 2) {
			SpawnUtils.spawnItemInvulnerabilidad(items);
		}
		if(numero == 4) {
			SpawnUtils.spawnItemVelocidad(items);
		}
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
		for(LaserEnemigo laser : laseresEnemigos) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}

