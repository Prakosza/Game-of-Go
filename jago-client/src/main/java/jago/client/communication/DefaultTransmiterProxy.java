package jago.client.communication;

import jago.client.board.Board;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DefaultTransmiterProxy implements TransmiterProxy {

    private Socket server;
    private PrintWriter out;
    private Board board;

    public DefaultTransmiterProxy(Socket server, Board board,boolean bot) {
        this.server = server;
        this.board = board;
        String line;
        try {
            out = new PrintWriter(server.getOutputStream(), true);
            if(bot)
            {
                line="b ";
            }
            else
                line="h ";
            line=line+board.getBoardSize();
            out.println(line);
        } catch (IOException ex) {
            cleanup();
            Logger.getLogger(DefaultTransmiterProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void makeMove(int x, int y) {
        if (!board.isTerritoryPhase()) {
            board.setTurn(false);
        }
        out.println("p " + x + " " + y);
    }

    @Override
    public void forfeit() {
        out.println("f");
    }

    @Override
    public void pass() {
        board.setTurn(false);
        out.println("t");
    }

    @Override
    public void cleanup() {
        try {
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close");
            System.exit(-1);
        }
    }

    @Override
    public void accept() {
        board.setTurn(false);
        forfeit();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            cleanup();
        } finally {
            super.finalize();
        }
    }

    @Override
    public void rematch() {
        out.println("r");
    }

    // for tests only!
    protected DefaultTransmiterProxy(PrintWriter out, Board board) {
        this.out = out;
        this.board = board;
    }

}
