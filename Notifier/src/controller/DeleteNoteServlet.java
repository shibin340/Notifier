package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoteDao;
import dao.NotebookDao;
import dao.UserDao;
import model.Notebook;
import model.User;

/**
 * Servlet implementation class DeleteNoteServlet
 */
@WebServlet("/DeleteNote")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println(request.getSession(false).getAttribute("Student").toString());
			if(request.getSession(false).getAttribute("Student").toString()==null)
			{
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			User user=UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			System.out.println("name is::"+user.getName());
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("deletenote.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String note = request.getParameter("note");
		String book = request.getParameter("book");
		User user;
		try {
			user = UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			request.setAttribute("user", user);
			if(NoteDao.deleteNote(user.getEmail(), note, book)) {
				request.setAttribute("errMessage", "Deleted successfully");
			}else {
				request.setAttribute("errMessage", "Incorrect note or notebookname, Deletion failed.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("deletenote.jsp");
		dispatcher.forward(request, response);
	}

}
