package dao;

import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import util.DAOTemplet;
import util.DAOadapter;
import util.annotion.PO;

@PO("dao.po.User")
public class userDAO extends DAOadapter{
	@Override
	public Object findById(int id) throws Exception {
		Object obj = new DAOTemplet().findByid(id,con,this);
		return obj;
	}
	
	@Override
	public List findAll() throws Exception {
		// TODO Auto-generated method stub
		return new DAOTemplet().findAll(con,this);
	}
	
	@Override
	public List findByProperty(Map map) throws Exception {
		// TODO Auto-generated method stub
		return new DAOTemplet().findByProperty(map,this,con);
	}
	
	@Override
	public int delecteById(int id) throws Exception {
		
		return new DAOTemplet().deleteById(id, con, this);
	}

	@Override
	public void save() throws Exception{
		new DAOTemplet().save(con);
	}
	
	@Override
	public int delecteByProperty(Map map) throws Exception {
		
		return new DAOTemplet().deleteByProperty(map,con,this);
	}
	
	@Override
	public int insert(Map map) throws Exception {
		
		return new DAOTemplet().insert(map,con,this);
	}
	
	@Override
	public int update(Map map, Map map2) throws Exception {
		
		return new DAOTemplet().update(map,map2,con,this);
	}
	
}
