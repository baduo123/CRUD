package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.userDAO;
import dao.po.User;
import dto.User_dto;
import util.DAOinterface;
import util.DBUtil;
public class LoginService {
	public  boolean login(User_dto user_dto) throws Exception
	{
		DAOinterface di = new userDAO();
		di.setInfo(DBUtil.getConnection());
		Map<String, String>map = new HashMap<>();
		map.put("account", user_dto.getAccount());
		List<User>list = di.findByProperty(map);
		if (list.size()>0) {
		User user =list.get(0);
		if (user.getAccount().equals(user_dto.getAccount())) {
			if (user.getPass().equals(user_dto.getPassword())) {
				user_dto.setIslogin(true);
				user_dto.setMsg("µÇÂ¼³É¹¦");
				return true;
			}else {
				user_dto.setIslogin(false);
				user_dto.setMsg("ÃÜÂë´íÎó");
			}
		}else {
			user_dto.setIslogin(false);
			user_dto.setMsg("ÕÊºÅ´íÎó");
		}
		
	}
		return false;
	}
}
