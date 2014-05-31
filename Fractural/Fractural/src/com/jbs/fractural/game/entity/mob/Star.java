package com.jbs.fractural.game.entity.mob;

import com.jbs.fractural.Constants;
import com.jbs.fractural.assets.Assets;

public class Star extends TexturedMob {

	private float moveSpeed;
	
	public Star(float x, float y, float size) {
		super(Constants.rand.nextBoolean() ? Assets.star : Assets.redStar, x, y, size, size);
		this.moveSpeed = Constants.rand.nextFloat();
	}

	@Override
	public void tick() {
		if(getY() + getHeight() <= 0) kill();
		addPos(0, -moveSpeed);
	}
}
