package game.controller;


/**
 * The interface of a controller from the perspective of a view object.
 * All elements that are not part of this interface are invisible for the view.
 * @author Yohanes Kirana
 *
 */
public interface IGame4GewinnController {
	/**
	 * This method should be called whenever controller should decide what view should be displayed.
	 * The controller will call one of the drawX()_Methods from the view. 
	 * @return State that is displayed.
	 */
	public GameState nextFrame();
	
	/**
	 * Change the game state into start game.
	 */
	public void startGame();
	
	/**
	 * Distribute a move that a player made onto model.
	 * This method also check if the game already over.
	 */
	public void insertMove(int n);
	
	/**
	 * Distribute the condition of current board in model.
	 * @return Condition of board.
	 */
	public int checkBoard(int n);

	
	/**
	 * Get which turn of player is now making move.
	 * @return Turn of player.
	 */
	public int checkTurn();

	/**
	 * Distribute the condition of current board in model.
	 * @return Array of content of the board.
	 */
	public int[] getBoard();

	/**
	 * Start ClientServerThreadClass without starting a thread.
	 */
	public void prepareThread();

	/**
	 * Initialize new server and start thread for server.
	 * @param port port of server.
	 */
	public void newServer(int port);

	/**
	 * Send player's move into network.
	 * @param i move of player.
	 */
	public void sendMove(int i);

	/**
	 * Initialize new Client and start thread for client.
	 * @param ip ip address of client.
	 * @param port port of client that should be identical to server.
	 */
	public void newClient(String ip, int port);

	/**
	 * This method check if the game already over.
	 */
	public void getWinner();
}
