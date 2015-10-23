import java.util.concurrent.CountDownLatch;

public class ManagerRoom {
	private CountDownLatch startManagerMeeting, endManagerMeeting;
	private static final int NUM_ATTENDING_MANAGER_MEETING = 4;
	public ManagerRoom()
	{
		startManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
		endManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
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
		InstantPrint.PrintInstantly(Time.getTime() + " Daily morning standup meeting begins.");
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
		InstantPrint.PrintInstantly(Time.getTime() + " Daily morning standup meeting ends.");
		endManagerMeeting.countDown();
		endManagerMeeting.await();
	}
	public void joinManagerMeeting() throws InterruptedException
	{
		// Wait for everyone to join
		startManagerMeeting.countDown();
		startManagerMeeting.await();
		endManagerMeeting.countDown();
		endManagerMeeting.await();
	}
	
	public void leaveManagerMeeting()
	{}
}
