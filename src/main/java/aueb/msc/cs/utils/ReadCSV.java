package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class ReadCSV {

	public static ArrayList<String[]> readFile(String file) {

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

	public static ArrayList<String[]> readFileChunk(BufferedReader filebr, int chunksize) throws IOException {

		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> relation = new ArrayList<>();

		for (int i = 0; i < chunksize; i++) {
			line = filebr.readLine();
			if (line != null)
				relation.add(line.split(cvsSplitBy));
			else
				break;

		}

		if (relation.size() == 0)
			return null;
		else
			return relation;
	}

	public static ArrayList<String[]> readFileChunkReverse(ReversedLinesFileReader reversefilebr, int chunksize)
			throws IOException {

		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> relation = new ArrayList<>();

		for (int i = 0; i < chunksize; i++) {
			line = reversefilebr.readLine();
			if (line != null)
				relation.add(line.split(cvsSplitBy));
			else
				break;

		}

		if (relation.size() == 0)
			return null;
		else
			return relation;
	}

	public static String[] readtuple(String line) throws IOException {

		String cvsSplitBy = ",";
		if (line!= null)
			return line.split(cvsSplitBy);
		else
			return null;

	}
}