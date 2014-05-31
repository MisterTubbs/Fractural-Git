package com.jbs.fractural.game.entity.mob;

import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.entity.Lane;

public class LaneFill extends TexturedMob {

	private float moveSpeed = 4;
	
	public LaneFill(float x) {
		super(Assets.laneFill, x, Main.screenSize.y, Lane.laneWidth, Assets.laneFill.getHeight());
	}

	@Override
	public void tick() {
		if(getY() + getHeight() <= 0) kill();
		addPos(0, -moveSpeed);
	}
}
