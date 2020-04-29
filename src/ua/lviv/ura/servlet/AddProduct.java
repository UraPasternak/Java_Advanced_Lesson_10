package ua.lviv.ura.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.ura.domain.Product;
import ua.lviv.ura.service.ProductService;
import ua.lviv.ura.service.impl.ProductServiceImpl;

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = ProductServiceImpl.getProductService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nameProduct = request.getParameter("nameProduct");
		String descriptionProduct = request.getParameter("descriptionProduct");
		int price =  Integer.parseInt(request.getParameter("price"));
		
		productService.create(new Product(1, nameProduct, descriptionProduct, price));
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success!");
	}
}
