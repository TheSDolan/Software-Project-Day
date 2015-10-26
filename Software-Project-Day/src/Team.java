import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Team {
	
	private int teamNum;
	private boolean teamLeadGone;
	private CountDownLatch startTeamMeeting, endTeamMeeting;
	private Semaphore questionsAsked,questionsAnswered;
	private ManagerRoom managerRoom;
	private static final int NUM_ATTENDING_TEAM_MEETING = 4;
	private ConferenceRoom conference;
	private Random rand;
	private ConcurrentLinkedQueue<Thread> questionQueue;
	
	/**
	 * The constructor for the team
	 * @param teamNum - The team identifier
	 * @param conference - the conference room resource
	 * @param managerRoom - the manager's room resource
	 */
	public Team(int teamNum, ConferenceRoom conference, ManagerRoom managerRoom)
	{
		this.teamNum = teamNum;
		startTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		endTeamMeeting = new CountDownLatch(NUM_ATTENDING_TEAM_MEETING);
		this.questionsAsked = new Semaphore(0,true);
		this.questionsAnswered = new Semaphore(0,true);
		this.conference = conference;
		this.managerRoom = managerRoom;
		questionQueue = new ConcurrentLinkedQueue<Thread>();
		rand = new Random();
		teamLeadGone = false;
	}
	
	/**
	 * Allows developers to join the morning team meeting
	 * @throws InterruptedException
	 */
	public void joinTeamToday() throws InterruptedException
	{
		startTeamMeeting.countDown();
		startTeamMeeting.await();
		endTeamMeeting.countDown();
		endTeamMeeting.await();
	}
	

	/**
	 * Allows the team lead to join the morning team meeting
	 * @throws InterruptedException
	 */
	public void teamLeadJoinTeamToday() throws InterruptedException {
		startTeamMeeting.countDown();
		startTeamMeeting.await();
		InstantPrint.PrintInstantly(Time.getTime() + " Everyone on team " + teamNum + " has arrived.");
		conference.startStandUpMeeting(this);
		endTeamMeeting.countDown();
		endTeamMeeting.await();
		
	}
	
	/**
	 * 
	 * @param d - The developer asking the question
	 * @throws InterruptedException
	 */
	public void askQuestion(Developer d) throws InterruptedException
	{
		// Check if the team lead is even around to answer the developer
		if(teamLeadGone)
		{
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " cannot ask their question because the Team Lead is gone.");
			return;
		}
		InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " asks a question.");
		questionsAsked.release();
		questionQueue.add(d);
		questionsAnswered.acquire();
	}
	
	public void askQuestion(TeamLead lead) throws InterruptedException
	{
		InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " asks the manager a question.");
		managerRoom.askQuestion(lead);
	}
	
	/**
	 * The team lead checks if anyone on his team is asking a question
	 * @param lead - The lead checking if there is a question
	 * @throws InterruptedException
	 */
	public void checkQuestion(TeamLead lead) throws InterruptedException
	{
		// Answer all available questions
		while(questionsAsked.tryAcquire()){
			String questionAsker = questionQueue.poll().getName();
			if(rand.nextInt(2) == 0)
			{
				InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " cannot answer " + questionAsker + "'s question.");
				askQuestion(lead);
			}
			else
			{
				InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " answers " + questionAsker + "'s question.");
			}
			questionsAnswered.release();
		}
	}

	/**
	 * Allows the Team lead to dismiss all questions if its the end of the day
	 */
	public void dismissQuestions() {
		teamLeadGone = true;
		while(questionsAsked.tryAcquire()){
			String questionAsker = questionQueue.poll().getName();
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " dismisses " + questionAsker + "'s question.");
			questionsAnswered.release();
		}
	}
	
	public int getTeamNum() {
		return teamNum;
	}
}
