package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NotebookDao;
import dao.UserDao;
import model.Notebook;
import model.User;

@WebServlet("/NotebooksServlet")
public class NotebooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NotebooksServlet() {
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
			List<Notebook> list = NotebookDao.getNotebooks(user.getEmail());
			request.setAttribute("booklist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("notebooks.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user;
		try {
			user = UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			request.setAttribute("user", user);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		doGet(request, response);
		request.getRequestDispatcher("notebooks.jsp").forward(request, response);
		
	}

}
