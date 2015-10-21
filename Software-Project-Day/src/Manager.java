
public class Manager extends Thread {

	private ManagerRoom managerRoom;
	private Time time;
	private ConferenceRoom conference;
	
	private static final TimeObject SHOW_UP_TIME = new TimeObject(8, 0);
	
	
	public Manager(ManagerRoom managerRoom, ConferenceRoom conference, Time time)
	{
		this.managerRoom = managerRoom;
		this.time = time;
		this.conference = conference;
	}
	
	@Override
	public void run()
	{
		while(time.getTime().compareTo(SHOW_UP_TIME) <= 0)
		{
			System.out.println("MANAGER cannot come to work yet!");
		}
		System.out.println("MANAGER came to work!");
	}
}
