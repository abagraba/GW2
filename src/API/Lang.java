package API;

import java.util.LinkedList;

public class Lang {

	public final String name;
	public final LinkedList<World> worlds = new LinkedList<World>(); 
	
	public Lang(String name){
		this.name = name;
	}
	
	public void addWorld(World world){
		worlds.add(world);
	}
	
	public String toString(){
		return '[' + name + ']';
	}
	
}
