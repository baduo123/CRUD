package util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface DAOinterface {
	public abstract List findAll() throws Exception;
	public abstract Object findById(int id) throws Exception;
	public abstract List findByProperty(Map map) throws Exception;
	public abstract int delecteById(int id) throws Exception;
	public abstract int delecteByProperty(Map map) throws Exception;
	public abstract int insert(Map map) throws Exception;
	public abstract int update(Map map,Map map2) throws Exception;
	
	
	public abstract void setInfo(Connection con);
	public abstract void save() throws Exception;

}
