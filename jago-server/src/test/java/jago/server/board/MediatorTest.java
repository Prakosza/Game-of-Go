/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import jago.server.models.Game;

/**
 *
 * @author prako
 */
public class MediatorTest {

	Mediator mediator;

	@Before
	public void setUp() {
		Game game = mock(Game.class);
		mediator = new Mediator(BoardSize.LARGE, game);
	}

	/**
	 * Test of move method, of class Mediator.
	 */
	@Test
	public void testmove() {
		int a = mediator.getTurn();
		mediator.pass();
		int b = mediator.getTurn();
		assertTrue(a != b);
	}

	/**
	 * Test of getTurn method, of class Mediator.
	 */
	@Test
	public void testgetState() {
		assertTrue(mediator.move(StoneColor.BLACK, 1, 1));
		assertTrue(mediator.move(StoneColor.WHITE, 0, 0));
	}

	/**
	 * Test of getNewStones method, of class Mediator.
	 */
	@Test
	public void testgetNewStones() {
		assertTrue(mediator.move(StoneColor.BLACK, 1, 1));
		LinkedList<int[]> result = mediator.getNewStones();
		assertTrue(result.get(0)[0] == 1 && result.get(0)[1] == 1);
	}

	/**
	 * Test of move pass, of class Mediator.
	 */
	@Test
	public void testpass() {
		mediator.pass();
		mediator.pass();
		mediator.pass();
		int a = mediator.getTurn();
		assertTrue(mediator.move(StoneColor.WHITE, 0, 0));
		int b = mediator.getTurn();
		assertTrue(a == b);
	}

	/**
	 * Test of move method, of class Mediator.
	 */
	@Test
	public void testgotForfeit() {
		assertTrue(!mediator.gotForfeit());
	}

	/**
	 * Test of move method, of class Mediator.
	 */
	@Test
	public void testsetGame() {
		mediator.setGame();
		assertFalse(mediator.territory);
	}

	/**
	 * Test of move method, of class Mediator.
	 */
	@Test
	public void testgetColor() {
		assertTrue(mediator.move(StoneColor.WHITE, 0, 1));
		assertTrue(mediator.getColor(0, 1) == StoneColor.WHITE);
	}

}
