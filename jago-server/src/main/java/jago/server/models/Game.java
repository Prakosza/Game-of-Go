package jago.server.models;

import jago.server.board.BoardSize;
import jago.server.board.Mediator;
import jago.server.board.StoneColor;
import java.util.LinkedList;

public class Game extends Thread {

	Player player[];
	Mediator myMediator;
	private int turn;
	private boolean check;

	Game(Player blackPlayer, Player whitePlayer, BoardSize boardSize) {
		player = new Player[2];
		myMediator = new Mediator(boardSize, this);
		player[0] = whitePlayer;
		player[1] = blackPlayer;
		setGame(player);
	}

	/**
	 * set Game object for Game
	 */
	private void setGame(Player[] player) {
		player[0].setGame(this);
		player[1].setGame(this);
	}

	/**
	 * Run object
	 */
	@Override
	public void run() {
		turn = 1;
		check = true;
		while (check) {
			turn = myMediator.getTurn();
			player[turn].makeMove();
		}
	}

	/**
	 * change state and place stones from new stones
	 */
	public void changeState(LinkedList<int[]> newStones) {
		player[0].changeState(newStones);
		player[1].changeState(newStones);
	}

	/**
	 * just change state
	 */
	public void changeState() {
		player[0].changeState();
		player[1].changeState();
	}

	/**
	 * get Colour from mediator
	 */
	StoneColor getColor(int x, int y) {
		return myMediator.getColor(x, y);
	}

	/**
	 * Make territories again by computer
	 */
	void remadeTerritories() {
		LinkedList<int[]> newStones = myMediator.remadeTerritories();
		player[0].placeStone(newStones);
		player[1].placeStone(newStones);
	}

	/**
	 * Make move
	 */
	void move(int x, int y) {
		if (myMediator.move(StoneColor.getColor(turn), x, y)) {
			player[0].placeStone(myMediator.getNewStones());
			player[1].placeStone(myMediator.getNewStones());
		}
	}

	/**
	 * Make pass
	 */
	void pass() {
		myMediator.pass();
	}

	/**
	 * Make pass wait not not this do forfeit, close one, you woulnd't know
	 * otherwise
	 */
	void forfeit() {
		check = myMediator.gotForfeit();
		if (!check) {
			player[0].forfeit(myMediator.getScore() * (-1));
			player[1].forfeit(myMediator.getScore());
		}
	}

	/**
	 * This do rematch, gosh good thing you have me
	 */
	void rematch() {
		myMediator.setGame();
	}

	/**
	 * Report error
	 */
	void error() {
		player[0].forfeit("These pretzels are making me thirsty");
		player[1].forfeit("These pretzels are making me thirsty");
		check = false;
	}
}
