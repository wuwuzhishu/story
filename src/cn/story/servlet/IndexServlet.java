package cn.story.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import cn.story.domain.Category;
import cn.story.domain.Product;
import cn.story.service.CategoryService;
import cn.story.service.ProductService;
import cn.story.utils.BaseServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends BaseServlet {

	/*public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/index.jsp";

	}*/
	
	/*public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用导航CategoryService 层  dao层 （查询数据库）
		CategoryService cs = new CategoryService();
	    try {
			List<Category> clist =  cs.findCg();
			request.setAttribute("list", clist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/index.jsp";
	}
*/
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 //查询商品
		 //步骤一：调用service层
		 //步骤二：调用dao层 （查询数据） --》返回数据
		ProductService ps = new ProductService();
		  try {
			  List<Product> hlist =  ps.findHot(); //查询热门商品
			  List<Product> nlist =  ps.findNew(); //查询最新商品
			  
			  request.setAttribute("hlist", hlist);//查询热门商品
			  request.setAttribute("nlist", nlist);//查询最新商品
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return "/jsp/index.jsp";

	}
}