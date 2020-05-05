package ua.lviv.ura.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ua.lviv.ura.domain.Korzina;
import ua.lviv.ura.domain.Product;
import ua.lviv.ura.dto.KorzinaDto;
import ua.lviv.ura.service.KorzinaService;
import ua.lviv.ura.service.ProductService;
import ua.lviv.ura.service.impl.KorzinaServiceImpl;
import ua.lviv.ura.service.impl.ProductServiceImpl;

public class KorzinaAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KorzinaService korzinaService = KorzinaServiceImpl.getKorzinaService();
	private ProductService productService = ProductServiceImpl.getProductService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Korzina> korzina = korzinaService.readAll();
		Map<Integer, Product> idToProduct = productService.readAllMap();
		List<KorzinaDto> listOfKorzinaDtos = map(korzina, idToProduct);
		
		String json = new Gson().toJson(listOfKorzinaDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korzinaId = request.getParameter("korzinaId");
		korzinaService.delete(Integer.parseInt(korzinaId));
		
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}
	
	
	public List<KorzinaDto> map(List<Korzina> korzina, Map<Integer, Product> idToProduct){

		return	korzina.stream().map(Korzina->{
			KorzinaDto korzinaDto = new KorzinaDto();
			korzinaDto.korzinaId = Korzina.getId();
			korzinaDto.purchaseDate = Korzina.getPurchaseDate();
		   
			Product product = idToProduct.get(Korzina.getProductId());
		    korzinaDto.name = product.getName();
		    korzinaDto.description = product.getDescription();
		    korzinaDto.price = product.getPrice();
			
			return korzinaDto;
		}).collect(Collectors.toList());
		
	} 
}