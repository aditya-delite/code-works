package com.aditya.player.impl;

import java.util.List;

import com.aditya.ResponseEnum.ResponseEnum;
import com.aditya.battleship.impl.Battleship;
import com.aditya.game.Game;
import com.aditya.game.utils.BattleshipConstants;
import com.aditya.model.Location;
import com.aditya.player.IPlayer;

public class Player implements IPlayer {

	/*
	 * array of battlearea
	 */
	private int[][] battleArea;
	/*
	 * list of battleship
	 */
	private List<Battleship> shipList;
	/*
	 * player name
	 */
	private String name;

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<Location> targetLocation;

	public List<Location> getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(List<Location> targetLocation) {
		this.targetLocation = targetLocation;
	}

	public int[][] getBattleArea() {
		return battleArea;
	}

	public void setBattleArea(int[][] battleArea) {
		this.battleArea = battleArea;
	}

	public List<Battleship> getShipList() {
		return shipList;
	}

	public void setShipList(List<Battleship> shipList) {
		this.shipList = shipList;
	}

	/**
	 * This method will shoot the missile on the opponent
	 */
	@Override
	public boolean shootOpponent(Player opponentPlayer) {
		ResponseEnum missileFiringResponse = null;
		for (int i = 0; i < this.targetLocation.size(); i++) {
			Location target = this.targetLocation.get(i);
			if (Game.battleareaWidth <= target.getPositionX() && Game.battleareaHeight <= target.getPositionY()) {
				System.out.println(BattleshipConstants.ILLEGEAL_TERRITORY);
				return false;
			} else {

				for (Battleship opponentShip : opponentPlayer.getShipList()) {
					missileFiringResponse = opponentShip.isShipTargeted(target);
					this.targetLocation.remove(i);
					if (missileFiringResponse == ResponseEnum.DESTROYED
							|| missileFiringResponse == ResponseEnum.PARTIALLY_DESTROYED) {
						System.out.println(this.name + BattleshipConstants.MISSILE_FIRED + target.getCoordinates()
								+ BattleshipConstants.MISSILE_HIT);
						if (opponentShip.getLiveCell() == 0) {
							opponentPlayer.getShipList().remove(opponentShip);
						}
						return true;
					} else if (missileFiringResponse == ResponseEnum.MISS) {
						System.out.println(this.name + BattleshipConstants.MISSILE_FIRED + target.getCoordinates()
								+ BattleshipConstants.MISSILE_MISSED);
						return false;
					}
				}

				System.out.println(this.getName() + BattleshipConstants.BATTLE_WON);
				System.exit(0);
			}
		}
		System.out.println(this.getName() + BattleshipConstants.NO_MISSILE_LEFT);
		return false;

	}

}
