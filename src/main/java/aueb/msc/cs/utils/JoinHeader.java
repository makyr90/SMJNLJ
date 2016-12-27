package aueb.msc.cs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JoinHeader {

	public static List<String> makeHeaderRow(String file1, String file2, int file1cols, int file2cols, int col1,
			int col2, boolean reverse) {

		File filename1 = new File(file1);
		String fileName1 = filename1.getName();
		fileName1 = fileName1.substring(0, fileName1.lastIndexOf(".") + 0);

		File filename2 = new File(file2);
		String fileName2 = filename2.getName();
		fileName2 = fileName2.substring(0, fileName2.lastIndexOf(".") + 0);
		List<String> hrow = new ArrayList<>();

		if (!(reverse)) {
			hrow.add(fileName1 + col1 + " = " + fileName2 + col2 + " (join column)");
			for (int i = 0; i < file1cols; i++) {
				if (i != col1)
					hrow.add(fileName1 + i);
			}

			for (int i = 0; i < file2cols; i++) {
				if (i != col2)
					hrow.add(fileName2 + i);
			}

		} else {
			hrow.add(fileName2 + col2 + " = " + fileName1 + col1 + " (join column)");
			for (int i = 0; i < file2cols; i++) {
				if (i != col2)
					hrow.add(fileName2 + i);
			}

			for (int i = 0; i < file1cols; i++) {
				if (i != col2)
					hrow.add(fileName1 + i);
			}

		}

		return hrow;
	}

}
