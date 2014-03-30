package Market;

public class Input {

	public final Item		item;
	public final int		count;

	public Input(Item item, int count) {
		this.item = item;
		this.count = count;
	}

	public int getCost() {
		return count * item.getCost();
	}
}
