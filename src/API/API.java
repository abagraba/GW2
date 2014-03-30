package API;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ListModel;

import IO.DownloadManager;
import IO.ImageLoader;
import JSON.JSONArray;
import JSON.JSONObject;
import JSON.JSONTokener;



public class API {

	private static final String			lang			= "lang=EN";

	public static final String			gw2				= "https://api.guildwars2.com/v1/";
	public static final String			map				= "https://tiles.guildwars2.com/1/";

	public static final DownloadManager	mapLoader		= new DownloadManager("GW/maps", 40);
	public static final ImageLoader		mapTileLoader	= new ImageLoader(20);

	public static final String			build			= "build.json";
	public static final String			colors			= "colors.json";
	public static final String			continents		= "continents.json";
	public static final String			eventDetails	= "event_details.json";
	public static final String			eventNames		= "event_names.json";
	public static final String			events			= "events.json";
	public static final String			files			= "files.json";
	public static final String			guilds			= "guild_details.json";
	public static final String			itemDetails		= "item_details.json";
	public static final String			items			= "items.json";
	public static final String			mapFloor		= "map_floor.json";
	public static final String			mapNames		= "map_names.json";
	public static final String			maps			= "maps.json";
	public static final String			recipeDetails	= "recipe_details.json";
	public static final String			recipes			= "recipes.json";
	public static final String			worldNames		= "world_names.json";
	public static final String			wvwMatchDetails	= "wvw/match_details.json";
	public static final String			wvwMatches		= "wvw/matches.json";
	public static final String			wvwObjectives	= "wvw/objective_names.json";

	private static JSONTokener connect(String target) {
		System.out.println("Connecting to " + target + "...");
		try {
			return new JSONTokener(new InputStreamReader(new URL(gw2 + target).openStream()));
		}
		catch (IOException e) {
			System.out.println("Failure to connect to " + target + ".");
			return null;
		}
	}

	public static JSONTokener connectGW2(String address, String... args) {
		if (args == null)
			return connect(gw2 + address);
		for (String arg : args)
			address += arg;
		return connect(gw2 + address);
	}

	public static JSONTokener connectMap(String address, String... args) {
		if (args == null)
			return connect(maps + address);
		for (String arg : args)
			address += arg;
		return connect(maps + address);
	}

	public static JSONObject listMaps() {
		JSONTokener tokener = connect(maps + "?" + lang);
		if (tokener == null)
			return null;
		return new JSONObject(tokener);
	}

	public static JSONObject listContinents() {
		JSONTokener tokener = connect(continents);
		if (tokener == null)
			return null;
		return new JSONObject(tokener);
	}

	public static JSONArray listWorlds() {
		JSONTokener tokener = connect(worldNames + "?" + lang);
		if (tokener == null)
			return null;
		return new JSONArray(tokener);
	}

	public static BufferedImage getMapTile(byte f, byte z, byte x, byte y, boolean urgent) throws IOException {
		String name = f + "_" + z + '/' + x + '_' + y + ".jpg";
		String address = f + "/" + z + '/' + x + '/' + y + ".jpg";
		File tile = mapLoader.getFile(name);
		if (tile != null)
			return mapTileLoader.getImage(tile);
		if (mapLoader.queueDownload(name, new URL(map + address), name, urgent))
			System.out.println("Tile not found. Loading from [" + map + address + "].");
		return null;
	}

	public static void clean() {
		mapLoader.halt();
		mapTileLoader.halt();
	}

}
