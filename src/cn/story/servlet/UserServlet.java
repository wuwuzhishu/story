package cn.story.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import cn.story.domain.User;
import cn.story.service.UserService;
import cn.story.utils.BaseServlet;
import cn.story.utils.UUIDUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends BaseServlet {

	 //注册页面的跳转
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		return "/jsp/register.jsp";

	}
	//登录页面的跳转
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/login.jsp";
		
	}
	//退出
	public String quitvate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得session并且销毁  invalidate()销毁session
		request.getSession().invalidate(); 
		response.sendRedirect(request.getContextPath());
		
		return null;
		
	}
	//登录
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*（1）接收用户提交的参数
		（2）封装用户提交的参数
		（3）调用service层 ---》dao层查询
		（4）如果能在数据库查到 说明之前已经注册 可以登录 |查不到 重新登录
		（5）跳转到我们首页面*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String parameter = request.getParameter("remer");
	     //封装
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		//调用service层 
		UserService us = new UserService();
	    try {
			User exu = us.login(u);
			if(exu==null){//查询结果不存在 不能登录
				
				request.setAttribute("msg", "用户名或密码错误");
				return "/jsp/login.jsp";
	           
			}else{ //可以登录
				
				if(parameter==null){//判断是否勾选 null 没有勾选
					
					Cookie c = new Cookie("u",exu.getUsername());
					c.setMaxAge(0);//有效时间
                    c.setPath(request.getContextPath());  //设置有效项目			
                    response.addCookie(c); //可以理解为将cookie的信息 放到浏览器
					
				}else{//要进行记住用户名
					 
					Cookie c = new Cookie("u",exu.getUsername());
					c.setMaxAge(60*60*24*7);//有效时间
                    c.setPath(request.getContextPath());  //设置有效项目			
                    response.addCookie(c); //可以理解为将cookie的信息 放到浏览器
					
				}
				
				//登录以后我们把用户的信息记录在session中
				HttpSession session = request.getSession();	
				/*第一次执行时创建对象  第二次是获得对象*/
				session.setAttribute("user", exu);
				
				//跳转到首页
				response.sendRedirect(request.getContextPath());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	//ajax的方法
	public String ajaxByname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户提交的参数
		String username = request.getParameter("username");
        //调用service层 dao层 （查询数据库的操作）
		UserService  us = new UserService();
		try {
			User u = us.ajaxByname(username); //查询 如果能查到一个 u  如果查不到 还可以使用
			if(u==null){//可以使用
				
				response.getWriter().print(true);  //通过这个东西 向页返回效果
				
			}else{
				response.getWriter().print(false);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return null;
		
	}
	
	//注册的方法
	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//步骤一：接收用户提交的数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		//步骤二：数据的封装
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setUid(UUIDUtils.getUuids());  //写一个随机的一个uuid
		//步骤三：调用service ，dao（操作数据库）
		UserService  us = new UserService();
		try {
			us.register(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//页面跳转
		return "/jsp/login.jsp";
		
	}

}