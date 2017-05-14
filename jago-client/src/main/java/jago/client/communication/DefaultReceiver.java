package jago.client.communication;

import jago.client.board.Board;
import jago.client.board.Stone;
import jago.client.mediator.Mediator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultReceiver extends Thread implements Receiver {

    Board myBoard;
    Socket server;
    private BufferedReader in = null;
    private final Mediator mediator;

    public DefaultReceiver(Socket server, Board board, Mediator mediator) {
        this.mediator = mediator;
        myBoard = board;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(
                    server.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(DefaultReceiver.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

    }

    @Override
    public void run() {
        receiveLoop();
    }

    @Override
    public void cleanup() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Could not close");
            System.exit(-1);
        }
    }

    @Override 
    public void receiveLoop() { 
        String line;
        boolean check = true;
        Stone state;
        try {
            while (check) {
                line = in.readLine();

                if (line == null) {
                    continue;
                }
                System.out.println(line);
                String[] com = line.split(" ");
                switch (com[0]) {
                    case "p":
                        state = Stone.NULL;
                        int size = Integer.parseInt(com[1]);
                        for (int i = 2; (i < size * 3 + 2); i = i + 3) {
                            switch (Integer.parseInt(com[i + 2])) {
                                case 0:
                                    state = Stone.WHITE;
                                    break;
                                case 1:
                                    state = Stone.BLACK;
                                    break;
                                case 2:
                                    state = Stone.NULL;
                                    break;
                            }
                            myBoard.makeMove(state, Integer.parseInt(com[i]), Integer.parseInt(com[i + 1]));
                        }
                        if(size>4)
                            myBoard.requestRepaint();
                        break;
                    case "y":
                        myBoard.setTurn(true);
                        break;
                    case "f":
                        check = false;
                        myBoard.resetBoard();
                        mediator.endGame(line.substring(2));
                        break;
                    case "r":
                        break;
                    case "s":
                        myBoard.setTerritoryPhase();
                        break;
                    default:
                        break;

                }
            }
        } catch (IOException e) {
            System.out.println("could not create streams");
            cleanup();
            System.exit(1);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            cleanup();
        } finally {
            super.finalize();
        }
    }

    // for test only!
    protected DefaultReceiver(BufferedReader in, Board board, Mediator mediator) {
        this.in = in;
        this.myBoard = board;
        this.mediator = mediator;
    }

}
