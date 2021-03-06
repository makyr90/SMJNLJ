package aueb.msc.cs.utils;

import java.util.ArrayList;
import java.util.List;

public class JoinHeader {

	public static List<String> makeHeaderRow(String file1, String file2, int file1cols, int file2cols, int col1,int col2) {

		
		String fileName1 = Utils.prefix(file1);
		String fileName2 = Utils.prefix(file2);
		
		List<String> hrow = new ArrayList<>();

		hrow.add(fileName1 + col1 + " = " + fileName2 + col2 + " (join column)");
		for (int i = 0; i < file1cols; i++) {
			if (i != col1)
				hrow.add(fileName1 + i);
		}

		for (int i = 0; i < file2cols; i++) {
			if (i != col2)
				hrow.add(fileName2 + i);
		}

		return hrow;
	}

}
