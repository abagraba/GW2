package API;

import java.util.LinkedList;

public class Region {

	public final int id;
	public final String name;
	public final LinkedList<Map> maps = new LinkedList<Map>(); 
	public Region(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public void addMap(Map map){
		maps.add(map);
	}
	
	public String toString(){
		return name;
	}
	
}
