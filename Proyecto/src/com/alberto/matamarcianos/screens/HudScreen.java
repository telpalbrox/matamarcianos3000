package com.alberto.matamarcianos.screens;

import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.InfoUtils;
import com.alberto.matamarcianos.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HudScreen implements Screen {
	
	final Espacio game;
	static final int RESOLUCIONX = InfoUtils.x();
	static final int RESOLUCIONY = InfoUtils.y();
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonExit, buttonContinue;
	private BitmapFont white, black; 
	private Label heading;
	
	public HudScreen(final Espacio gam) {
		this.game = gam;
		this.show();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
	public void render(float delta) {
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("data/ui/button.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		table.setBounds(RESOLUCIONX-115, RESOLUCIONY-175, 25, 50);

		white = new BitmapFont(Gdx.files.internal("data/fonts/fuente4.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("data/fonts/fuente6.fnt"), false);
		
		// creando los botones
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("boton.up");
		textButtonStyle.down = skin.getDrawable("boton.down");
		textButtonStyle.pressedOffsetX = 2;
		textButtonStyle.pressedOffsetY = -2;
		textButtonStyle.font = black;

		buttonExit = new TextButton("PAUSE", textButtonStyle);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(Screens.pause);
			}
		});

		buttonExit.pad(4);

		// aniadiendolo todo a la tabla para tenerlo todo junto
		table.add(buttonExit);
		table.debug();
		stage.addActor(table);

	}

}
