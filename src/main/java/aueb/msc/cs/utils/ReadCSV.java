package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

	public static ArrayList<String[]> readfile(String file) {
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> relation = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			br.readLine(); // Skip 1st line
			while ((line = br.readLine()) != null) {
				relation.add(line.split(cvsSplitBy));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return relation;
	}
}