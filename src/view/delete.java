package view;

import java.util.HashMap;
import java.util.Map;

import util.DAOinterface;
import util.DBUtil;
import util.annotion.DAO;

@DAO("dao.userDAO")
public class delete {
	public static void main(String[] args) throws Exception{
		DAO dao = delete.class.getAnnotation(DAO.class);
		Class clzz = Class.forName(dao.value());
		DAOinterface di = (DAOinterface)clzz.newInstance();
		di.setInfo(DBUtil.getConnection());
		
//		int b = di.delecteById(11);
//		System.out.println("对"+b+"条数据有影响");
//		if (b>0) {
//			//di.save();
//			System.out.println("删除成功");
//			
//		}else {
//			System.out.println("删除失败");
//		}
		
		Map<Object, Object>map = new HashMap<>();
		map.put("uname", "王五");
		int a = di.delecteByProperty(null);
		System.out.println(a);
		
		
		
	}
}
