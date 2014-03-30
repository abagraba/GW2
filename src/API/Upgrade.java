package API;

import java.util.Comparator;
import java.util.PriorityQueue;



public enum Upgrade {
	ore12("Ore Upgrade T1-T2", new Items[] { Items.oret1, Items.oret2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.oret2, 40, 190), /**/
	ore24("Ore Upgrade T2-T4", new Items[] { Items.oret2, Items.oret4, Items.dust3 }, new int[] { 250, 1, 5 }, Items.oret4, 20, 190), /**/
	ore45("Ore Upgrade T4-T5", new Items[] { Items.oret4, Items.oret5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.oret5, 40, 190), /**/
	ore56("Ore Upgrade T5-T6", new Items[] { Items.oret5, Items.oret6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.oret6, 10, 40), /**/

	jore23("Jewelery Ore Upgrade T2-T3", new Items[] { Items.joret2, Items.joret3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.joret3, 40, 190), /**/
	jore34("Jewelery Ore Upgrade T3-T4", new Items[] { Items.joret3, Items.oret4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.oret4, 40, 190), /**/

	/**/
	rore12("Bar Upgrade T1-T2", new Items[] { Items.bart1, Items.bart2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.bart2, 40, 200), /**/
	rore23("Bar Upgrade T2-T3", new Items[] { Items.bart2, Items.bart3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.bart3, 20, 190), /**/
	rore45("Bar Upgrade T4-T5", new Items[] { Items.bart4, Items.bart5, Items.dust4 }, new int[] { 250, 1, 5 }, Items.bart5, 40, 190), /**/
	rore56("Bar Upgrade T5-T6", new Items[] { Items.bart5, Items.bart6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.bart6, 10, 40), /**/

	rjore12("Jewelery Bar Upgrade T1-T2", new Items[] { Items.jbart1, Items.jbart2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.jbart2, 40, 190), /**/
	rjore23("Jewelery Bar Upgrade T2-T3", new Items[] { Items.jbart2, Items.jbart3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.jbart3, 40, 190), /**/
	rjore34("Jewelery Bar Upgrade T3-T4", new Items[] { Items.jbart3, Items.jbart4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.jbart4, 40, 190), /**/
	rjore45("Jewelery Bar Upgrade T4-T5", new Items[] { Items.jbart4, Items.bart5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.bart5, 40, 190), /**/

	/**/
	log12("Log Upgrade T1-T2", new Items[] { Items.logt1, Items.logt2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.logt2, 40, 190), /**/
	log23("Log Upgrade T2-T3", new Items[] { Items.logt2, Items.logt3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.logt3, 40, 190), /**/
	log34("Log Upgrade T3-T4", new Items[] { Items.logt3, Items.logt4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.logt4, 40, 190), /**/
	log45("Log Upgrade T4-T5", new Items[] { Items.logt4, Items.logt5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.logt5, 40, 190), /**/
	log56("Log Upgrade T5-T6", new Items[] { Items.logt5, Items.logt6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.logt6, 10, 40), /**/

	/**/
	rlog12("Plank Upgrade T1-T2", new Items[] { Items.plat1, Items.plat2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.plat2, 40, 190), /**/
	rlog23("Plank Upgrade T2-T3", new Items[] { Items.plat2, Items.plat3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.plat3, 40, 190), /**/
	rlog34("Plank Upgrade T3-T4", new Items[] { Items.plat3, Items.plat4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.plat4, 40, 190), /**/
	rlog45("Plank Upgrade T4-T5", new Items[] { Items.plat4, Items.plat5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.plat5, 40, 190), /**/
	rlog56("Plank Upgrade T5-T6", new Items[] { Items.plat5, Items.plat6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.plat6, 10, 40), /**/

	/**/
	clo12("Scrap Upgrade T1-T2", new Items[] { Items.clot1, Items.clot2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.clot2, 40, 190), /**/
	clo23("Scrap Upgrade T2-T3", new Items[] { Items.clot2, Items.clot3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.clot3, 40, 190), /**/
	clo34("Scrap Upgrade T3-T4", new Items[] { Items.clot3, Items.clot4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.clot4, 40, 190), /**/
	clo45("Scrap Upgrade T4-T5", new Items[] { Items.clot4, Items.clot5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.clot5, 40, 190), /**/
	clo56("Scrap Upgrade T5-T6", new Items[] { Items.clot5, Items.clot6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.clot6, 10, 40), /**/

	/**/
	rclo12("Bolt Upgrade T1-T2", new Items[] { Items.bolt1, Items.bolt2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.bolt2, 40, 190), /**/
	rclo23("Bolt Upgrade T2-T3", new Items[] { Items.bolt2, Items.bolt3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.bolt3, 40, 190), /**/
	rclo34("Bolt Upgrade T3-T4", new Items[] { Items.bolt3, Items.bolt4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.bolt4, 40, 190), /**/
	rclo45("Bolt Upgrade T4-T5", new Items[] { Items.bolt4, Items.bolt5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.bolt5, 40, 190), /**/
	rclo56("Bolt Upgrade T5-T6", new Items[] { Items.bolt5, Items.bolt6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.bolt6, 10, 40), /**/

	/**/
	lea12("Section Upgrade T1-T2", new Items[] { Items.leat1, Items.leat2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.leat2, 40, 190), /**/
	lea23("Section Upgrade T2-T3", new Items[] { Items.leat2, Items.leat3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.leat3, 40, 190), /**/
	lea34("Section Upgrade T3-T4", new Items[] { Items.leat3, Items.leat4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.leat4, 40, 190), /**/
	lea45("Section Upgrade T4-T5", new Items[] { Items.leat4, Items.leat5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.leat5, 40, 190), /**/
	lea56("Section Upgrade T5-T6", new Items[] { Items.leat5, Items.leat6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.leat6, 10, 40), /**/

	/**/
	rlea12("Square Upgrade T1-T2", new Items[] { Items.sqat1, Items.sqat2, Items.dust2 }, new int[] { 250, 1, 5 }, Items.sqat2, 40, 190), /**/
	rlea23("Square Upgrade T2-T3", new Items[] { Items.sqat2, Items.sqat3, Items.dust3 }, new int[] { 250, 1, 5 }, Items.sqat3, 40, 190), /**/
	rlea34("Non-functioning Square Upgrade T3-T4", new Items[] { Items.sqat3, Items.sqat4, Items.dust4 }, new int[] { 250, 1, 5 }, Items.sqat4, 40, 190), /**/
	rlea45("Square Upgrade T4-T5", new Items[] { Items.sqat4, Items.sqat5, Items.dust5 }, new int[] { 250, 1, 5 }, Items.sqat5, 40, 190), /**/
	rlea56("Square Upgrade T5-T6", new Items[] { Items.sqat5, Items.sqat6, Items.dust6 }, new int[] { 250, 1, 5 }, Items.sqat6, 10, 40), /**/

	/**/
	dust12("Dust Upgrade T1-T2", new Items[] { Items.dust1, Items.dust2 }, new int[] { 250, 1 }, Items.dust2, 40, 200), /**/
	dust23("Dust Upgrade T2-T3", new Items[] { Items.dust2, Items.dust3 }, new int[] { 250, 1 }, Items.dust3, 40, 200), /**/
	dust34("Dust Upgrade T3-T4", new Items[] { Items.dust3, Items.dust4 }, new int[] { 250, 1 }, Items.dust4, 40, 200), /**/
	dust45("Dust Upgrade T4-T5", new Items[] { Items.dust4, Items.dust5 }, new int[] { 250, 1 }, Items.dust5, 40, 200), /**/
	dust56("Dust Upgrade T5-T6", new Items[] { Items.dust5, Items.dust6 }, new int[] { 250, 1 }, Items.dust6, 6, 40), /**/

	/**/
	fbont12("Bone Upgrade T1-T2", new Items[] { Items.fbont1, Items.fbont2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.fbont2, 7, 40), /**/
	fbont23("Bone Upgrade T2-T3", new Items[] { Items.fbont2, Items.fbont3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.fbont3, 7, 40), /**/
	fbont34("Bone Upgrade T3-T4", new Items[] { Items.fbont3, Items.fbont4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.fbont4, 7, 40), /**/
	fbont45("Bone Upgrade T4-T5", new Items[] { Items.fbont4, Items.fbont5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.fbont5, 7, 40), /**/
	fbont56("Bone Upgrade T5-T6", new Items[] { Items.fbont5, Items.fbont6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.fbont6, 5, 12), /**/

	/**/
	fclat12("Claw Upgrade T1-T2", new Items[] { Items.fclat1, Items.fclat2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.fclat2, 7, 40), /**/
	fclat23("Claw Upgrade T2-T3", new Items[] { Items.fclat2, Items.fclat3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.fclat3, 7, 40), /**/
	fclat34("Claw Upgrade T3-T4", new Items[] { Items.fclat3, Items.fclat4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.fclat4, 7, 40), /**/
	fclat45("Claw Upgrade T4-T5", new Items[] { Items.fclat4, Items.fclat5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.fclat5, 7, 40), /**/
	fclat56("Claw Upgrade T5-T6", new Items[] { Items.fclat5, Items.fclat6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.fclat6, 5, 12), /**/

	/**/
	ffant12("Fang Upgrade T1-T2", new Items[] { Items.ffant1, Items.ffant2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.ffant2, 7, 40), /**/
	ffant23("Fang Upgrade T2-T3", new Items[] { Items.ffant2, Items.ffant3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.ffant3, 7, 40), /**/
	ffant34("Fang Upgrade T3-T4", new Items[] { Items.ffant3, Items.ffant4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.ffant4, 7, 40), /**/
	ffant45("Fang Upgrade T4-T5", new Items[] { Items.ffant4, Items.ffant5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.ffant5, 7, 40), /**/
	ffant56("Fang Upgrade T5-T6", new Items[] { Items.ffant5, Items.ffant6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.ffant6, 5, 12), /**/

	/**/
	fscat12("Scale Upgrade T1-T2", new Items[] { Items.fscat1, Items.fscat2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.fscat2, 7, 40), /**/
	fscat23("Scale Upgrade T2-T3", new Items[] { Items.fscat2, Items.fscat3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.fscat3, 7, 40), /**/
	fscat34("Scale Upgrade T3-T4", new Items[] { Items.fscat3, Items.fscat4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.fscat4, 7, 40), /**/
	fscat45("Scale Upgrade T4-T5", new Items[] { Items.fscat4, Items.fscat5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.fscat5, 7, 40), /**/
	fscat56("Scale Upgrade T5-T6", new Items[] { Items.fscat5, Items.fscat6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.fscat6, 5, 12), /**/

	/**/
	ftott12("Totem Upgrade T1-T2", new Items[] { Items.ftott1, Items.ftott2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.ftott2, 7, 40), /**/
	ftott23("Totem Upgrade T2-T3", new Items[] { Items.ftott2, Items.ftott3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.ftott3, 7, 40), /**/
	ftott34("Totem Upgrade T3-T4", new Items[] { Items.ftott3, Items.ftott4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.ftott4, 7, 40), /**/
	ftott45("Totem Upgrade T4-T5", new Items[] { Items.ftott4, Items.ftott5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.ftott5, 7, 40), /**/
	ftott56("Totem Upgrade T5-T6", new Items[] { Items.ftott5, Items.ftott6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.ftott6, 5, 12), /**/

	/**/
	fsact12("Venom Sac Upgrade T1-T2", new Items[] { Items.fsact1, Items.fsact2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.fsact2, 7, 40), /**/
	fsact23("Venom Sac Upgrade T2-T3", new Items[] { Items.fsact2, Items.fsact3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.fsact3, 7, 40), /**/
	fsact34("Venom Sac Upgrade T3-T4", new Items[] { Items.fsact3, Items.fsact4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.fsact4, 7, 40), /**/
	fsact45("Venom Sac Upgrade T4-T5", new Items[] { Items.fsact4, Items.fsact5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.fsact5, 7, 40), /**/
	fsact56("Venom Sac Upgrade T5-T6", new Items[] { Items.fsact5, Items.fsact6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.fsact6, 5, 12), /**/

	/**/
	fblot12("Blood Upgrade T1-T2", new Items[] { Items.fblot1, Items.fblot2, Items.dust2 }, new int[] { 50, 1, 5 }, Items.fblot2, 7, 40), /**/
	fblot23("Blood Upgrade T2-T3", new Items[] { Items.fblot2, Items.fblot3, Items.dust3 }, new int[] { 50, 1, 5 }, Items.fblot3, 7, 40), /**/
	fblot34("Blood Upgrade T3-T4", new Items[] { Items.fblot3, Items.fblot4, Items.dust4 }, new int[] { 50, 1, 5 }, Items.fblot4, 7, 40), /**/
	fblot45("Blood Upgrade T4-T5", new Items[] { Items.fblot4, Items.fblot5, Items.dust5 }, new int[] { 50, 1, 5 }, Items.fblot5, 7, 40), /**/
	fblot56("Blood Upgrade T5-T6", new Items[] { Items.fblot5, Items.fblot6, Items.dust6 }, new int[] { 50, 1, 5 }, Items.fblot6, 5, 12), /**/

	;
	private Recipe	recipe;

	private Upgrade(String name, Items[] ingredients, int[] count, Items product, int minAmount, int maxAmount) {
		recipe = new Recipe(name, ingredients, count, product, minAmount, maxAmount);
	}

	public static void displayOptimal() {
		PriorityQueue<Recipe> queue = new PriorityQueue<Recipe>(25, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe o1, Recipe o2) {
				return (int) (o1.weightedValue() - o2.weightedValue());
			}
		});
		for (Upgrade upgrade : values()) {
			Recipe recipe = upgrade.recipe;
			if (recipe.safe())
				queue.add(recipe);
		}
		while (!queue.isEmpty())
			queue.poll().printRecipe();
	}
}
