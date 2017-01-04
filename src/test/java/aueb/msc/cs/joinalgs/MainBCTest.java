package aueb.msc.cs.joinalgs;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import aueb.msc.cs.utils.ReadCSV;
 
public class MainBCTest {
    
	 @Rule
     public TemporaryFolder folder= new TemporaryFolder();

     @Test
     public void testMainB0C0nlj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C0.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "0"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C0smj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C0.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "0"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C1nlj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C1.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "1"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C1smj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C1.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "1"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C2nlj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C2.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "2"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C2smj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C2.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "2"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C3nlj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C3.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "nlj", "0", "3"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
     
     @Test
     public void testMainB0C3smj() throws IOException {
            
    	    File createdFile= folder.newFile("output.csv");
            ClassLoader classLoader = getClass().getClassLoader();
            File file1 = new File(classLoader.getResource("B.csv").getFile());
            File file2 = new File(classLoader.getResource("C.csv").getFile());
            File dbmsResults = new File(classLoader.getResource("B0C3.csv").getFile());
             
			Main.main(Utilities.parseArguments(file1, file2, createdFile, "smj", "0", "3"));
            ArrayList<String[]> programResults = ReadCSV.readFile(createdFile.getAbsolutePath(), ",");
            ArrayList<String[]> DBMSResults = ReadCSV.readFile(dbmsResults.getAbsolutePath(), ";");
            
            assertTrue("DBMS and program results have different length",programResults.size()==DBMSResults.size());
            assertTrue("DBMS and program results are different",Utilities.equalResults(programResults, DBMSResults));
            
     }
    
}