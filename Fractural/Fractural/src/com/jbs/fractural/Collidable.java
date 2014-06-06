package com.jbs.fractural;

import java.util.List;

import com.jbs.fractural.game.entity.Entity;
import com.jbs.fractural.game.entity.mob.Mob;

public interface Collidable {
	public void isColliding(List<Entity> entities);
	public void onCollide(Mob m);
}
