import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
	Connection connection;
	Statement statement;
	
	public Database() throws ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		connection = null;
		statement = null;
	}
	public void onCreate(String dbName)
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			statement = connection.createStatement();
			System.out.println("test");
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			System.out.println("error");
		}
	}
	

}
