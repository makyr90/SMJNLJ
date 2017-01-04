package aueb.msc.cs.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class RelationsJoin {

	public static void writejoin(ArrayList<String[]> r1, ArrayList<String[]> r2, int col1, int col2, Writer writer)
			throws IOException {

		ArrayList<List<String>> results = new ArrayList<>();
		for (String[] tupler1 : r1) {
			for (String[] tupler2 : r2) {
				if (tupler1[col1].equals(tupler2[col2])) {

					String[] rel1 = new String[tupler1.length];
					String[] rel2 = new String[tupler2.length];
					System.arraycopy(tupler2, 0, rel2, 0, tupler2.length);

					rel1[0] = tupler1[col1];

					for (int i = 0; i < tupler1.length; i++) {
						if (i < col1)
							rel1[i + 1] = tupler1[i];
						else if (i > col1)
							rel1[i] = tupler1[i];

					}
					rel2 = ArrayUtils.remove(rel2, col2);

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

	public static void writejoin(String[] tupler1, ArrayList<String[]> r2, int col1, int col2, Writer writer)
			throws IOException {

		ArrayList<List<String>> results = new ArrayList<>();
		String[] rel1 = new String[tupler1.length];
		for (String[] tupler2 : r2) {
			if (tupler1[col1].equals(tupler2[col2])) {

				String[] rel2 = new String[tupler2.length];
				System.arraycopy(tupler2, 0, rel2, 0, tupler2.length);

				rel1[0] = tupler1[col1];

				for (int i = 0; i < tupler1.length; i++) {
					if (i < col1)
						rel1[i + 1] = tupler1[i];
					else if (i > col1)
						rel1[i] = tupler1[i];

				}
				rel2 = ArrayUtils.remove(rel2, col2);

				List<String> joinattr = Arrays.asList(ArrayUtils.addAll(rel1, rel2));
				results.add(joinattr);

			}
		}

		for (List<String> joinedtuple : results) {
			CSVWriter.writeLine(writer, joinedtuple);
		}
		writer.flush();
	}

	public static void writejoin(String[] tupler1, String[] tupler2, int col1, int col2, Writer writer)
			throws IOException {

		String[] rel1 = new String[tupler1.length];
		String[] rel2 = new String[tupler2.length];
		System.arraycopy(tupler2, 0, rel2, 0, tupler2.length);

		rel1[0] = tupler1[col1];

		for (int i = 0; i < tupler1.length; i++) {
			if (i < col1)
				rel1[i + 1] = tupler1[i];
			else if (i > col1)
				rel1[i] = tupler1[i];

		}
		rel2 = ArrayUtils.remove(rel2, col2);

		List<String> joinattr = Arrays.asList(ArrayUtils.addAll(rel1, rel2));
		CSVWriter.writeLine(writer, joinattr);
		writer.flush();

	}

	public static void writejoin(ArrayList<String[]> r1, ArrayList<String[]> r2, int col1, int col2, FileWriter writer,
			boolean nljswap) throws IOException {
		if (nljswap)
			writejoin(r2, r1, col2, col1, writer);
		else
			writejoin(r1, r2, col1, col2, writer);

	}

}
