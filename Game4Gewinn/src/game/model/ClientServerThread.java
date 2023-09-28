 package game.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;





/**
 * Class to handle network communication between server and client. 
 * This class extend thread from java, and will be used for both server and client.
 * @author Yohanes Kirana
 *
 */
public class ClientServerThread extends Thread{
	
	private ServerSocket serversocket;
	private Socket socket;
	private ObjectOutputStream oos;
	private Game4GewinnModel model;
	private static ClientServerThread cst;
	
	/**
	 * Create a new thread for the communication between server and client.
	 * @param model The model Object that should be used by ClientServerThread.
	 */
	private ClientServerThread(Game4GewinnModel model) {
		this.model= model;
	}
	
	/**
	 * Start a game without knowing if the object a server or a client. 
	 * It will be used for the beginning of view of game.
	 * @param model  The model Object that should be used by ClientServerThread.
	 * @return a clientServerThread object.
	 */
	public static ClientServerThread startGame(Game4GewinnModel model) {
		cst = new ClientServerThread(model);
		return cst;
		
	}
	
	/**
	 * Start a new sever connection with given port number.
	 * @param port The given port number to start the server connection.
	 * @return a clientServerThread object.
	 */
	public ClientServerThread newServer(int port) {
		try {
			cst.serversocket = new ServerSocket(port);
		} catch (IOException e){
			e.printStackTrace();
		}
		return cst;
	}
	
	/**
	 * Start a new client connection with given port number.
	 * @param ip The given ip address of the client.
	 * @param port The given port number that is identical to server's port. 
	 * @return a clientServerThread object.
	 */
	public ClientServerThread newClient(String ip, int port) {
		try {
			cst.socket = new Socket(ip, port);
			cst.oos = new ObjectOutputStream(cst.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cst;
	}
	
	/**
	 * Check if the server is connected with a client.
	 * @return True if connected, false otherwise.
	 */
	public boolean isConnected() {
		return (socket != null && socket.isConnected());
	}
	
	/**
	 * stream a move that is made by server or client as an object.
	 * @param n Column number that is inserted by player.
	 */
	public void streamMove(int n) {
		try {
			oos.writeObject(n);
		}catch (IOException e) {
			model.setConnectionError(true);
			e.printStackTrace();
		}
	}
	
	/**
	 *Run method that execute the thread.
	 *Thread will allow server to accept one client if server does not have one.
	 *Server and client will read the stream which contains movement of another player.
	 *The move is then sent to the view to appear in the window.
	 */
	@Override
	public void run() {
		try {
			// If this is a server accept one client

			 if(socket == null) {
				socket = serversocket.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
			}
			
			//Read objects
			if(isConnected()) {				
				var ois = new ObjectInputStream(socket.getInputStream());
				while (true) {
					Object obj = ois.readObject();
					model.insertMove((int) obj);
				}
			}
		} catch (NullPointerException e){
			model.setFalsePort(true);
			e.printStackTrace();

		} catch (IOException e) {
			model.setConnectionError(true);
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);

		}
	}

}
