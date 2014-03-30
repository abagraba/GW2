package API;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JSON.JSONArray;
import JSON.JSONObject;



public class GW2Data {

	private static Pattern					serverLang	= Pattern.compile("(.*) \\[(..)\\]");

	private static int						world		= 1021;
	public static HashMap<String, Lang>		language	= new HashMap<String, Lang>();
	public static HashMap<Integer, Region>	regions		= new HashMap<Integer, Region>();

	private static HashSet<Integer>			validMaps	= new HashSet<Integer>();
	private static int[]					maps		= new int[] { 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 34, 35, 39,
			50, 51, 53, 54, 62, 65, 73, 91, 139, 218, 326, 873 };

	//
	public static void init() {
		for (int i = 0; i < maps.length; i++)
			validMaps.add(maps[i]);

		initWorlds();
		initMaps();

		for (String s : language.keySet()) {
			Lang l = language.get(s);
			System.out.println(l);
			for (World w : l.worlds)
				System.out.println("\t\t" + w);
		}
		for (int id : regions.keySet()) {
			Region r = regions.get(id);
			System.out.println(id + "\t" + r);
			for (Map m : r.maps)
				System.out.println("\t\t" + m);
		}
	}

	private static void initWorlds() {
		JSONArray worldList = API.listWorlds();
		for (int i = 0; i < worldList.length(); i++) {
			JSONObject world = worldList.getJSONObject(i);

			int id = world.getInt("id");
			String name = world.getString("name");
			String lang = "EN";

			Matcher m = serverLang.matcher(name);
			if (m.find()) {
				name = m.group(1);
				lang = m.group(2);
			}
			if (!language.containsKey(lang))
				language.put(lang, new Lang(lang));
			language.get(lang).addWorld(new World(id, name));
		}
	}

	private static void initMaps(){
		JSONObject mapList = API.listMaps().getJSONObject("maps");
		String[] mapIDs = JSONObject.getNames(mapList);
		for (int i = 0; i < mapIDs.length; i++) {
			JSONObject mapData = mapList.getJSONObject(mapIDs[i]);
			int mapID = Integer.parseInt(mapIDs[i]);
			int regionID = mapData.getInt("region_id");
			if (validMaps.contains(mapID)) {
				Map map = new Map(mapID, mapData.getString("map_name"));
				Region r = regions.get(regionID);
				if (r == null) {
					r = new Region(regionID, mapData.getString("region_name"));
					regions.put(regionID, r);
				}
				r.addMap(map);
			}
		}
	}
}
