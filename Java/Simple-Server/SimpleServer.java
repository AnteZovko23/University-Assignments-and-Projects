import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * Accepts socket connections on port 4444.
 * 
 * Returns either a random fortune or a specific one based on user input
 * 
 * see Java Network Programming, p. 189
 * 
 * @author andrianoff - Further developed by Ante Zovko
 * @version March 9, 2012 - Updated 3/23/2019
 */

public class SimpleServer {
	public static void main(String[] args) {
		ServerSocket server;
		Socket serverSocket;
		FortuneTeller fortune = new FortuneTeller("Fortunes.txt");

		try {

			// Creating the socket and server
			
			server = new ServerSocket(4444);
			System.out.println("Server Started");
			serverSocket = server.accept();

			
			
			// INPUT FROM THE CLIENT
			
			Scanner inputFromClient = new Scanner(serverSocket.getInputStream());

			
			// INPUT TO THE CLIENT
			
			PrintWriter inputToClient = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()), true);

			
			inputToClient.println("Press enter for random fortune or enter a string for a fortune containing that string");
			inputToClient.flush();

			
			// SENDING THE DATA
			
			String input = inputFromClient.nextLine();

			if (input.contentEquals("")) {
				inputToClient.println(fortune.getFortune());
			} 
			else {
				inputToClient.println(fortune.getFortune(input));
			}

			
			
			
			inputFromClient.close();

			server.close();

		} catch (IOException e) {
			System.err.println("IOException(SimpleServer): " + e);
		}
	}

}
