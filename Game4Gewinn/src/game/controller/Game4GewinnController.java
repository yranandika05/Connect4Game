package game.controller;

import game.model.*;


/**
 * A Controller implementation for a GUI view. This controller creates and uses a game model from package game.model.
 * It implements a state machine to keep track of the state the view should draw next.
 * @author Yohanes Kirana
 *
 */
public class Game4GewinnController implements IGame4GewinnController{
	private Game4GewinnModel game;
	private boolean gameOver = false;
	private IGame4GewinnView view;
	private GameState state;
	private boolean falsePort = false;

	/**
	 * Creates a new controller object with the given view and size
	 * @param view The view object that should be used by the controller. Cannot be change later.
	 * @param width The width of the game field.
	 * @param height The height of the game field.
	 */
	public Game4GewinnController(IGame4GewinnView view, int width, int height) {
		this.view = view;
		this.game = new Game4GewinnModel(width, height);
		this.state = GameState.Title_Screen;
		
	}
	

	/**
	 * Calls the draw methods of view, depending on current game state.
	 * During the game, this method also check if the game is over.
	 */
	public GameState nextFrame() {
		switch(state) {
			case Title_Screen -> {
				view.drawStartGame();
			}
			case Game -> {
				if(gameOver) {
					state = GameState.Game_Over;
				}else {					
					view.drawGame();
				}
				if(clientServerCloseNotification()){
					view.drawClientServerClosedMessage();
				}
				if(falsePortNotification()){
					view.drawFalsePortMessage();
				}
				
			}
			case Game_Over -> {
				view.drawGameOver();

			}
		}
		return state;
	}
	
	/**
	 * Change the game state into start game.
	 */
	public void startGame() {
		state = GameState.Game;
	}

	
	/**
	 * Distribute a move that a player made onto model.
	 */
	public void insertMove(int n) {
		game.insertMove(n);
	}

	/**
	 * This method check if the game already over.
	 */
	public void getWinner(){
		if(game.getWinner() == 0) {
			gameOver = true;
			view.setWinner(0);
		}else if(game.getWinner() == 1) {
			gameOver = true;
			view.setWinner(1);
		}else if(game.getWinner() == 2) {
			gameOver = true;
			view.setWinner(2);
		}
	}
	
	/**
	 * Distribute the content of selected slot of board.
	 * @return content of selected slot.
	 */
	public int checkBoard(int n) {
		return game.getSlot(n);
	}
	
	/**
	 * Distribute the condition of current board in model.
	 * @return Array of content of the board.
	 */
	public int[] getBoard() {
		return game.getBoard();
	}
	
	/**
	 * Get which turn of player is now making move.
	 * @return Turn of player.
	 */
	public int checkTurn() {
		return game.getPlayerTurn();
	}
	
	/**
	 * Get a condition of column and check if it is full.
	 * @param n Selected column
	 * @return True if the column is full, false otherwise.
	 */
	public boolean isFull(int n) {
		if(game.isFull(n)) {
			return true;
		}else
		return false;
	}
	
	/**
	 * Start ClientServerThreadClass without starting a thread.
	 */
	public void prepareThread() {
		game.prepareThread();
		
	 }
	
	/**
	 * Initialize new server and start thread for server.
	 * @param port port of server.
	 */
	public  void newServer(int port) {
		game.newServer(port);
	}
	
	/**
	 * Initialize new Client and start thread for client.
	 * @param ip ip address of client.
	 * @param port port of client that should be identical to server.
	 */
	public void newClient(String ip, int port) {
		game.newClient(ip, port);
	}

	 /**
	  * Send player's move into network.
	  * @param n move of player.
	  */
	 public void sendMove(int n) {
		game.sendMove(n);
	 }

	/**
	 * set the error notification to true so the view can draw message.
	 */
	public boolean clientServerCloseNotification(){ return game.getConnectionError(); }

	/**
	 * set the error notification to true so the view can draw message.
	 */
	 public boolean falsePortNotification(){ return game.getFalsePort(); }


	
	
}
