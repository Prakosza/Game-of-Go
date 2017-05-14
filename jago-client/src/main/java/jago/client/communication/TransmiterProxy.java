/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.client.communication;

/**
 * transmits messages to server
 *
 * @author prako
 */
public interface TransmiterProxy {

    // transmit move
    void makeMove(int x, int y);

    // pass
    void pass();

    // give up
    void forfeit();

    // close streams etc.
    void cleanup();

    // accept division of territories
    void accept();

    // request rematch
    void rematch();
}
