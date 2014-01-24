package com.alberto.matamarcianos.screens;

import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.InfoUtils;
import com.alberto.matamarcianos.Screens;
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

public class ErrorScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonExit, buttonContinue;
	private BitmapFont white, black; 
	private Label heading;

	final Espacio game;
	OrthographicCamera camera;
	String error;

	public ErrorScreen(final Espacio gam, String error) {
		this.error = error;
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, InfoUtils.x(), InfoUtils.y());

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		stage.draw();

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
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
				game.setScreen(new MainMenuScreen2(game));
			}
		});

		buttonExit.pad(10);
		buttonContinue.pad(10);

		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);

		heading = new Label("No hay conexion", headingStyle);

		// aniadiendolo todo a la tabla para tenerlo todo junto
		table.add(heading);
		table.row();
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
