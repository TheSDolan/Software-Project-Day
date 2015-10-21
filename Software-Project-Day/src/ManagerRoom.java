import java.util.concurrent.CountDownLatch;

public class ManagerRoom {
	private CountDownLatch startManagerMeeting;
	private static final int NUM_ATTENDING_MANAGER_MEETING = 4;
	public ManagerRoom()
	{}
	
	public void askQuestion(TeamLead lead)
	{
		startManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
	}
	
	public void checkQuestion()
	{
		
	}
	
	public void joinManagerMeeting() throws InterruptedException
	{
		// Wait for everyone to join
		startManagerMeeting.countDown();
		System.out.println(Time.getTime() + " Daily morning standup meeting begins.");
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
		System.out.println(Time.getTime() + " Daily morning standup meeting ends.");
	}
	
	public void leaveManagerMeeting()
	{}
}
