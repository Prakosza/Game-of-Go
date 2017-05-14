/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.models;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author prako
 */
public class HumanPlayerTest {

	private BufferedReader in;
	private PrintWriter out;
	private Game myGame;
	private HumanPlayer player;

	@Before
	public void setUp() {
		in = mock(BufferedReader.class);
		out = mock(PrintWriter.class);
		Socket socket = mock(Socket.class);
		myGame = mock(Game.class);
		try {
			when(in.readLine()).thenReturn("p 0 1").thenReturn("t").thenReturn("f").thenReturn("r");
		} catch (IOException ex) {

		}
		player = new HumanPlayer(in, out);
		player.setGame(myGame);
		player.mySocket = socket;
	}

	/**
	 * Test of makeMove method, of class HumanPlayer.
	 */
	@Test
	public void testmakeMove() {
		player.makeMove();
		player.makeMove();
		player.makeMove();
		player.makeMove();
		verify(myGame, times(1)).move(0, 1);
		verify(myGame, times(1)).pass();
		verify(myGame, times(1)).forfeit();
		verify(myGame, times(1)).rematch();
	}

	/**
	 * Test of placeStone method, of class HumanPlayer.
	 */
	@Test
	public void testplaceStone() {
		player.placeStone(1, 1, 1);
		verify(out, times(1)).println("p 1 1 1");
	}

	/**
	 * Test of forfeit method, of class HumanPlayer.
	 */
	@Test
	public void testforfeit() {
		player.forfeit("aa");
		verify(out, times(1)).println("f aa");
		player.forfeit(-10);
		verify(out, times(1)).println("f Wow you have only lost by " + 10 + " points...");
		player.forfeit(10);
		verify(out, times(1)).println("f Congrats " + 10 + " points is consider a lot... in Chine");
		player.forfeit(-999);
		verify(out, times(1)).println("f Could you be bigger losser?");
		player.forfeit(999);
		verify(out, times(1)).println("f You won by fault, the only way you know how");
	}

	/**
	 * Test of placeStoneWithList method, of class HumanPlayer.
	 */
	@Test
	public void testplaceStoneWithList() {
		player.placeStone(new LinkedList<int[]>());
		verify(out, times(1)).println("p 0");
	}

	/**
	 * Test of getLine method, of class HumanPlayer.
	 */
	@Test
	public void testgetLine() {
		assertTrue(player.getLine().equals("p 0 1"));
	}

	/**
	 * Test of changeState method, of class HumanPlayer.
	 */
	@Test
	public void testchangeState() {
		player.changeState();
		verify(out, times(1)).println("s");
	}
}
