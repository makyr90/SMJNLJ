package aueb.msc.cs.joinalgs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static String file1,file2,joinmethod,temp,output;
	public static int col1,col2,msize,file1size,file2size;

	public static void main(String[] args) {
		Checkargs.checklength(args);
		initialize(args);
		

	}

	public static void initialize(String[] arguments) {
		for (int i = 0; i < arguments.length; i = i + 2) {
			if (arguments[i].equalsIgnoreCase("-f1")) {
				file1 = arguments[i + 1];
				Checkargs.checkpath(file1);
			} else if (arguments[i].equalsIgnoreCase("-f2")) {
				file2 = arguments[i + 1];
				Checkargs.checkpath(file2);

			} else if (arguments[i].equalsIgnoreCase("-j")) {
				joinmethod = arguments[i + 1].toUpperCase();
				Checkargs.checkjoinalgorithm(joinmethod);
				
			} else if (arguments[i].equalsIgnoreCase("-m")) {
				msize = Integer.parseInt(arguments[i + 1]);
				Checkargs.checkmemorysize(msize);

			}

			else if (arguments[i].equalsIgnoreCase("-t")) {
				temp = arguments[i + 1];
				Checkargs.checkpath(temp);

			}

			else if (arguments[i].equalsIgnoreCase("-o")) {
				output = arguments[i + 1];
				File newFile = new File(output);
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (arguments[i].equalsIgnoreCase("-a1")) {
				col1 = Integer.parseInt(arguments[i + 1]);
				file1size = Checkargs.checkcolumn(file1, col1);

			}

			else if (arguments[i].equalsIgnoreCase("-a2")) {
				col2 = Integer.parseInt(arguments[i + 1]);
				file2size = Checkargs.checkcolumn(file2, col2);

			}

		}
	}
}