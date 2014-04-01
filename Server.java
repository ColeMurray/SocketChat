import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String argv[])
	{
		int port_number = Integer.parseInt(argv[0]);
		try
		{
			ServerSocket serv = new ServerSocket (port_number);
			Socket clientSock = serv.accept();
			System.out.print("Client accepted \n");
			PrintWriter out = new PrintWriter(clientSock.getOutputStream(),true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSock.getInputStream()));
			String userInput;
			while ((userInput = in.readLine())!= null)
			{
				System.out.println(userInput);
				out.println("Echo " + userInput);
			}
			
			out.close();
			in.close();
			serv.close();
			clientSock.close();
		}	
		catch (Exception E)
		{
			System.out.println("Error fail");
		}
		finally
		{
			
		}
			
		
	}

}
