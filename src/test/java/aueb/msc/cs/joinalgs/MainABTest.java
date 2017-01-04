package aueb.msc.cs.joinalgs;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import aueb.msc.cs.utils.ArrayListCompare;
import aueb.msc.cs.utils.ReadCSV;
 
public class MainABTest {
    
	 @Rule
     public TemporaryFolder folder= new TemporaryFolder();

     @Test
     public void testMainA0B0nlj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("A.csv").getFile());
            File file2 = new File(classLoader.getResource("B.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("A0B0.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "0"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            ArrayListCompare.sort(programResults, 0);
            ArrayListCompare.sort(DBMSResults, 0);
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainA0B0smj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("A.csv").getFile());
            File file2 = new File(classLoader.getResource("B.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("A0B0.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "0"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            ArrayListCompare.sort(programResults, 0);
            ArrayListCompare.sort(DBMSResults, 0);
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     
}