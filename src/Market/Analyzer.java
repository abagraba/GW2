package Market;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;



public class Analyzer {

	public static PriorityQueue<Recipe> analyze(Collection<Recipe> recipes) {
		PriorityQueue<Recipe> results = new PriorityQueue<Recipe>(128, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe o1, Recipe o2) {
				return o1.netProfit()-o2.netProfit();
			}});
		for(Recipe recipe : recipes)
			if (recipe.getProfit() > recipe.getCost())
				results.add(recipe);
		return results;
	}
}
