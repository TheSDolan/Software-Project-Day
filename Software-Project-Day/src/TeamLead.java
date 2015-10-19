
public class TeamLead extends Thread {

	private Manager manager;
	private Team team;
	private ConferenceRoom conferenceRoom;
	private Time time;
	
	public TeamLead(Manager manager, Team team, ConferenceRoom conferenceRoom, Time time) {
		super();
		this.manager = manager;
		this.team = team;
		this.time = time;
		this.conferenceRoom = conferenceRoom;
	}
	
	@Override
	public void run()
	{}
}
