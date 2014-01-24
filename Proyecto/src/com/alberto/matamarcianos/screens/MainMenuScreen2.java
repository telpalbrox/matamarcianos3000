package com.alberto.matamarcianos.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.alberto.matamarcianos.Espacio;
import com.alberto.matamarcianos.Screens;
import com.alberto.matamarcianos.tween.ActorAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen2 implements Screen {


	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonExit, buttonPlay;
	private BitmapFont white, black; 
	private Label heading, titulo, instrucciones;
	private TweenManager tweenManager;

	final Espacio game;
	boolean bJuego = false;
	static GameScreen juego;


	public MainMenuScreen2(final Espacio gam) {
		this.game = gam;
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Table.drawDebug(stage);

		stage.act(delta);

		stage.draw();

		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
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
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		white = new BitmapFont(Gdx.files.internal("data/fonts/fuente4.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("data/fonts/fuente3.fnt"), false);

		// creando los botos
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("boton.up");
		textButtonStyle.down = skin.getDrawable("boton.down");
		textButtonStyle.pressedOffsetX = 2;
		textButtonStyle.pressedOffsetY = -2;
		textButtonStyle.font = black;

		buttonExit = new TextButton("SALIR", textButtonStyle);
		buttonPlay = new TextButton("JUGAR", textButtonStyle);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Screens.juego = new GameScreen(game);
				game.setScreen(Screens.juego);
			}
		});

		buttonExit.pad(10);
		buttonPlay.pad(10);

		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);

		heading = new Label("Matamarcianos 3000", headingStyle);
		instrucciones = new Label("A > Mover derecha\nD > Mover izquierda\nL > Disparar", headingStyle);
		instrucciones.setFontScaleY(2);
		titulo = new Label("Bienvenido a esta cosa rara", headingStyle);

		// aniadiendolo todo a la tabla para tenerlo todo junto
		table.add(heading).spaceBottom(100).row();
		table.add(titulo).spaceBottom(100).row();
		table.add(buttonPlay).spaceBottom(15).row();
		table.add(buttonExit).spaceBottom(15).row();
		table.add(instrucciones);
		table.debug();

		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		// heading color animation
		Timeline.createSequence().beginSequence()
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);

		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
		.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .2f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
	}

}
