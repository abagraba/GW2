package Map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import API.API;

public class Tile {
	private static final HashMap<Integer, Tile> tileCache = new HashMap<Integer, Tile>();

	public final int id;
	public final BufferedImage tileImage;

	public static Tile getTile(byte f, byte z, byte x, byte y, boolean urgent) {
		int id = f << 24 | z << 16 | x << 8 | y;
		if (tileCache.containsKey(id))
			return tileCache.get(id);
		Tile t = new Tile(f, z, x, y, urgent);
		if (t.tileImage == null)
			return null;
		tileCache.put(id, t);
		return t;
	}

	private Tile(byte f, byte z, byte x, byte y, boolean urgent) {
		id = f << 24 | z << 16 | x << 8 | y;
		BufferedImage image = null;
		try {
			image = API.getMapTile(f, z, x, y, urgent);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		tileImage = image;
	}
}
