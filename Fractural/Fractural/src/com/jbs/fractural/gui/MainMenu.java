package com.jbs.fractural.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.fractural.Screen;

public class MainMenu implements Screen {
	
	private Button[] buttons;
	
	public MainMenu() {
		buttons = new Button[1];
		buttons[0] = new PlayButton();
	}
		
	@Override
	public void render(SpriteBatch batch) {
		for(Button b : buttons)
			b.render(batch);
	}

	@Override
	public void tick() {
		for(Button b : buttons)
			b.tick();
	}

	@Override
	public Color getClearColor() {
		return Color.BLACK;
	}
}
