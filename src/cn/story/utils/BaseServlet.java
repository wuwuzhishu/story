package cn.story.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	@Override
	public  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");		
		   //处理乱码
		  String methodName = request.getParameter("method");
		  if(methodName==null || methodName.trim().length()==0){
			  
			  methodName="index";
		  }
		  
	      Class clazz = this.getClass();  //获得我们请求的Servlet的字节码（包裹）
		  try {
			 Method  method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);//获得
		     String Path = (String)method.invoke(this, request,response); //执行.path="/jsp/XXX.jsp"
		     if(Path!=null){
		         request.getRequestDispatcher(Path).forward(request, response);
		       }
		   } catch (Exception e) {
		    	e.printStackTrace();
		    	request.setAttribute("msg", "路径错误");
		    	request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
		  } 
	
	
	}

}
