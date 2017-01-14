package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class SMJSort {

	private static int totalsublists = 0;

	public static File sortRelation(String file, int msize, int col, String temp, boolean efficient) {

		int sublistindex = 0;
		String fileprefix = Utils.prefix(file);
		String nameprefix = fileprefix + "_Sublist_col_"+col+"_";
		ArrayList<String[]> relation = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			br.readLine(); // Skip 1st line
			while ((relation = ReadCSV.readFileChunk(br, msize)) != null) {
				sublistindex++;
				String sublist = temp + File.separatorChar + nameprefix + sublistindex + ".csv";
				FileWriter writer = new FileWriter(sublist);
				ArrayListCompare.sort(relation, col);
				for (String[] sa : relation) {
					CSVWriter.writeLine(writer, Arrays.asList(sa));

				}
				writer.flush();
				writer.close();

			}
			/* If we are going to use the efficient SMJ algorithm there is
			/* no need to continue on merging sublists
			 */
			if (efficient)
				return null;
			mergeSublists(nameprefix, temp, fileprefix, 0, sublistindex, msize, col);
			System.gc();
			for (int i = 1; i < totalsublists; i++) {
				String filetodelete = temp + File.separator + nameprefix + i + ".csv";
				File delfile = new File(filetodelete);
				delfile.delete();

			}
			File oldfile = new File(temp + File.separator + nameprefix + totalsublists + ".csv");
			File newfile = new File(temp + File.separatorChar + fileprefix + "_Sorted_" +col+ ".csv");
			oldfile.renameTo(newfile);
			return newfile;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void mergeSublists(String fileprefix, String path, String filename, int startindex,
			int sublistsnumber, int msize, int col) throws IOException {

		int initialsublistnumber = sublistsnumber;
		int mergelistsnumber;

		if ((sublistsnumber - startindex) == (msize - 1))
			mergelistsnumber = 1;
		else
			mergelistsnumber = (int) (Math.floor((sublistsnumber - startindex) / (msize - 1))) + 1;

		if ((sublistsnumber - startindex) == 1) {
			// Recursion base case just set the total # of sublists used

			totalsublists = sublistsnumber;
			return;

		} else {
			for (int round = 0; round < mergelistsnumber; round++) {

				Comparator<Map.Entry<BufferedReader, String[]>> comparator = new HmapComparator(col);
				PriorityQueue<Map.Entry<BufferedReader, String[]>> queue = new PriorityQueue<Map.Entry<BufferedReader, String[]>>(
						comparator);

				String line = "";
				String cvsSplitBy = ",";
				// initialize queue
				sublistsnumber++;
				String output = path + File.separator + fileprefix + sublistsnumber + ".csv";
				FileWriter writer = new FileWriter(output);
				for (int i = 0; i < msize - 1; i++) {
					startindex++;

					if (startindex > initialsublistnumber) {
						break;
					}

					String file = path + File.separator + fileprefix + startindex + ".csv";
					BufferedReader br = new BufferedReader(new FileReader(file));
					if ((line = br.readLine()) != null) {
						String[] val = line.split(cvsSplitBy);
						queue.add(new HmapEntry(br, val));
					}

				}

				while ((queue.size() > 0)) {
					HmapEntry temp = (HmapEntry) queue.remove();
					if ((line = temp.getKey().readLine()) != null) {
						CSVWriter.writeLine(writer, Arrays.asList(temp.getValue()));
						temp.setValue(line.split(cvsSplitBy));
						queue.add(temp);
					} else {
						CSVWriter.writeLine(writer, Arrays.asList(temp.getValue()));
						temp.getKey().close();

					}
					writer.flush();
				}
				writer.flush();
				writer.close();

			}

		}
		int startpoint = (sublistsnumber - mergelistsnumber);
		mergeSublists(fileprefix, path, filename, startpoint, sublistsnumber, msize, col);
	}
}