/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board(BoardSize.LARGE);
    }

    /**
     * Test of getDeads method, of class Board.
     */
    @Test
    public void testGetDeads() {
        assertTrue(0 == board.getDeads().size());
    }

    /**
     * Test of resetCheck method, of class Board.
     */
    @Test
    public void testResetCheck() {
        board.resetCheck();
    }

    /**
     * Test of move method, of class Board.
     */
    @Test
    public void testMove() {
        //Testing of KO
        assertTrue(board.move(StoneColor.WHITE,0, 0));
        assertFalse(board.move(StoneColor.WHITE, 0, 0));
        assertTrue(board.move(StoneColor.BLACK, 1, 1));
        assertTrue(board.move(StoneColor.BLACK,2, 2));
        assertTrue(board.move(StoneColor.WHITE,2, 3));
        assertTrue(board.move(StoneColor.WHITE,1, 4));
        assertTrue(board.move(StoneColor.WHITE,0, 3));
        assertTrue(board.move(StoneColor.BLACK,0, 2));
        assertTrue(board.move(StoneColor.BLACK,1, 3));
        assertTrue(board.move(StoneColor.WHITE,1, 2));
        //testing of Deaths
        assertTrue(board.getDeads().size()>0);
        //testing of false moves
        assertFalse(board.move(StoneColor.BLACK,1, 3));
        assertTrue(board.move(StoneColor.BLACK,6, 4));
        assertTrue(board.move(StoneColor.BLACK,5, 5));
        assertTrue(board.move(StoneColor.BLACK,6, 6));
        assertTrue(board.move(StoneColor.BLACK,7, 5));
        assertFalse(board.move(StoneColor.WHITE,6,5));
    }
    /**
     * Test of putOwner method, of class Board.
     */
    @Test
    public void testputOwner()
    {
        board.putOwner(StoneColor.WHITE, 0, 0);
        assertEquals(board.newStone,board.board[0][0]);
        board.putOwner(StoneColor.WHITE, 0, 0);
        assertEquals(board.newStone,board.board[0][0]);
        board.putOwner(StoneColor.BLACK, 1, 1);
        assertEquals(board.newStone,board.board[1][1]);  
    }
    /**
     * Test of setOwners method, of class Board.
     */
     @Test
    public void testOwners()
    {
        assertTrue(board.move(StoneColor.BLACK,6, 4));
        assertTrue(board.move(StoneColor.BLACK,5, 5));
        assertTrue(board.move(StoneColor.BLACK,6, 6));
        assertTrue(board.move(StoneColor.BLACK,7, 5));
        board.setOwners();
        assertTrue(board.board[6][5].owner==StoneColor.BLACK);
    }

}
