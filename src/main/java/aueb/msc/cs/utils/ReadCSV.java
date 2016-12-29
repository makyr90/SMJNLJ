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

	public static ArrayList<String[]> readFileChunk(BufferedReader file1br, int chunksize) throws IOException {
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> relation = new ArrayList<>();

		for(int i=0; i< chunksize;i++) {
			line = file1br.readLine();
			if (line != null)
				relation.add(line.split(cvsSplitBy));

		}
		if (relation.size() == 0)
			return null;
		else
		return relation;
	}
	
	public static ArrayList<String[]> readFileChunkReverse(ReversedLinesFileReader rbr, int chunksize) throws IOException {
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> relation = new ArrayList<>();

		for(int i=0; i< chunksize;i++) {
			line = rbr.readLine();
			relation.add(line.split(cvsSplitBy));

		}

		return relation;
	}
}