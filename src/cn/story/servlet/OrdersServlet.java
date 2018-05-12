package cn.story.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import cn.story.domain.Orders;
import cn.story.domain.User;
import cn.story.service.OrdersService;
import cn.story.utils.BaseServlet;
import cn.story.utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrdersServlet extends BaseServlet {

	public String buyProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*商品的名字（oname）  , 商品的图片（oimage）,商品的价格（oprice）
        商品的数量(ocount),  购买商品的时间(ordertime)*/
		//(购买之前先登录)判断用户是否登录
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			request.setAttribute("msg", "请先登录");
			return "/jsp/msg.jsp";
		}
		
//		接收参数
		String oname = request.getParameter("oname");
		String oimage = request.getParameter("oimage");
		Double  price = Double.parseDouble(request.getParameter("oprice"));
		int ocount = Integer.parseInt(request.getParameter("ocount"));
//		封装参数
		Orders os = new Orders();
		os.setOid(UUIDUtils.getUuids());//封装oid
		os.setOname(oname);
		os.setOimage(oimage);
		os.setOprice(price);
		os.setOcount(ocount);
		os.setOrdertime(new Date());		
//		调用service层
		OrdersService oce = new OrdersService();
		try {
			oce.buyProducts(os);  //购买向数据库插入数据
			//购买完成 我们希望跳转到购买的列表页面
			String pth = request.getContextPath()+"/OrdersServlet?method=orderlist";
			
			response.sendRedirect(pth);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		调用dao层
		return null;

	}
	
	//查询已经购买的商品
	public String orderlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(购买之前先登录)判断用户是否登录
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			request.setAttribute("msg", "请先登录");
			return "/jsp/msg.jsp";
		}
		
		//--------、查询
		//步骤一：调用service层
		OrdersService os = new OrdersService();
		try {
			List<Orders> olist = os.orderlist();
			
			request.setAttribute("list", olist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//调用dao层（查询数据库）
		
		return "/jsp/order_list.jsp";
		
	}

}