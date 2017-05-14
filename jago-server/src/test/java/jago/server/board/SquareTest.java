/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SquareTest {

    private Square square;
    private Board board;

    @Before
    public void setUp() {
        board = mock(Board.class);
        square = new Square(board, 2, 3);
    }

    /**
     * Test of getXY method, of class Square.
     */
    @Test
    public void testGetXY() {
        int xy[];
        xy = square.getXY();
        assertEquals(xy[0], 2);
        assertEquals(xy[1], 3);
    }

    /**
     * Test of addNeighbor method, of class Square.
     */
    @Test
    public void testAddNeighbor() {
        square.addNeighbor(mock(Square.class));
        assertEquals(1, square.neighbourSquare.size());
    }

    /**
     * Test of resetCheck method, of class Square.
     */
    @Test
    public void testResetCheck() {
        square.resetCheck();
        verify(board, times(1)).resetCheck();
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree() {
        assertEquals(1, square.isFree(StoneColor.BLACK));
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut() {
        square.myStone = new Stone(StoneColor.BLACK, square);
        assertFalse(square.put(StoneColor.WHITE));
    }

    /**
     * Test of addMe method, of class Square.
     */
    @Test
    public void testAddMe() {
        square.myStone = new Stone(StoneColor.BLACK, square);
        Square mock = new Square(board, 0, 0);
        square.addMe(mock);
        assertEquals(1, square.myStone.Group.size());
    }

    /**
     * Test of die method, of class Square.
     */
    @Test
    public void testDie() {
        square.die();
    }

}
