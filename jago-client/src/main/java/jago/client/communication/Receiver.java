/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jago.client.communication;
//insanely useless
public interface Receiver {

    // read from stream and process it
    void receiveLoop();

    // close streams etc.
    void cleanup();
}
