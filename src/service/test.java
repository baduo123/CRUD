package service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import dao.po.User;
import util.DBUtil;

public class test {

	public static void main(String[] args) throws Exception{
		Connection con = DBUtil.getConnection();
		User user = new User("ÀîËÄ", "lisi", "321");
		new test().find(user, user.getClass());
		}

	public List find(Object obj,Class clzz)
	throws Exception{
		String sql = "select ";
		Field[]fields = clzz.getDeclaredFields();
		for (Field field : fields) {
			sql = sql + field.getName()+",";
		}
		sql = sql.substring(0, sql.length()-1);
		sql = sql +" from "+ clzz.getSimpleName() +" where 1=1";
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.print (field+"   ");
			System.out.print(field.getName() + "   ");
			System.out.println(field.get(obj));
			if (field.get(obj) != null) {
				sql = sql + " and " + field.getName() + " = '" + field.get(obj)+"'";
			}
		}
		System.out.println(sql);
		Statement s = DBUtil.getConnection().createStatement();
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getObject("account"));
		}
 		return null;
	}


}