import java.util.Comparator;

public class TimeObject implements Comparator<TimeObject>, Comparable<TimeObject> {
	public int hour, minute;

	public TimeObject(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int compareTo(TimeObject compare) {
		if (this.hour > compare.hour) {
			return 1;
		} else if (this.hour < compare.hour) {
			return -1;
		} else {
			if (this.minute > compare.minute) {
				return 1;
			} else if (this.minute < compare.minute) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int compare(TimeObject compare, TimeObject compare2) {
		if (compare.hour > compare2.hour) {
			return 1;
		} else if (compare.hour < compare2.hour) {
			return -1;
		} else {
			if (compare.minute > compare2.minute) {
				return 1;
			} else if (compare.minute < compare2.minute) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	 
	@Override
	public String toString()
	{
		int tempHour = hour;
		String amOrPm = "AM";
		if(tempHour > 12)
		{
			tempHour -= 12;
			amOrPm = "PM";
		}
		return String.format("%02d:%02d %s", tempHour, minute, amOrPm);
	}

}
