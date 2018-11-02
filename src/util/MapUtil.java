package util;

import java.util.Map;
import java.util.Set;

public class MapUtil {
	public static void getMap(Map map)
	{
		Set set = map.keySet();
		for (Object object : set) {
			map.put(object,"'"+map.get(object)+"'");
		}
	}
}
