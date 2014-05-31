package com.jbs.fractural.game.entity.mob;

import java.util.LinkedList;
import java.util.List;

import com.jbs.fractural.Main;
import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.entity.Lane;

public class Player extends TexturedMob {

	private Lane currentLane, rightLane, leftLane, targetLane;
	private LinkedList<Lane> lanes;
	private float moveSpeed = 16;
	public int laneCussion = 15;
	private boolean foundLaneDirection, laneDir;

	public Player(LinkedList<Lane> lanes) {
		super(Assets.player, Main.centered.x - 49, 100, 98, 75);
		this.lanes = lanes;
	}

	@Override
	public void create() {
		targetLane = null;
		getLanes();
	}
	
	private void getLanes() {
		if (lanes.size() >= 1) {
			for (int i = 0; i < lanes.size(); i++) {
				if (Collision.isColliding(getBoundingBox(), lanes.get(i).getBoundingBox())) {
					currentLane = lanes.get(i);
					if (i - 1 >= 0) 
						rightLane = lanes.get(i - 1);
					if (i + 1 <= lanes.size() - 1) 
						leftLane = lanes.get(i + 1);
					
					break;
				}
			}
		}
	}

	@Override
	public void tick() {
		if (isDirty()) {
			getBoundingBox().set(getX(), getY(), getWidth(), getHeight());
			setDirty(false);
		}
		for(Lane l : lanes) {
			List<Debris> d = l.getDebris();
			for(Debris debris : d) {
				if(Collision.isColliding(getBoundingBox(), debris.getBoundingBox())) {
					debris.kill();
				}
			}
		}
		if (foundLaneDirection) {
			addPos(laneDir ? moveSpeed : -moveSpeed, 0);
			setDirty(true);

			if (laneDir) {
				if (getX() + getWidth() > targetLane.getX() + (targetLane.getWidth() - laneCussion)) {
					targetLane = null;
					foundLaneDirection = false;
					getLanes();
				}
			}
			if (!laneDir) {
				if (getX() < targetLane.getX() + laneCussion) {
					targetLane = null;
					foundLaneDirection = false;
					getLanes();
				}
			}
		}
		if (targetLane != null) {
			if (getX() < targetLane.getX() && !foundLaneDirection) {
				foundLaneDirection = true;
				laneDir = true;
			}
			if (getX() > targetLane.getX() && !foundLaneDirection) {
				foundLaneDirection = true;
				laneDir = false;
			}
		}
	}

	public void moveToLane(Lane l) {
		this.targetLane = l;
	}

	public Lane getCurrentLane() {
		return currentLane;
	}

	public void setCurrentLane(Lane currentLane) {
		this.currentLane = currentLane;
	}

	public Lane getRightLane() {
		return rightLane;
	}

	public void setRightLane(Lane rightLane) {
		this.rightLane = rightLane;
	}

	public Lane getLeftLane() {
		return leftLane;
	}

	public void setLeftLane(Lane leftLane) {
		this.leftLane = leftLane;
	}
}
