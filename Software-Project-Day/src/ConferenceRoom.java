import java.util.concurrent.CountDownLatch;

public class ConferenceRoom {
	private static final int NUM_ATTENDING_TEAM_MEETING = 4;
	private static boolean conferenceRoomBusy;
	public ConferenceRoom()
	{
		conferenceRoomBusy = false;
	}
	
	public void JoinEndOfDayMeeting()
	{
		
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
