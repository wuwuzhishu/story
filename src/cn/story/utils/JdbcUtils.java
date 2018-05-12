package cn.story.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//数据源的封装
public class JdbcUtils {

	private static final 	ComboPooledDataSource dataSource = new ComboPooledDataSource(); 
	
	public static DataSource getDataSource(){
		
		return dataSource;
		
	}
	
}
