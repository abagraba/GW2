package IO;

import java.io.File;
import java.net.URL;

public class Download {
	public final String	name;
	public final URL		src;
	public final File		dst;

	protected Download(String name, URL src, File dst) {
		this.name = name;
		this.src = src;
		this.dst = dst;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Download))
			return false;
		if (name == null)
			return false;
		return name.equals(((Download) o).name);
	}
}
