package com.jbs.fractural.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jbs.fractural.InputProxy;
import com.jbs.fractural.Tickable;

public abstract class Button implements Tickable {

	protected Vector2 loc, size;
	
	public Button(float x, float y, float w, float h) {
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	public Button(Vector2 loc, Vector2 size) {
		this.loc = loc;
		this.size = size;
	}
	
	@Override
	public void tick() {
		if(Gdx.input.justTouched() && isClicked()) onClick();
	}

	public boolean isClicked() {
		return inputIntersects(InputProxy.getTouch());
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract boolean inputIntersects(Vector2 touchPos);
	public abstract void onClick();

	public Vector2 getLoc() {
		return loc;
	}

	public void setLoc(Vector2 loc) {
		this.loc = loc;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
}
