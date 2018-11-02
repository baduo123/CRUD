package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ProxyXML {
	private static Document doc;
	static
	{
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(ProxyXML.class.getResourceAsStream("/dao.xml"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static Map readDao(String name)
	{
		String path = "//connection[@type='"+name+"']";
		Element e = (Element)doc.selectSingleNode(path);
		Map map = new HashMap();
		map.put("url", e.attributeValue("url"));
		map.put("name", e.attributeValue("name"));
		map.put("pass", e.attributeValue("pass"));
		map.put("driver", e.attributeValue("driver"));
		return map;
	}
	
	public static Map readProperty(String name)
	{
		String path = "//po[@class = '"+ name+"']/property";
		List<Element>es = doc.selectNodes(path);
		Map map = new HashMap();
		for (Element element : es) {
			map.put(element.attributeValue("name"), element.attributeValue("type"));
		}
		return map;
	}
	
	
	
}
