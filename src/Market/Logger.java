package Market;

public class Logger {
	public static String	indent	= "";
	private static String	separator = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	public static void log(String string) {
		System.out.println(indent + string);
	}

	public static void indent() {
		indent += '\t';
	}

	public static void unindent() {
		if (indent.length() > 0)
			indent = indent.substring(0, indent.length() - 1);
	}

	public static void separator() {
		System.out.println(separator );
	}

}
