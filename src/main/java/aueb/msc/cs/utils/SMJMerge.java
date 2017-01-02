package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class SMJMerge {

	private static String line1 = "";
	private static String line2 = "";
	private static BufferedReader br1 = null;
	private static BufferedReader br2 = null;

	public static void merge(File file1, File file2, int col1, int col2, int file2size, Writer writer, String output)
			throws IOException {

		br1 = new BufferedReader(new FileReader(file1));
		br2 = new BufferedReader(new FileReader(file2));
		line1 = br1.readLine();
		line2 = br2.readLine();
		while ((line1 != null) && (line2 != null)) {

			String[] tuple1 = ReadCSV.readtuple(line1);
			String[] tuple2 = ReadCSV.readtuple(line2);

			if ((Integer.parseInt(tuple1[col1])) == (Integer.parseInt(tuple2[col2]))) {
				outputTuples(writer, tuple1, tuple2, col1, col2);

			} else if ((Integer.parseInt(tuple1[col1])) < (Integer.parseInt(tuple2[col2]))) {

				line1 = br1.readLine();
			} else {

				line2 = br2.readLine();
			}
		}
		br1.close();
		br2.close();
		writer.close();

	}

	public static void outputTuples(Writer writer, String[] tuple1, String[] tuple2, int col1, int col2)
			throws IOException {

		boolean firstpass = true;
		ArrayList<String[]> joinedtuples = new ArrayList<>();
		String[] tuple2clone = tuple2;
		while ((Integer.parseInt(tuple1[col1])) == (Integer.parseInt(tuple2clone[col2])) & (line1 != null)) {
			if (firstpass) {
				firstpass = false;
			} else {
				RelationsJoin.writejoin(tuple1, joinedtuples, col1, col2, writer);
				line1 = br1.readLine();
				if (line1 != null) {
					tuple1 = ReadCSV.readtuple(line1);
				}
				continue;
			}
			while ((Integer.parseInt(tuple1[col1])) == (Integer.parseInt(tuple2[col2])) & (line2 != null)) {
				RelationsJoin.writejoin(tuple1, tuple2, col1, col2, writer);
				joinedtuples.add(tuple2);
				line2 = br2.readLine();
				if (line2 != null)
					tuple2 = ReadCSV.readtuple(line2);

			}

			line1 = br1.readLine();
			if (line1 != null)
				tuple1 = ReadCSV.readtuple(line1);
		}
	}

}
