package jago.server.models;

import java.util.LinkedList;

/**
 * Player interface class
 */
interface Player {

	public void makeMove();

	public void placeStone(int x, int y, int state);

	public void forfeit(String message);

	public void forfeit(int score);

	public void changeState(LinkedList<int[]> newStones);

	public void setGame(Game game);

	public void placeStone(LinkedList<int[]> newStones);

	public void changeState();

}
