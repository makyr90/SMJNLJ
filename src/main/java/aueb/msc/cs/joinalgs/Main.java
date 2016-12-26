package aueb.msc.cs.joinalgs;

public class Main {

	public static void main(String[] args) {
		
		Checkargs.checklength(args);
		Join join = new Join(args);
		if (join.getFile1size() + join.getFile2size() <= join.getMsize()) {
			join.singlePass();
		}

	}

}