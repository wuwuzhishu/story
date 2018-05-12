package cn.story.service;

import java.util.List;

import cn.story.dao.OrdersDao;
import cn.story.domain.Orders;

public class OrdersService {
    //调用dao层
	OrdersDao od = new OrdersDao();
	public void buyProducts(Orders os) throws Exception {
	
		od.buyProducts(os);
	}
	public List<Orders> orderlist() throws Exception {
		
		return od.orderlist();
	}

}
