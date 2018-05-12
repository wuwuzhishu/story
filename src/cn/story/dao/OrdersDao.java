package cn.story.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.story.domain.Orders;
import cn.story.utils.JdbcUtils;

public class OrdersDao {
    //操作数据库
	public void buyProducts(Orders os) throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		System.out.println("aaaaaaa");
		String sql = "insert into orders values(?,?,?,?,?,?)";
		qr.update(sql, os.getOid(),os.getOname(),
				os.getOimage(),os.getOprice(),os.getOcount(),os.getOrdertime());
		
	}

	public List<Orders> orderlist() throws Exception {
	   QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	   String sql = "select * from orders order by ordertime desc";
	   return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
	}

}
