package com.jbs.fractural.assets;

import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Assets {
	
	public static TextureAsset playButton, player, lane, star, redStar, laneFill, m1, m2, m3, m4, blueLaz, redLaz, fireButton;
	
	public static void load() {
		playButton = TextureAsset.loadTexture("GUI/play.png");
		
		fireButton = TextureAsset.loadTexture("GUI/fire.png");
		fireButton.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		player = TextureAsset.loadTexture("mobs/player.png");
		lane = TextureAsset.loadTexture("game/lane.png");
		star = TextureAsset.loadTexture("game/star.png");
		redStar = TextureAsset.loadTexture("game/redStar.png");
		laneFill = TextureAsset.loadTexture("game/laneFill.png");
		m1 = TextureAsset.loadTexture("game/debris/m1.png");
		m2 = TextureAsset.loadTexture("game/debris/m2.png");
		m3 = TextureAsset.loadTexture("game/debris/m3.png");
		m4 = TextureAsset.loadTexture("game/debris/m4.png");
		blueLaz = TextureAsset.loadTexture("game/laser_blue.png");
		redLaz = TextureAsset.loadTexture("game/laser_red.png");
	}

}
