package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoteDao;
import dao.NotebookDao;
import dao.UserDao;
import model.Note;
import model.Notebook;
import model.User;

/**
 * Servlet implementation class AddNoteServlet
 */
@WebServlet("/AddNewNote")
public class AddNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			List<Notebook> list = NotebookDao.getNotebooks(user.getEmail());
			request.setAttribute("booklist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("addnote.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String nbname = request.getParameter("notebook");
		String remdate = request.getParameter("remdate");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(formatter.format(date));
		User user;
		try {
			user = UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			request.setAttribute("user", user);
			List<Notebook> list = NotebookDao.getNotebooks(user.getEmail());
			request.setAttribute("booklist", list);
			if(remdate.charAt(2) == '/' && remdate.charAt(5) == '/')
				NoteDao.addNote(new Note(user.getEmail(),nbname,name,desc,remdate));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("addnote.jsp");
		dispatcher.forward(request, response);
	}

}
