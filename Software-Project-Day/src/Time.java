
public class Time {
	private static final long MS_PER_MIN = 10;
	private static final long START_MINUTES = 60*7 + 45; // Start at 7:45 am
	private long sTime;
	private static Time instance;
	public static TimeObject LUNCH_TIME = new TimeObject(12,0);
	
	public Time()
	{}
	
	public void startTime()
	{
		sTime = System.currentTimeMillis();
	}
	
	private static Time getInstance()
	{
		if(instance == null)
		{
			instance = new Time();
			instance.startTime();
		}
		return instance;
	}
	
	public static void StartTime()
	{
		Time.getInstance();
	}
	
	/**
	 * 
	 * @return a Time object of the current time
	 */
	public static TimeObject getTime()
	{
		// Figure out the total minutes already passed in the day (starting at 0:00)
		long elapsed = System.currentTimeMillis() - getInstance().sTime;
		long minutesPassed = elapsed/MS_PER_MIN;
		long totalMinutes = START_MINUTES + minutesPassed;
		int hour = (int) (totalMinutes/60);
		int minute = (int) (totalMinutes%60);
		return new TimeObject(hour, minute);	
	}
	
	public static long getPause(TimeObject timeObject)
	{
		// Get the total minutes to skip
		int totalMinutes = (timeObject.hour * 60) + timeObject.minute;
		return totalMinutes * MS_PER_MIN;
	}
	
}
