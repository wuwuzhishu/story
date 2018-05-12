package cn.story.service;

import java.util.List;

import cn.story.dao.CategoryDao;
import cn.story.domain.Category;

public class CategoryService {
    //调用dao层  
	CategoryDao cd  = new CategoryDao();
	public List<Category> findCg() throws Exception {
		
		return cd.findCg();
	}

}
