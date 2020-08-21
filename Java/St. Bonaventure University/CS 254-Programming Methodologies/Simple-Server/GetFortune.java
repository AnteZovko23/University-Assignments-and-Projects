import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Connects to CS-LAB11 server on port 4444 and asks for a fortune (see Java
 * Network Programming, p. 153)
 * 
 * @author andrianoff - Further developed by Ante Zovko
 * @version 1/29/2010 - Updated 3/23/2019
 *
 */
public class GetFortune {

	public static void main(String[] args) {
		Socket clientSocket;
		String host = "CS-LAB11.compsci.sbu.edu";

		try {

			// Creating the socket
			
			clientSocket = new Socket(host, 4444);

			
			
			// INPUT TO THE SERVER

			PrintWriter inputToServer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			
			// INPUT FROM THE SERVER
			
			Scanner inputFromServer = new Scanner(clientSocket.getInputStream());

			System.out.println(inputFromServer.nextLine());

			// INPUT FROM THE USER
			 
			Scanner user = new Scanner(System.in);
			String input = user.nextLine();
			inputToServer.println(input);

			inputToServer.flush();

			
			// RECEIVING THE DATA
			
			while (inputFromServer.hasNextLine()) {
				System.out.println(inputFromServer.nextLine());
			}

			clientSocket.close();

		} catch (UnknownHostException e) {
			System.err.println("UnknownHostException(LookForPorts): " + e);
		} catch (IOException e) {
			

		}

		System.out.println("Done!");
	}
}
