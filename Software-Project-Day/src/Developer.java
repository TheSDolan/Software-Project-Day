import java.util.Random;

public class Developer extends Thread {

	private TeamLead teamLead;
	private Team team;
	private ConferenceRoom conferenceRoom;
	private final TimeObject SHOW_UP_TIME, GO_HOME_TIME;
	private Random rand;
	private int questionProb, timeBetweenQuestions;
	
	public Developer(TeamLead teamLead, Team team, ConferenceRoom conferenceRoom, int startTimeOffset, int questionProb, int timeBetweenQuestions) {
		super();
		this.teamLead = teamLead;
		this.team = team;
		this.conferenceRoom = conferenceRoom;
		rand = new Random();
		this.questionProb = questionProb;
		this.timeBetweenQuestions = timeBetweenQuestions;
		SHOW_UP_TIME = new TimeObject(8, startTimeOffset);
		GO_HOME_TIME = new TimeObject(16, 30 + startTimeOffset);
	}
	
	@Override
	public void run()
	{
		try {
			// Wait to show up to work
			while (Time.getTime().compareTo(SHOW_UP_TIME) < 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " arrives at work.");
			
			team.joinTeamToday();

			//do work, potentially generate questions until lunch time
			while(Time.getTime().compareTo(Time.LUNCH_TIME) < 0){

			}
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " goes to lunch.");
			Thread.sleep(Time.getPause(new TimeObject(1,0)));
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " returns from lunch.");

			while(Time.getTime().compareTo(Time.MEETING_TIME)< 0){
				
			}
			conferenceRoom.joinEndOfDayMeeting();
			// Wait to go home from work
			while (Time.getTime().compareTo(GO_HOME_TIME) < 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " leaves work.");
		} catch (InterruptedException e) {

		}
	}
	private void doWork(){
		if(rand.nextInt(100) +1 < questionProb){

		}
		try {
			Thread.sleep(Time.getPause(new TimeObject(0, timeBetweenQuestions)));
		}
	 	catch (InterruptedException e) {

		}

	}
	
}
