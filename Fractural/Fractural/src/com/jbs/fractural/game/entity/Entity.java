package com.jbs.fractural.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.Tickable;

public abstract class Entity implements Tickable {

	private Vector2 pos;
	private boolean alive;

	public Entity(float x, float y) {
		this(new Vector2(x, y));
	}
 	
	public Entity(Vector2 pos) {
		this.pos = pos;
		this.alive = true;
	}

	@Override
	public abstract void tick();

	public void create() {
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public void addPos(float mx, float my) {
		setPos(getX() + mx, getY() + my);
	}

	public void setPos(float x, float y) {
		pos.set(x, y);
	}
	
	public void setPos(Vector2 pos) {
		this.pos.set(pos);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void kill() {
		setAlive(false);
	}
	
	public void destroy() {
	}
}
