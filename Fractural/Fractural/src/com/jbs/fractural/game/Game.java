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

	private EntityManager entityManager;
	private GameGUI gui;
	private LinkedList<Lane> lanes;
	private int time, debrisSpawnChance;
	
	public Game() {
		createLevel();
		this.gui = new GameGUI(this);
	}

	private void createLevel() {
		time = 0;
		debrisSpawnChance = 40;

		lanes = new LinkedList<Lane>();
		createLanes(5);

		entityManager = new EntityManager();
		entityManager.setPlayer(new Player(this, lanes));
		for (int i = 0; i < 100; i++) {
			if (Constants.rand.nextInt(10) == 0) {
				Star s = new Star(Constants.rand.nextInt((int) Main.screenSize.x), Constants.rand.nextInt((int) Main.screenSize.y), Constants.rand.nextInt(16) + 8);
				boolean collides = false;
				for (Lane l : lanes) {
					if (Collision.isColliding(s.getBoundingBox(), l.getBoundingBox()))
						collides = true;
				}
				if (!collides)
					entityManager.add(s);
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Lane l : lanes)
			l.render(batch);
		entityManager.render(batch);
	}
	
	@Override
	public void renderText(SpriteBatch textBatch) {
		Main.font.draw(textBatch, "Lanes: " + lanes.size(), Main.centered.x - (32 * 2) - 16, Main.screenSize.y - 32);
		gui.render(textBatch);
	}

	@Override
	public void tick() {
		gui.tick();
		entityManager.tick();
		Main.camera.position.set(entityManager.getPlayer().getCenter().x, Main.centered.y, 0);

		spawnDebris();

		if (Constants.rand.nextInt(100) == 0)
			for (Lane l : lanes)
				l.addFill();

		if (Constants.rand.nextInt(25) == 0) {
			Star s = new Star(Constants.rand.nextInt((int) (((int) lanes.get(lanes.size() - 1).getX()) + Main.screenSize.x)) + 1, Main.screenSize.y, Constants.rand.nextInt(16) + 8);
			boolean collides = false;
			for (Lane l : lanes)
				if (Collision.isColliding(s.getBoundingBox(), l.getBoundingBox()))
					collides = true;
			if (!collides)
				entityManager.add(s);
		}

		if(!GameGUI.blockGameInput)
			gameInput();
		else 
			GameGUI.blockGameInput ^= true;
		
		time++;
	}

	private void gameInput() {
		if (Gdx.input.justTouched() && lanes.size() > 1)
			entityManager.getPlayer().moveToLane(InputProxy.getTouchSide(entityManager.getPlayer().getX()) ? entityManager.getPlayer().getLeftLane() : entityManager.getPlayer().getRightLane());
	}
	
	private void spawnDebris() {
		if (time % (Constants.rand.nextInt(1000) + 1) == 0) {
			if (debrisSpawnChance >= 2)
				debrisSpawnChance--;
			if (Debris.moveSpeed <= 10)
				Debris.moveSpeed += 0.2f;
		}
		if (Constants.rand.nextInt(debrisSpawnChance) == 0) {
			int lane = Constants.rand.nextInt(lanes.size());
			int rLane = lane - 1;
			int lLane = lane + 1;

			boolean canSpawnR = false, canSpawnL = false;

			if (rLane >= 0) {
				Lane l = lanes.get(rLane);
				if (l.getDebris().isEmpty()) {
					canSpawnR = true;
				} else {
					if (l.getDebris().get(l.getDebris().size() - 1).getY() <= Main.screenSize.y - 400)
						canSpawnR = true;
					else {
						canSpawnR = false;
					}
				}
			} else {
				canSpawnR = true;
			}

			if (lLane <= lanes.size() - 1) {
				Lane l = lanes.get(lLane);
				if (l.getDebris().isEmpty()) {
					canSpawnL = true;
				} else {
					if (l.getDebris().get(l.getDebris().size() - 1).getY() <= Main.screenSize.y - 400)
						canSpawnL = true;
					else {
						canSpawnL = false;
					}
				}
			} else {
				canSpawnL = true;
			}

			if (canSpawnR || canSpawnL)
				lanes.get(lane).addDebris();
		}
	}

	public void defractalize() {
		if (lanes.size() >= 2) {
			lanes.remove(lanes.size() - 1);
		}
	}

	private void createLanes(int numLanes) {
		for (int i = lanes.size(); i < numLanes; i++) 
			lanes.add(new Lane(i));
	}

	public void reset() {
		entityManager.reset();
	}

	@Override
	public Color getClearColor() {
		return Color.BLACK;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
