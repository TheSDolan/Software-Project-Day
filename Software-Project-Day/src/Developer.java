
public class Developer extends Thread {

	private TeamLead teamLead;
	private Team team;
	private ConferenceRoom ConferenceRoom;
	
	public Developer(TeamLead teamLead, Team team, ConferenceRoom conferenceRoom, int startTimeOffset) {
		super();
		this.teamLead = teamLead;
		this.team = team;
		ConferenceRoom = conferenceRoom;
	}
	
	@Override
	public void run()
	{}
	
}
