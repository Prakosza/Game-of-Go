/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.models;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author prako
 */
public class BotPlayerTest {
	private BotPlayer player;
	private Game myGame;

	@Before
	public void setUp() {
		player = new BotPlayer(13);
		myGame = mock(Game.class);
		player.setGame(myGame);
	}

	/**
	 * Test of makeMove method, of class BotPlayer.
	 */
	@Test
	public void testmakeMove() {
		player.makeMove();
		verify(myGame, times(1)).move(player.x, player.y);
		for (int i = 0; i < 100; i++) {
			player.makeMove();
		}
		player.makeMove();
		verify(myGame, times(1)).pass();
	}

	/**
	 * Test of placeStone method, of class BotPlayer.
	 */
	@Test
	public void testplaceStone() {
		player.placeStone(1, 1, 1);
		player.placeStone(new LinkedList<>());
		assertFalse(player.anotherRound);
	}

	/**
	 * Test of changeState method, of class BotPlayer.
	 */
	@Test
	public void changeState() {
		player.changeState(new LinkedList<>());
		assertFalse(player.isGame);
		player.makeMove();
		verify(myGame, times(1)).remadeTerritories();
		verify(myGame, times(1)).forfeit();
		player.changeState();
	}

}
