package aueb.msc.cs.joinalgs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.input.ReversedLinesFileReader;

import aueb.msc.cs.utils.ArrayListCompare;
import aueb.msc.cs.utils.CSVWriter;
import aueb.msc.cs.utils.Checkargs;
import aueb.msc.cs.utils.EfficientSMJMerge;
import aueb.msc.cs.utils.JoinHeader;
import aueb.msc.cs.utils.ReadCSV;
import aueb.msc.cs.utils.RelationsJoin;
import aueb.msc.cs.utils.SMJMerge;
import aueb.msc.cs.utils.SMJSort;
import aueb.msc.cs.utils.WriteTuples;

public class Join {

	private String file1, file2, joinmethod, temp, output;
	private int col1, col2, msize, file1size, file2size;
	private ArrayList<String[]> NLJrockingCache = null;
	private boolean nljswap = false;

	public Join(String[] arguments) {
		for (int i = 0; i < arguments.length; i = i + 2) {
			if (arguments[i].equalsIgnoreCase("-f1")) {
				this.file1 = arguments[i + 1];
				Checkargs.checkpath(this.file1);
			} else if (arguments[i].equalsIgnoreCase("-f2")) {
				this.file2 = arguments[i + 1];
				Checkargs.checkpath(this.file2);

			} else if (arguments[i].equalsIgnoreCase("-j")) {
				this.joinmethod = arguments[i + 1].toUpperCase();
				Checkargs.checkjoinalgorithm(this.joinmethod);

			} else if (arguments[i].equalsIgnoreCase("-m")) {
				this.msize = Integer.parseInt(arguments[i + 1]);
				Checkargs.checkmemorysize(this.msize);

			}

			else if (arguments[i].equalsIgnoreCase("-t")) {
				this.temp = arguments[i + 1];
				Checkargs.checkpath(this.temp);

			}

			else if (arguments[i].equalsIgnoreCase("-o")) {
				this.output = arguments[i + 1];
				File newFile = new File(this.output);
				try {
					@SuppressWarnings({ "unused", "resource" })
					BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (arguments[i].equalsIgnoreCase("-a1")) {
				this.col1 = Integer.parseInt(arguments[i + 1]);
				this.file1size = Checkargs.checkcolumn(this.file1, this.col1);

			}

			else if (arguments[i].equalsIgnoreCase("-a2")) {
				this.col2 = Integer.parseInt(arguments[i + 1]);
				this.file2size = Checkargs.checkcolumn(this.file2, this.col2);

			}

		}

	}

	public String getFile1() {
		return file1;
	}

	public String getFile2() {
		return file2;
	}

	public String getJoinmethod() {
		return joinmethod;
	}

	public String getTemp() {
		return temp;
	}

	public String getOutput() {
		return output;
	}

	public int getCol1() {
		return col1;
	}

	public int getCol2() {
		return col2;
	}

	public int getMsize() {
		return msize;
	}

	public int getFile1size() {
		return file1size;
	}

	public int getFile2size() {
		return file2size;
	}

	public ArrayList<String[]> getNLJrockingCache() {
		return NLJrockingCache;
	}

	public void setNLJrockingCache(ArrayList<String[]> nLJrockingCache) {
		NLJrockingCache = nLJrockingCache;
	}

	public void singlePass() {

		try {
			if (this.joinmethod.equals("NLJ")) {
				this.singlePassNLJ();
			} else
				this.singlePassSMJ();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void singlePassNLJ() throws IOException {

		ArrayList<String[]> r1 = ReadCSV.readFile(this.file1,",");
		ArrayList<String[]> r2 = ReadCSV.readFile(this.file2,",");
		FileWriter writer = new FileWriter(this.output);
		CSVWriter.writeLine(writer, JoinHeader.makeHeaderRow(this.file1, this.file2, r1.get(0).length, r2.get(0).length,
				this.col1, this.col2));
		writer.flush();
		RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer);
		writer.flush();
		writer.close();

	}

	public void singlePassSMJ() throws IOException {

		ArrayList<String[]> r1 = ReadCSV.readFile(this.file1,",");
		ArrayList<String[]> r2 = ReadCSV.readFile(this.file2,",");
		ArrayListCompare.sort(r1, this.col1);
		ArrayListCompare.sort(r2, this.col2);
		int iindex = 0, jindex = 0;
		int[] indexes;
		FileWriter writer = new FileWriter(this.output);
		CSVWriter.writeLine(writer, JoinHeader.makeHeaderRow(this.file1, this.file2, r1.get(0).length, r2.get(0).length,
				this.col1, this.col2));
		writer.flush();
		while ((iindex < r1.size()) && (jindex < r2.size())) {
			if ((Integer.parseInt(r1.get(iindex)[col1])) == (Integer.parseInt(r2.get(jindex)[col2]))) {
				indexes = WriteTuples.outputTuples(writer, r1, r2, iindex, jindex, this.col1, this.col2);
				writer.flush();
				iindex = indexes[0];
				jindex = indexes[1];

			} else if ((Integer.parseInt(r1.get(iindex)[col1])) < (Integer.parseInt(r2.get(jindex)[col2]))) {
				iindex++;
			} else {
				jindex++;
			}
		}
		writer.close();
	}

	public void rockingNLJ() throws IOException, FileNotFoundException {

		FileWriter writer = new FileWriter(this.output);
		// reverse join relation to reduce IOS
		if (this.file2size < this.file1size) {
			this.nljswap = true;
			String swapfile = this.file1;
			this.file1 = this.file2;
			this.file2 = swapfile;
			int swapcol = this.col1;
			this.col1 = this.col2;
			this.col2 = swapcol;
			int swapfilesize = this.file1size;
			this.file1size = this.file2size;
			this.file2size = swapfilesize;
		}

		BufferedReader file1br = new BufferedReader(new FileReader(this.file1));

		// Skip 1st line
		file1br.readLine();
		ArrayList<String[]> r1 = new ArrayList<>();
		Object br2 = null;
		boolean reverse = false;
		boolean headerRow = true;
		while (((r1 = ReadCSV.readFileChunk(file1br, this.msize - 1))) != null) {

			if (headerRow) {

				BufferedReader brheder = new BufferedReader(new FileReader(this.file2));
				ArrayList<String[]> r2header = ReadCSV.readFileChunk(brheder, 2);
				if (this.nljswap)
					CSVWriter.writeLine(writer, JoinHeader.makeHeaderRow(this.file2, this.file1, r2header.get(1).length,
							r1.get(0).length, this.col1, this.col2));
				else
					CSVWriter.writeLine(writer, JoinHeader.makeHeaderRow(this.file1, this.file2, r1.get(0).length,
							r2header.get(1).length, this.col1, this.col2));
				headerRow = false;
			}

			br2 = this.NLJjoinChunks(br2, writer, r1, reverse);
			writer.flush();
			reverse = !reverse;

		}

		writer.close();
		file1br.close();

	}

	public Object NLJjoinChunks(Object br, FileWriter writer, ArrayList<String[]> r1, boolean reverse)
			throws FileNotFoundException, IOException {

		ArrayList<String[]> r2 = new ArrayList<>();
		// Smaller relation can fit in memory
		if ((br == null) & ((r1.size()) < (this.msize - 1))) {
			int availiablebuffers = (this.msize - r1.size());
			try (BufferedReader br2 = new BufferedReader(new FileReader(this.file2))) {
				// Skip 1st line
				br2.readLine();
				int rounds = (int) Math.ceil(((this.file2size) / availiablebuffers));
				for (int i = 0; i < rounds; i++) {
					r2 = ReadCSV.readFileChunk(br2, availiablebuffers);
					RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
					writer.flush();
				}
				br2.close();
			}
			return null;

			// Smaller relation fits exactly in memory
		} else if ((br == null) & (r1.size() == (this.msize - 1))) {
			try (BufferedReader br2 = new BufferedReader(new FileReader(this.file2))) {
				// Skip 1st line
				br2.readLine();
				for (int i = 0; i < (this.file2size); i++) {
					r2 = ReadCSV.readFileChunk(br2, 1);
					RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
					writer.flush();
				}
				br2.close();
			}
			return null;

		} else if (br == null) {
			try (BufferedReader br2 = new BufferedReader(new FileReader(this.file2))) {
				// Skip 1st line
				br2.readLine();
				for (int i = 0; i < (this.file2size - 1); i++) {
					r2 = ReadCSV.readFileChunk(br2, 1);
					RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
					writer.flush();

				}
				br2.close();
			}
			ReversedLinesFileReader rbr = new ReversedLinesFileReader(new File(this.file2), Charset.defaultCharset());
			r2 = ReadCSV.readFileChunkReverse(rbr, 1);
			setNLJrockingCache(r2);
			RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
			writer.flush();

			return (Object) rbr;

		} else if ((br != null) & ((r1.size()) == (this.msize - 1)) & (reverse)) {
			RelationsJoin.writejoin(r1, this.NLJrockingCache, this.col1, this.col2, writer, this.nljswap);
			this.NLJrockingCache.clear();
			for (int i = 0; i < (this.file2size - 2); i++) {
				r2 = ReadCSV.readFileChunkReverse((ReversedLinesFileReader) br, 1);
				RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
				writer.flush();
			}
			((ReversedLinesFileReader) br).close();
			BufferedReader br2 = new BufferedReader(new FileReader(this.file2));
			// Skip 1st line
			br2.readLine();
			r2 = ReadCSV.readFileChunk(br2, 1);
			setNLJrockingCache(r2);
			RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
			writer.flush();

			return (Object) br2;

		} else if ((br != null) & ((r1.size()) == (this.msize - 1)) & (!reverse)) {
			RelationsJoin.writejoin(r1, this.NLJrockingCache, this.col1, this.col2, writer, this.nljswap);
			this.NLJrockingCache.clear();
			for (int i = 0; i < (this.file2size - 1); i++) {
				r2 = ReadCSV.readFileChunk((BufferedReader) br, 1);
				RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
				writer.flush();
			}
			((BufferedReader) br).close();
			ReversedLinesFileReader rbr = new ReversedLinesFileReader(new File(this.file2), Charset.defaultCharset());
			r2 = ReadCSV.readFileChunkReverse(rbr, 1);
			setNLJrockingCache(r2);
			RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
			writer.flush();

			return (Object) rbr;

		} else {
			RelationsJoin.writejoin(r1, this.NLJrockingCache, this.col1, this.col2, writer, this.nljswap);
			this.NLJrockingCache.clear();
			int availiablebuffers = (this.msize - r1.size());
			if (br instanceof BufferedReader) {
				int rounds = (int) Math.ceil(((this.file2size - 1) / availiablebuffers));
				for (int i = 0; i < rounds; i++) {
					r2 = ReadCSV.readFileChunk((BufferedReader) br, availiablebuffers);
					RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
					writer.flush();
				}
				((BufferedReader) br).close();

			} else {
				int rounds = (int) Math.ceil(((this.file2size - 2) / availiablebuffers));
				for (int i = 0; i < rounds; i++) {
					r2 = ReadCSV.readFileChunkReverse((ReversedLinesFileReader) br, availiablebuffers);
					RelationsJoin.writejoin(r1, r2, this.col1, this.col2, writer, this.nljswap);
					writer.flush();

				}
				((ReversedLinesFileReader) br).close();
			}
			return null;
		}

	}

	public void multipassSMJ() throws IOException {

		FileWriter writer = new FileWriter(output);
		// Write header row
		BufferedReader br1 = new BufferedReader(new FileReader(this.file1));
		ArrayList<String[]> r1 = ReadCSV.readFileChunk(br1, 2);
		BufferedReader br2 = new BufferedReader(new FileReader(this.file1));
		ArrayList<String[]> r2 = ReadCSV.readFileChunk(br2, 2);
		CSVWriter.writeLine(writer, JoinHeader.makeHeaderRow(this.file1, this.file2, r1.get(1).length, r2.get(1).length,
				this.col1, this.col2));
		br1.close();
		br2.close();
		writer.flush();

		/*
		 * If the produced m-size sorted sublists are less or equal to msize-1
		 * we can save 2*(B(R1)+B(R2)) IO's by using the EfficientSMGMerge
		 * algorithm , which joins the relations directly from sorted sublists
		 * without merge the sublists into one sorted sublist for each relation.
		 * We are going to use again a priority queue of Hashmaps with
		 * buffereaders as keys and br.readline() as value. We are going to use
		 * one such priority queue for each relation.
		 */
		if ((Math.floor((this.file1size + this.file2size) / this.msize) + 1) <= (this.msize - 1)) {
			//System.out.println("SMJ efficient");
			EfficientSMJMerge.merge(this.file1, this.file2, this.msize, this.col1, this.col2, this.file1size,
					this.file2size, this.temp, writer);
			
		} else {
			//System.out.println("SMJ not efficient");
			File file1 = SMJSort.sortRelation(this.file1, this.msize, this.col1, this.temp, false);
			File file2 = SMJSort.sortRelation(this.file2, this.msize, this.col2, this.temp, false);
			SMJMerge.merge(file1, file2, this.col1, this.col2, this.file2size, writer, this.output);
			file1.delete();
			file2.delete();

		}
		writer.close();
	}
}