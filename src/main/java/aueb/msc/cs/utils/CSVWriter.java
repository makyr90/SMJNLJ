package aueb.msc.cs.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVWriter {

	private static final char DEFAULT_SEPARATOR = ',';

	public static void writeLine(Writer w, List<String> values) {

		boolean first = true;

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (first) {
				sb.append(value);
				first = false;

			} else {
				sb.append(DEFAULT_SEPARATOR);
				sb.append(value);
			}

		}
		sb.append("\n");
		try {
			w.append(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
