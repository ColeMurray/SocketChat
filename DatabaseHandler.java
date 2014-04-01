import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseHandler 
{

	Database database = null;
	String tableName = "userList";
	String keyUsername = "username";
	String keyIp = "ip";
	String keyStatus = "status";
	
	public DatabaseHandler() throws ClassNotFoundException
	{
		System.out.println("Generic DbHand");
		database= new Database();
		database.onCreate("test.db");
		
	}
	public DatabaseHandler(Database database)
	{
		System.out.println("Error here");
		this.database.onCreate("random.db");
		System.out.println("here");
		this.database.connection = database.connection;
		this.database.statement = database.statement;
		System.out.println("here");
	}
	
	public void onCreate() throws SQLException
	{
		String drop = "DROP TABLE IF EXISTS " + tableName;
		this.database.statement.executeUpdate(drop);
		String sql = "CREATE TABLE " + tableName + 
				"(" + keyUsername + " " +  "TEXT," + 
				keyIp + " " + "TEXT," + 
				keyStatus + " " + "INTEGER" + ")";
		this.database.statement.executeUpdate(sql);
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
			
			//insertUser.close();
			System.out.println("Successful user add");
	}
	
	public String getUser (String username) throws SQLException
	{
		System.out.println("Getting user " + username);
		String getUser = "SELECT * " +  "FROM " + tableName
					+ " WHERE " + keyUsername + " LIKE " + username;
		System.out.println ( "userString: " + getUser );
		ResultSet rs = this.database.statement.executeQuery(getUser);
		String uName = null;
		while (rs.next())
		{
			uName = rs.getString("username");
			System.out.println(uName);
			//return uName;
		}
		return uName;
		
	}
	public static void main(String arg[])
	{
		String username = "Alice";
		String ip = "192.158.1.1";
		boolean available = true;
		try
		{
			User test = new User(username,ip,available);
			System.out.println(test.getUsername());
			System.out.println("Sucessful creation of database");
			System.out.println("Sucessful creation of database1");

			DatabaseHandler dbHand = new DatabaseHandler();
			dbHand.onCreate();
			System.out.println("Successful Dbhandler");
			dbHand.addUser(test);
			System.out.println("Sucessful addition of user");

			String alice = dbHand.getUser("Alice");
			System.out.println("Alice: " + alice);
			ResultSet qu = dbHand.database.statement.executeQuery(
							"SELECT * from userList");
			while (qu.next())
			{
				String uname = qu.getString("username");
				System.out.println("username " + uname);
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			
		}
		
	}
			

}
