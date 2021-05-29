package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import model.User;

public class UserDao {

//	Database database;
	public static int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO login" +
            "  (email, password,name, mobile ) VALUES " +
            " ( ?, ?, ?, ?);";

        int result = 0;


        try {
        		Connection connection= Database.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            		 
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(4, user.getMobile());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }


	public static String validate(User user) throws ClassNotFoundException {
        String userNameDB = "";

        try {Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select * from login where email = ? and password = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                userNameDB = rs.getString("email");
                return "correct";
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return "Invalid login credentials";
    }
	public static User getUser(String email) throws ClassNotFoundException {
        User user=new User();
        try {
        	Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select * from login  where email = ?");
            preparedStatement.setString(1, email);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	user.setMobile(rs.getString("mobile"));
            	user.setEmail(rs.getString("email"));
            	user.setName(rs.getString("name"));
            	user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
            
        }
        return user;
    }
	

	public boolean updatePass(String pass,String email)
	{
		boolean status=false;
		try {
			Connection connection=Database.getConnection();
			PreparedStatement preparedStatement=connection.
					prepareStatement("update login set password=? where username=?");
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, email);
			status=preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}
	
	public static boolean updateUser(String email,User user)
	{
		boolean status=false;
		try {
			Connection connection=Database.getConnection();
			PreparedStatement preparedStatement=connection.
					prepareStatement("update login set email=?, password=?, name=?, mobile=? where email=?");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getMobile());
			preparedStatement.setString(5, email);
			status=preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}
	
	public static boolean userAlreadyPresent(String email) throws ClassNotFoundException {

        try {
        	Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select name from login  where email = ?");
            preparedStatement.setString(1, email);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
            	return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }


    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}