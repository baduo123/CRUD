package view;

import java.util.HashMap;
import java.util.Map;

import util.DAOinterface;
import util.DBUtil;
import util.annotion.DAO;

@DAO("dao.userDAO")
public class update {
	public static void main(String[] args) throws Exception{
		DAO dao = update.class.getAnnotation(DAO.class);
		Class clzz = Class.forName(dao.value());
		DAOinterface di = (DAOinterface)clzz.newInstance();
		di.setInfo(DBUtil.getConnection());
		Map<Object, Object>map = new HashMap<>();
		Map<Object, Object>map2 = new HashMap<>();
		map.put("pass", "2008");
		map.put("uname", "丁猫");
		map2.put("id", "3");
		int a = di.update(map, map2);
		System.out.println(a+"条数据有影响");
	}
}
