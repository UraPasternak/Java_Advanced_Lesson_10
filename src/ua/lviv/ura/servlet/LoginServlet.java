package ua.lviv.ura.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.ura.domain.User;
import ua.lviv.ura.service.UserService;
import ua.lviv.ura.service.impl.UserServiceImpl;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = userService.getUserByEmail(login);
		
		if(user != null && user.getPassword().equals(password)) {
			request.setAttribute("userEmail", login);
			request.getRequestDispatcher("cabinet.jsp").forward(request, response);
		}else {
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
