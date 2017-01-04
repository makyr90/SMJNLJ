package aueb.msc.cs.joinalgs;

import java.io.IOException;
import java.util.ArrayList;

import aueb.msc.cs.utils.Checkargs;
import aueb.msc.cs.utils.ReadCSV;

public class Main {

	public static void main(String[] args) {

		long startTime = 1, endTime = 1, elapsedTime;
		Checkargs.checklength(args);
		Join join = new Join(args);
		if (join.getFile1size() + join.getFile2size() <= join.getMsize()) {
			startTime = System.nanoTime();
			join.singlePass();
			endTime = System.nanoTime();

		} else if (join.getJoinmethod().equals("NLJ")) {
			try {
				startTime = System.nanoTime();
				join.setNLJrockingCache(new ArrayList<String[]>());
				join.rockingNLJ();
				endTime = System.nanoTime();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else
			try {
				startTime = System.nanoTime();
				join.multipassSMJ();
				endTime = System.nanoTime();
			} catch (IOException e) {
				e.printStackTrace();
			}
		// Test output and measure execution time in milliseconds
		ArrayList<String[]> results = ReadCSV.readFile(join.getOutput(),",");
		System.out.println("Joins: " + results.size());
		elapsedTime = (endTime-startTime)/1000000;
		System.out.println("Execution time: "+elapsedTime+" ms");

	}
}
