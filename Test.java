package firewallSimulator;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// Main execution point of the program
		Scanner sc = new Scanner(System.in);

		// System Authentication using username and password
		System.out.print("Username: ");
		String username = sc.next();
		System.out.print("Password: ");
		String password = sc.next();

		// Check if credentials match the system records
		if (username.equals("admin") && password.equals("1234")) {
			displayMainMenu(sc);
		} else {
			System.out.println("Access Denied: Incorrect credentials.");
		}
	}

	// Displays the interactive menu for the firewall simulator
	public static void displayMainMenu(Scanner sc) {
		// Initializing the manager with capacity for 10 records
		FirewallManager manager = new FirewallManager(10);
		// Loads all saved records from the text file into the program at startup.
		manager.loadRecordsFromFile();
		int options;

		// Loop to keep the menu running until the user exits
		do {
			System.out.println("\n--- Firewall Simulator Menu ---");
			System.out.println("1. Add New Web Traffic");
			System.out.println("2. Search Records");
			System.out.println("3. Display All Stored Records");
			System.out.println("4. Save Records to Text File");
			System.out.println("5. Exit");
			System.out.print("Select an option: ");
			try { // preventing the program from crashing if a user enters incorrect data
				options = sc.nextInt();
			} catch (Exception e) {
				System.out.println(">>> Error: Invalid Input! Please enter a number (1-5) only.");
				sc.next(); // used to clear the invalid input from the Scanner's memory
				options = 0; // allowing the user to try again
				continue;
			}

			// Handle user choices using a switch statement
			switch (options) {
			case 1:
				// Collecting data to create WebTraffic object
				System.out.print("Enter IP: ");
				String sourceIP = sc.next();
				// Check if the entered IP follows the correct format (contains dots)
				if (!sourceIP.contains(".") || sourceIP.length() < 7) {
					System.out.println(">>> Warning: Invalid IP format entered!");
					System.out.println(">>> Using default secure IP: 127.0.0.1");
					sourceIP = "127.0.0.1";
				}
				System.out.print("Enter Port (Available: 80, 443, 8080): ");
				int portNumber = sc.nextInt();
				System.out.print("Enter Protocol (Choose: TCP, UDP, HTTP): ");
				String protocolName = sc.next();
				System.out.print("Enter URL: ");
				String targetURL = sc.next();
				System.out.print("Enter Browser: ");
				String userAgent = sc.next();

				// Check if this IP and URL combination already exists in the system
				if (manager.isDuplicate(sourceIP, targetURL)) {
					System.out.println(">> Alert: This IP has already accessed this URL!");
					System.out.println(">> Action: Record will be marked as LOG_ONLY for monitoring.");

					// Create the object and mark it for monitoring (LOG_ONLY) instead of normal
					// filter
					WebTraffic webTrafficObject = new WebTraffic(sourceIP, portNumber, protocolName, targetURL,
							userAgent);
					webTrafficObject.applyAction(ActionStatus.LOG_ONLY);
					manager.addTrafficRecord(webTrafficObject);
				} else {

					// If it's a new entry, proceed with the standard security filtering
					WebTraffic webTrafficObject = new WebTraffic(sourceIP, portNumber, protocolName, targetURL,
							userAgent);
					manager.applySecurityFilter(webTrafficObject);
					manager.addTrafficRecord(webTrafficObject);
				}
				break;
			case 2:
				// Searching logic
				System.out.print("Enter IP to search: ");
				String searchIP = sc.next();
				manager.searchByIP(searchIP);
				break;
			case 3:
				// display all records
				manager.displayAllRecords();
				break;

			case 4:
				// save record to the file
				manager.saveRecordsToFile();
				break;
			case 5:
				System.out.println("Closing System...");
				break;

			default:
				System.out.println("Invalid option! Please choose 1, 2, 3, 4 or 5.");
			}
		} while (options != 5);
	}
}
