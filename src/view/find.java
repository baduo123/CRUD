package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.User_dto;
import util.DAOinterface;
import util.DBUtil;
import util.MapUtil;
import util.annotion.DAO;

@DAO("dao.userDAO")
public class find {
	public static void main(String[] args) throws Exception{
		DAO dao = find.class.getAnnotation(DAO.class);
		Class clzz = Class.forName(dao.value());
		DAOinterface di = (DAOinterface)clzz.newInstance();
		di.setInfo(DBUtil.getConnection());
		
		Object object = di.findById(2);
		//System.out.println(object);
		List<Object>list = di.findAll();
//		for (Object object2 : list) {
//			System.out.println(object2);
//		}
		
		Map<String, String>map = new HashMap<>();
		map.put("pass", "321");
		List<Object>list2 =  di.findByProperty(map);
		System.out.println(list2);
		
		
	}
	
	

}
