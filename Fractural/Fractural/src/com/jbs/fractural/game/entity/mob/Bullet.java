package com.jbs.fractural.game.entity.mob;

import java.util.List;

import com.jbs.fractural.Collidable;
import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.entity.Entity;

public class Bullet extends TexturedMob implements Collidable {

	private int moveSpeed;
	private boolean color;
	private Mob mob;

	public Bullet(Mob mob, boolean color, float x, float y) {
		super(color ? Assets.blueLaz : Assets.redLaz, x, y, Assets.blueLaz.getWidth(), Assets.blueLaz.getHeight());
		setMoveSpeed(color ? 20 : -20);
		this.color = color;
		this.mob = mob;
	}

	@Override
	public void tick() {
		if (isDirty()) {
			getBoundingBox().set(getX(), getY(), getWidth(), getHeight());
			setDirty(false);
		}
		
		if(getY() + getHeight() < 0 || getY() > Main.screenSize.y) kill();
		
		addPos(0, moveSpeed);
		setDirty(true);
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	@Override
	public void onCollide(Mob m) {
		if (color && !(m instanceof Player)) {
			if (m instanceof Collidable)
				((Collidable) m).onCollide(this);
			else if (!(m instanceof Collidable))
				m.kill();
		}
		kill();
	}

	@Override
	public void isColliding(List<Entity> entities) {
		for (Entity e : entities) {
			if (!(e instanceof Bullet) && !(e instanceof Star)) {
				if (e instanceof Mob)
					if (Collision.isColliding(getBoundingBox(), ((Mob) e).getBoundingBox()))
						onCollide(((Mob) e));
			}
		} 
		if(mob instanceof Player) {
			Player p = ((Player) mob);
			for(Debris d : p.getCurrentLane().getDebris()) {
				if(Collision.isColliding(getBoundingBox(), d.getBoundingBox()))
					onCollide(d);
			}
		}
	}
}
