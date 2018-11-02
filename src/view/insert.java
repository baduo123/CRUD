package view;

import java.util.HashMap;
import java.util.Map;

import util.DAOinterface;
import util.DBUtil;
import util.annotion.DAO;

@DAO("dao.userDAO")
public class insert {
	public static void main(String[] args) throws Exception{
		DAO dao = insert.class.getAnnotation(DAO.class);
		Class clzz = Class.forName(dao.value());
		DAOinterface di = (DAOinterface)clzz.newInstance();
		di.setInfo(DBUtil.getConnection());
		Map<Object,Object>map = new HashMap<>();
		map.put("uname", "王八八");
		map.put("account", "admin");
		map.put("pass", "1997");
		int a = di.insert(map);
		System.out.println("对"+a+"条数据有影响");
	}
}
