package hw5.model;

import java.util.ArrayList;
import java.util.List;

public class Quarter {
	
	List<String> next = new ArrayList<String>() ;
	
	public Quarter()
	{
		
	}
	
	public List<String> getQuarter(int week,int year)
	{
		
		
		
		
		
		if(week>=1 && week <13)
		{
			String nextq = "Spring"+" "+year;
			next.add(nextq);
		}
		
		if(week>=13 && week <25)
		{
			String nextq = "Summer"+" "+year;
			next.add(nextq);
		}
		
		if(week>=25 && week <38)
		{
			String nextq = "Fall"+" "+year;
			next.add(nextq);
		}
		
		if(week>=38)
		{
			year=year+1;
			String nextq = "Winter"+" "+(year);
			next.add(nextq);
		}
		
		return next;
		
	}
	

}
