/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import java.util.LinkedList;

public class TerritoryState implements State {

	Board myBoard;
	int forfeitCount;
	int turn;

	TerritoryState(Board myBoard) {
		this.myBoard = myBoard;
		turn = 1;
		forfeitCount = 0;
	}

	/**
	 * State is which player turn it is
	 */
	@Override
	public int getTurn() {
		return turn;
	}

	/**
	 * Make move
	 */
	@Override
	public boolean move(StoneColor color, int i, int i0) {
		myBoard.putOwner(color, i, i0);
		forfeitCount = 0;
		return true;
	}

	/**
	 * get Deads
	 */
	@Override
	public LinkedList<Square> getDeads() {
		return null;
	}

	/**
	 * Make send
	 */
	@Override
	public void pass() {
		forfeitCount = 0;
	}

	/**
	 * Receive accept
	 */
	@Override
	public boolean gotForfeit() {
		forfeitCount++;
		if (forfeitCount == 2)
			return false;
		turn = 1 - turn;
		return true;
	}

	/**
	 * Return new stones
	 */
	@Override
	public LinkedList<int[]> getNewStones() {
		LinkedList<int[]> returns = new LinkedList<int[]>();
		if (myBoard.newStone != null)
			returns.add(myBoard.newStone.getXY());
		return returns;
	}

	/**
	 * get colour of owner/square
	 */
	@Override
	public StoneColor getColor(int x, int y) {
		Square stone = myBoard.getSquare(x, y);
		return stone.owner;
	}

	/**
	 * return score
	 */
	@Override
	public int getScore() {
		return myBoard.getScore();
	}

}
