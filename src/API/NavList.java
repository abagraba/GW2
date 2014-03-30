package API;

import java.util.Collection;
import java.util.Stack;

public class NavList extends JSelect {

	private Window window;
	
	private Lang lang;
	private World world;
	private int region;
	private int map;

	private static final Object[] menuOptions = new Object[]{"Event Map", "Resource Refining", "Dragon Timer"};
	
	private static final int langSelect = 0;
	private static final int worldSelect = 1;
	private static final int regionSelect = 2;
	private static final int mapSelect = 3;
	private static final int menu = 4;

	private int layer = 0;

	private Stack<Integer> state = new Stack<Integer>();

	public NavList(Window window) {
		super("/back.png", "/MenuOption.png");
		this.window = window;
		initLayer();
	}

	public void unselect() {
		if (!state.isEmpty()) {
			layer = state.pop();
			initLayer();
		}
	}

	public void select(int i) {
		if (i < 0 || i > selection.length)
			return;
		if (selection == null)
			return;
		selected = selection[i];
		
		switch (layer) {
			case langSelect:
				setLayer(worldSelect, i);
				lang = ((Lang) selected);
				break;
			case worldSelect:
				setLayer(menu, i);
				world = (World) selected;
				break;
			case menu:
				switch(i){
					case 0:
						window.openMap();
						setLayer(regionSelect, i);
						break;
				}
				break;
			case regionSelect:
				setLayer(mapSelect, i);
				region = ((Region) selected).id;
				break;
			case mapSelect:
				map = ((Map) selected).id;
				break;
		}
		initLayer();
	}

	private void setLayer(int layer, int i){
		state.push(this.layer);
		this.layer = layer;
	}
	
	public void initLayer() {
		switch (layer) {
			case langSelect:
				Collection langs = GW2Data.language.values();
				title = "";
				setSelection(langs.toArray(new Object[langs.size()]));
				break;
			case worldSelect:
				Collection worlds = GW2Data.language.get(lang.name).worlds;
				title = lang.toString();
				setSelection(worlds.toArray(new Object[worlds.size()]));
				break;
			case menu:
				setSelection(menuOptions);
				title = world + " " + lang;
				window.openSplash();
				break;
			case regionSelect:
				Collection regions = GW2Data.regions.values();
				setSelection(regions.toArray(new Object[regions.size()]));
				break;
			case mapSelect:
				Collection maps = GW2Data.regions.get(region).maps;
				setSelection(maps.toArray(new Object[maps.size()]));
				break;
		}
		repaint();
	}

	private static class State {
		public final int state;
		public final String name;

		public State(int state, String name) {
			this.state = state;
			this.name = name;
		}
	}

}
