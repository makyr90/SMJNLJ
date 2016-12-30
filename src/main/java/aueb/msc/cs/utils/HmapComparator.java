package aueb.msc.cs.utils;


import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Map;

public class HmapComparator implements Comparator<Map.Entry<BufferedReader, String[]>>
{
	public static int col = 0 ;
   
	@Override
    public int compare(Map.Entry<BufferedReader, String[]> t1, Map.Entry<BufferedReader, String[]> t2)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if ((Integer.parseInt((t1.getValue())[col])) < (Integer.parseInt((t2.getValue())[col])))
        {
            return -1;
        }
        if ((Integer.parseInt((t1.getValue())[col])) > (Integer.parseInt((t2.getValue())[col])))
        {
            return 1;
        }
        return 0;
    }
}