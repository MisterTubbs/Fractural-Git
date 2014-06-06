package com.jbs.fractural.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.fractural.GameObject;
import com.jbs.fractural.gui.Button;
import com.jbs.fractural.gui.FireButton;

public class GameGUI implements GameObject {
	
	public static boolean blockGameInput;
	
	private Game game;
	private Button buttons[];
	
	public GameGUI(Game game) {
		this.setGame(game);
		this.buttons = new Button[1];
		buttons[0] = new FireButton(game.getEntityManager());
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
