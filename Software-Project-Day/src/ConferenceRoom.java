import java.util.concurrent.CountDownLatch;

public class ConferenceRoom {
	
	private static boolean conferenceRoomBusy;
	private CountDownLatch startEndOfDayMeeting, endEndOfDayMeeting;
	private static final int NUMBER_ATTENDING_END_DAY_MEETING = 13;
	public ConferenceRoom()
	{
		conferenceRoomBusy = false;
		startEndOfDayMeeting = new CountDownLatch(NUMBER_ATTENDING_END_DAY_MEETING);
		endEndOfDayMeeting = new CountDownLatch(NUMBER_ATTENDING_END_DAY_MEETING);
	}
	
	public void joinEndOfDayMeeting() throws InterruptedException
	{
		InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " arrives at end of day meeting.");
		startEndOfDayMeeting.countDown();
		startEndOfDayMeeting.await();
		endEndOfDayMeeting.countDown();
		endEndOfDayMeeting.await();
	}
	public void managerJoinEndOfDayMeeting() throws InterruptedException
	{
		InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " arrives at end of day meeting.");
		startEndOfDayMeeting.countDown();
		startEndOfDayMeeting.await();
		InstantPrint.PrintInstantly(Time.getTime() + " End of day meeting has started.");
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
		InstantPrint.PrintInstantly(Time.getTime() + " End of day meeting has ended.");
		endEndOfDayMeeting.countDown();
		endEndOfDayMeeting.await();
	}
	
	public synchronized void startStandUpMeeting(Team t) throws InterruptedException
	{
		
		while(conferenceRoomBusy)
		{
			wait();
		}
		conferenceRoomBusy = true;
		InstantPrint.PrintInstantly(Time.getTime() + " Team " + t.getTeamNum() + " has started their meeting.");
		Thread.sleep(Time.getPause(new TimeObject(0, 15)));
		stopStandUpMeeting(t);
	}
	
	public void LeaveEndOfDayMeeting()
	{
	}
	
	public synchronized void stopStandUpMeeting(Team t)
	{
		InstantPrint.PrintInstantly(Time.getTime() + " Team " + t.getTeamNum() + " has ended their meeting.");
		conferenceRoomBusy = false;
		notifyAll();
		conferenceRoomBusy = false;
	}
}
