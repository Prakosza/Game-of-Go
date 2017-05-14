package jago.server.models;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotPlayer implements Player {

	private int counter;
	private final Random randomGenerator;
	boolean anotherRound;
	boolean isGame;
	int size;
	Game myGame;
	int x, y;

	public BotPlayer(int size) {
		this.counter = 0;
		anotherRound = false;
		randomGenerator = new Random();
		this.size = size;
		isGame = true;
	}

	/**
	 * Make move
	 */
	@Override
	public void makeMove() {
		try {
			if (!anotherRound) {
				Thread.sleep(400);
			}
			anotherRound = true;
		} catch (InterruptedException ex) {
			Logger.getLogger(BotPlayer.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (isGame) {
			if (counter > 100) {
				myGame.pass();
			} else {
				counter++;
				x = randomGenerator.nextInt(size);
				y = randomGenerator.nextInt(size);
				myGame.move(x, y);
			}
		} else {
			myGame.remadeTerritories();
			myGame.forfeit();
		}
	}

	/**
	 * place yourself stone
	 */
	@Override
	public void placeStone(int x, int y, int state) {
		counter = 0;
		anotherRound = false;
	}

	/**
	 * Do nothing
	 */
	@Override
	public void forfeit(String message) {

	}

	/**
	 * Change state of game
	 */
	@Override
	public void changeState(LinkedList<int[]> newStones) {
		counter = 0;
		anotherRound = false;
		isGame = false;
	}

	/**
	 * forfeit with score
	 */
	@Override
	public void forfeit(int score) {

	}

	/**
	 * Stuff
	 */
	@Override
	public void placeStone(LinkedList<int[]> newStones) {
		counter = 0;
		anotherRound = false;
	}

	/**
	 * Set game
	 */
	@Override
	public void setGame(Game game) {
		myGame = game;
	}

	/**
	 * Play like it is a game
	 */
	@Override
	public void changeState() {
		counter = 0;
		anotherRound = false;
		isGame = true;
	}

}
