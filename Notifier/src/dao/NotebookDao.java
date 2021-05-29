package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import model.Notebook;
import model.User;

public class NotebookDao {
	public static int addNotebook(Notebook notebook) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO notebooks" +
            "  (email, notebookname,notes) VALUES " +
            " ( ?, ?, ?);";

        int result = 0;
        try {
        	Connection connection= Database.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            		 
            preparedStatement.setString(2, notebook.getNotebookname());
            preparedStatement.setString(1,notebook.getEmail());
            preparedStatement.setInt(3, notebook.getNotes());
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
	
	
	public static List<Notebook> getNotebooks(String email) throws ClassNotFoundException {
        List<Notebook> list=new ArrayList<Notebook>();
        try {
        	Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from notebooks  where email = ?");
            preparedStatement.setString(1, email);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	Notebook notebook = new Notebook();
            	notebook.setEmail(rs.getString("email"));
            	notebook.setNotebookname(rs.getString("notebookname"));
            	notebook.setNotes(rs.getInt("notes"));
            	list.add(notebook);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
            
        }
        return list;
    }
	
	public static boolean deleteNotebook(String email,String name)
	{
	  boolean status=false;
	  try {
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("delete from notebooks where email=? and notebookname=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, name);
		status=preparedStatement.executeUpdate()>0;
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return status;
	}
	
	public static void deleteNotesInNotebook(String email,String name)
	{
	  try {
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("delete from notes where email=? and notebookname=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, name);
		preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
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
