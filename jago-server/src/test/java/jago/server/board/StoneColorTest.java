/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoneColorTest {

    private StoneColor color1;
    private StoneColor color2;
     private StoneColor color3;

    @Before
    public void setUp() {
        color1 = StoneColor.BLACK;
        color2 = StoneColor.WHITE;
        color3=StoneColor.EMPTY;
    }

    /**
     * Test of getNumber method, of class StoneColor.
     */
    @Test
    public void testGetNumber() {
        assertEquals(color1.getNumber(), 1);
        assertEquals(color2.getNumber(), 0);
        assertEquals(color2, StoneColor.getColor(0));
        assertEquals(color1, StoneColor.getColor(1));
        assertEquals(color3, StoneColor.getColor(2));
    }

    /**
     * Test of turnAround method, of class StoneColor.
     */
    @Test
    public void testTurnAround() {
        assertTrue(color1 == StoneColor.turnAround(color2));
        assertTrue(color2 == StoneColor.turnAround(color1));
    }

}
