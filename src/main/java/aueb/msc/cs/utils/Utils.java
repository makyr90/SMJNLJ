package aueb.msc.cs.utils;

import java.io.File;
import java.util.ArrayList;

public class Utils {
	public static ArrayList<String[]> cloneList(ArrayList<String[]> list) {

		ArrayList<String[]> clone = new ArrayList<String[]>(list.size());
		for (String[] item : list) {
			String[] line = new String[item.length];
			System.arraycopy(item, 0, line, 0, item.length);
			clone.add(line);
		}
		return clone;
	}

	public static String prefix(String file) {

		File filename = new File(file);
		String fileName = filename.getName();
		fileName = fileName.substring(0, fileName.lastIndexOf(".") + 0);
		return fileName;

	}
	
	public static String Fileprefix(String file) {

		File filename = new File(file);
		String fileName = filename.getAbsolutePath();
		fileName = fileName.substring(0, fileName.lastIndexOf(".") + 0);
		return fileName;

	}


	
	
}
