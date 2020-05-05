package ua.lviv.ura.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.ura.domain.Korzina;
import ua.lviv.ura.service.KorzinaService;
import ua.lviv.ura.service.impl.KorzinaServiceImpl;

public class ByProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KorzinaService korzinaService = KorzinaServiceImpl.getKorzinaService();
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		Korzina korzina = new Korzina(userId, Integer.parseInt(productId), new Date());
		korzinaService.create(korzina);
		
		
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}
