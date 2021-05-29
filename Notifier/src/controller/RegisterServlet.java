package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		String cpassword=request.getParameter("cpassword");
		model.User user=new User(email,password,name,mobile);
		
		try {
			if(password.length()<6) {
				request.setAttribute("errMessage", "Password too short");
			}
			else if(mobile.length()!=10) {
				request.setAttribute("errMessage", "Mobile number should have 10 digits");
			}
			else if(!password.equals(cpassword)) {
				request.setAttribute("errMessage", "Password not same");
			}
			else if(UserDao.userAlreadyPresent(email)==false)
			{
				request.setAttribute("errMessage", "Registered successfully");
				UserDao.registerUser(user);
			}
			else
			{
				request.setAttribute("errMessage", "email already registered");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

}
