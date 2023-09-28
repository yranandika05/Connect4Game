package game.controller;

/**
 * All possible game states. This class is used to implement a state machine in the controller.
 * @author Yohanes Kirana
 *
 */
public enum GameState {
	/**
	 * The starting state of the game, where server and client register their port and ip address.
	 */
	Title_Screen,
	/**
	 * The state in which the user can play Connect 4.
	 */
	Game,
	/**
	 * The end state of the game, when one of the player win or the game end tied.
	 */
	Game_Over
}
