package com.jbs.fractural.game.entity.mob;

import com.jbs.fractural.Constants;
import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.assets.TextureAsset;
import com.jbs.fractural.game.entity.Lane;

public class Debris extends TexturedMob {

	public static float moveSpeed = 8;

	public Debris(float x) {
		this(x, Main.screenSize.y, Lane.laneWidth, 75);
	}
	
	public Debris(float x, float y, float w, float h) {
		super(getDebrisTexture(), x, y, w, h);
	}

	private static TextureAsset getDebrisTexture() {
		int t = Constants.rand.nextInt(3);
		switch (t) {
		case 0:
			return Assets.m1;
		case 1:
			return Assets.m2;
		case 2:
			return Assets.m3;
		case 3:
			return Assets.m4;
		}
		return Assets.m1;
	}

	@Override
	public void tick() {
		if (isDirty()) {
			getBoundingBox().set(getX(), getY(), getWidth(), getHeight());
			setDirty(false);
		}
		if (getY() + getHeight() <= 0) kill();
		addPos(0, -moveSpeed);
		setDirty(true);
	}
}
