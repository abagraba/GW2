package Market;

public class SingleOutput extends Output {

	private Item	item;
	private int		count;

	public SingleOutput(Item item, int count) {
		this.item = item;
		this.count = count;
	}

	@Override
	public int getProfit() {
		return item.getProfit() * count;
	}

	@Override
	public void printProfit() {
		item.printProfit(count);
	}

	@Override
	public void addRecipe(Recipe recipe) {
		item.addRecipe(recipe);
	}

}
