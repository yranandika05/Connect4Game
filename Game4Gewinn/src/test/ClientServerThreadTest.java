package test;

import game.model.ClientServerThread;

import game.model.Game4GewinnModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org. junit.jupiter.api.Assertions.assertTrue;
import static org. junit.jupiter.api.Assertions.assertFalse;

/**
 * A test class to test the connection between server and client.
 */
public class ClientServerThreadTest {

    /**
     * This test check if error happen when server and client try to connect using same port.
     */
    @Test
    void ServerAndClientShouldBeConnectedWithIdenticalPort(){
        var server = new Game4GewinnModel(800, 800);
        server.prepareThread();
        server.newServer(1234);

        var client = new Game4GewinnModel(800, 800);
        client.prepareThread();
        client.newClient("localhost", 1234);
    }
}
