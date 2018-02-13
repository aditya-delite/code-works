package com.aditya.game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.aditya.game.Game;
import com.aditya.game.HeightEnum.HeightEnum;
import com.aditya.model.Location;

/**
 * This is a utility class
 * 
 * @author aditya-gu
 *
 */
public class Utility {

	/**
	 * This method converts the height into corresponding integer value
	 * 
	 * @param battleHieght
	 * @return int battleareaHeight
	 */
	public static int calculateHeight(String battleHieght) {
		int battleareaHeight = 0;
		for (HeightEnum height : HeightEnum.values()) {
			String hgt = height.toString();
			if (hgt.equals(battleHieght)) {
				battleareaHeight = height.getHeight();
				return battleareaHeight;
			}
		}
		return -1;
	}

	/**
	 * This method parses the input to get the battleship position
	 * 
	 * @param position
	 * @return Map<String, Integer> positionMap
	 * @throws Exception
	 */
	public static Map<String, Integer> getPosition(String position) throws Exception {
		Map<String, Integer> positionMap = new HashMap<>();
		// String position = sc.next();
		String posY = position.charAt(0) + "";
		int posX = position.charAt(1) - 48;
		if (posX < 0 || posX > Game.battleareaWidth) {
			throw new Exception(BattleshipConstants.WIDTH_EXCEPTION);
		}
		// bShip.setPositionX(posX);
		positionMap.put(BattleshipConstants.POSX, posX);
		int hght = calculateHeight(posY);
		if (hght == -1 || hght > Game.battleareaHeight) {
			throw new Exception(BattleshipConstants.HEIGHT_EXCEPTION);
		}
		positionMap.put(BattleshipConstants.POSY, hght);
		return positionMap;
	}

	/**
	 * This method returns a list of missile targets
	 * 
	 * @param sc
	 * @return List<Location> locationArr
	 * @throws Exception
	 */
	public static List<Location> missileTargetsInfo(Scanner sc) throws Exception {
		List<Location> locationArr = new ArrayList<>();
		System.out.println(BattleshipConstants.MISSILE_POSITIONS);
		String line = sc.nextLine();
		StringTokenizer tokens = new StringTokenizer(line, " ");
		while (tokens.hasMoreTokens()) {
			String position = tokens.nextToken();
			Map<String, Integer> posMap = getPosition(position);
			Location targetLocation = new Location();
			targetLocation.setCoordinates(position);
			if (posMap.containsKey(BattleshipConstants.POSX))
				targetLocation.setPositionX(posMap.get(BattleshipConstants.POSX));
			if (posMap.containsKey(BattleshipConstants.POSY))
				targetLocation.setPositionY(posMap.get(BattleshipConstants.POSY));
			locationArr.add(targetLocation);
		}
		return locationArr;
	}
}
