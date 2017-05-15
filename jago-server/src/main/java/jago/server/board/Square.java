package jago.server.board;

import static jago.server.board.StoneColor.BLACK;

import java.util.LinkedList;

public class Square {

	protected LinkedList<Square> neighbourSquare;
	protected Stone myStone;
	protected boolean check;
	protected Board myBoard;
	protected int XY[];
	boolean pass;
	StoneColor owner;

	protected Square(Board board, int x, int y) {
		check = false;
		XY = new int[2];
		XY[0] = x;
		XY[1] = y;
		myBoard = board;
		neighbourSquare = new LinkedList<>();
		myStone = null;
		owner = StoneColor.EMPTY;
	}

	/**
	 * Name say it all, isn't it?
	 */
	public int[] getXY() {
		return XY;
	}

	/**
	 * Add me neighbour.
	 */
	protected void addNeighbor(Square neighbor) {
		neighbourSquare.add(neighbor);
	}

	/**
	 * Reset all check in board
	 */

	protected void resetCheck() {
		myBoard.resetCheck();
	}

	/**
	 * Count my breaths
	 */
	protected int isFree(StoneColor color) {
		check = true;
		if (myStone == null) {
			return 1;
		} else {
			if (myStone.myColor == color) {
				int result = 0;
				for (Square neighbour : neighbourSquare) {
					if (!neighbour.check) {
						result = result + neighbour.isFree(color);
					}
				}
				return result;
			} else
				return 0;
		}
	}

	/**
	 * Check if I'm neutral ,it returns number which is can be divide by 2 if
	 * there is black stone on path or dead white and 3*n when is white or dead
	 * black
	 */
	protected int isNeutral() {
		check = true;
		if (myStone != null) {
			if (myStone.myColor == BLACK) {
				if (owner.equals(StoneColor.BLACK))
					return 2;
				else
					return 3;
			} else {
				if (owner.equals(StoneColor.WHITE))
					return 3;
				else
					return 2;
			}
		} else {
			int result = 1;
			for (Square neighbour : neighbourSquare) {
				if (!neighbour.check) {
					result = result * neighbour.isNeutral();
					if (result % 6 == 0)
						return result;
				}
			}
			return result;
		}
	}

	/**
	 * Put stone
	 */
	protected boolean put(StoneColor color) {
		if (myStone == null) {
			pass = true;
			myStone = new Stone(color, this);
			if (!pass) {
				myStone = null;
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add friend to my group
	 */
	protected void addMe(Square myPlace) {
		myStone.Group.add(myPlace);
	}

	/**
	 * Just die and say all your friends to die too
	 */
	protected void die() {
		if (myStone != null) {
			myBoard.Deads.add(getXY());
			myStone.die();
		}
	}
}
