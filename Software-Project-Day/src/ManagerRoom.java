import java.util.concurrent.CountDownLatch;

public class ManagerRoom {
	private CountDownLatch startManagerMeeting;
	private static final int NUM_ATTENDING_MANAGER_MEETING = 4;
	public ManagerRoom()
	{
		startManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
	}
	
	public void askQuestion(TeamLead lead)
	{}
	
	public void checkQuestion()
	{
		
	}
	
	public void managerJoinManagerMeeting() throws InterruptedException
	{
		startManagerMeeting.countDown();
		startManagerMeeting.await();
		System.out.println(Time.getTime() + " Daily morning standup meeting begins.");
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
		System.out.println(Time.getTime() + " Daily morning standup meeting ends.");
	}
	public void joinManagerMeeting() throws InterruptedException
	{
		// Wait for everyone to join
		startManagerMeeting.countDown();
		startManagerMeeting.await();
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
	}
	
	public void leaveManagerMeeting()
	{}
}
