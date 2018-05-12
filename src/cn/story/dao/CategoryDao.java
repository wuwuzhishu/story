package cn.story.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.story.domain.Category;
import cn.story.utils.JdbcUtils;

public class CategoryDao {

	public List<Category> findCg() throws Exception {
		//步骤一：创建核心操作对象
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from category";
		
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

}
