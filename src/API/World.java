package API;

public class World {

	public final int id;
	public final String name;
	
	public World(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
