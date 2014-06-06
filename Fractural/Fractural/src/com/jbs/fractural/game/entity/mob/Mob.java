package com.jbs.fractural.game.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.Renderable;
import com.jbs.fractural.game.entity.Entity;

public class Mob extends Entity implements Renderable {

	private Vector2 size;
	private Rectangle boundingBox;
	private boolean dirty;
	
	public Mob(float x, float y, float w, float h) {
		super(x, y);
		this.size = new Vector2(w, h);
		this.boundingBox = new Rectangle(x, y, w, h);
	}
	
	public Mob(float x, float y, float w, float h, Rectangle boundingBox) {
		super(x, y);
		this.size = new Vector2(w, h);
		this.boundingBox = boundingBox;
	}

	@Override
	public void tick() {
		if(dirty) {
			boundingBox.set(getX(), getY(), getWidth(), getHeight());
			dirty = false;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	public Vector2 getSize() {
		return size;
	}
	
	public float getWidth() {
		return size.x;
	}
	
	public float getHeight() {
		return size.y;
	}
	
	public Vector2 getCenter() {
		return new Vector2(getX() + (getWidth() / 2), getY() + (getHeight() / 2));
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
	public void setWidth(int width) {
		size.x = width;
	}
	
	public void setHeight(int height) {
		size.y = height;
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
}
