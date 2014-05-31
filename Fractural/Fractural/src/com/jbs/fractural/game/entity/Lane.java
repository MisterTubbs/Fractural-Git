package com.jbs.fractural.game.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbs.fractural.Main;
import com.jbs.fractural.Renderable;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.Game;
import com.jbs.fractural.game.entity.mob.Debris;
import com.jbs.fractural.game.entity.mob.LaneFill;
import com.jbs.fractural.game.entity.mob.Mob;

public class Lane extends Mob implements Renderable {

	public static int laneWidth = 120, betweenWidth = 230;
	
	private float centerX;
	private List<LaneFill> filler;
	private List<Debris> debris;
	
	public Lane(int index) {
		super(betweenWidth * index + laneWidth * (index - 1), 0, laneWidth, Main.screenSize.y);
		this.centerX = getX() + (laneWidth / 2);
		this.filler = new ArrayList<LaneFill>();
		this.debris = new ArrayList<Debris>();
	}
	
	public void addFill() {
		filler.add(new LaneFill(getX()));
	}
	
	public void addDebris() {
		debris.add(new Debris(getX()));
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.lane.getTexture(), getX(), getY(), laneWidth * Game.xScale, Main.screenSize.y);
		Iterator<LaneFill> fills = filler.iterator();
		while(fills.hasNext()) {
			LaneFill f = fills.next();
			f.tick();
			f.render(batch);
			if(!f.isAlive()) fills.remove();
		}
		Iterator<Debris> ds = debris.iterator();
		while(ds.hasNext()) {
			Debris d = ds.next();
			d.tick();
			d.render(batch);
			if(!d.isAlive()) ds.remove();
		}
	}
	
	public void fractilize() {
	}
	
	public float getCenterX() {
		return centerX;
	}
	
	public List<Debris> getDebris() {
		return debris;
	}
}
