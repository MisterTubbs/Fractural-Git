package com.jbs.fractural.game.entity.mob;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jbs.fractural.assets.TextureAsset;

public class TexturedMob extends Mob {

	private TextureAsset texture;
	
	public TexturedMob(TextureAsset texture, float x, float y, float w, float h) {
		super(x, y, w, h);
		this.texture = texture;
	}
	
	public TexturedMob(TextureAsset texture, float x, float y, float w, float h, Rectangle boundingBox) {
		super(x, y, w, h, boundingBox);
		this.texture = texture;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture.getTexture(), getX(), getY(), getWidth(), getHeight());
	}
}
