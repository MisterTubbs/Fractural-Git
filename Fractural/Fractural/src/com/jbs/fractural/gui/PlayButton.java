package com.jbs.fractural.gui;

import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.InputProxy;
import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.Game;

public class PlayButton extends TexturedButton {

	public PlayButton() {
		super(Assets.playButton, Main.centered.x - (Assets.playButton.getTexture().getWidth() / 2), Main.centered.y, Assets.playButton.getWidth(), Assets.playButton.getHeight());
	}

	@Override
	public boolean inputIntersects(Vector2 touchPos) {
		Vector2 pos = InputProxy.getTouch();
		int x = (int) pos.x;
		int y = (int) pos.y;
		
		return x >= loc.x && y >= loc.y && x <= loc.x + size.x && y <= loc.y + size.y;
	}

	@Override
	public void onClick() {
		Main.activeGame.switchState(new Game());
	}
}
