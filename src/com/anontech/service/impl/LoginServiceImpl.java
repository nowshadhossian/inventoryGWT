package com.anontech.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anontech.dao.InventoryDao;
import com.anontech.db.User;
import com.anontech.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements
		LoginService {

	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	private HttpServletRequest request;

	@Override
	public User login(String login, String password) {
		User user = null;
		user = inventoryDao.login(login, password);
		if(user != null){
			user.setLoggedIn(true);
			if (user.isLoggedIn()) {
				storeUserInSession(user);
			}
		}
		return user;
	}

	@Override
	public User getLoggedUserFromSession() {
		User user = null;
		HttpServletRequest httpServletRequest = request;
		HttpSession session = httpServletRequest.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj != null && userObj instanceof User) {
			user = (User) userObj;
		}
		return user;
	}

	@Override
	public void logout() {
		deleteUserFromSession();
	}
	
	private void deleteUserFromSession() {
		HttpServletRequest httpServletRequest = request;
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("user");
	}

	private void storeUserInSession(User user) {
		HttpServletRequest httpServletRequest = request;
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("user", user);
	}
}
