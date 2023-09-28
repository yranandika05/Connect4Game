package game.model;


/**
 * The game implementation of 4 Gewinn or Connect 4. This class is the model of the game.
 * It can be either used in combination with view and controller or directly through Jshell.
 * @author Yohanes Kirana
 *
 */
public class Game4GewinnModel {
	private int width, height;
	private int insert;
	private int player = 1;
	private int winner = -1;
	private int[] board = new int[42];
	private ClientServerThread thread;
	private boolean error = false;
	private boolean falsePort = false;



	
	/*	BOARD VIEW
	 	
	 	 0   1   2   3   4   5   6
	 	 7   8   9  10  11  12  13
	 	14  15  16  17  18  19  20
	 	21  22  23  24  25  26  27
	 	28  29  30  31  32  33  34
	 	35  36  37  38  39  40  41
	 
	 */
	
	/**
	 * Creates a new game instance with the given game field size.
	 * @param width width of the game field
	 * @param height height of the game field
	 */
	public Game4GewinnModel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Save the move that is inserted by player into the game board.
	 * If the selected column is full, player will be asked to choose another column. 
	 * @param column Column which player choose.
	 */
	public boolean insertMove(int column) {
		int pointer;
		int turn = getPlayerTurn();
		if(isInsideColumnRange(column)){
			if(!isFull(column)) {
				for(int i = 5; i >= 0; i --) {
					pointer = column + i*7;
					if(board[pointer] == 0){
						board[pointer] = turn;
						break;
					}
				}
				changeTurn();
			}else {
				System.out.println("The column you inserted is full. Please insert another column!");
				return false;
			}
		}else{
			System.out.println("The column you inserted is out of range. Please insert between column 0 - 6");
			return false;
		}
		printBoard();
		return true;
	}

	
	/**
	 * Print the current condition of the board game.
	 */
	private void printBoard() {
		for(int i = 0; i < board.length; i++) {
			if(i % 7 == 0) {
				System.out.print("\n");	
			}
			System.out.print(board[i]);
		}
		System.out.println("\nnow is player " + player + " turn");
		System.out.println("GameState: " +getWinner());
	}
	
	/**
	 * Get the contents of the slot on the board. 
	 * @param n Selected slot.
	 * @return Content of the selected slot.
	 */
	public int getSlot(int n) {
		return board[n];
	}
	
	/**
	 * Get the contents of the entire board.
	 * @return Contents of the entire board.
	 */
	public int[] getBoard() {
		return board;
	}
	
	/**
	 * Change the current player's turn.
	 */
	private void changeTurn() {
		if(player == 1) {
			player = 2;
		}else {
			player = 1;
		}
	}
	
	/**
	 * Set the player's turn.
	 * @param n Player's turn.
	 */
	public void setPlayerTurn(int n) {
		player = n;
	}
	
	/**
	 * Get the current turn.
	 * @return Current turn.
	 */
	public int getPlayerTurn() {
		return player;
	}
	
	/**
	 * Check if there is a winner.
	 * A winner is determined, if he has 4 balls in a row horizontally, vertically, or diagonally.
	 * @return True if a winner is determined, false otherwise.
	 */
	private boolean isWinner() {

		//check horizontal:
		for(int i = 0; i < 6; i ++) {
			for(int j = 0; j < 4; j ++){
				if(board[(i*7) + j] == board[(i*7) + j + 1] && board[(i*7) + j + 1] == board[(i*7) + j + 2] && board[(i*7) + j + 2] == board[(i*7) + j + 3]
					&&board[(i*7) + j] != 0 && board[(i*7) + j + 1] != 0 && board[(i*7) + j + 2] != 0 &&board[(i*7) + j + 3] != 0) {
					return true;
				}
			}
		}
		
		//check vertical:
		 for(int i = 0; i < 7; i ++) {
			 for(int j = 0; j < 3; j ++) {
				 if(board[i + (j*7)] == board[i + (j*7) + 7] && board[i + (j*7) + 7] == board[i + (j*7) + 14] && board[i + (j*7) + 14] == board[i + (j*7) + 21]
				 	&& board[i + (j*7)] !=0 && board[i + (j*7) + 7] !=0 && board[i + (j*7) + 14] !=0 && board[i + (j*7) + 21] != 0) {
					 return true;
				 }
			 }
		 }
		 
		 //check diagonal:
		 for(int i = 0; i < 4; i ++) {
			 for(int j = 0; j < 3; j ++) {
				 if(board[i + (j*7)] == board[(i + 1) + ((j + 1)*7)] && board[(i + 1) + ((j + 1)*7)] == board[(i + 2) + ((j + 2)*7)] && board[(i + 2) + ((j + 2)*7)] == board[(i + 3) + ((j + 3)*7)]
				 	&& board[i + (j*7)] != 0 && board[(i + 1) + ((j + 1)*7)] != 0 && board[(i + 2) + ((j + 2)*7)] != 0 && board[(i + 3) + ((j + 3)*7)] != 0) {
					 return true;
				 }
			 }
		 }
		 for(int i = 6; i > 2 ; i --) {
			 for(int j = 0; j < 3; j ++) {
				 if(board[i + (j*7)] == board[(i - 1) + ((j + 1)*7)] && board[(i - 1) + ((j + 1)*7)] == board[(i - 2) + ((j + 2)*7)] && board[(i - 2) + ((j + 2)*7)] == board[(i - 3) + ((j + 3)*7)]
				 	&&board[i + (j*7)] != 0 && board[(i - 1) + ((j + 1)*7)] != 0 && board[(i - 2) + ((j + 2)*7)] != 0 && board[(i - 3) + ((j + 3)*7)] != 0) {
					 return true;
				 }
			 }
		 }
		 return false;
	}
	
	/**
	 * Check if the game end without any winner.
	 * @return True if there is no winner, false if the game has not end yet.
	 */
	private boolean isTie() {
		for(int i = 0; i < board.length; i++) {
			if(board[i] == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if the game is over.
	 * This method update winner state if the game ends.
	 */
	private void isGameOver() {
		if(isWinner()) {
			if(getPlayerTurn() == 1) {
				winner = 2;
			}else {
				winner = 1;
			}

		}
		if(isTie()) {
			winner = 0;
		}
	}

	/**
	 * Get The winner of the Game.
	 * @return Winner, -1 if the not over, 0 if there is no winner.
	 */
	public int getWinner(){
		isGameOver();
		return winner;
	}
	
	
	/**
	 * Check if the column is Full. the player will be asked to insert another column if it does.
	 * @param column Column that will be inserted.
	 * @return True if the column is full, false if the column is not full.
	 */
	public boolean isFull(int column) {
		if(column > 6) {
			System.out.println("Please pick column 0 up to 6");
		}else {
			if(board[column] != 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the column inserted inside of range.
	 * @param column Column that will be inserted.
	 * @return True if the column is inside of range , false if the column is outside of range.
	 */
	private boolean isInsideColumnRange(int column){
		return column >= 0 && column < 7;
	}

	/**
	 * Start ClientServerThreadClass without starting a thread.
	 */
	public void prepareThread() {
		thread = ClientServerThread.startGame(this);

	}

	/**
	 * Initialize new server and start thread for server.
	 * @param port port of server.
	 */
	public  void newServer(int port) {
		thread.newServer(port);
		thread.start();
	}

	/**
	 * Initialize new Client and start thread for client.
	 * @param ip ip address of client.
	 * @param port port of client that should be identical to server.
	 */
	public void newClient(String ip, int port) {
		thread.newClient(ip, port);
		thread.start();
	}

	/**
	 * Send player's move into network.
	 * @param n move of player.
	 */
	public void sendMove(int n) {
		thread.streamMove(n);
	}

	/**
	 * Set the network error notification.
	 * @param message true or false if the connection error.
	 */
	public void setConnectionError(boolean message){ error = message; }

	/**
	 * Get the network error notification to true so the view can draw message.
	 * @return true or false
	 */
	public boolean getConnectionError(){ return error; }

	/**
	 * Set the port error notification .
	 * @param message true or false if the port false.
	 */
	public void setFalsePort(boolean message){ falsePort = message; }

	/**
	 * Get the port error notification to true so the view can draw message.
	 * @return true or false.
	 */
	public boolean getFalsePort(){ return falsePort; }
	
	
}
