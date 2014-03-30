package Market;

public class Recipe {

	private int		cost	= 0;
	private int		profit	= 0;

	public boolean	complete;

	private String	name;
	private Input[]	inputs;
	private Output	output;

	public Recipe(String name, Input[] inputs, Output output) {
		this.name = name;
		this.inputs = inputs;
		this.output = output;
		output.addRecipe(this);
	}

	public void updateCraftCost() {
		if (complete)
			return;
		for (Input input : inputs){
			System.out.println(input.item);
			if (!input.item.complete)
				input.item.updateCraftCost();
		}
		for (Input input : inputs) {
			cost += input.getCost();
		}
		profit = output.getProfit();
		complete = true;
	}

	public int getCost() {
		return cost;
	}

	public int getProfit() {
		return profit;
	}

	public int netProfit() {
		return profit - cost;
	}

	public void printCost() {
		Logger.separator();
		Logger.log((complete?"":"!!!") + name);
		Logger.log("Cost:");
		Logger.indent();
		printCost(1);
		Logger.unindent();
		Logger.log("Total Cost:");
		Logger.log("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + Item.cash(cost));
	}

	public void printCost(int count) {
		for (Input input : inputs)
			input.item.printCost(input.count * count);
	}

	public void printProfit() {
		if (profit != 0) {
			Logger.log("Profit:");
			Logger.indent();
			output.printProfit();
			Logger.unindent();
			Logger.log("");
			Logger.log("Net Profit:\t" + Item.cash(netProfit()));
		}
	}

	public void printAll() {
		printCost();
		printProfit();

	}
}
