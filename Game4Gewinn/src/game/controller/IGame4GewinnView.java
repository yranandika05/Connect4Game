package game.controller;

/**
 * this interface contains all necessary classes of a view to be usable by controller.
 * Please note that view class contains additional methods, which will not accessible by controller.
 * @author Yohanes Kirana
 *
 */
public interface IGame4GewinnView {
	/**
	 * Show the title screen of connect 4.
	 */
	public void drawStartGame();
	
	/**
	 * Show the game state of connect 4. 
	 * It should display the board game and the move that player made, both from server and client.
	 * It also display which turn of player is it now. 
	 */
	public void drawGame();
	
	/**
	 * Display game over screen of connect 4.
	 * The display depends on who won the game or the game ends without winner.
	 */
	public void drawGameOver();
	
	/**
	 * Get the winner from model through controller and set it in game view. 
	 * it will be used to display the game over screen.
	 * @param i Player identity, or 0 for no winner.
	 */
	public void setWinner(int i);

	/**
	 * get the current object of controller from view.
	 * @return Object of controller
	 */
	public Game4GewinnController getController();

	/**
	 * Draw notification message that client or server has closed the game.
	 * User need to close the window and start over.
	 */
	public void drawClientServerClosedMessage();


	/**
	 * Draw notification message that user inserted false port or ip address.
	 * User need to close the window and start over.
	 */
	void drawFalsePortMessage();
}
