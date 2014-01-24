package com.alberto.matamarcianos.screens;

import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.conexion.Facade;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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

public class EnviarPuntuacion implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonSend, buttonCancel;
	private BitmapFont white, black; 
	private Label heading, lPuntuacion;
	private int puntuacion;
	
	Facade fachada = new Facade();
	MyTextInputListener listener = new MyTextInputListener();

	final Espacio game;

	public EnviarPuntuacion(final Espacio gam, int pun) {
		this.game = gam;
		puntuacion = pun;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		heading.setText(listener.entrada);
		
		Table.drawDebug(stage);
		
		stage.act(delta);
		
		stage.draw();

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
		
		buttonSend = new TextButton("ENVIAR", textButtonStyle);
		buttonCancel = new TextButton("NO ENVIAR", textButtonStyle);
		buttonSend.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new DeadScreen(game, listener.entrada, puntuacion));
				dispose();
			}
		});
		buttonCancel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new DeadScreen(game, "", puntuacion));
				dispose();
			}
		});

		buttonSend.pad(10);
		buttonCancel.pad(10);

		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);

		heading = new Label("Matamarcianos 3000", headingStyle);
		lPuntuacion = new Label("Puntuacion: "+puntuacion, headingStyle);

		// aniadiendolo todo a la tabla para tenerlo todo junto
		table.add(heading);
		table.row();
		table.add(lPuntuacion);
		table.row();
		table.add(buttonSend);
		table.row();
		table.add(buttonCancel);
		table.row();
		stage.addActor(table);
		
		listener.entrada = "";
		Gdx.input.getTextInput(listener, "Se va a enviar tu puntuacion, instroduce tu nombre "
				+ "(no mas de 10 caracteres): ", "Nombre");

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
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

class MyTextInputListener implements TextInputListener {
	public String entrada = "";
	@Override
	public void input (String text) {
		this.entrada = text;
	}

	@Override
	public void canceled () {
	}
}
