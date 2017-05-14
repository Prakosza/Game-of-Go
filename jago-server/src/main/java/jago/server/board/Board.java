package jago.server.board;

import java.util.LinkedList;

public class Board {

    protected LinkedList<int[]> Deads;// list of dead
    Square newStone;// fresh new stone
    Square[][] board; // array of square
    private final int size;
    int[] cant; // can't-put-there-any-stone-of-selected-colour value

    public Board(BoardSize boardSize) {
        size = boardSize.getSize();
        cant = new int[3];
        resetCant();
        newStone = null;
        Deads = new LinkedList<>();
        newBoard();
    }

    /**
     * Reset of can't-put-there-any-stone-of-selected-colour value
     */
    private void resetCant() {
        cant[0] = -1;
        cant[1] = -1;
        cant[2] = -1;
    }

    /**
     * Making of the new board
     */
    private void newBoard() {
        board = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square(this, i, j);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < size - 1) {
                    board[i][j].addNeighbor(board[i + 1][j]);
                    board[i + 1][j].addNeighbor(board[i][j]);
                }
                if (j < size - 1) {
                    board[i][j].addNeighbor(board[i][j + 1]);
                    board[i][j + 1].addNeighbor(board[i][j]);
                }
            }
        }
    }

    /**
     * Get list of all dead stones
     */
    public LinkedList<int[]> getDeads() {
        return Deads;
    }

    /**
     * Reset check for every square
     */
    protected void resetCheck() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].check = false;
            }
        }

    }

    /**
     * Move stone
     */
    public boolean move(StoneColor color, int x, int y) {
        Deads.clear();
        newStone = null;
        if (color.getNumber() == cant[2] && x == cant[0] && y == cant[1]) {
            return false;
        }

        boolean returns = board[x][y].put(color);
        resetCant();
        if (returns) {
            if (Deads.size() == 1) { // If the only one stone were killed
                // and stone which did that has only one breath
                if (board[x][y].isFree(color) == 1 && board[x][y].myStone.Group.size() == 0) {
                    cant[0] = Deads.get(0)[0];// block it
                    cant[1] = Deads.get(0)[1];
                    cant[2] = 1 - color.getNumber();
                }
                resetCheck();
            }
            newStone = board[x][y];
        }
        return returns;
    }

    /**
     * Set a owner of the square
     */
    void putOwner(StoneColor color, int x, int y) {
        newStone = null;
        if (board[x][y].owner.equals(color)) { // if there is already owner set
            // on state set empty
            board[x][y].owner = StoneColor.EMPTY;
        } else {
            board[x][y].owner = color;
        }
        newStone = board[x][y];
    }

    void smallClean() {
        Deads.clear();
        newStone = null;
    }

    /**
     * Count score
     */
    int getScore() {
        int tab[] = new int[3];
        tab[0] = 7;
        tab[1] = 0;
        tab[2] = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].myStone == null) {
                    tab[board[i][j].owner.getNumber()]++;
                } else {
                    if (board[i][j].owner.getNumber() == 2) {
                        StoneColor currentColor = board[i][j].myStone.myColor;
                        tab[currentColor.getNumber()]++;
                    } else {
                        tab[board[i][j].owner.getNumber()]++;
                    }
                }
            }
        }
        return tab[1] - tab[0];

    }

    /**
     * Return square of position x,y
     */
    protected Square getSquare(int x, int y) {
        return board[x][y];
    }

    /**
     * Count owner of every square
     */
    protected void setOwners() {
        newStone = null;
        Deads.clear();
        for (int i = 0; i < size; i++) { // every stone with one breath is dead
            for (int j = 0; j < size; j++) {
                if (board[i][j].myStone != null) {
                    board[i][j].check = true;
                    if (board[i][j].isFree(board[i][j].myStone.myColor) == 1) {
                        board[i][j].owner = StoneColor.turnAround(board[i][j].myStone.myColor);
                    } else {
                        board[i][j].owner = board[i][j].myStone.myColor;
                    }
                    Deads.add(board[i][j].getXY());
                    resetCheck();
                }
            }
        }
        for (int i = 0; i < size; i++) {// if there is a path to two
            // different-coloured stones or none then
            // is neutral
            for (int j = 0; j < size; j++) {
                if (board[i][j].myStone == null) {
                    board[i][j].check = true;
                    int result = board[i][j].isNeutral();
                    if (result % 6 == 0) {
                        board[i][j].owner = StoneColor.EMPTY;
                    } else {
                        if (result % 2 == 0) {
                            board[i][j].owner = StoneColor.BLACK;
                        } else {
                            board[i][j].owner = StoneColor.WHITE;
                        }
                    }
                    resetCheck();
                    Deads.add(board[i][j].getXY());
                }
            }
        }
    }
}
