package com.jbs.fractural;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainActivity {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Fractural";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.addIcon("mobs/player.png", FileType.Internal);
		
		new LwjglApplication(new Main(), cfg);
	}
}
