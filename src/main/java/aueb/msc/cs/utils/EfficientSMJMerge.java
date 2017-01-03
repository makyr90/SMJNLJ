package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class EfficientSMJMerge {

	private static Comparator<Map.Entry<BufferedReader, String[]>> comparatorfile1;
	private static PriorityQueue<Map.Entry<BufferedReader, String[]>> queuefile1;
	private static Comparator<Map.Entry<BufferedReader, String[]>> comparatorfile2;
	private static PriorityQueue<Map.Entry<BufferedReader, String[]>> queuefile2;
	public static ArrayList<BufferedReader> buffers = new ArrayList<>();
	public static String cvsSplitBy = ",";

	public static void merge(String file1, String file2, int msize, int col1, int col2, int file1size, int file2size,
			String temp, Writer writer) throws IOException {

		SMJSort.sortRelation(file1, msize, col1, temp, true);
		SMJSort.sortRelation(file2, msize, col2, temp, true);
		int file1sublists = sublistsnumber(file1size, msize);
		int file2sublists = sublistsnumber(file2size, msize);
		String file1prefix = sublistsprefix(file1);
		String file2prefix = sublistsprefix(file2);

		comparatorfile1 = new HmapComparator(col1);
		queuefile1 = new PriorityQueue<Map.Entry<BufferedReader, String[]>>(comparatorfile1);

		comparatorfile2 = new HmapComparator(col2);
		queuefile2 = new PriorityQueue<Map.Entry<BufferedReader, String[]>>(comparatorfile2);

		// initialize queuefile1
		initializeQueue(file1prefix, file1sublists, 1, temp);

		// initialize queuefile2
		initializeQueue(file2prefix, file2sublists, 2, temp);


		HmapEntry tuple1 = (HmapEntry) queuefile1.remove();
		HmapEntry tuple2 = (HmapEntry) queuefile2.remove();

		while ((tuple1 != null) && (tuple2 != null)) {
			
			if ((Integer.parseInt(tuple1.getValue()[col1])) == (Integer.parseInt(tuple2.getValue()[col2]))) {
				HmapEntry[] indexes= outputTuples(writer, tuple1, tuple2, col1, col2);
				tuple1 = indexes[0];
				tuple2 = indexes[1];

			} else if ((Integer.parseInt(tuple1.getValue()[col1])) < (Integer.parseInt(tuple2.getValue()[col2])))
				tuple1 = updateQueueFile1(tuple1);

			else
				tuple2 = updateQueueFile2(tuple2);

		}

		// close all BufferedReaders
		closeBuffers();

		// clear temp directory from sublists

		cleanDirectory(temp, file1prefix, file1sublists);
		cleanDirectory(temp, file2prefix, file2sublists);

	}
	
	public static HmapEntry[] outputTuples(Writer writer, HmapEntry tuple1, HmapEntry tuple2, int col1, int col2)
			throws IOException {
		
		ArrayList<String[]> joinedtuples = new ArrayList<>();
		String[] tuple2clone = tuple2.getValue();
		boolean firstpass = true;
		
		while ((Integer.parseInt(tuple1.getValue()[col1])) == (Integer.parseInt(tuple2clone[col2]))
				& (tuple1 != null)) {
			if (firstpass) {
				firstpass = false;
			} else {
				RelationsJoin.writejoin(tuple1.getValue(), joinedtuples, col1, col2, writer);
				tuple1 = updateQueueFile1(tuple1);
				if (tuple1 == null)
					break;

				continue;
			}
			while ((Integer.parseInt(tuple1.getValue()[col1])) == (Integer.parseInt(tuple2.getValue()[col2]))
					& (tuple2 != null)) {
				RelationsJoin.writejoin(tuple1.getValue(), tuple2.getValue(), col1, col2, writer);
				joinedtuples.add(tuple2.getValue());
				tuple2 = updateQueueFile2(tuple2);
				if (tuple2 == null)
					break;
			}
			tuple1 = updateQueueFile1(tuple1);

		}
		
		return new HmapEntry[] {tuple1,tuple2};
		
	}

	public static int sublistsnumber(int filesize, int msize) {

		if (filesize == msize)
			return 1;
		else if ((filesize % msize) == 0)
			return (int) (filesize / msize);
		else
			return ((int) (Math.floor(filesize / msize)) + 1);

	}

	public static String sublistsprefix(String file) {

		File filename1 = new File(file);
		String fileName1 = filename1.getName();
		fileName1 = fileName1.substring(0, fileName1.lastIndexOf(".") + 0);
		String nameprefix = fileName1 + "_Sublist_";
		return nameprefix;
	}

	public static void initializeQueue(String fileprefix, int sublists, int queue, String temp) throws IOException {

		String line = "";
		for (int i = 1; i <= sublists; i++) {
			String file = temp + File.separator + fileprefix + i + ".csv";
			BufferedReader br = new BufferedReader(new FileReader(file));
			buffers.add(br);
			line = br.readLine();
			String[] val = line.split(cvsSplitBy);
			if (queue == 1)
				queuefile1.add(new HmapEntry(br, val));
			else if (queue == 2)
				queuefile2.add(new HmapEntry(br, val));

		}
	}

	public static void closeBuffers() throws IOException {

		for (BufferedReader br : buffers) {
			br.close();
		}
	}

	public static void cleanDirectory(String temp, String fileprefix, int sublists) {

		System.gc();
		for (int i = 1; i <= sublists; i++) {
			String filetodelete = temp + File.separator + fileprefix + i + ".csv";
			File delfile = new File(filetodelete);
			delfile.delete();
		}

	}

	public static HmapEntry updateQueueFile1(HmapEntry tuple1) throws IOException {

		String line = "";
		line = tuple1.getKey().readLine();
		if (line != null) {
			tuple1.setValue(line.split(cvsSplitBy));
			queuefile1.add(tuple1);
		} else
			tuple1.getKey().close();
		if (queuefile1.size() > 0)
			return (HmapEntry) queuefile1.remove();
		else
			return null;

	}

	public static HmapEntry updateQueueFile2(HmapEntry tuple2) throws IOException {

		String line = "";
		line = tuple2.getKey().readLine();
		if (line != null) {
			tuple2.setValue(line.split(cvsSplitBy));
			queuefile2.add(tuple2);
		} else
			tuple2.getKey().close();
		if (queuefile2.size() > 0)
			return (HmapEntry) queuefile2.remove();
		else
			return null;

	}
}