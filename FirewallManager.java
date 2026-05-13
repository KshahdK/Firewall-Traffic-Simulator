package firewallSimulator;

import java.io.*;//to use PrintWritter & FlieWritter
import java.util.Scanner;

/**
 * FirewallManager Class This class is responsible for managing network traffic
 * records, applying security filters, and performing search operations.
 */
public class FirewallManager {
	private NetworkTraffic[] trafficRecords;
	private int recordCount;

	// Constructor to initialize the array with a specific size
	public FirewallManager(int capacity) {
		this.trafficRecords = new NetworkTraffic[capacity];
		this.recordCount = 0;
	}

	// Method to add a new traffic object to the array (Requirement: Objects as
	// parameters)
	public void addTrafficRecord(NetworkTraffic traffic) {
		if (recordCount < trafficRecords.length) {
			trafficRecords[recordCount] = traffic;
			recordCount++;
			System.out.println("Success: Traffic record added to system.");
		} else {
			System.out.println("Error: Storage full!");
		}
	}

	// Requirement: Method Overloading (Searching by IP as a String)
	public void searchByIP(String ip) {
		System.out.println("Searching for IP: " + ip);
		// Loop through the array to find a matching IP
		for (int i = 0; i < recordCount; i++) {
			if (trafficRecords[i].getSourceIP().equals(ip)) {
				trafficRecords[i].displayNetworkTraffic();
				return;
			}
		}
		System.out.println("Result: No records found for this IP.");
	}

	// Requirement: Method Overloading (Searching for traffic by Port as an Integer)
	public void searchByPort(int port) {
		System.out.println("Searching for Port: " + port);
		boolean found = false;

		// Loop through the array to find a record matching the entered Port
		for (int i = 0; i < recordCount; i++) {
			if (trafficRecords[i].getPort() == port) {
				trafficRecords[i].displayNetworkTraffic(); // Display the object details if the port matches the search
															// criteria
				found = true;
			}
		}

		if (!found) {
			System.out.println("No records found for Port: " + port);
		}
	}

	// Method to automatically analyze traffic and apply security actions
	public void applySecurityFilter(WebTraffic traffic) {
		// Block traffic if the URL contains "malicious" or uses an unsafe port
		if (traffic.getUrl().contains("malicious") || traffic.getPort() == 666) {
			traffic.applyAction(ActionStatus.BLOCK);
		} else {
			// Allow traffic if it passes the security check
			traffic.applyAction(ActionStatus.ALLOW);
		}
	}

	// Method to display a full report of all stored network traffic records
	public void displayAllRecords() {
		// Check if the system has any records before printing
		if (recordCount == 0) {
			System.out.println("No records found in the system.");
			return;
		}
		System.out.println("========== ALL NETWORK TRAFFIC REPORT ==========");
		// Iterate through the array and display each record's details
		for (int i = 0; i < recordCount; i++) {
			System.out.println("Record #" + (i + 1));
			trafficRecords[i].displayNetworkTraffic(); // Polymorphism will work here!
		}
		System.out.println("================================================");
	}

	// Method to check if the same IP address is accessing the same URL again (Rate
	// Limiting)
	public boolean isDuplicate(String ip, String url) {
		// Loop through the existing records to find a match
		for (int i = 0; i < recordCount; i++) {
			// Using instanceof for safe Downcasting to prevent ClassCastException
			if (trafficRecords[i] instanceof WebTraffic) {
				// We use Downcasting to access the URL from the WebTraffic object
				if (trafficRecords[i].getSourceIP().equals(ip)
						&& ((WebTraffic) trafficRecords[i]).getUrl().equals(url)) {
					return true; // Match found: This is a duplicate request
				}
			}
		}
		return false; // No match found: This is a unique request
	}

	public void saveRecordsToFile() { // method for save the records into a file
		try (PrintWriter writer = new PrintWriter(new FileWriter("FirewallReport.txt"))) { // try-with-resources
			// to ensures that all network traffic information is saved on the disk
			for (int i = 0; i < recordCount; i++) {
				if (trafficRecords[i] instanceof WebTraffic) { // check if method uses the instanceof operator to detect
																// WebTraffic objects
					WebTraffic wt = (WebTraffic) trafficRecords[i];

					writer.println(wt.getSourceIP() + "," + wt.getPort() + "," + wt.getProtocol() + "," + wt.getUrl()
							+ "," + wt.getUserAgent());
				}
			}
			System.out.println("Success: All records saved to FirewallReport.txt");
		} catch (IOException e) {

			System.out.println("Error: Could not save file. " + e.getMessage());
		}
	}

	public void loadRecordsFromFile() {// to save data from the text file into the program's array at startup
		File file = new File("FirewallReport.txt");
		if (!file.exists())
			return;

		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] data = line.split(",");// to break the text into individual pieces
				if (data.length == 5) {
					// uses Integer.parseInt to convert the port number from string to integer so it
					// can be stored correctly in the object
					WebTraffic loadedWeb = new WebTraffic(data[0], Integer.parseInt(data[1]), data[2], data[3],
							data[4]);
					// to re-verify the safety of each link, ensuring that "malicious" URLs remain
					// blocked.
					applySecurityFilter(loadedWeb);

					if (recordCount < trafficRecords.length) {
						trafficRecords[recordCount] = loadedWeb;
						recordCount++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Note: No previous save file detected. Starting fresh.");
		}
	}
}
