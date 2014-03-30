package Market;

import java.util.HashMap;
import java.util.LinkedList;



public class Initializer {

	public static HashMap<String, Item>		items	= new HashMap<String, Item>();
	public static HashMap<String, Recipe>	recipes	= new HashMap<String, Recipe>();

	public static LinkedList<Recipe> priority = new LinkedList<Recipe>();
	
	public static void addItem(String name, String id) {
		System.out.println("New Item:  \t" + name + "\t[" + id + "]");
		items.put(name, new Item(extractID(id)));
	}

	public static void addRecipe(String name, Input[] inputs, Output output) {
		System.out.println("New Recipe:\t" + name);
		recipes.put(name, new Recipe(name, inputs, output));
	}

	private static byte getVal(char c) {
		if (c >= 'A' && c <= 'Z')
			return (byte) (c - 'A');
		if (c >= 'a' && c <= 'z')
			return (byte) (26 + c - 'a');
		if (c >= '0' && c <= '9')
			return (byte) (52 + c - '0');
		if (c == '+')
			return 62;
		if (c == '/')
			return 63;
		return -1;
	}

	/* TODO: OPTIMIZE */
	private static int extractID(String s) {
		if (s.length() != 8)
			return -1;
		int a = ((getVal(s.charAt(2)) & 0x03) << 6) + (getVal(s.charAt(3)));
		int b = ((getVal(s.charAt(4)) & 0x3F) << 2) + ((getVal(s.charAt(5)) & 0x30) >> 4);
		int c = ((getVal(s.charAt(5)) & 0x0F) << 4) + ((getVal(s.charAt(6)) & 0x3C) >> 2);
		return a + b * 256 + c * 65536;
	}

	public static void init() {
		System.out.println();
		for (Item item : items.values()){
			System.out.println("Updating:\t" + item);
			item.updateCraftCost();
		}
	}

	public static Item getItem(String name) {
		return items.get(name);
	}

	public static void prioritize(String name) {
		priority.add(recipes.get(name));
	}
}
