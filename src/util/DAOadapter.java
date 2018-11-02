package util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class DAOadapter implements DAOinterface{
	protected Connection con;
	
	
	
	public void setInfo(Connection con)
	{
		this.con = con;
	}



	@Override
	public List findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List findByProperty(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int delecteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save() throws Exception{
		// TODO Auto-generated method stub
		
	}



	@Override
	public int delecteByProperty(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int insert(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int update(Map map, Map map2) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}




}
