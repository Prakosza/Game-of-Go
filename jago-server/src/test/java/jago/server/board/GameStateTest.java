/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author prako
 */
public class GameStateTest {

	Board myBoard;
	GameState myState;

	@Before
	public void setUp() {
		myBoard = new Board(BoardSize.LARGE);
		myState = new GameState(myBoard);
	}

	/**
	 * Test of gotForfeit method, of class GameState.
	 */
	@Test
	public void testgotForfeit() {
		assertFalse(myState.gotForfeit());
	}

	/**
	 * Test of getNewStones method, of class GameState.
	 */
	@Test
	public void testgetNewStones() {
		assertTrue(myState.getNewStones().size() == 0);
		assertTrue(myState.move(StoneColor.WHITE, 0, 1));
		assertTrue(myState.getNewStones().size() != 0);
	}

	/**
	 * Test of getColor method, of class GameState.
	 */
	@Test
	public void testgetColor() {
		assertTrue(myState.move(StoneColor.BLACK, 0, 1));
		assertTrue(myState.getColor(0, 1) == StoneColor.BLACK);
	}

	/**
	 * Test of getScore method, of class GameState.
	 */
	@Test
	public void testgetScore() {
		assertTrue(myState.move(StoneColor.BLACK, 0, 1));
		assertTrue(myState.getScore() != 0);
	}
}
