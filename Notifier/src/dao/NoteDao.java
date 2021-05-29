package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.Database;
import model.Note;
import model.Notebook;

public class NoteDao {
	public static int addNote(Note notebook) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO notes" +
            "  (email, notebookname,note,descr,remdate) VALUES " +
            " ( ?, ?, ?, ?, ?);";

        int result = 0;
        try {
        	Connection connection= Database.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            		 
            preparedStatement.setString(2, notebook.getNotebookname());
            preparedStatement.setString(1,notebook.getEmail());
            preparedStatement.setString(3, notebook.getNote());
            preparedStatement.setString(4, notebook.getDesc());
            preparedStatement.setString(5, notebook.getRemdate());
            
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
	
	
	public static List<Note> getNotes(String email) throws ClassNotFoundException {
        List<Note> list=new ArrayList<Note>();
        try {
        	Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from notes  where email = ?");
            preparedStatement.setString(1, email);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	Note notebook = new Note();
            	notebook.setEmail(rs.getString("email"));
            	notebook.setNotebookname(rs.getString("notebookname"));
            	notebook.setNote(rs.getString("note"));
            	notebook.setDesc(rs.getString("descr"));
            	notebook.setRemdate(rs.getString("remdate"));
            	list.add(notebook);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
            
        }
        return list;
    }
	
	public static boolean deleteNote(String email,String note,String book)
	{
	  boolean status=false;
	  try {
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("delete from notes where email=? and note=? and notebookname=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, note);
		preparedStatement.setString(3, book);
		status=preparedStatement.executeUpdate()>0;
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return status;
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


	public static List<Note> getRemainders(String email, String format) {
		List<Note> list=new ArrayList<Note>();
        try {
        	Connection connection= Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from notes  where email = ? and remdate = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, format);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	Note notebook = new Note();
            	notebook.setEmail(rs.getString("email"));
            	notebook.setNotebookname(rs.getString("notebookname"));
            	notebook.setNote(rs.getString("note"));
            	notebook.setDesc(rs.getString("descr"));
            	notebook.setRemdate(rs.getString("remdate"));
            	list.add(notebook);
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
            
        }
        return list;
	}
}
