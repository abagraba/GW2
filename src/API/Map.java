package API;

public class Map {

	public final int id;
	public final String name;
	
	public Map(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
