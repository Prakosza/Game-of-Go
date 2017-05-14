package jago.server.board;

public enum StoneColor {
	BLACK(1), WHITE(0), EMPTY(2);
	protected int value;

	StoneColor(int i) {
		value = i;
	}

	public int getNumber() {
		return value;
	}

	/**
	 * Change colour for opposite
	 */
	static public StoneColor turnAround(StoneColor color) {
		if (color == BLACK) {
			return WHITE;
		} else if (color == WHITE) {
			return BLACK;
		}
                else
                    return EMPTY;
	}

	/**
	 * Get colour of number
	 */
	public static StoneColor getColor(int color) {
		switch (color) {
		case 0:
			return WHITE;
		case 1:
			return BLACK;
		default:
			return EMPTY;
		}
	}
}
