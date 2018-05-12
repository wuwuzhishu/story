package cn.story.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.story.domain.Product;
import cn.story.utils.JdbcUtils;

public class ProductDao {

	public List<Product> findHot() throws Exception {
		//创建查询 的核心对象limit 参数1（开始的索引值），参数2（查询的条数）|limit ？；一个参数 表示查询数据的条数
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from product where is_hot = ? and pflag = ? limit ? ";
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class), 1,0,9);
	}

	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from product where  pflag = ? order by pdate desc limit ? ";
		return qr.query(sql, new BeanListHandler<Product>(Product.class),0,9);
	}
 //dao层操作数d据库
	public Product findbyid(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select *  from  product where pid = ?";
		
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	public List<Product> findBycid(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from product where cid= ? ";
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid);
	}

}
