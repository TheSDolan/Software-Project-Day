
public class Manager extends Thread {

	private ManagerRoom managerRoom;
	private Time time;
	private ConferenceRoom conference;
	
	public Manager(ManagerRoom managerRoom, ConferenceRoom conference, Time time)
	{
		this.managerRoom = managerRoom;
		this.time = time;
		this.conference = conference;
	}
	
	@Override
	public void run()
	{}
}
