package API;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import JSON.JSONException;
import JSON.JSONObject;
import JSON.JSONTokener;

public enum Items {
	oret1("AgHxTAAA"), oret2("AgHzTAAA"), oret4("AgH2TAAA"), oret5("AgH0TAAA"), oret6("AgH1TAAA"),
	/**/
	bart1("AgHfTAAA"), bart2("AgHjTAAA"), bart3("AgHoTAAA"), bart4("AgHhTAAA"), bart5("AgHkTAAA"), bart6("AgHlTAAA"),
	/**/
	soret1("AgH4TAAA", 8), soret3("AgEmTQAA", 16), soret4("AgHUTQAA", 48),
	/**/
	joret2("AgH3TAAA"), joret3("AgHyTAAA"),
	/**/
	jbart1("AgHgTAAA"), jbart2("AgHnTAAA"), jbart3("AgHiTAAA"), jbart4("AgHmTAAA"),
	/**/
	logt1("AgELTQAA"), logt2("AgEOTQAA"), logt3("AgEPTQAA"), logt4("AgEMTQAA"), logt5("AgEKTQAA"), logt6("AgENTQAA"),
	/**/
	plat1("AgH+TAAA"), plat2("AgEBTQAA"), plat3("AgECTQAA"), plat4("AgH/TAAA"), plat5("AgH9TAAA"), plat6("AgEATQAA"),
	/**/
	clot1("AgEGTQAA"), tclot1("AgFQTQAA"), clot2("AgEbTQAA"), tclot2("AgFNTQAA"), clot3("AgEdTQAA"), tclot3("AgFSTQAA"), clot4("AgEfTQAA"), tclot4(
			"AgFRTQAA"), clot5("AgEkTQAA"), tclot5("AgFPTQAA"), clot6("AgEhTQAA"), tclot6("AgFOTQAA"),
	/**/
	bolt1("AgEITQAA"), bolt2("AgEcTQAA"), bolt3("AgEeTQAA"), bolt4("AgEgTQAA"), bolt5("AgEjTQAA"), bolt6("AgEiTQAA"),
	/**/
	leat1("AgEHTQAA"), leat2("AgEQTQAA"), leat3("AgESTQAA"), leat4("AgETTQAA"), leat5("AgERTQAA"), leat6("AgEUTQAA"),
	/**/
	sqat1("AgEaTQAA"), sqat2("AgEVTQAA"), sqat3("AgEWTQAA"), sqat4("AgEYTQAA"), sqat5("AgEXTQAA"), sqat6("AgEZTQAA"),
	/**/
	dust1("AgHQXgAA"), dust2("AgHRXgAA"), dust3("AgHSXgAA"), dust4("AgHTXgAA"), dust5("AgHUXgAA"), dust6("AgHVXgAA"),
	/**/
	fbont1("AgEWXwAA"), fbont2("AgEXXwAA"), fbont3("AgEYXwAA"), fbont4("AgEZXwAA"), fbont5("AgEVXwAA"), fbont6("AgEmXwAA"),
	/**/
	fclat1("AgEaXwAA"), fclat2("AgEbXwAA"), fclat3("AgEcXwAA"), fclat4("AgEdXwAA"), fclat5("AgEeXwAA"), fclat6("AgEfXwAA"),
	/**/
	ffant1("AgEgXwAA"), ffant2("AgEhXwAA"), ffant3("AgEiXwAA"), ffant4("AgEjXwAA"), ffant5("AgEkXwAA"), ffant6("AgElXwAA"),
	/**/
	fscat1("AgHcXgAA"), fscat2("AgHdXgAA"), fscat3("AgHeXgAA"), fscat4("AgHfXgAA"), fscat5("AgHgXgAA"), fscat6("AgHhXgAA"),
	/**/
	ftott1("AgHoXgAA"), ftott2("AgHpXgAA"), ftott3("AgHqXgAA"), ftott4("AgErXwAA"), ftott5("AgHrXgAA"), ftott6("AgHsXgAA"),
	/**/
	fsact1("AgHWXgAA"), fsact2("AgHXXgAA"), fsact3("AgHYXgAA"), fsact4("AgHZXgAA"), fsact5("AgHaXgAA"), fsact6("AgHbXgAA"),
	/**/
	fblot1("AgHiXgAA"), fblot2("AgHjXgAA"), fblot3("AgHkXgAA"), fblot4("AgHlXgAA"), fblot5("AgHmXgAA"), fblot6("AgHnXgAA"),
	/**/
	ecto("AgEJTQAA"), thermo("AgGbtgAA", 150), abar("AgGStgAA"), apla("AgGQtgAA"), abol("AgGVtgAA"), asqa("AgGTtgAA"),
	/**/
	coin("AgEITgAA"), clover(Integer.MAX_VALUE, "AgHbTAAA"), runestone(10000, "AgHcTAAA"),
	/**/
	magic(Integer.MAX_VALUE, "AgHZTAAA"), might(Integer.MAX_VALUE, "AgHYTAAA"), fortune(Integer.MAX_VALUE, "AgGqTAAA"),
	/**/
	wood(Integer.MAX_VALUE, "AgGmTAAA"), metal(Integer.MAX_VALUE, "AgGlTAAA"), 
	/**/
	unicorn(Integer.MAX_VALUE, "AgGsTAAA"),
	/**/
	dreamergift(Integer.MAX_VALUE, "AgHMTAAA"),
	/**/
	;
	private static final String	tpapi		= "http://www.gw2spidy.com/api/v0.9/json/item/";

	private final int			id;
	private boolean				buy			= false;

	private String				name		= null;
	private int					offer		= -1;
	private int					sale		= -1;
	public int					craftCost	= Integer.MAX_VALUE;

	private Items(int id) {
		this.id = id;
	}

	private Items(int forcedValue, String s) {
		this(s);
		update();
		offer = sale = forcedValue;
	}

	private Items(String s) {
		this(extractID(s));
	}

	private Items(String s, int craftCost) {
		this(extractID(s));
		this.craftCost = craftCost;
		buy = true;
	}

	public void update() {
		JSONObject result = null;
		System.out.println("Looking up " + name() + "...");
		try {
			result = new JSONObject(new JSONTokener(new InputStreamReader(new URL(tpapi + id).openStream()))).getJSONObject("result");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		name = result.getString("name");
		offer = result.getInt("max_offer_unit_price");
		sale = result.getInt("min_sale_unit_price");
	}

	public String getName() {
		if (name == null)
			update();
		return name;
	}

	public int getOffer() {
		if (offer == -1)
			update();
		return offer;
	}

	public int getSale() {
		if (sale == -1)
			update();
		return sale;
	}

	public String toString() {
		if (name == null)
			update();
		if (offer == -1)
			update();
		if (sale == -1)
			update();
		if (craftCost != 0)
			return name + "" + "\n\tCraft Cost: " + craftCost + "\n\tOffer: " + offer + "\n\tSell:  " + sale;
		return name + "" + "\n\tOffer: " + offer + "\n\tSell:  " + sale;
	}

	public static byte getVal(char c) {
		if (c >= 'A' && c <= 'Z')
			return (byte) (c - 'A');
		if (c >= 'a' && c <= 'z')
			return (byte) (26 + c - 'a');
		if (c >= '0' && c <= '9')
			return (byte) (52 + c - '0');
		if (c == '+')
			return 62;
		if (c == '/')
			return 63;
		return -1;
	}

	private static String namePad(String name) {
		int pad = (43 - name.length()) / 4;
		String s = name;
		for (int i = 0; i < pad; i++)
			s += '\t';
		return s;
	}

	public int cost(int count, int cut) {
		int c = ((Recipe.bestChoice ? getOffer() : getSale()) + cut) * count;
		if (craftCost == Integer.MAX_VALUE)
			return c;
		return Math.min(c, craftCost * count);
	}

	public int profit(int count, int cut) {
		return (int) (((Recipe.bestChoice ? getSale() : getOffer()) - cut) * count * 0.85f + 0.9999f);
	}

	public int logcost(int count, int cut) {
		int unitcost = ((Recipe.bestChoice ? getOffer() : getSale()) + cut);
		if (craftCost < unitcost) {
			unitcost = craftCost;
			if (Recipe.log)
				System.out.println("\t-\t" + namePad((buy ? "[BUY]   " : "[CRAFT] ") + count + " " + getName()) + "\tCost:  \t"
						+ Recipe.cash(unitcost * count));
		}
		else if (Recipe.log)
			System.out.println("\t-\t" + namePad("        " + count + " " + getName()) + "\tCost:  \t" + Recipe.cash(unitcost * count));
		return unitcost * count;
	}

	public int logprofit(int count, int cut) {
		int pretax = ((Recipe.bestChoice ? getSale() : getOffer()) - cut) * count;
		int tax = (int) (Math.ceil(pretax) * 0.15f);
		int profit = (int) Math.floor(pretax * 0.85f);
		if (Recipe.log) {
			System.out.println("\t\t~\t" + namePad("TP Fees") + "\t      :\t" + Recipe.cash(tax));
			System.out.println("\t\t~\t" + namePad(count + " " + getName()) + "\tProfit:\t" + Recipe.cash(pretax));
		}
		return profit;
	}

	public static int extractID(String s) {
		if (s.length() != 8)
			return -1;
		int a = ((getVal(s.charAt(2)) & 0x03) << 6) + (getVal(s.charAt(3)));
		int b = ((getVal(s.charAt(4)) & 0x3F) << 2) + ((getVal(s.charAt(5)) & 0x30) >> 4);
		int c = ((getVal(s.charAt(5)) & 0x0F) << 4) + ((getVal(s.charAt(6)) & 0x3C) >> 2);
		return a + b * 256 + c * 65536;
	}
}
