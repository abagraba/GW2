package API;

import java.util.Arrays;



public class Recipe {

	public static final boolean	bestChoice	= true;
	public static boolean		cutTP		= true;
	public static boolean		log			= true;

	private String				name;

	Items[]						ingredients;
	int[]						ingredientCount;

	private Items				product;
	int							minproductCount;
	int							maxproductCount;

	private int					cost, minProfit, maxProfit, breakeven;
	private final float			safety;

	public Recipe(String name, Items[] ingredients, int[] count, Items product, int minAmount, int maxAmount) {
		this.name = name;
		this.ingredients = ingredients;
		this.ingredientCount = count;
		this.product = product;
		this.minproductCount = minAmount;
		this.maxproductCount = maxAmount;
		for (int i = 0; i < ingredients.length && i < ingredientCount.length; i++)
			cost += ingredients[i].cost(ingredientCount[i], cutTP ? 1 : 0);
		minProfit = product.profit(minproductCount, cutTP ? 1 : 0);
		maxProfit = product.profit(maxproductCount, cutTP ? 1 : 0);
		safety = safety();
		breakeven = breakeven();
		if (maxAmount == -1) {
			int unitCost = cost / minAmount;
			if (unitCost < product.craftCost)
				product.craftCost = unitCost;
		}
	}

	public Recipe(String name, Items[] ingredients, int[] count, Items product, int amount) {
		this(name, ingredients, count, product, amount, -1);
	}

	private int logcost() {
		int cost = 0;
		for (int i = 0; i < ingredients.length && i < ingredientCount.length; i++) {
			int c = ingredients[i].logcost(ingredientCount[i], cutTP ? 1 : 0);
			cost += c;
		}
		return cost;
	}

	private int logminProfit() {
		return product.logprofit(minproductCount, cutTP ? 1 : 0);
	}

	private int logmaxProfit() {
		return product.logprofit(maxproductCount, cutTP ? 1 : 0);
	}

	private float safety() {
		if (maxproductCount == -1)
			return -1;
		if (minProfit > cost)
			return -1;
		return (float) (maxProfit - cost) / (maxProfit - minProfit);
	}

	private int breakeven() {
		if (safety == -1)
			return -1;
		return (int) Math.ceil(maxproductCount - (safety * (maxproductCount - minproductCount)));
	}

	public int weightedValue() {
		if (maxproductCount == -1)
			return minProfit - cost;
		return (int) ((maxProfit - minProfit) * safety) - cost;
	}

	public boolean safe() {
		return minProfit > 0 || (maxProfit > 0 && safety > 0.7);
	}

	public void printCost() {
		int c = logcost();
		if (product.craftCost > c)
			product.craftCost = c;
		if (product.craftCost < cost)
			cost = product.craftCost;
		System.out.println();
		if (Recipe.log)
			System.out.println(name + "\n\t\t~\t\t\t\t\t\t\t\t\t\t\t" + "Total Cost:\t" + Recipe.cash(cost));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public void printRecipe() {
		logcost();
		System.out.println();
		if (Recipe.log)
			System.out.println("\t\t~\t\t\t\t\t\t\t\t\t\t\t" + "Total Cost:\t" + Recipe.cash(cost));
		logminProfit();
		if (maxproductCount == -1) {
			System.out.println(name + "\n\tNet Profit:\t" + cash(minProfit - cost));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			return;
		}
		logmaxProfit();
		System.out.println(name + "\n\tMinimum Profit:\t" + cash(minProfit - cost) + "\n\tMaximum Profit:\t" + cash(maxProfit - cost)
				+ (safety != -1 ? "\n\t\tSafety Margin:\t" + safety * 100 + "%" : "") + (breakeven != -1 ? "\n\t\tBreak Even:\t" + breakeven : ""));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public Recipe clone() {
		return new Recipe(name, ingredients, Arrays.copyOf(ingredientCount, ingredientCount.length), product, minproductCount, maxproductCount);
	}

	public void decrement(int[] quantities) {
		for (int i = 0; i < ingredientCount.length && i < quantities.length; i++)
			ingredientCount[i] -= quantities[i];
	}

	public static void main(String[] args) {
		boolean refine = false;

		if (refine)
			Refine.displayOptimal();
		else
			Upgrade.displayOptimal();
	}

	public static String cash(int val) {
		boolean negative = val < 0;
		if (negative)
			val *= -1;
		int g = val / 10000;
		int s = (val % 10000) / 100;
		int c = val % 100;
		return (negative ? "-" : " ") + g + "g " + s + "s " + c + "c";
	}
}
