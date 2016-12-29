package aueb.msc.cs.utils;


import java.io.Writer;
import java.util.ArrayList;

public class WriteTuples {
	
	public static int[] outputTuples(Writer writer,ArrayList<String[]> r1,ArrayList<String[]> r2,
						int i_index, int j_index ,int col1, int col2) {
		
		int jjindex = j_index;
		int iindex = i_index;
		int[] indexes ={iindex,jjindex} ;
		
		try {
		while((iindex < r1.size()) && (Integer.parseInt(r1.get(iindex)[col1])) == (Integer.parseInt(r2.get(j_index)[col2]))) {
			jjindex = j_index;
			
			while((jjindex < r2.size())&&(Integer.parseInt(r1.get(iindex)[col1])) == (Integer.parseInt(r2.get(jjindex)[col2]))) {
				
				RelationsJoin.writejoin(r1.get(iindex), r2.get(jjindex), col1, col2, writer);
				jjindex++;
			}
			iindex++;
		}
		indexes[0] =  iindex;
		indexes[1] =  jjindex;
		return indexes;
		} catch (Exception e){
			e.printStackTrace();
			indexes[0] =  iindex;
			indexes[1] =  jjindex;
			return indexes;
		}
		
	}

}
