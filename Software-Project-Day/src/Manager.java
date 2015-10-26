
public class Manager extends Thread {

	private ManagerRoom managerRoom;
	private ConferenceRoom conference;

	// Important times
	private static final TimeObject SHOW_UP_TIME = new TimeObject(8, 0);
	private static final TimeObject GO_HOME_TIME = new TimeObject(17, 0);
	private static final TimeObject MEETING_ONE = new TimeObject(10, 0);
	private static final TimeObject MEETING_TWO = new TimeObject(14, 0);

	/**
	 * 
	 * @param managerRoom - this manager's room resource
	 * @param conference - the conference room resource
	 */
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
			
			while(Time.getTime().compareTo(MEETING_ONE)< 0){
				doNormalTask();
			}
			
			// Go to first executive meeting
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " goes to executive meeting.");
			Thread.sleep(Time.getPause(new TimeObject(1,0)));
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " returns from executive meeting.");
			
			//do work, potentially generate questions until lunch time
			while(Time.getTime().compareTo(Time.LUNCH_TIME) < 0){
				doNormalTask();
			}
			// Go to lunch
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " goes to lunch.");
			Thread.sleep(Time.getPause(new TimeObject(1,0)));
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " returns from lunch.");

			while(Time.getTime().compareTo(MEETING_TWO)< 0){
				doNormalTask();
			}
			// Go to second executive meeting
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " goes to executive meeting.");
			Thread.sleep(Time.getPause(new TimeObject(1,0)));
			InstantPrint.PrintInstantly(Time.getTime() + " " + Thread.currentThread().getName() + " returns from executive meeting.");
			
			while(Time.getTime().compareTo(Time.MEETING_TIME)< 0){
				doNormalTask();
			}
			managerRoom.dismissQuestions();
			conference.managerJoinEndOfDayMeeting();
			
			// Wait to go home from work
			while (Time.getTime().compareTo(GO_HOME_TIME) < 0) {
				// Sleep for a simulation minute
				Thread.sleep(Time.getPause(new TimeObject(0, 1)));
			}
			System.out.println(Time.getTime() + " Manager leaves work.");
		} catch (InterruptedException e) {

		}
	}
	
	private void doNormalTask() throws InterruptedException
	{
		managerRoom.checkQuestion();
		Thread.sleep(Time.getPause(new TimeObject(0,1)));
	}
}