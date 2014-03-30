package Map;

public class Rectangle {
	public int x1, x2, y1, y2;

	public Rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public String toString() {
		return "(" + x1 + "," + y1 + ") (" + x2 + "," + y2 + ")";
	}
}
