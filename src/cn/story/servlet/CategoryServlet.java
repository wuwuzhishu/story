package cn.story.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import cn.story.domain.Category;
import cn.story.service.CategoryService;
import cn.story.utils.BaseServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryServlet extends BaseServlet {

	public String findcg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //设置乱码问题
		response.setContentType("html/text;charset=utf-8");
		//调用service层
		CategoryService cs = new CategoryService();
		try {
			List<Category> list = cs.findCg();
			//使用插件转换格式
			ObjectMapper  om = new ObjectMapper();
			String str = om.writeValueAsString(list);
			response.getWriter().print(str);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;

	}

}