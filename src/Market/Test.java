package Market;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.PriorityQueue;

import Parsing.GW2Parser;

public class Test {

	public static void testRefine(){
		try {
			new GW2Parser(new FileReader(new File("src/Market/Market.gw"))).run();
			PriorityQueue<Recipe> results = Analyzer.analyze(Initializer.recipes.values());
			while(!results.isEmpty())
				  results.poll().printAll();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}

	public static void testLegendary(){
		try {
			new GW2Parser(new FileReader(new File("src/Market/Legendary.gw"))).run();
			for (Recipe recipe: Initializer.priority)
				  recipe.printAll();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	
	public static void testAscended(){
		try {
			new GW2Parser(new FileReader(new File("src/Market/MAscended.gw"))).run();
			for (Recipe recipe: Initializer.priority)
				  recipe.printAll();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	
	public static void main(String[] args){
		testAscended();
	}
	
}
