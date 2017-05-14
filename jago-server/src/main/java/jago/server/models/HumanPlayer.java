package jago.server.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class HumanPlayer implements Player {

	Socket mySocket;
	private BufferedReader in;
	private PrintWriter out;
	Game myGame;

	/**
	 * Make connection
	 */
	HumanPlayer(Socket socket) {
		mySocket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			out = new PrintWriter(mySocket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("could not create streams");
			System.exit(-1);
		}
	}

	/**
	 * Only for test
	 */
	HumanPlayer(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;

	}

	/**
	 * Make move and report that to game by method in game.
	 */
	@Override
	public void makeMove() {
		String line;
		int x, y;
		try {
			do {
				out.println("y");
				line = in.readLine();
			} while (line == null);
			String[] com = line.split(" ");
			switch (com[0]) {
			case "p": // place stone
				x = Integer.parseInt(com[1]);
				y = Integer.parseInt(com[2]);
				myGame.move(x, y);
				break;
			case "t": // pass turn
				myGame.pass();
				break;
			case "f": // forfeit
				myGame.forfeit();
				break;
			case "r": // rematch
				myGame.rematch();
			default:
				break;
			}
		} catch (IOException e) {
			System.out.println("error");
			cleanup();
			myGame.error();
		}
	}

	/**
	 * place stone on colour and x y
	 */
	@Override
	public void placeStone(int x, int y, int state) {
		out.println("p " + x + " " + y + " " + state);
	}

	/**
	 * forfeit with text
	 */
	@Override
	public void forfeit(String message) {
		out.println("f " + message);
		cleanup();
	}

	public void cleanup() {
		try {
			in.close();
			out.close();
			mySocket.close();
		} catch (IOException e) {
		}
	}

	/**
	 * change state and place list
	 */
	@Override
	public void changeState(LinkedList<int[]> newStones) {
		out.println("s");
		String line;
		line = "p " + newStones.size();
		for (int[] stone : newStones) {
			line = line + " " + stone[0] + " " + stone[1] + " " + myGame.getColor(stone[0], stone[1]).getNumber();
		}
		out.println(line);
	}

	/**
	 * Forfeit with score
	 */
	@Override
	public void forfeit(int score) {
		if (score < 0) {
			if (score > -999)
				out.println("f Wow you have only lost by " + (score * (-1)) + " points...");
			else
				out.println("f Could you be bigger losser?");
		} else {
			if (score < 999)
				out.println("f Congrats " + score + " points is consider a lot... in Chine");
			else
				out.println("f You won by fault, the only way you know how");
		}
		cleanup();
	}

	/**
	 * place stone from list
	 */
	@Override
	public void placeStone(LinkedList<int[]> newStones) {
		String line;
		line = "p " + newStones.size();
		for (int[] stone : newStones) {
			line = line + " " + stone[0] + " " + stone[1] + " " + myGame.getColor(stone[0], stone[1]).getNumber();
		}
		out.println(line);
	}

	@Override
	public void setGame(Game game) {
		myGame = game;
	}

	/**
	 * return read line
	 */
	String getLine() {
		String line = null;
		try {
			do {
				line = in.readLine();
			} while (line == null);
		} catch (IOException e) {
			System.out.println("error");
			cleanup();
		}
		return line;
	}

	/**
	 * Say to player about change of state
	 */
	@Override
	public void changeState() {
		out.println("s");
	}

}
