package com.jbs.fractural.game.entity.mob;

import com.badlogic.gdx.math.Rectangle;

public class Collision {
	
	public static boolean isColliding(Rectangle a, Rectangle b) {
		return a.overlaps(b);
	}
	
	public static boolean isCenterLine(Rectangle a, float x) {
		return a.getX() + (a.getWidth() / 2) == x;
	}
}
