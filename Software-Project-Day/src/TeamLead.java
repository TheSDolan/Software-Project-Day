
public class TeamLead extends Thread {

	private Manager manager;
	private Team team;
	private ConferenceRoom conferenceRoom;
	
	public TeamLead(Manager manager, Team team, ConferenceRoom conferenceRoom) {
		super();
		this.manager = manager;
		this.team = team;
		this.conferenceRoom = conferenceRoom;
	}
	
	@Override
	public void run()
	{}
}
