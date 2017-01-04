package aueb.msc.cs.joinalgs;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import aueb.msc.cs.utils.ReadCSV;

public class MainDETest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testMainD0E0nlj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E0.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "0"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}

	@Test
	public void testMainD0E0smj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E0.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "0"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}
	@Test
	public void testMainD0E1nlj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E1.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "1"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}

	@Test
	public void testMainD0E1smj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E1.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "1"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}
	
	@Test
	public void testMainD0E2nlj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E2.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "2"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}

	@Test
	public void testMainD0E2smj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E2.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "2"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}
	
	@Test
	public void testMainD0E3nlj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E3.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "3"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}

	@Test
	public void testMainD0E3smj() throws IOException {

		File createdFile = folder.newFile("output.csv");
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("D.csv").getFile());
		File file2 = new File(classLoader.getResource("E.csv").getFile());
		File dbmsResults = new File(classLoader.getResource("D0E3.csv").getFile());

		Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "3"));
		ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
		ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");

		assertTrue("DBMS and program results have different length", programResults.size() == DBMSResults.size());
		assertTrue("DBMS and program results are different", Utilities.equalResults(programResults, DBMSResults));

	}



}
