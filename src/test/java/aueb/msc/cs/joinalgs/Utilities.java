package aueb.msc.cs.joinalgs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {

	public static boolean equalResults(ArrayList<String[]> programResults, ArrayList<String[]> DBMSResults) {

		for (String[] sa : programResults) {
			boolean equal = false;
			for (String[] sb : DBMSResults) {
				if (Arrays.equals(sa, sb)) {
					equal = true;
					break;
				}
			}
			if (equal)
				continue;
			else
				return false;

		}
		return true;

	}

	public static String[] parseArguments(File file1, File file2, File createdFile, String method, String col1,
			String col2) {

		return new String[] { "-f1", file1.getAbsolutePath(), "-a1", col1, "-f2", file2.getAbsolutePath(), "-a2", col2,
				"-j", method, "-m", "200", "-t", file1.getParentFile().getAbsolutePath(), "-o",
				createdFile.getAbsolutePath() };
	}
}
