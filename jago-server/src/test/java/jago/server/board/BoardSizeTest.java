/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.server.board;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardSizeTest {

    private BoardSize sBoardSize;
    private BoardSize mBoardSize;
    private BoardSize lBoardSize;
    
    @Before
    public void setUp() {
        sBoardSize = BoardSize.SMALL;
        mBoardSize = BoardSize.MEDIUM;
        lBoardSize = BoardSize.LARGE;
    }

    /**
     * Test of getSize method, of class BoardSize.
     */
    @Test
    public void testGetSize() {
        assertEquals(sBoardSize, BoardSize.getBoardSize(9));
        assertEquals(mBoardSize, BoardSize.getBoardSize(13));
        assertEquals(lBoardSize, BoardSize.getBoardSize(19));
        assertEquals(sBoardSize.getSize(), 9);
        assertEquals(mBoardSize.getSize(), 13);
        assertEquals(lBoardSize.getSize(), 19);
        assertTrue(0==BoardSize.indexBoardSize(9));
        assertTrue(1==BoardSize.indexBoardSize(13));
        assertTrue(2==BoardSize.indexBoardSize(19));
        
    }

}
