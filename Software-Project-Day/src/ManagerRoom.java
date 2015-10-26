import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ManagerRoom {
	private CountDownLatch startManagerMeeting, endManagerMeeting;
	private Semaphore questionsAsked,questionsAnswered;
	private static final int NUM_ATTENDING_MANAGER_MEETING = 4;
	private ConcurrentLinkedQueue<Thread> questionQueue;
	private boolean doorLocked;
	
	public ManagerRoom()
	{
		startManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
		endManagerMeeting = new CountDownLatch(NUM_ATTENDING_MANAGER_MEETING);
		this.questionsAsked = new Semaphore(0,true);
		this.questionsAnswered = new Semaphore(0,true);
		questionQueue = new ConcurrentLinkedQueue<Thread>();
		doorLocked = false;
	}
	
	/**
	 * Allows the team lead to ask a question of the manger
	 * @param lead - the team lead asking the question
	 * @throws InterruptedException
	 */
	public void askQuestion(TeamLead lead) throws InterruptedException
	{
		// Check if the manager is around
		if(doorLocked)
		{
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " cannot ask their question because the Manager's room is locked.");
			return;
		}
		questionsAsked.release();
		questionQueue.add(lead);
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.WAITING);
		questionsAnswered.acquire();
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.WORKING);
	}
	
	/**
	 * Allows the manager to check if any questions are at his door
	 * @throws InterruptedException
	 */
	public void checkQuestion() throws InterruptedException
	{
		if(questionsAsked.tryAcquire()){
			String questionAsker = questionQueue.poll().getName();
			InstantPrint.PrintInstantly(Time.getTime() + " Manager starts answering " + questionAsker + "'s question.");
			Thread.sleep(Time.getPause(new TimeObject(0,10)));
			InstantPrint.PrintInstantly(Time.getTime() + " Manager finishes answering " + questionAsker + "'s question.");
			questionsAnswered.release();
		}
	}
	
	/**
	 * Allows the manager to join the morning meeting with the team leads
	 * @throws InterruptedException
	 */
	public void managerJoinManagerMeeting() throws InterruptedException
	{
		startManagerMeeting.countDown();
		startManagerMeeting.await();
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.MEETING);
		InstantPrint.PrintInstantly(Time.getTime() + " Daily morning standup meeting begins.");
		Thread.sleep(Time.getPause(new TimeObject(0,15)));
		InstantPrint.PrintInstantly(Time.getTime() + " Daily morning standup meeting ends.");
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.WORKING);
		endManagerMeeting.countDown();
		endManagerMeeting.await();
	}
	
	/**
	 * allows the team leads to join the manager's morning meeting
	 * @throws InterruptedException
	 */
	public void joinManagerMeeting() throws InterruptedException
	{
		// Wait for everyone to join
		startManagerMeeting.countDown();
		startManagerMeeting.await();
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.MEETING);
		endManagerMeeting.countDown();
		endManagerMeeting.await();
		StatisticGatherer.changeTask(StatisticGatherer.TaskType.WORKING);
	}
	
	/**
	 * Allows the manager to dismiss all questions waiting at his office
	 */
	public void dismissQuestions() 
	{
		doorLocked = true;
		while(questionsAsked.tryAcquire()){
			String questionAsker = questionQueue.poll().getName();
			InstantPrint.PrintInstantly(Time.getTime() + " Manager dismisses " + questionAsker + "'s question.");
			questionsAnswered.release();
		}
	}
}
