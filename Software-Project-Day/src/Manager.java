
public class Manager extends Thread {

	private ManagerRoom managerRoom;
	private ConferenceRoom conference;

	private static final TimeObject SHOW_UP_TIME = new TimeObject(8, 0);
	private static final TimeObject GO_HOME_TIME = new TimeObject(17, 0);

	public Manager(ManagerRoom managerRoom, ConferenceRoom conference) {
		this.managerRoom = managerRoom;
		this.conference = conference;
	}

	@Override
	public void run() {
		try {
			// Wait to show up to work
			while (Time.getTime().compareTo(SHOW_UP_TIME) < 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			System.out.println(Time.getTime() + " Manager arrives at work.");
			
			// join the morning meeting
			managerRoom.managerJoinManagerMeeting();
			
			
			// Wait to go home from work
			while (Time.getTime().compareTo(GO_HOME_TIME) <= 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			System.out.println(Time.getTime() + " Manager leaves work.");
		} catch (InterruptedException e) {

		}
	}
}