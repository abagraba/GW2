package Market;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import JSON.JSONException;
import JSON.JSONObject;
import JSON.JSONTokener;



public class Item {
	private static final String		tpapi			= "http://www.gw2spidy.com/api/v0.9/json/item/";

	private static final boolean	optimalCost		= true;
	private static final boolean	optimalProfit	= true;
	private int						cut				= 0;

	private final int				id;
	private int						purchaseCost	= Integer.MAX_VALUE;
	public boolean					complete		= false;

	public LinkedList<Recipe>		recipes			= new LinkedList<Recipe>();

	private String					name			= null;
	private int						buy				= Integer.MAX_VALUE;
	private int						sell			= -1;
	private int						craftCost		= Integer.MAX_VALUE;

	private Recipe					optimal			= null;

	public Item(int id) {
		this(id, Integer.MAX_VALUE);
	}

	public Item(int id, int purchaseCost) {
		this.id = id;
		this.purchaseCost = purchaseCost;
		readValues();
	}

	public void readValues() {
		JSONObject result = null;
		try {
			result = new JSONObject(new JSONTokener(new InputStreamReader(new URL(tpapi + id).openStream()))).getJSONObject("result");
		}
		catch (JSONException e) {
			System.err.println("Error interpreting JSON.");
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			System.err.println("Malformed URL");
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		name = result.getString("name");
		buy = optimalCost ? result.getInt("max_offer_unit_price") + cut : result.getInt("min_sale_unit_price");
		sell = optimalProfit ? result.getInt("min_sale_unit_price") - cut : result.getInt("max_offer_unit_price");
		if (buy == 0)
			buy = Integer.MAX_VALUE;
	}

	public void updateCraftCost() {
		if (complete)
			return;
		for (Recipe recipe : recipes)
			if (!recipe.complete)
				recipe.updateCraftCost();
		for (Recipe recipe : recipes)
			if (recipe.getCost() < craftCost) {
				optimal = recipe;
				craftCost = optimal.getCost();
			}
		complete = true;
	}

	public void addRecipe(Recipe r) {
		recipes.add(r);
	}

	public boolean vendor() {
		return getCost() == purchaseCost;
	}

	public boolean buy() {
		return optimal == null;
	}

	public int getCost() {
		int cost = Math.min(buy, Math.min(craftCost, purchaseCost));
		if (cost == Integer.MAX_VALUE)
			return 0;
		return cost;
	}

	public int getProfit() {
		return (int) (sell * 0.85f);
	}

	public void printCost(int count) {
		int cost = getCost();
		if (cost != Integer.MAX_VALUE)
			cost *= count;
		if (vendor())
			Logger.log("[VENDOR]\t" + count + "\t" + pad(name, 50) + cash(cost));
		else if (buy())
			Logger.log("[ BUY  ]\t" + count + "\t" + pad(name, 50) + cash(cost));
		else {
			Logger.log("[CRAFT ]\t" + count + "\t" + pad(name, 50));
			Logger.indent();
			optimal.printCost(count);
			Logger.unindent();
		}
	}

	public void printProfit(int count) {
		Logger.log("        \t" + count + "\t" + pad(name, 50) + count * getProfit());
	}

	private static String pad(String string, int length) {
		length -= Logger.indent.length() * 4;
		if (string.length() > length)
			return string.substring(0, length);
		else
			while (string.length() < length)
				string += ' ';
		return string;
	}

	public static String cash(int cost) {
		return (cost / 10000) + "g\t" + (cost / 100 % 100) + "s\t" + (cost % 100);
	}

	public String toString() {
		return name;
	}

}
