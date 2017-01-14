package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Checkargs {

	public static void checklength(String[] arguments) {
		if (arguments.length != 16) {
			System.out.println("Program requires exactly 16 input arguments but " + arguments.length + " are given");
			System.exit(0);

		}
	}

	public static void checkpath(String path) {
		File file = new File(path);
		if (!(file.exists())) {
			System.out.println("Path " + path + " does not exist");
			System.exit(0);
		}
	}

	public static void checkjoinalgorithm(String joinmeth) {
		if (!(joinmeth.equals("NLJ") || joinmeth.equals("SMJ"))) {
			System.out.println("You can either use NLJ or SMJ as arguments for join algorithm");
			System.exit(0);
		}
	}

	public static void checkmemorysize(int m) {

		if (m <= 2) {
			System.out.println("Memory size -m should be greater than one");
			System.exit(0);
		}

	}

	public static int checkcolumn(String file, int column) {
		String line = "";
		String cvsSplitBy = ",";
		int totalrecords = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			line = br.readLine();
			String[] values = line.split(cvsSplitBy);
			totalrecords = Integer.parseInt(values[0]);
			line = br.readLine();
			String[] values2 = line.split(cvsSplitBy);
			if (((values2.length - 1) < column) || (column < 0)) {
				System.out.println("The selected column number for file " + file + " is out of the correct range [0,"
						+ (values2.length - 1) + "]");
				System.exit(0);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ne) {
			System.out.println("The selected file: " + file + " has no records");
			System.exit(0);
		}

		return totalrecords;

	}
}