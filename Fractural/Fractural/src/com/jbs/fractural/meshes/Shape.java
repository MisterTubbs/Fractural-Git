package com.jbs.fractural.meshes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jbs.fractural.Main;

public class Shape {
	
	public static ShapeRenderer renderer = new ShapeRenderer();

	public static void begin(ShapeType type) {
		renderer.setProjectionMatrix(Main.camera.combined);
		renderer.begin(type);
	}
	
	public static void end() {
		renderer.end();
	}
}
