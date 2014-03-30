package API;

public enum Legendary {

	wood("Gift of Wood", new Items[] { Items.plat6, Items.plat5, Items.plat4, Items.plat3 }, new int[] { 250, 250, 250, 250 }, Items.wood, 1), /**/
	// metal("Gift of Metal", new Item[] { Item.bart6, Item.bart5, Item.bart4, Item.bart3 }, new int[] { 250, 250, 250,
	// 250 }, Item.metal, 1), /**/

	unicorn("Unicorn Statue", new Items[] { Items.bart6, }, new int[] { 250, 100, 100 }, Items.unicorn, 1), /**/

	dreamergift("Gift of Dreamer", new Items[] { Items.wood, Items.unicorn, Items.runestone }, new int[] { 1, 1, 100 }, Items.dreamergift, 1), /**/

	magic("Gift of Magic", new Items[] { Items.fblot6, Items.fsact6, Items.ftott6, Items.dust6 }, new int[] { 250, 250, 250, 250 }, Items.magic, 1), /**/
	might("Gift of Might", new Items[] { Items.ffant6, Items.fscat6, Items.fclat6, Items.fbont6 }, new int[] { 250, 250, 250, 250 }, Items.might, 1), /**/
	clover("Clover", new Items[] { Items.ecto, Items.coin }, new int[] { 3, 3 }, Items.clover, 1), /**/
	fortune("Gift of Fortune", new Items[] { Items.might, Items.magic, Items.clover, Items.ecto }, new int[] { 1, 1, 77, 250 }, Items.fortune, 1),

	;
	private Recipe			recipe;

	private static Items[]	raw			= new Items[] { Items.bart6, Items.bart5, Items.bart4, Items.bart3, Items.plat6, Items.plat5, Items.plat4,
			Items.plat3, Items.bolt6, Items.bolt5, Items.bolt4, Items.bolt3, Items.sqat6, Items.sqat5, Items.sqat4, Items.sqat3, };
	private static int[]	rawCount	= new int[] { 63, 250, 50, 6, 28, 250, 125, 150, 50, 103, 16, 20, 241, 178, 190, 220 };

	private static Items[]	fine		= new Items[] { Items.fblot6, Items.fbont6, Items.fclat6, Items.ffant6, Items.fscat6, Items.ftott6, Items.fsact6 };
	private static int[]	fineCount	= new int[] { 42, 41, 40, 31, 31, 31, 36 };

	private static Items[]	other		= new Items[] { Items.ecto, Items.clover };
	private static int[]	otherCount	= new int[] { 72, 14 };

	private Legendary(String name, Items[] ingredients, int[] count, Items product, int amount) {
		recipe = new Recipe(name, ingredients, count, product, amount);
	}

	public static void displayCosts() {
		Recipe.cutTP = false;
		for (Legendary gift : values()) {
			Recipe r = gift.recipe.clone();
			for (int k = 0; k < r.ingredients.length && k < r.ingredientCount.length; k++) {
				for (int i = 0; i < raw.length && i < rawCount.length; i++)
					if (raw[i] == r.ingredients[k]) {
						int min = Math.min(rawCount[i], r.ingredientCount[k]);
						rawCount[i] -= min;
						r.ingredientCount[k] -= min;
					}
				for (int i = 0; i < fine.length && i < fineCount.length; i++)
					if (fine[i] == r.ingredients[k]) {
						int min = Math.min(fineCount[i], r.ingredientCount[k]);
						fineCount[i] -= min;
						r.ingredientCount[k] -= min;
					}
				for (int i = 0; i < other.length && i < otherCount.length; i++)
					if (other[i] == r.ingredients[k]) {
						int min = Math.min(otherCount[i], r.ingredientCount[k]);
						otherCount[i] -= min;
						r.ingredientCount[k] -= min;
					}
			}

			r.printCost();
		}
	}

	public static void main(String[] args) {
		System.out.println(Items.unicorn);
		Legendary.displayCosts();
	}

}
