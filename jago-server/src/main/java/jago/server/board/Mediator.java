/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import jago.server.models.Game;
import java.util.LinkedList;

public class Mediator 
{
    State myState;
    int passCount;
    Board myBoard;
    Game myGame;
    boolean territory;
    
    public Mediator(BoardSize boardSize, Game aThis) {
        myBoard=new Board(boardSize);
        myState=new GameState(myBoard);
        territory=false;
        myGame=aThis;
        passCount=0;
    }
    /**
     * Return state
     */
    public int getTurn() {
        return myState.getTurn();
    }
    /**
     * Make move on current state
     */
    public boolean move(StoneColor color, int i, int i0) {
        passCount=0;
        return myState.move(color,i,i0);
    }
    /**
     * Return newStones from myState
     */
    public LinkedList<int[]> getNewStones() {
        return myState.getNewStones();
    }
    /**
     * If pass was done two times then go to territorystate
     */
    public void pass() {
        myState.pass();
        if(!territory)
        {
            passCount++;
            if(passCount==2)
            {
                myState=new TerritoryState(myBoard);
                myBoard.setOwners();
                myGame.changeState(myBoard.getDeads());
            }
        }
    }
    /**
     * Forfeit say if I should end the game  
     */
    public boolean gotForfeit() {
        return myState.gotForfeit();
    }
    /**
     * Return score
     */
    public int getScore() {
       return myState.getScore();
    }
    /**
     * Set rematch
     */
    public void setGame() {
        myState=new GameState(myBoard,(1-myState.getTurn()));
        territory=false;
        passCount=0;
        myGame.changeState();
       
    }
    /**
     * Test of changeState method, of class HumanPlayer.
     */   
    public StoneColor getColor(int x,int y) {
        return myState.getColor(x,y);
    }
    /**
     * Made territories great again (set them by computer logic-style)
     */
    public LinkedList<int[]> remadeTerritories() {
        myBoard.setOwners();
        myState.pass();
        return myBoard.getDeads();
    }

}
