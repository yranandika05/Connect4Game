package game.view;

import processing.core.PApplet;

/**
 * Class for server to start its view.
 * @author Yohanes Kirana
 *
 */
public class MainServer {
	/**
	 * Main method to start a view for server.
	 * @param args
	 */
	public static void main(String[] args) {
		var view = Game4GewinnView.newServer();
		PApplet.runSketch(new String[] {"Game4Gewinn"}, view);
	}

}
