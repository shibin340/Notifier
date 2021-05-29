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
import model.User;

@WebServlet("/DeleteNotebook")
public class DeleteNotebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteNotebookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("deletenotebook.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		User user;
		try {
			user = UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			request.setAttribute("user", user);
			if(NotebookDao.deleteNotebook(user.getEmail(), name)) {
				NotebookDao.deleteNotesInNotebook(user.getEmail(), name);
				request.setAttribute("errMessage", "Deleted successfully");
			}else {
				request.setAttribute("errMessage", "Incorrect notebookname, Deletion failed.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("deletenotebook.jsp");
		dispatcher.forward(request, response);
	}

}
