package com.jbs.fractural.game.entity.mob;

import java.util.LinkedList;
import java.util.List;

import com.jbs.fractural.assets.Assets;
import com.jbs.fractural.game.Game;
import com.jbs.fractural.game.entity.Lane;

public class Player extends TexturedMob {

	private Lane currentLane, rightLane, leftLane, targetLane;
	private LinkedList<Lane> lanes;
	private Game game;
	private float moveSpeed = 20;
	private boolean foundLaneDirection, laneDir;

	public Player(Game game, LinkedList<Lane> lanes) {
		super(Assets.player, snapToGrid(lanes.get(3).getIndex() + 0.45f), 100, Lane.laneWidth - 22, Lane.laneWidth - 23);
		this.game = game;
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

		for (int i = 0; i < lanes.size(); i++) {
			List<Debris> d = lanes.get(i).getDebris();
			for (int j = 0; j < d.size(); j++) {
				if (Collision.isColliding(getBoundingBox(), d.get(j).getBoundingBox())) {
					d.get(j).kill();
					game.defractalize();
				}
			}
		}

		if (foundLaneDirection) {
			addPos(laneDir ? moveSpeed : -moveSpeed, 0);
			setDirty(true);
			
			if (laneDir && getX() > targetLane.getX()) {
				targetLane = null;
				foundLaneDirection = false;
				getLanes();
			}
			if (!laneDir && getX() - 25 < targetLane.getX()) {
				targetLane = null;
				foundLaneDirection = false;
				getLanes();
			}
		}
		
		if (targetLane != null && !foundLaneDirection) {
			if (getX() < targetLane.getX()) {
				foundLaneDirection = true;
				laneDir = true;
			}
			if (getX() > targetLane.getX()) {
				foundLaneDirection = true;
				laneDir = false;
			}
		}
	}

	private static float snapToGrid(float grid) {
		return grid * 200;
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
