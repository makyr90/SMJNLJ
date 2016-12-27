package aueb.msc.cs.joinalgs;

import aueb.msc.cs.utils.Checkargs;

public class Main {

	public static void main(String[] args) {
		
		Checkargs.checklength(args);
		Join join = new Join(args);
		if (join.getFile1size() + join.getFile2size() <= join.getMsize()) 
			join.singlePass();
			
		else if (join.getJoinmethod().equals("NLJ"))
			join.rockingNLJ();

	}

}