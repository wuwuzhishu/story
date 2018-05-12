package cn.story.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import cn.story.domain.User;
import cn.story.utils.JdbcUtils;

public class UserDao {
    //作用是操作数据库
	public 	void register(User u) throws Exception {

		/*ComboPooledDataSource dataSource = new ComboPooledDataSource(); *///数据源
		/*dataSource.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver            
		dataSource.setJdbcUrl("jdbc:mysql:///stroy01");
		dataSource.setUser("root");                                  
		dataSource.setPassword("root");      */                                 
		//创建操作数据库的核心对象
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into user values(?,?,?)";
		//执行
		qr.update(sql, u.getUid(),u.getUsername(),u.getPassword());  //sql可以指的是 增删改的方法 update（）。  查询 query（）	
	}

	public User ajaxByname(String username) throws Exception {
		//创建操作数据库的核心对象
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		//sql语句
		String sql = "select * from user where username=? ";
		//执行操作
		User user = qr.query(sql, new BeanHandler<User>(User.class), username);
		return user;
	}

	//查询操作 (用户登录)
	public User login(User u) throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from user where username=? and password=? ";
		//执行查询操作
		User user = qr.query(sql, new BeanHandler<User>(User.class), u.getUsername(),u.getPassword());
		return user;
	}

}
