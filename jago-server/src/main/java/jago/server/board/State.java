/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import java.util.LinkedList;
/**
     * State of game
     */
public interface State 
{

    public int getTurn();

    public boolean move(StoneColor color, int i, int i0);

    public LinkedList<Square> getDeads();

    public void pass();

    public boolean gotForfeit();

    public LinkedList<int[]> getNewStones();

    public StoneColor getColor(int x, int y);

    public int getScore();
    
    
}
