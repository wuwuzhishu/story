package cn.story.service;

import cn.story.dao.UserDao;
import cn.story.domain.User;

public class UserService {
    //调用dao层
	UserDao ud = new UserDao();
	public void register(User u) throws Exception {
		
		ud.register(u);
	}
	public User ajaxByname(String username) throws Exception {
		
		return ud.ajaxByname(username);
	}
	public User login(User u) throws Exception {
		
		return ud.login(u);
	}

}
