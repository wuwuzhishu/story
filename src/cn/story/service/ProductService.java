package cn.story.service;

import java.util.List;

import cn.story.dao.ProductDao;
import cn.story.domain.Product;

public class ProductService {
	ProductDao pd = new  ProductDao();
	public List<Product> findHot() throws Exception {
		 
		return pd.findHot();
	}

	public List<Product> findNew() throws Exception {
		return pd.findNew();
	}

	public Product findbyid(String pid) throws Exception {
		
		return pd.findbyid(pid);
	}

	public List<Product> findBycid(String cid) throws Exception {
		
		return pd.findBycid(cid);
	}

}
