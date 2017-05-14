/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.models;

import jago.server.board.BoardSize;
import jago.server.board.StoneColor;
import java.util.LinkedList;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 *
 * @author prako
 */
public class GameTest {
    Game myGame;
    Player player1;
   Player player2;
   @Before
    public void setUp() {
        player1 = mock(Player.class);
        player2 = mock(Player.class);
        myGame=new Game(player1,player2,BoardSize.LARGE);
    }
    /**
     * Test of changeState method, of class GameTest.
     */
    @Test
    public void testchangeState()
    {
        myGame.changeState();
        LinkedList<int[]> list=new LinkedList<>();
        myGame.changeState(list);
        verify(player1, times(1)).changeState();
        verify(player1, times(1)).changeState(list);
        verify(player2, times(1)).changeState();
        verify(player2, times(1)).changeState(list);
    }
    /**
     * Test of getColor method, of class GameTest.
     */
    @Test
    public void testgetColor()
    {
        assertTrue(myGame.getColor(0,0)==StoneColor.EMPTY);
        
    }
    /**
     * Test of remadeTerritories method, of class GameTest.
     */
    @Test
   public void testremadeTerritories()
    {
        myGame.remadeTerritories();
        assertTrue(myGame.myMediator.getNewStones().size()>0);
    }
   /**
     * Test of move method, of class GameTest.
     */
    @Test
  public  void testmove()
    {
        myGame.move(0,0);
        assertTrue(myGame.myMediator.getNewStones().size()>0);
    }
  /**
     * Test of rest method, of class GameTest.
     */
    @Test
   public void testRest()
    {
        myGame.forfeit();
        myGame.rematch();
        myGame.error();
        verify(player1, times(1)).forfeit("These pretzels are making me thirsty");
        verify(player2, times(1)).forfeit("These pretzels are making me thirsty");
    }
    
}
