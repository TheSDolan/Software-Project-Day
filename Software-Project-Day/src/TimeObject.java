import java.util.Comparator;

public class TimeObject implements Comparator, Comparable {
	public int hour, minute;
	
	public TimeObject(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}
	

	@Override
	public int compareTo(Object arg0) {
		if(arg0 instanceof TimeObject)
		{
			TimeObject compare = (TimeObject)arg0;
			if(this.hour > compare.hour)
			{
				return 1;
			}
			else if(this.hour < compare.hour)
			{
				return -1;
			}
			else
			{
				if(this.minute > compare.minute)
				{
					return 1;
				}
				else if(this.minute < compare.minute)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		}
		return 0;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		if(arg0 instanceof TimeObject && arg1 instanceof TimeObject)
		{
			TimeObject compare = (TimeObject)arg0;
			TimeObject compare2 = (TimeObject)arg1;
			if(compare.hour > compare2.hour)
			{
				return 1;
			}
			else if(compare.hour < compare2.hour)
			{
				return -1;
			}
			else
			{
				if(compare.minute > compare2.minute)
				{
					return 1;
				}
				else if(compare.minute < compare2.minute)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		}
		return 0;
	}
	
}
