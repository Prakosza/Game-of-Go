/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author prako
 */
public class StoneTest {

	private Stone stone;
	private Square mock;
	private Square mock2;

	@Before
	public void setUp() {
		mock = mock(Square.class);
		LinkedList<Square> list = new LinkedList<>();
		mock2 = mock(Square.class);
		mock2.neighbourSquare = new LinkedList<>();
		mock2.myStone = new Stone(StoneColor.BLACK, mock2);
		list.add(mock2);
		mock.neighbourSquare = list;
		stone = new Stone(StoneColor.WHITE, mock);
		mock.myStone = stone;
	}

	/**
	 * Test of die method, of class Stone.
	 */
	@Test
	public void testDie() {
		stone.die();
		assertNull(mock.myStone);
	}

}
