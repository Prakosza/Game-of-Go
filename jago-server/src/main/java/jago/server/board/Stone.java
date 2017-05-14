package jago.server.board;

import java.util.LinkedList;

public class Stone {

	protected LinkedList<Square> Group; // List of my friends
	protected StoneColor myColor;
	protected Square myPlace;
	protected Stone myStone;

	/**
	 * Test if new stone can be put there and if not then not
	 */
	protected Stone(StoneColor color, Square place) {
		myColor = color;
		Group = new LinkedList<>();
		myPlace = place;
		int result;
		boolean killer = false;
		for (Square neighbour : myPlace.neighbourSquare) { // if I kill any of my
															// neighbour
			if (neighbour.myStone != null) {
				result = 0;
				if (neighbour.myStone.myColor != myColor) {
					myPlace.check = true;
					result = neighbour.isFree(StoneColor.turnAround(myColor));
					myPlace.resetCheck();
					if (result == 0) {
						neighbour.die();
						killer = true;
					}
				} else {
					Group.add(neighbour);
				}
			}
		}
		if (!killer) { // if I haven't killed anybody does that mean that I am
						// the one who need to go away for good
			result = 0;
			for (Square neighbor : myPlace.neighbourSquare) {
				myPlace.check = true;
				result = result + neighbor.isFree(myColor);
				myPlace.resetCheck();
			}
			if (result == 0) {
				myPlace.pass = false;
				return;
			}
		}
		for (Square friend : Group) {
			friend.addMe(myPlace);
		}
	}

	/**
	 * Yes just die
	 */
	void die() {
		myPlace.myStone = null;
		Group.forEach((friend) -> {
			friend.die();
		});
	}
}
