/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import java.util.LinkedList;

public class GameState implements State {

	Board myBoard;
	int turn; // turn of player 0-white 1-black
	int runOff; // which one of player surrender

	GameState(Board myBoard) {
		this.myBoard = myBoard;
		turn = 1;
		runOff = -1;
	}

	GameState(Board myBoard, int state) {
		this.myBoard = myBoard;
		this.turn = state;
	}

	/**
	 * Return who turn it is
	 */
	@Override
	public int getTurn() {
		return turn;
	}

	/**
	 * Move receive from game
	 */
	@Override
	public boolean move(StoneColor color, int i, int i0) {
		boolean result;
		result = myBoard.move(color, i, i0);
		if (result) {
			this.turn = 1 - this.turn;
		}
		return result;
	}

	/**
	 * returns deads
	 */
	@Override
	public LinkedList<Square> getDeads() {
		return null;
	}

	/**
	 * Receive pass
	 */
	@Override
	public void pass() {
		turn = 1 - turn;
	}

	/**
	 * Receive forfeit
	 */
	@Override
	public boolean gotForfeit() {
		runOff = turn;
		return false;
	}

	/**
	 * Returns all deads and one new stone
	 */
	@Override
	public LinkedList<int[]> getNewStones() {
		if (myBoard.newStone != null)
			myBoard.getDeads().add(myBoard.newStone.getXY());
		return myBoard.getDeads();
	}

	/**
	 * Return colour of x,y square
	 */
	@Override
	public StoneColor getColor(int x, int y) {
		Square stone = myBoard.getSquare(x, y);
		if (stone.myStone != null)
			return stone.myStone.myColor;
		else
			return StoneColor.EMPTY;
	}

	/**
	 * Get score of game
	 */
	@Override
	public int getScore() {
		if (runOff == -1)
			return myBoard.getScore();
		else {
			if (runOff == 0)
				return 999;
			else
				return -999;
		}
	}

}
