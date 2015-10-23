import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Team {
	
	private int teamNum;
	
	public int getTeamNum() {
		return teamNum;
	}

	private CountDownLatch startTeamMeeting, endTeamMeeting;
	private Semaphore questionsAsked,questionsAnswered;
	private ManagerRoom managerRoom;
	private static final int NUM_ATTENDING_TEAM_MEETING = 4;
	private ConferenceRoom conference;
	private Random rand;
	
	public Team(int teamNum, ConferenceRoom conference, ManagerRoom managerroom)
	{
		this.teamNum = teamNum;
		startTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		endTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		this.questionsAsked = new Semaphore(0,true);
		this.questionsAnswered = new Semaphore(0,true);
		this.conference = conference;
		this.managerRoom = managerRoom;
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
		questionsAsked.release();
		try {
			questionsAnswered.acquire();
		}
		catch(Exception e){

		}
	}
	
	public void askQuestion(TeamLead lead)
	{
		managerRoom.askQuestion(lead);
	}
	
	public void checkQuestion(TeamLead lead)
	{
		if(questionsAsked.tryAcquire()){
			if(rand.nextInt(2) == 0) askQuestion(lead);
			questionsAnswered.release();
		}
	}
}
