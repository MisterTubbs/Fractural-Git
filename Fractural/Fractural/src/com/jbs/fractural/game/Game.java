package com.jbs.fractural.game;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.fractural.Constants;
import com.jbs.fractural.InputProxy;
import com.jbs.fractural.Main;
import com.jbs.fractural.Screen;
import com.jbs.fractural.game.entity.EntityManager;
import com.jbs.fractural.game.entity.Lane;
import com.jbs.fractural.game.entity.mob.Collision;
import com.jbs.fractural.game.entity.mob.Debris;
import com.jbs.fractural.game.entity.mob.Player;
import com.jbs.fractural.game.entity.mob.Star;

public class Game implements Screen {

	public static float xScale = 1f;

	private EntityManager entityManager;
	private LinkedList<Lane> lanes;
	private int time, debrisSpawnChance;
	
	public Game() {
		createLevel();
	}
	
	private void createLevel() {
		time = 0;
		debrisSpawnChance = 50;
		lanes = new LinkedList<Lane>();
		for(int i = 1; i <= 3; i++) {
			lanes.add(new Lane(i));
		}
		entityManager = new EntityManager();
		entityManager.setPlayer(new Player(lanes));
		
		for(int i = 0; i < 100; i++) {
			if(Constants.rand.nextInt(10) == 0) {
				Star s = new Star(Constants.rand.nextInt((int) Main.screenSize.x), Constants.rand.nextInt((int) Main.screenSize.y), Constants.rand.nextInt(16) + 8);
				boolean collides = false;
				for(Lane l : lanes) {
					if(Collision.isColliding(s.getBoundingBox(), l.getBoundingBox())) collides = true;
				}
				if(!collides) entityManager.add(s);
			}
		}
	}
	
 	@Override
	public void render(SpriteBatch batch) {
 		for(Lane l : lanes)
 			l.render(batch);
		entityManager.render(batch);
	}

	@Override
	public void tick() {
		entityManager.tick();
		
		spawnDebris();
		
		if(Constants.rand.nextInt(100) == 0) for(Lane l : lanes) l.addFill();
		
		if(Constants.rand.nextInt(50) == 0) {
			Star s = new Star(Constants.rand.nextInt((int) Main.screenSize.x), Main.screenSize.y, Constants.rand.nextInt(16) + 8);
			boolean collides = false;
			for(Lane l : lanes) if(Collision.isColliding(s.getBoundingBox(), l.getBoundingBox())) collides = true;
			if(!collides) entityManager.add(s);
		}
		
		if(Gdx.input.justTouched()) entityManager.getPlayer().moveToLane(InputProxy.getTouchSide(entityManager.getPlayer().getX()) ? entityManager.getPlayer().getLeftLane() : entityManager.getPlayer().getRightLane());
		time++;
	}
	
	private void spawnDebris() {
		if(time % (Constants.rand.nextInt(1000) + 1) == 0) {
			if(debrisSpawnChance >= 15) debrisSpawnChance--;
			if(Debris.moveSpeed <= 10) Debris.moveSpeed += 0.2f;
		}
		if(Constants.rand.nextInt(debrisSpawnChance) == 0) {
			int lane = Constants.rand.nextInt(lanes.size());
			int rLane = lane - 1;
			int lLane = lane + 1;
			
			boolean canSpawnR = false, canSpawnL = false;
			
			if(rLane >= 0) {
				Lane l = lanes.get(rLane);
				if(l.getDebris().isEmpty()) {
					canSpawnR = true;
				} else {
					if(l.getDebris().get(0).getY() <= Main.screenSize.y - 400) canSpawnR = true;
				}
			}
			
			if(lLane <= lanes.size() - 1) {
				Lane l = lanes.get(lLane);
				if(l.getDebris().isEmpty()) {
					canSpawnL = true;
				} else {
					if(l.getDebris().get(0).getY() <= Main.screenSize.y - 400) canSpawnL = true;
				}
			}
			
			if(canSpawnR || canSpawnL) lanes.get(lane).addDebris();
		}
	}

	@SuppressWarnings("unused")
	private void fractalize() {
	}
	
	public void reset() {
		entityManager.reset();
	}
	
	@Override
	public Color getClearColor() {
		return Color.BLACK;
	}
}
