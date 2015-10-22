
public class TeamLead extends Thread {

	private ManagerRoom managerRoom;
	private Team team;
	private ConferenceRoom conferenceRoom;
	private final TimeObject SHOW_UP_TIME, GO_HOME_TIME;
	
	
	
	public TeamLead(ManagerRoom managerRoom, Team team, ConferenceRoom conferenceRoom, int startTimeOffset) {
		super();
		this.managerRoom = managerRoom;
		this.team = team;
		this.conferenceRoom = conferenceRoom;
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
			
			// join the morning meeting
			managerRoom.joinManagerMeeting();
			team.teamLeadJoinTeamToday();
			
			// Wait to go home from work
			while (Time.getTime().compareTo(GO_HOME_TIME) < 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " leaves work.");
		} catch (InterruptedException e) {

		}
		
	}
}
