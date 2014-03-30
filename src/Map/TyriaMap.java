package Map;

import API.API;
import JSON.JSONArray;
import JSON.JSONObject;

public class TyriaMap extends MapDisplay {

	private static final long serialVersionUID = -1505701033268003549L;

	protected int floor = 1;

	public void init(String continent) {
		JSONObject tyria = API.listContinents().getJSONObject("continents").getJSONObject(continent);
		JSONArray dim = tyria.getJSONArray("continent_dims");
		minZoom = tyria.getInt("min_zoom");
		maxZoom = tyria.getInt("max_zoom");
		zoom = 2;
		lock = mapBounds = new Rectangle(0, 0, dim.getInt(0), dim.getInt(1));
		super.init();
		for (int x = 0; x <= mapWidthTile(0); x++)
			for (int y = 0; y <= mapHeightTile(0); y++)
				Tile.getTile((byte) 1, (byte) 0, (byte) x, (byte) y, true);
		for (int x = 0; x <= mapWidthTile(4); x++)
			for (int y = 0; y <= mapHeightTile(4); y++)
				Tile.getTile((byte) 1, (byte) 4, (byte) x, (byte) y, true);
	}

}
