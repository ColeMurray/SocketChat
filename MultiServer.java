import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiServer
{
	private static int port;
	private static int maxConnections = 6;
	
	public static void main (String args[]) throws Exception
	{
		int clientCount = 0;
		port = Integer.parseInt(args[0]);
		ServerSocket listener = new ServerSocket(port); 

		try
		{
			while (clientCount++ < maxConnections)
			{
				new ThreadServ (listener.accept(),clientCount).start();
				
				
			}

		}
		catch (IOException E)
		{
			System.out.println("Error establishing server socket");
		}
		finally
		{
			listener.close();
		}
	}
	
	private static class ThreadServ extends Thread
	{
		private Socket socket;
		private int clientNumber;
		
		public ThreadServ (Socket s, int client)
		{
			this.socket = s;
			this.clientNumber = client;
			System.out.println("You are client number: " + clientNumber);
			
		}
		
		public void run()
		{
			try
			{
				PrintWriter out = 
						new PrintWriter(socket.getOutputStream(),true);
				BufferedReader in =
						new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
				String userInput;
				while ((userInput = in.readLine()) != null)
				{
					out.println("Echo " +userInput);
				}

			}
			catch (Exception E)
			{
				System.out.println("something went wrong");
			}
		}
		
	}

}
