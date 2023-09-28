package test;

import game.model.Game4GewinnModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org. junit.jupiter.api.Assertions.assertTrue;
import static org. junit.jupiter.api.Assertions.assertFalse;

/**
 * This is a class that contains all tests from Game4GewinnModel.
 * All tests runs to check if error happen when running functions.
 */
public class Game4GewinnModelTest {
    /**
     * Test that prove that game start with empty board.
     */
    @Test
    void ShouldStartWithEmptyBoard(){
        var game = new Game4GewinnModel(800, 800);
        int[] checkBoard = game.getBoard();
        for(int i = 0; i < 42; i++){
            assertEquals(0, checkBoard[i]);
        }
    }

    /**
     * Test to check if move is saved in board after inserted.
     */
    @Test
    void BoardShouldContainInsertedMove(){
        var game = new Game4GewinnModel(800, 800);
        game.insertMove(0);
        assertEquals(1, game.getSlot(35));
    }

    /**
     * Test to check if turn change after insert a move.
     */
    @Test
    void ShouldChangeTurnAfterInsertMove(){
        var game = new Game4GewinnModel(800, 800);
        game.insertMove(0);
        assertEquals(2, game.getPlayerTurn());
    }

    /**
     * Test to check changeTurn() method.
     */
    @Test
    void TurnShouldChangeAfterChangeTurn(){
        var game = new Game4GewinnModel(800, 800);
        game.setPlayerTurn(2);
        assertEquals(2, game.getPlayerTurn());
    }

    /**
     * Test to check that the game is not end at begin of the game.
     */
    @Test
    void GameShouldStartWithGameIsNotOver(){
        var game = new Game4GewinnModel(800, 800);
        assertEquals(-1, game.getWinner());
    }

    /**
     * Test to check that all the column is empty at begin of the game.
     */
    @Test
    void GameShouldStartWithoutFullColumn(){
        var game = new Game4GewinnModel(800, 800);
        for(int i = 0; i < 7; i++){
            assertFalse(game.isFull(i));
        }
    }

    /**
     * Test to check that player could only insert to column 0 until 6.
     */
    @Test
    void ShouldOnlyInsertColumn_0_until_6(){
        var game = new Game4GewinnModel(800, 800);
        assertTrue(game.insertMove(4));
        assertFalse(game.insertMove(9));
    }

    /**
     * Test to check that the board will not change if player insert to full column.
     */
    @Test
    void BoardShouldNotChange_AfterInsertMoveIntoFuLLColumn(){
        var game = new Game4GewinnModel(800, 800);
        game.setPlayerTurn(1);
        for(int i = 0; i < 8; i++){
            game.insertMove(0);
        }
        assertEquals(1, game.getSlot(35));
        assertEquals(2, game.getSlot(28));
        assertEquals(1, game.getSlot(21));
        assertEquals(2, game.getSlot(14));
        assertEquals(1, game.getSlot(7));
        assertEquals(2, game.getSlot(0));
    }

    /**
     * Test to check that turn will not change if player insert to full column.
     */
    @Test
    void TurnShouldNotChange_AfterInsertMoveIntoFuLLColumn(){
        var game = new Game4GewinnModel(800, 800);
        game.setPlayerTurn(1);
        for(int i = 0; i < 8; i++){
            game.insertMove(0);
        }
        assertEquals(1, game.getPlayerTurn());
    }

    /**
     * Test to check that turn will not change if player insert to column that outside range (0-6).
     */
    @Test
    void TurnShouldNotChange_AfterInsertOutsideRangeColumn(){
        var game = new Game4GewinnModel(800, 800);
        game.setPlayerTurn(1);
        game.insertMove(10);
        assertEquals(1, game.getPlayerTurn());
    }

    /**
     * Test to check  if the game return the winner if the player wins.
     */
    @Test
    void GameShouldReturnWinner(){
        var game = new Game4GewinnModel(800, 800);
        game.setPlayerTurn(1);
        game.insertMove(0);
        game.insertMove(1);
        game.insertMove(0);
        game.insertMove(1);
        game.insertMove(0);
        game.insertMove(1);
        game.insertMove(0);
        assertEquals(1, game.getWinner());
    }

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
