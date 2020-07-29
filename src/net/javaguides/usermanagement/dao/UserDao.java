package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.javaguides.usermanagement.model.User;

public class UserDao {
	
	private static final String jdbcURL="jdbc:mysql://localhost:3306/java_demo?useSSL=false";
    private static final String jdbcUsername="root";
    private static final String jdbcpassword="root";
    
    private static final String Create="INSERT INTO users"+"(name,email,country) VALUES"+
			"(?,?,?);";
	private static final String Read="SELECT id,name,email,country from users WHERE ID=?";
	private static final String Read_All="SELECT * FROM USERS";
	private static final String Delete="Delete from users where id=?;";
	private static final String Update="update users set name = ?,email = ?, country =? WHERE id=?;";
	
	    
    public static Connection getConnection() {
    	
    	Connection con=null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcpassword);
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    	catch(ClassNotFoundException e)	{
    		e.printStackTrace();
    	}
    	return con;
    	
    }
    
    public void createUser(User user){
    	try(Connection con=getConnection();
    			PreparedStatement ps=con.prepareStatement(Create)){
    		ps.setString(1, user.getName());
    		ps.setString(2, user.getEmail());
    		ps.setString(3, user.getCountry());
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public User selectUser(int id)
    {
    	User user=null;
    	try(Connection con=getConnection();
    			PreparedStatement ps=con.prepareStatement(Read)){
    		ps.setInt(1,id);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			String name=rs.getString("name");
    			String email=rs.getString("email");
    			String country=rs.getString("country");
  
    			user=new User(name,email,country);
    		}
    	}
    		catch(SQLException e){
    			e.printStackTrace();
    		}
    	return user;
    		
    }
    
    public List<User> selectAllUsers()
    {
    	List<User> us=new ArrayList<>();
    	try(Connection con=getConnection();
    			PreparedStatement ps=con.prepareStatement(Read_All)){
    		
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			int id=rs.getInt("id");
    			String name=rs.getString("name");
    			String email=rs.getString("email");
    			String country=rs.getString("country");
    			us.add(new User(id,name,email,country));
    		}
    	}
    		catch(SQLException e){
    			e.printStackTrace();
    		}
    	return us;
    		
    }
    
    public boolean updateUser(User user){
    	boolean rowupdated=false;
    	try(Connection con=getConnection();
    			PreparedStatement statement=con.prepareStatement(Update)){
    		
    		statement.setString(1, user.getName());
    		statement.setString(2, user.getEmail());
    		statement.setString(3, user.getCountry());
    		statement.setInt(4, user.getId());
    		
    		rowupdated=statement.executeUpdate()>0;
    	  System.out.println(rowupdated);
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return rowupdated;
    }
    public boolean deleteUser(int id){
    	boolean rowupdated=false;
    	try(Connection con=getConnection();
    			PreparedStatement statement=con.prepareStatement(Delete)){
    		
    		statement.setInt(1,id);
    		rowupdated=statement.executeUpdate()>0;
    		 System.out.println(rowupdated);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rowupdated;
    }
    


}
    
    
