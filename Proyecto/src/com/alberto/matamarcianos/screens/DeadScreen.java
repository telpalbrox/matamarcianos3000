package com.alberto.matamarcianos.screens;

import java.util.Collection;

import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.InfoUtils;
import com.alberto.matamarcianos.Screens;
import com.alberto.matamarcianos.conexion.Facade;
import com.alberto.matamarcianos.conexion.PuntuacionesDTO;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DeadScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonExit, buttonContinue;
	private BitmapFont white, black; 
	private Label heading;

	final Espacio game;
	int puntuacion;
	OrthographicCamera camera;
	Facade fachada = new Facade();
	Collection<PuntuacionesDTO> puntuaciones = null;
	InfoUtils informacion = new InfoUtils();
	boolean conexion = false;
	int contador = 0;

	public DeadScreen(final Espacio gam, String jugador, int pun) {
		game = gam;
		puntuacion = pun;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, InfoUtils.x(), InfoUtils.y());
		if(informacion.hayConexion()) {
			conexion = true;
			if(!jugador.isEmpty()) { //Si le da a cancelar no se envia la puntuacion
				fachada.insertarPuntuacion(jugador, pun);
			}
			puntuaciones = fachada.obtenerPuntuaciones();
		}
		else {
			conexion = false;
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if(conexion) {
			camera.update();
			game.batch.setProjectionMatrix(camera.combined);
			game.batch.begin();
			game.font.draw(game.batch, "Mejores Puntuaciones", 25, 650);
			contador = 0;
			for(PuntuacionesDTO puntuacion : puntuaciones) {
				game.font.draw(game.batch, puntuacion.obtenerJugador(), 25, 600-(contador*25));
				game.font.draw(game.batch, String.valueOf(puntuacion.obtenerPuntuacion()), 125, 600-(contador*25));
				if(puntuacion.obtenerVersion() != null)
					game.font.draw(game.batch, puntuacion.obtenerVersion(), 160, 600-(contador*25));
				contador++;
			}
			game.font.draw(game.batch, "Has muerto...", 225, 750);
			game.font.draw(game.batch, "Pulsa R o tecla menu para continuar", 225, 725);
			game.font.draw(game.batch, "Has conseguido: "+puntuacion+" puntos.", 225, 700);
			game.batch.end();
			
			stage.act(delta);
			
			stage.draw();
		}
		else {
			game.setScreen(new ErrorScreen(game, "No hay conexion con la base de datos :("));	
		}
	}

	public void resume() {

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("data/ui/button.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		table.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 200, 200, 200);

		white = new BitmapFont(Gdx.files.internal("data/fonts/fuente4.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("data/fonts/fuente3.fnt"), false);

		// creando los botones
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("boton.up");
		textButtonStyle.down = skin.getDrawable("boton.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;

		buttonExit = new TextButton("SALIR", textButtonStyle);
		buttonContinue = new TextButton("VOLVER A JUGAR", textButtonStyle);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		buttonContinue.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(Screens.juego = new GameScreen(game));
			}
		});

		buttonExit.pad(4);
		buttonContinue.pad(4);

		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);

		heading = new Label("Matamarcianos 3000", headingStyle);

		// aniadiendolo todo a la tabla para tenerlo todo junto
		table.add(buttonContinue);
		table.row();
		table.add(buttonExit);
		table.debug();
		stage.addActor(table);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {


	}

}
