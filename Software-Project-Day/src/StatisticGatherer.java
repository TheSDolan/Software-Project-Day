import java.util.HashMap;

/**
 * StatisticGatherer gathers statistics for the entire simulation
 * @author ermam
 */
public class StatisticGatherer {
	private static StatisticGatherer instance;
	private HashMap<Thread, StatObject> threadMap;
	
	/**
	 * Enum for the different Tasks
	 * @author ermam
	 */
	public enum TaskType
	{
		WORKING,
		WAITING,
		LUNCH,
		MEETING
	}
	
	private StatisticGatherer()
	{
		threadMap = new HashMap<Thread, StatObject>();
	}
	
	/**
	 * Singleton instance accessor
	 * @return
	 */
	private static StatisticGatherer getInstance()
	{
		if(instance == null)
		{
			instance = new StatisticGatherer();
		}
		return instance;
	}
	
	/**
	 * Singleton method to change the task for the current thread
	 * 
	 * @param tt - the new task
	 */
	public synchronized static void changeTask(TaskType tt)
	{
		getInstance().changeTaskPrivate(tt);
	}
	
	/**
	 * Changes the task for the current thread
	 * @param tt - the new task
	 */
	private void changeTaskPrivate(TaskType tt)
	{
		if(this.threadMap.containsKey(Thread.currentThread()))
		{
			StatObject so = threadMap.get(Thread.currentThread());
			so.changeTask(tt);
		}
		else
		{
			this.threadMap.put(Thread.currentThread(), new StatObject(tt));
		}
	}
	
	/**
	 * Prints out a string that tells the 4 statistics of how people spent their time
	 * @return
	 */
	public static String getStatistics()
	{
		long totalLunch = 0;
		long totalWorking = 0;
		long totalMeeting = 0;
		long totalWaiting = 0;
		
		for(StatObject so : getInstance().threadMap.values())
		{
			totalLunch += so.totalLunch;
			totalWorking += so.totalWorking;
			totalMeeting += so.totalMeeting;
			totalWaiting += so.totalWaiting;
		}
		return "Total Lunch: " + millisToMinutes(totalLunch) + " Total Working: " +
		millisToMinutes(totalWorking) + " Total Meeting: " + millisToMinutes(totalMeeting)
		+ " Total Waiting: " + millisToMinutes(totalWaiting);
	}
	
	private static int millisToMinutes(long millis)
	{
		return (int) (millis/10);
	}
	
	private class StatObject
	{
		private long lastCheckIn, totalWorking, totalLunch, totalMeeting, totalWaiting ;
		private TaskType currentType;
		
		public StatObject(TaskType startingTask)
		{
			lastCheckIn = System.currentTimeMillis();
			currentType = startingTask;
			totalWorking = 0;
			totalLunch = 0;
			totalWaiting = 0;
			totalMeeting = 0;
		}
		
		public void changeTask(TaskType tt)
		{
			long elapsed = System.currentTimeMillis() - lastCheckIn;
			switch (currentType)
			{
				case WORKING:
					totalWorking += elapsed;
					break;
				case LUNCH:
					totalLunch += elapsed;
					break;
				case WAITING:
					totalWaiting += elapsed;
					break;
				case MEETING:
					totalMeeting += elapsed;
					break;
				default:
					System.out.println("ISSUE FOUND");
			}
			lastCheckIn = System.currentTimeMillis();
			currentType = tt;
		}
	}
}
