package controller;

import java.io.IOException;
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

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUserServlet() {
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
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("editprofile.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        
        try {
			System.out.println(request.getSession(false).getAttribute("Student").toString());
			if(request.getSession(false).getAttribute("Student").toString()==null)
			{
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			User user=UserDao.getUser(request.getSession(false).getAttribute("Student").toString());
			User newUser = new User(user.getEmail(),password,name,mobile);
			if(password.length()<6) {
				request.setAttribute("errMessage", "Password too short");
			}
			else if(mobile.length()!=10) {
				request.setAttribute("errMessage", "Mobile number should have 10 digits");
			}
			else if(UserDao.updateUser(user.getEmail(),newUser)) {
				request.setAttribute("errMessage", "User data Updated successfully.");
				request.setAttribute("user", newUser);
			}
			else {
				request.setAttribute("errMessage", "User data Updation failed.");
				request.setAttribute("user", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("editprofile.jsp");
		requestDispatcher.forward(request, response);
	}

}
