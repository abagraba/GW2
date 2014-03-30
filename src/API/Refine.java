package API;

import java.util.Comparator;
import java.util.PriorityQueue;

public enum Refine{
	
	bar1("Refine Bar T1", new Items[] { Items.oret1, Items.soret1 }, new int[] { 10, 1 }, Items.bart1, 5), /**/
	bar2("Refine Bar T2", new Items[] { Items.oret2 }, new int[] { 3 }, Items.bart2, 1), /**/
	bar3("Refine Bar T3", new Items[] { Items.oret2, Items.soret3 }, new int[] { 3, 1 }, Items.bart3, 1), /**/
	bar4("Refine Bar T4", new Items[] { Items.oret4, Items.soret4 }, new int[] { 2, 1 }, Items.bart4, 1), /**/
	bar5("Refine Bar T5", new Items[] { Items.oret5 }, new int[] { 2 }, Items.bart5, 1), /**/
	bar6("Refine Bar T6", new Items[] { Items.oret6 }, new int[] { 2 }, Items.bart6, 1), /**/

	jbar1("Refine Jewelery Bar T1", new Items[] { Items.oret1 }, new int[] { 2 }, Items.jbart1, 1), /**/
	jbar2("Refine Jewelery Bar T2", new Items[] { Items.joret2 }, new int[] { 2 }, Items.jbart2, 1), /**/
	jbar3("Refine Jewelery Bar T3", new Items[] { Items.joret3 }, new int[] { 2 }, Items.jbart3, 1), /**/
	jbar4("Refine Jewelery Bar T4", new Items[] { Items.oret4 }, new int[] { 2 }, Items.jbart4, 1), /**/

	pla1("Refine Plank T1", new Items[] { Items.logt1 }, new int[] { 3 }, Items.plat1, 1), /**/
	pla2("Refine Plank T2", new Items[] { Items.logt2 }, new int[] { 4 }, Items.plat2, 1), /**/
	pla3("Refine Plank T3", new Items[] { Items.logt3 }, new int[] { 3 }, Items.plat3, 1), /**/
	pla4("Refine Plank T4", new Items[] { Items.logt4 }, new int[] { 3 }, Items.plat4, 1), /**/
	pla5("Refine Plank T5", new Items[] { Items.logt5 }, new int[] { 3 }, Items.plat5, 1), /**/
	pla6("Refine Plank T6", new Items[] { Items.logt6 }, new int[] { 3 }, Items.plat6, 1), /**/

	sqa1("Refine Square T1", new Items[] { Items.leat1 }, new int[] { 2 }, Items.sqat1, 1), /**/
	sqa2("Refine Square T2", new Items[] { Items.leat2 }, new int[] { 2 }, Items.sqat2, 1), /**/
	sqa3("Refine Square T3", new Items[] { Items.leat3 }, new int[] { 2 }, Items.sqat3, 1), /**/
	sqa4("Refine Square T4", new Items[] { Items.leat4 }, new int[] { 2 }, Items.sqat4, 1), /**/
	sqa5("Refine Square T5", new Items[] { Items.leat5 }, new int[] { 3 }, Items.sqat5, 1), /**/
	sqa6("Refine Square T6", new Items[] { Items.leat6 }, new int[] { 2 }, Items.sqat6, 1), /**/

	bol1("Refine Bolt T1", new Items[] { Items.clot1 }, new int[] { 2 }, Items.bolt1, 1), /**/
	bol2("Refine Bolt T2", new Items[] { Items.clot2 }, new int[] { 2 }, Items.bolt2, 1), /**/
	bol3("Refine Bolt T3", new Items[] { Items.clot3 }, new int[] { 2 }, Items.bolt3, 1), /**/
	bol4("Refine Bolt T4", new Items[] { Items.clot4 }, new int[] { 2 }, Items.bolt4, 1), /**/
	bol5("Refine Bolt T5", new Items[] { Items.clot5 }, new int[] { 3 }, Items.bolt5, 1), /**/
	bol6("Refine Bolt T6", new Items[] { Items.clot6 }, new int[] { 2 }, Items.bolt6, 1), /**/

	/**/

	abar("Ascended Bar", new Items[] {Items.bart2, Items.bart3, Items.bart4, Items.bart5, Items.ecto, Items.thermo}, new int[]{20, 10, 20, 50, 1, 10}, Items.abar, 1),
	apla("Ascended Plank", new Items[] {Items.plat2, Items.plat3, Items.plat4, Items.plat5, Items.ecto, Items.thermo}, new int[]{20, 10, 20, 50, 1, 10}, Items.apla, 1),
	abol("Ascended Bolt", new Items[] {Items.bolt2, Items.bolt3, Items.bolt4, Items.bolt5, Items.ecto, Items.tclot6}, new int[]{20, 10, 20, 100, 1, 25}, Items.abol, 1),
	asqa("Ascended Square", new Items[] {Items.sqat2, Items.sqat3, Items.sqat4, Items.sqat5, Items.ecto, Items.thermo}, new int[]{20, 10, 20, 50, 1, 10}, Items.asqa, 1),
	
	;
	private Recipe recipe;
	
	private Refine(String name, Items[] ingredients, int[] count, Items product, int amount){
		recipe = new Recipe(name, ingredients, count, product, amount);
	}
	
	public static void displayOptimal() {
		PriorityQueue<Recipe> queue = new PriorityQueue<Recipe>(25, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe o1, Recipe o2) {
				return (int) (o1.weightedValue() - o2.weightedValue());
			}
		});
		for (Refine refine : values()) {
			Recipe recipe = refine.recipe;
			if (recipe.safe())
				queue.add(recipe);
		}
		while (!queue.isEmpty())
			queue.poll().printRecipe();
	}
	
}
