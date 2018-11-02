package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.annotion.Column;
import util.annotion.ID;
import util.annotion.PO;
import util.annotion.TABLE;

public class DAOTemplet {
	private String poClass = "";
	private String tableName = "";
	public Object findByid(int id, Connection con, DAOinterface di) throws Exception{
		StringBuffer sql = new StringBuffer("select ");
		PO po = (PO)di.getClass().getAnnotation(PO.class);
		if (po != null) {
			poClass = po.value();
		}
		Class poName = Class.forName(poClass);
		Field[]fields = poName.getDeclaredFields();
		String newId = "";
		for (Field field : fields) {
			if (field.getAnnotation(ID.class)!= null) {
				newId = field.getAnnotation(ID.class).value();
			}
			sql.append(field.getAnnotation(Column.class).value());
			sql.append(",");
		}
		
		sql.deleteCharAt(sql.length()-1);
 
		TABLE table = (TABLE)poName.getAnnotation(TABLE.class);
		if (table != null) {
			tableName = table.value();
		}	
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(newId);
		sql.append("=");
		sql.append(id);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql.toString());
		while (rs.next()) {
			Object obj = poName.newInstance();
			for (Field field : fields) {
				String string = field.getName();
				String methodName = "set"+string.substring(0, 1).toUpperCase()+string.substring(1);
				Class type = field.getType();
				Method method = poName.getMethod(methodName, type);
				method.invoke(obj, rs.getObject(string)); 
			}
			return obj;
		}
		return null;
	}
	
	public int deleteById(int id, Connection con,DAOinterface di) throws Exception{
		con.setAutoCommit(false);
		StringBuffer sql = new StringBuffer("delete from ");
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Class po = Class.forName(poClass.value());
		TABLE table =(TABLE) po.getAnnotation(TABLE.class);
		sql.append(table.value());
		sql.append(" where ");
		Field[] field = po.getDeclaredFields();
		for (Field field2 : field) {
			if (field2.getAnnotation(ID.class) != null) {
				sql.append(field2.getAnnotation(ID.class).value());
			}
		}
		sql.append(" = ");
		sql.append(id);
		//System.out.println(sql);
		Statement s = con.createStatement();
		return s.executeUpdate(sql.toString());
	}

	public List findAll(Connection con, DAOinterface di) throws Exception{
		StringBuffer sql = new StringBuffer("select ");
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Map map = ProxyXML.readProperty(poClass.value());
		Set<String>keyset = map.keySet();
		for (String string : keyset) {
			sql.append(string);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" from ");
		Class po = Class.forName(poClass.value());
		TABLE table = (TABLE)po.getAnnotation(TABLE.class);
		sql.append(table.value());
		Statement s= con.createStatement();
		ResultSet rs = s.executeQuery(sql.toString());
		List list = new ArrayList();
		while (rs.next()) {
			Object obj = po.newInstance();
			for (String string : keyset) {
				String methodName ="set"+ string.substring(0, 1).toUpperCase()+string.substring(1);
				Class type = Class.forName(map.get(string).toString());
				Method method = obj.getClass().getMethod(methodName, type);
				method.invoke(obj, rs.getObject(string));
			}
			list.add(obj);
		}
		return list;
	}

	public List findByProperty(Map maps, DAOinterface di, Connection con) throws Exception{
		StringBuffer sql = new StringBuffer("select ");
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Map map = ProxyXML.readProperty(poClass.value());
		Set<String>keyset = map.keySet();
		for (String string : keyset) {
			sql.append(string);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" from ");
		Class po = Class.forName(poClass.value());
		TABLE table = (TABLE)po.getAnnotation(TABLE.class);
		sql.append(table.value());
		sql.append(" where ");
		if (maps != null && maps.size()>0) {
			MapUtil.getMap(maps);	
			Set set = maps.entrySet();
			for (Object object : set) {
				sql.append(object.toString());
				sql.append(" and ");
			}
		}else {
			sql.append("1=1 and ");
		}
		sql.delete(sql.length()-5, sql.length()-1);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql.toString());
		List list = new ArrayList();
		while (rs.next()) {
			Object obj = po.newInstance();
			for (String string : keyset) {
				String methodName ="set"+ string.substring(0, 1).toUpperCase()+string.substring(1);
				Class type = Class.forName(map.get(string).toString());
				Method method = obj.getClass().getMethod(methodName, type);
				method.invoke(obj, rs.getObject(string));
			}
			list.add(obj);
		}
		return list;
	}

	public int deleteByProperty(Map infoMap, Connection con,  DAOinterface di) throws Exception{
		con.setAutoCommit(false);
		if (infoMap==null) {
			return 0;
		}
		MapUtil.getMap(infoMap);	
		StringBuffer sql = new StringBuffer("delete from ");
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Class po = Class.forName(poClass.value());
		TABLE table =(TABLE) po.getAnnotation(TABLE.class);
		sql.append(table.value());
		sql.append(" where ");
		Set<String>set = infoMap.keySet();
		for (String string : set) {
			sql.append(string);
			sql.append("= ");
			sql.append(infoMap.get(string).toString());
		}
		System.out.println(sql);
		Statement s = con.createStatement();
		
		return s.executeUpdate(sql.toString());
	}

	public int insert(Map infoMap, Connection con, DAOinterface di) throws Exception{
		con.setAutoCommit(false);
		if (infoMap==null) {
			return 0 ;	
		}
		MapUtil.getMap(infoMap);
		StringBuffer sql = new StringBuffer("insert into ");
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Class po = Class.forName(poClass.value());
		TABLE table =(TABLE) po.getAnnotation(TABLE.class);
		sql.append(table.value());
		sql.append("(");
		Set<String>set = infoMap.keySet();
		for (String str : set) {
			sql.append(str);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")values(");
		for (String string : set) {
			sql.append(infoMap.get(string));
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		Statement s = con.createStatement();
		return s.executeUpdate(sql.toString());
	}


	public int update(Map map, Map map2, Connection con, DAOinterface di) throws Exception{
		con.setAutoCommit(false);
		StringBuffer sql = new StringBuffer("update ");
		if (map==null) {
			return 0;
		}
		MapUtil.getMap(map);
		if (map2!=null) {
			MapUtil.getMap(map2);			
		}
		PO poClass = (PO)di.getClass().getAnnotation(PO.class);
		Class po = Class.forName(poClass.value());
		TABLE table =(TABLE) po.getAnnotation(TABLE.class);
		sql.append(table.value());
		sql.append(" SET ");
		Set set1 = map.entrySet();
		for (Object object : set1) {
			sql.append(object);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(" where ");
		if (map2 != null) {
			Set set2 = map2.entrySet();
			for (Object object : set2) {
				sql.append(object);
			}
		}else {
			sql.append("1 = 1");
		}
		System.out.println(sql);
		Statement s = con.createStatement();
		
		return s.executeUpdate(sql.toString());
	}

	public void save(Connection con) throws Exception{
		con.commit();
		
	}	

}
