package jago.server.models;

import jago.server.board.BoardSize;
import java.net.*;
import java.io.*;

public class Server extends Thread {

	private ServerSocket server;
	private Socket client;
	private HumanPlayer waiter[];

	@Override
	public void run() {
		listenSocket();
	}

	Server() {
		waiter = new HumanPlayer[3];
		waiter[0] = null;
		waiter[1] = null;
		waiter[2] = null;
		int port = 8888;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Exception occured while creating server socket");
			System.exit(-1);
		}
	}

	/**
	 * Listen and when somebody connect read with another human or bot and size
	 * of wanted board
	 */
	public void listenSocket() {
		try {
			while (true) {
				client = server.accept();
				HumanPlayer player = new HumanPlayer(client);
				String line = player.getLine();
				String[] com = line.split(" ");
				int whatSize = Integer.parseInt(com[1]);
				System.out.println(com[0]);
				if (com[0].equals("h")) {
					if (waiter[BoardSize.indexBoardSize(whatSize)] == null) {
						waiter[BoardSize.indexBoardSize(whatSize)] = player;
					} else {
						Game newGame = new Game(waiter[BoardSize.indexBoardSize(whatSize)], player,
								BoardSize.getBoardSize(whatSize));
						newGame.start();
						waiter[BoardSize.indexBoardSize(whatSize)] = null;
					}
				} else {
					Game newGame = new Game(player, new BotPlayer(whatSize), BoardSize.getBoardSize(whatSize));
					newGame.start();
				}
			}
		} catch (IOException e) {
			System.out.println("Could not accept client");
			System.exit(-1);
		} finally {
			cleanup();
		}

	}

	/**
	 * Cleanup after listening
	 */
	protected void cleanup() {
		try {
			client.close();
			server.close();
			for (int i = 0; i < 3; i++) {
				if (waiter[i] != null)
					waiter[i].cleanup();
			}
		} catch (IOException e) {
			System.out.println("Could not close");
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

}
