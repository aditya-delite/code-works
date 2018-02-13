package com.aditya.battleship.impl;

import java.util.ArrayList;
import java.util.List;

import com.aditya.ResponseEnum.ResponseEnum;
import com.aditya.battleship.IBattleship;
import com.aditya.game.utils.BattleshipConstants;
import com.aditya.model.Location;

public class Battleship implements IBattleship {
	/*
	 * integer representation of x coordinate
	 */
	private int positionX;
	/*
	 * integer representation of y coordinate
	 */
	private int positionY;
	/*
	 * width of battleship
	 */
	private int width;
	/*
	 * height of battleship
	 */
	private int height;
	/*
	 * type of battleship
	 */
	private String type;
	/*
	 * no of livecell in ship
	 */
	private int liveCell;
	/*
	 * flag to check if ship is partially hit
	 */
	private boolean isPartiallyHit;
	/*
	 * firing history to not consider already hit target as success
	 */
	private List<Location> fireHistory = new ArrayList<>();

	public List<Location> getFireHistory() {
		return fireHistory;
	}

	public void setFireHistory(List<Location> fireHistory) {
		this.fireHistory = fireHistory;
	}

	public int getLiveCell() {
		return liveCell;
	}

	public void setLiveCell(int liveCell) {
		this.liveCell = liveCell;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method checks if the missile hits the opponent battleship or not
	 */
	@Override
	public ResponseEnum isShipTargeted(Location position) {
		if (liveCell > 0) {
			int withinWidthLimit = this.width > 1 ? this.positionX + this.width : this.positionX;
			int withinHeightLimit = this.height > 1 ? this.positionY + this.height : this.positionY;
			if (position.getPositionX() >= this.positionX && position.getPositionX() <= withinWidthLimit
					&& position.getPositionY() >= this.positionY && position.getPositionY() <= withinHeightLimit) {
				if (BattleshipConstants.TYPE_P.equals(this.type)) {
					boolean isDestroyed = isAlreadyDestroyed(fireHistory, position);
					if (isDestroyed)
						return ResponseEnum.MISS;
					this.liveCell--;
					fireHistory.add(position);
					return ResponseEnum.DESTROYED;
				} else if (BattleshipConstants.TYPE_Q.equals(this.type)) {
					if (isPartiallyHit) {
						this.liveCell--;
						return ResponseEnum.DESTROYED;
					}
					isPartiallyHit = true;
					return ResponseEnum.PARTIALLY_DESTROYED;
				}
			}
			return ResponseEnum.MISS;
		}
		return null;
	}

	/**
	 * This method checks if location targeted by player is already destroyed or
	 * not
	 * 
	 * @param fireList
	 * @param position
	 * @return boolean
	 */
	private boolean isAlreadyDestroyed(List<Location> fireList, Location position) {
		for (Location location : fireList) {
			if ((location.getPositionX() == position.getPositionX())
					&& (location.getPositionY() == position.getPositionY())) {
				return true;
			}
		}
		return false;
	}
}
