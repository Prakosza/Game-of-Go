package jago.server.board;

public enum BoardSize {
	SMALL(9), MEDIUM(13), LARGE(19);

	private final int size;

	BoardSize(int size) {
		this.size = size;
	}

	/**
	 * Return number of size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Return colour of given size
	 */
	public static BoardSize getBoardSize(int size) {
		switch (size) {
		case 9:
			return BoardSize.SMALL;
		case 13:
			return BoardSize.MEDIUM;
		case 19:
			return BoardSize.LARGE;
		default:
			return BoardSize.MEDIUM;
		}
	}

	/**
	 * index for all sort of array which depends on size of board
	 */
	public static int indexBoardSize(int size) {
		switch (size) {
		case 9:
			return 0;
		case 13:
			return 1;
		case 19:
			return 2;
		default:
			return 1;
		}
	}
}
