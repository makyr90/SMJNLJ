package aueb.msc.cs.utils;


import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

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
				
				String[] rel1 = new String[r1.get(iindex).length];
				System.arraycopy(r1.get(iindex), 0, rel1, 0, r1.get(iindex).length);
				String[] rel2 = new String[r2.get(jjindex).length];
				System.arraycopy(r2.get(jjindex), 0, rel2, 0, r2.get(jjindex).length);
				
				rel2 = ArrayUtils.removeElement(rel2, rel2[col2]);
				
				String swap = rel1[0];
				rel1[0] = rel1[col1];
				rel1[col1] = swap;
				List<String> joinattr = Arrays.asList(ArrayUtils.addAll(rel1, rel2));
				CSVWriter.writeLine(writer, joinattr);
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
