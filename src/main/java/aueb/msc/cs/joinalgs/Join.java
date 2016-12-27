package aueb.msc.cs.joinalgs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

import aueb.msc.cs.utils.ArrayListCompare;
import aueb.msc.cs.utils.CSVWriter;
import aueb.msc.cs.utils.Checkargs;
import aueb.msc.cs.utils.ReadCSV;
import aueb.msc.cs.utils.WriteTuples;

public class Join {

	private String file1, file2, joinmethod, temp, output;
	private int col1, col2, msize, file1size, file2size;

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

		ArrayList<String[]> r1 = ReadCSV.readfile(this.file1);
		ArrayList<String[]> r2 = ReadCSV.readfile(this.file2);

		ArrayList<List<String>> results = new ArrayList<>();
		for (String[] tupler1 : r1) {
			for (String[] tupler2 : r2) {
				if (tupler1[this.col1].equals(tupler2[this.col2])) {

					String[] rel1 = new String[tupler1.length];
					System.arraycopy(tupler1, 0, rel1, 0,tupler1.length);
					String[] rel2 = new String[tupler2.length];
					System.arraycopy(tupler2, 0, rel2, 0,tupler2.length);
					
					rel2 = ArrayUtils.removeElement(rel2, rel2[this.col2]);
					
					String swap = rel1[0];
					rel1[0] = rel1[this.col1];
					rel1[this.col1] = swap;
					List<String> joinattr = Arrays.asList(ArrayUtils.addAll(rel1, rel2));
					results.add(joinattr);

				}

			}
		}

		FileWriter writer = new FileWriter(this.output);
		for (List<String> joinedtuple : results) {
			CSVWriter.writeLine(writer, joinedtuple);
		}
		writer.flush();
		writer.close();

	}

	public void singlePassSMJ() throws IOException {
		
		ArrayList<String[]> r1 = ReadCSV.readfile(this.file1);
		ArrayList<String[]> r2 = ReadCSV.readfile(this.file2);
		ArrayListCompare.sort(r1, this.col1);
		ArrayListCompare.sort(r2, this.col2);
		int iindex = 0, jindex = 0;
		int[] indexes;
		FileWriter writer = new FileWriter(this.output);
		while ((iindex < r1.size()) && (jindex < r2.size())) {
			if ((Integer.parseInt(r1.get(iindex)[col1])) == (Integer.parseInt(r2.get(jindex)[col2]))) {
				indexes = WriteTuples.outputTuples(writer, r1, r2, iindex, jindex, this.col1, this.col2);
				writer.flush();
				iindex = indexes[0];
				jindex = indexes[1];

			} else if ((Integer.parseInt(r1.get(iindex)[col1])) < (Integer.parseInt(r2.get(jindex)[col2]))){
				iindex++;
			}else {
				jindex++;
		}
		}
		writer.close();

	}}
