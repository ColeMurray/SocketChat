import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler 
{

	Database database = null;
	String tableName = "userList";
	String keyUsername = "username";
	String keyIp = "ip";
	String keyStatus = "status";
	
	public DatabaseHandler() throws ClassNotFoundException
	{
		database= new Database();
		database.onCreate("test.db");
		
	}
	public DatabaseHandler(Database database)
	{
		this.database.connection = database.connection;
		this.database.statement = database.statement;
	}
	
	public void onCreate() throws SQLException
	{
		String drop = "DROP TABLE IF EXISTS " + tableName;
		this.database.statement.executeUpdate(drop);
		
		String createTable = "CREATE TABLE " + tableName + 
				"(" + keyUsername + " " +  "TEXT," + 
				keyIp + " " + "TEXT," + 
				keyStatus + " " + "INTEGER" + ")";
		
		this.database.statement.executeUpdate(createTable);
	}
	
	public void addUser(User user) throws SQLException
	{

			PreparedStatement insertUser = this.database.connection.prepareStatement(
					"INSERT INTO "+ tableName + "(" + keyUsername + "," + keyIp + ","
					+ keyStatus + ") VALUES (?,?,?)");
			
			insertUser.setString(1, user.getUsername());
			insertUser.setString(2, user.getIp());
			insertUser.setInt(3, user.getStatus());
			
			insertUser.executeUpdate();
			insertUser.close();
	}
	
	public User getUser (String username) throws SQLException
	{
		String uName = null;
		String uIp = null;
		int uStatusInt = 0;
		String getUser = "SELECT * " +  "FROM " + tableName
					+ " WHERE " + keyUsername + "=\"" + username + "\"";

		ResultSet rs = this.database.statement.executeQuery(getUser);
		while (rs.next())
		{
			uName = rs.getString(keyUsername);
			uIp = rs.getString (keyIp);
			uStatusInt = rs.getInt(keyStatus);
		}
		boolean uStatusBool = (uStatusInt != 0)? true : false;
		User user = new User(uName,uIp,uStatusBool);
		return user;
		
	}
	
	public List<User> getAllUsers () throws SQLException
	{
		List<User> allUsers = new ArrayList<User>();
		User user;
		String uName = null;
		String uIp = null;
		int uStatusInt = 0;
		
		String getAllUsers = "SELECT * FROM " + tableName ;
		ResultSet rs = this.database.statement.executeQuery(getAllUsers);
		while (rs.next())
		{
			uName = rs.getString(keyUsername);
			uIp = rs.getString (keyIp);
			uStatusInt = rs.getInt(keyStatus);
			boolean uStatusBool = (uStatusInt != 0) ? true: false ;
			user = new User(uName,uIp,uStatusBool);
			
			allUsers.add(user);
		}
		return allUsers;
		
	}
	public static void main(String arg[])
	{
		String username = "Alice";
		String ip = "192.158.1.1";
		boolean available = true;
		try
		{
			User alice = new User(username,ip,available);
			DatabaseHandler dbHand = new DatabaseHandler();
			dbHand.onCreate();
			dbHand.addUser(alice);
			dbHand.addUser(new User("Bob","182.1.1",true));
			List<User> allUsers = dbHand.getAllUsers();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			
		}
		
	}
			

}
