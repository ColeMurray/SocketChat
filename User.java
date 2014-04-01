public class User {
	
		String username;
		String ip;
		boolean available;
	public User ( String username, String ip,
			boolean available )
	{
		this.username = username;
		this.ip = ip;
		this.available = available;
	}
	
		
		// Return methods
	public	String getUsername()
		{
			return username;
		}
	public String getIp()
		{
			return ip;
		}
	public int getStatus()
		{
			return (available)? 1:0 ;
		}
		
		// Set methods
	public void setUsername(String username)
		{
			this.username = username;
		
		}
	public	void setIp(String ip)
		{
			this.ip = ip;
		}
	public	void setStatus(boolean available)
		{
			this.available = available;
		}
}
