package com.jbs.fractural.game.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.fractural.GameObject;
import com.jbs.fractural.Renderable;
import com.jbs.fractural.game.entity.mob.Player;

public class EntityManager implements GameObject {
	
	private List<Entity> entities, queue;
	private Player player;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
		queue = new ArrayList<Entity>();
	}

	public void add(Entity e) {
		queue.add(e);
	}
	
	public void setPlayer(Player p) {
		this.player = p;
		this.player.create();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void tick() {
		player.tick();
		Iterator<Entity> e = entities.iterator();
		while(e.hasNext()) {
			Entity entity = e.next();
			entity.tick();
			if(!entity.isAlive()) {
				entity.destroy();
				e.remove();
			}
		}
		Iterator<Entity> q = queue.iterator();
		while(q.hasNext()) {
			Entity entity = q.next();
			entity.create();
			entities.add(entity);
			q.remove();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		player.render(batch);
		for(Entity e : entities) {
			if(e instanceof Renderable) 
				((Renderable) e).render(batch);
		}
	}
	
	public void reset() {
		for(Entity e : entities)
			e.kill();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Entity> getQueue() {
		return queue;
	}
}
