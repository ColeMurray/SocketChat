import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
	/* usage: javac client host portnumber */
	public static void main(String argv[]){
		String host = argv[0];
		int portNumber = Integer.parseInt(argv[1]);
		try
		{
			Socket client = new Socket (host,portNumber);
			PrintWriter out = 
					new PrintWriter( client.getOutputStream(),true);
			BufferedReader in =
					new BufferedReader(
						new InputStreamReader(client.getInputStream()));
			BufferedReader stdin = 
					new BufferedReader(
						new InputStreamReader(System.in));
			String data;
			while ((data =stdin.readLine()) != null)
			{
				out.println(data);
				System.out.println(in.readLine());
			}
			in.close();
			
			
		}
		catch (Exception e)
		{
			System.out.print("Error");
		}
	}
}




