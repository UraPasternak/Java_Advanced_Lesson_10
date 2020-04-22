package ua.lviv.ura.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.ura.domain.User;
import ua.lviv.ura.domain.UserRole;
import ua.lviv.ura.service.UserService;
import ua.lviv.ura.service.impl.UserServiceImpl;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = UserServiceImpl.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		
		if(!email.isEmpty()&&!firstName.isEmpty()&&!lastName.isEmpty()&&!password.isEmpty()){
			userService.create(new User(email, firstName, lastName, UserRole.USER.toString(), password));
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success!");
	}

}
