package com.jbs.fractural;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Screen extends GameObject {
	public void renderText(SpriteBatch textBatch);
	public Color getClearColor();
}
