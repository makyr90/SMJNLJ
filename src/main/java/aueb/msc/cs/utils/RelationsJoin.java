package aueb.msc.cs.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class RelationsJoin {

	public static void writejoin(ArrayList<String[]> r1, ArrayList<String[]> r2, int col1, int col2, Writer writer) throws IOException {

		ArrayList<List<String>> results = new ArrayList<>();
		for (String[] tupler1 : r1) {
			for (String[] tupler2 : r2) {
				if (tupler1[col1].equals(tupler2[col2])) {

					String[] rel1 = new String[tupler1.length];
					System.arraycopy(tupler1, 0, rel1, 0, tupler1.length);
					String[] rel2 = new String[tupler2.length];
					System.arraycopy(tupler2, 0, rel2, 0, tupler2.length);

					rel2 = ArrayUtils.removeElement(rel2, rel2[col2]);

					String swap = rel1[0];
					rel1[0] = rel1[col1];
					rel1[col1] = swap;
					List<String> joinattr = Arrays.asList(ArrayUtils.addAll(rel1, rel2));
					results.add(joinattr);

				}
			}
		}
		for (List<String> joinedtuple : results) {
			CSVWriter.writeLine(writer, joinedtuple);
		}
		writer.flush();
	}

}
