package com.jbs.fractural.gui;

import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.InputProxy;
import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.GameGUI;
import com.jbs.fractural.game.entity.EntityManager;
import com.jbs.fractural.game.entity.mob.Bullet;

public class FireButton extends TexturedButton {

	private EntityManager manager;
	
	public FireButton(EntityManager manager) {
		super(Assets.fireButton, Main.screenSize.x - 300, 80, 100, 100);
		this.manager = manager;
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
		GameGUI.blockGameInput = true;
		manager.add(new Bullet(manager.getPlayer(), true, manager.getPlayer().getCenter().x - 4, manager.getPlayer().getY() + manager.getPlayer().getHeight()));
	}
}
