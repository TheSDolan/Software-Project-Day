import java.util.concurrent.CountDownLatch;

public class Team {
	
	private int teamNum;
	
	public int getTeamNum() {
		return teamNum;
	}

	private CountDownLatch startTeamMeeting, endTeamMeeting;
	private static final int NUM_ATTENDING_TEAM_MEETING = 4;
	private ConferenceRoom conference;
	
	public Team(int teamNum, ConferenceRoom conference) 
	{
		this.teamNum = teamNum;
		startTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		endTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		this.conference = conference;
	}
	
	public void joinTeamToday() throws InterruptedException
	{
		startTeamMeeting.countDown();
		startTeamMeeting.await();
		endTeamMeeting.countDown();
		endTeamMeeting.await();
	}
	

	public void teamLeadJoinTeamToday() throws InterruptedException {
		startTeamMeeting.countDown();
		startTeamMeeting.await();
		InstantPrint.PrintInstantly(Time.getTime() + " Everyone on team " + teamNum + " has arrived.");
		conference.startStandUpMeeting(this);
		endTeamMeeting.countDown();
		endTeamMeeting.await();
		
	}
	
	public void askQuestion(Developer d)
	{
		
	}
	
	public void askQuestion(TeamLead lead)
	{
		
	}
	
	public void checkQuestion()
	{
		
	}
}
