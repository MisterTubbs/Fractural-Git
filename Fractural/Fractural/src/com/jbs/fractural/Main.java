package com.jbs.fractural;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.gui.MainMenu;

public class Main implements ApplicationListener, Tickable {
	
	public static final Vector2 screenSize = new Vector2(1280, 720);
	public static final Vector2 centered = new Vector2(screenSize.x / 2, screenSize.y / 2);
	public static OrthographicCamera camera, textCamera; 
	public static Main activeGame;
	public static Vector2 actualScreenSize;
	public static float aspectRatio;
	public static BitmapFont font;
	
	private SpriteBatch batch, textBatch;
	private Color currentClearColor;
	private GameObject currentState;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		Assets.load();
		
		activeGame = this;
		
		actualScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		aspectRatio = screenSize.y / screenSize.x;
		camera = new OrthographicCamera(actualScreenSize.x, actualScreenSize.x * aspectRatio);
		camera.setToOrtho(false, (int) screenSize.x, (int) screenSize.y);

		textCamera = new OrthographicCamera(actualScreenSize.x, actualScreenSize.x * aspectRatio);
		textCamera.setToOrtho(false, (int) screenSize.x, (int) screenSize.y);
		
		currentState = new MainMenu();
		currentClearColor = Color.BLACK;
		
		font = new BitmapFont(Gdx.files.internal("fonts/gameFont.fnt"), Gdx.files.internal("fonts/gameFont.png"), false);
		
		batch = new SpriteBatch();
		textBatch = new SpriteBatch();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(currentClearColor.r, currentClearColor.g, currentClearColor.b, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		tick();
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		currentState.render(batch);
		batch.end();
		
		if(currentState instanceof Screen) {
			textCamera.update();
			textBatch.setProjectionMatrix(textCamera.combined);
			textBatch.begin();
			((Screen) currentState).renderText(textBatch);
			textBatch.end();
		}
	}


	@Override
	public void tick() {
		currentState.tick();
	}
	
	public void switchState(GameObject obj) {
		currentState = obj;
		getClearColor(obj);
	}
	
	public void getClearColor(GameObject obj) {
		if(obj instanceof Screen) 
			currentClearColor = ((Screen) obj).getClearColor();
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
