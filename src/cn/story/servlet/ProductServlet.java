package cn.story.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import cn.story.domain.Product;
import cn.story.service.ProductService;
import cn.story.utils.BaseServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductServlet extends BaseServlet {

	public String findByid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //接收参数
         //调用service层 --》dao层(查询数据库)--》返回 
		String pid = request.getParameter("pid");
		 ProductService ps = new ProductService();
		 try {
			Product prduct = ps.findbyid(pid);
			request.setAttribute("product", prduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_info.jsp";

	}
	public String findBycid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService ps = new ProductService();
		//接收参数
		String cid = request.getParameter("cid");
	//	HttpSession session = request.getSession();	
		try {
			List<Product> plist =  ps.findBycid(cid);
			request.setAttribute("list", plist);
	//		session.setAttribute("list", plist);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "/jsp/product_list.jsp";
		
	}

}