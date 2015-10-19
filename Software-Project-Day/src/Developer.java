
public class Developer extends Thread {

	private TeamLead teamLead;
	private Team team;
	private ConferenceRoom ConferenceRoom;
	private Time time;
	
	public Developer(TeamLead teamLead, Team team, ConferenceRoom conferenceRoom, Time time) {
		super();
		this.teamLead = teamLead;
		this.team = team;
		this.time = time;
		ConferenceRoom = conferenceRoom;
	}
	
	@Override
	public void run()
	{}
	
}
