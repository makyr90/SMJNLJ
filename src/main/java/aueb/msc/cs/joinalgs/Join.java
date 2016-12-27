package aueb.msc.cs.joinalgs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import aueb.msc.cs.utils.CSVWriter;
import aueb.msc.cs.utils.Checkargs;
import aueb.msc.cs.utils.ReadCSV;

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
		if (this.joinmethod.equals("NLJ")) {
			try {
				this.singlePassNLJ();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			this.singlePassSMJ();
	}

	public void singlePassNLJ() throws IOException {

		ArrayList<String[]> r1 = ReadCSV.readfile(this.file1);
		ArrayList<String[]> r2 = ReadCSV.readfile(this.file2);

		ArrayList<List<String>> results = new ArrayList<>();
		for (String[] tupler1 : r1) {
			for (String[] tupler2 : r2) {
				if (tupler1[this.col1].equals(tupler2[this.col2])) {

					tupler2 = ArrayUtils.removeElement(tupler2, tupler2[this.col2]);

					List<String> joinattr = Arrays.asList(ArrayUtils.addAll(tupler1, tupler2));
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

	public void singlePassSMJ() {

	}
}
