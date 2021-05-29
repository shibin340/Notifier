package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        User loginBean = new User();
        loginBean.setEmail(email);
        loginBean.setPassword(password);
        try {
            if (UserDao.validate(loginBean).equals("correct")) {
               
            	HttpSession session = request.getSession(); 
            	System.out.println("Credentials = "+UserDao.validate(loginBean));
                session.setAttribute("Student", email);
                request.setAttribute("username", email);
                RequestDispatcher dispatcher= request.getRequestDispatcher("Dashboard.jsp");
                dispatcher.forward(request, response);
            }
            else {
            	System.out.println("Error message = "+UserDao.validate(loginBean));
                request.setAttribute("errMessage", UserDao.validate(loginBean));
                RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
        		dispatcher.forward(request, response);
            
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}
