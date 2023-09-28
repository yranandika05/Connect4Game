package game.view;

import processing.core.PApplet;

/**
 * Class to start the view of client.
 * @author Yohanes Kirana 
 *
 */
abstract class MainClient {
	/**
	 * Main method to start the view of client.
	 * @param args
	 */
	public static void main(String[] args) {
		var view = Game4GewinnView.newClient();
		PApplet.runSketch(new String[] {"Game4Gewinn"}, view);
	}

}
