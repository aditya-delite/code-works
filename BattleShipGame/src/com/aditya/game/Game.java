package com.aditya.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aditya.battleship.impl.Battleship;
import com.aditya.game.utils.BattleshipConstants;
import com.aditya.game.utils.Utility;
import com.aditya.player.impl.Player;

/**
 * This is the main class to start the battleship game
 * 
 * @author aditya-gu Sample input
 *
 */
public class Game {
	public static int[][] battleArea;
	public static int battleareaHeight;
	public static int battleareaWidth;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		System.out.println(BattleshipConstants.BATTLE_AREA);
		Scanner sc = new Scanner(System.in);
		battleareaWidth = sc.nextInt();
		String battleHieght = sc.next();
		battleareaHeight = Utility.calculateHeight(battleHieght);
		if (battleareaHeight == -1) {
			throw new Exception(BattleshipConstants.BATTLEAREA_HEIGHT_MSG);
		}

		battleArea = new int[battleareaWidth][battleareaHeight];
		System.out.println(BattleshipConstants.NO_OF_BATTLESHIPS);

		Map<String, Player> playersMap = populatePlayers(sc);
		if (playersMap == null && playersMap.isEmpty()) {
			System.out.println(BattleshipConstants.DATA_CORRUPT_MSG);
			return;
		} else {
			Player player1 = null, player2 = null;
			if (playersMap.containsKey(BattleshipConstants.PLAYER1))
				player1 = playersMap.get(BattleshipConstants.PLAYER1);
			if (playersMap.containsKey(BattleshipConstants.PLAYER2))
				player2 = playersMap.get(BattleshipConstants.PLAYER2);
			startGame(player1, player2);
		}
		sc.next();
		sc.close();
	}

	/**
	 * This method will instantiate the Players
	 * 
	 * @param sc
	 * @return Map<String, Player> playersMap
	 * @throws Exception
	 */
	private static Map<String, Player> populatePlayers(Scanner sc) throws Exception {
		Map<String, Player> playersMap = new HashMap<>();
		Player player1 = new Player("Player-1");
		Player player2 = new Player("Player-2");
		List<Battleship> player1BattlesipList = new ArrayList<>();
		List<Battleship> player2BattleshipList = new ArrayList<>();
		int numOfShips = sc.nextInt();
		// collecting ship info and populating list of ships
		for (int i = 0; i < numOfShips; i++) {
			System.out.println(BattleshipConstants.SHIP_DETAILS + (i + 1));
			List<Battleship> battleShipList = populateShipData(sc);
			if (battleShipList != null && battleShipList.size() > 0) {
				player1BattlesipList.add(battleShipList.get(0));
				player2BattleshipList.add(battleShipList.get(1));
			}
			player1.setShipList(player1BattlesipList);
			player1.setBattleArea(battleArea);

			player2.setShipList(player2BattleshipList);
			player2.setBattleArea(battleArea);
		}
		sc.nextLine();
		player1.setTargetLocation(Utility.missileTargetsInfo(sc));
		player2.setTargetLocation(Utility.missileTargetsInfo(sc));
		playersMap.put(BattleshipConstants.PLAYER1, player1);
		playersMap.put(BattleshipConstants.PLAYER2, player2);
		return playersMap;
	}

	/**
	 * This method will start the battleship game
	 * 
	 * @param player1
	 * @param player2
	 */
	private static void startGame(Player player1, Player player2) {
		// Assuming player 1 to start game
		while (player1.getTargetLocation().size() >= 0 || player2.getTargetLocation().size() >= 0) {
			boolean resultplayer1 = player1.shootOpponent(player2);
			while (resultplayer1)
				resultplayer1 = player1.shootOpponent(player2);
			boolean resultplayer2 = player2.shootOpponent(player1);
			while (resultplayer2)
				resultplayer2 = player2.shootOpponent(player1);
			if (player1.getTargetLocation().isEmpty() && player2.getTargetLocation().isEmpty()) {
				System.out.println(BattleshipConstants.PEACE_DECLARED);
				break;
			}

		}
	}

	/**
	 * This method populates the battleship objects
	 * 
	 * @param sc
	 * @return List<Battleship> shipList
	 * @throws Exception
	 */
	public static List<Battleship> populateShipData(Scanner sc) throws Exception {
		List<Battleship> shipList = new ArrayList<>();
		String type = "";
		int width = 0;
		int height = 0;
		type = sc.next();

		width = sc.nextInt();

		height = sc.nextInt();
		for (int i = 0; i <= 1; i++) {
			Battleship bShip = new Battleship();
			bShip.setType(type);
			bShip.setWidth(width);
			bShip.setHeight(height);
			bShip.setLiveCell(width * height);
			Map<String, Integer> positionMap = Utility.getPosition(sc.next());
			if (positionMap.containsKey(BattleshipConstants.POSX))
				bShip.setPositionX(positionMap.get(BattleshipConstants.POSX));
			if (positionMap.containsKey(BattleshipConstants.POSY))
				bShip.setPositionY(positionMap.get(BattleshipConstants.POSY));
			shipList.add(bShip);
		}
		return shipList;
	}

}
