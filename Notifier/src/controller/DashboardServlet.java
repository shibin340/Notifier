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

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user1=UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			System.out.println("name is::"+user1.getName());
			request.setAttribute("user", user1);
			List<Notebook> list = NotebookDao.getNotebooks(user1.getEmail());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			System.out.println(formatter.format(date));
			List<Note> list2 = NoteDao.getRemainders(user1.getEmail(),formatter.format(date));
			request.setAttribute("booklist", list);
			request.setAttribute("remlist", list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user1=UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			System.out.println("name is::"+user1.getName());
			request.setAttribute("user", user1);
			List<Notebook> list = NotebookDao.getNotebooks(user1.getEmail());
			request.setAttribute("booklist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
	}

}
