import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {

	private static final int START_TIME_OFFSET = 30;
	public static void main(String [] args)
	{
		// List to hold all of the threads
		List<Thread> threads = new ArrayList<Thread>();
		
		// The single time object to synchronize the threads
		Time.StartTime();
		
		// The manager room resource
		ManagerRoom mr = new ManagerRoom();

		// The conference room resource
		ConferenceRoom conference = new ConferenceRoom();
				
		// The manager thread
		Manager manager = new Manager(mr, conference);
		manager.setName("Manager");
		manager.start();
		threads.add(manager);
		Random r = new Random();
		// Create the 3 teams
		for(int t = 0; t < 3; t++)
		{
			// Create the team resource
			Team team = new Team(t, conference);
			
			// Create the team lead thread
			TeamLead currLead = new TeamLead(mr, team, conference, r.nextInt(START_TIME_OFFSET));
			currLead.setName("Developer " + (t+1) + "1");
			currLead.start();
			threads.add(currLead);
			
			// Create the other 3 developers for this team
			for(int dev = 0; dev < 3; dev++)
			{
				// Create the developer thread
				Developer currDev = new Developer(currLead, team, conference, r.nextInt(START_TIME_OFFSET));
				currDev.setName("Developer" + (t+1) + "" + (dev + 2));
				currDev.start();
				threads.add(currDev);
			}
		}
		
		// Join all of the threads
		for(Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
