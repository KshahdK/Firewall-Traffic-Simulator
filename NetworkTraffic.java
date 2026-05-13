package firewallSimulator;

/**
 * Description: This is the Superclass (Parent Class) that represents general
 * network traffic. It demonstrates Encapsulation by using private modifiers and
 * includes methods that will be overridden by subclasses to show Polymorphism.
 */
public class NetworkTraffic {
	// Encapsulation: Private attributes to protect data
	private String sourceIP;
	private int port;
	private String protocol;

	// Initializes the traffic object with source IP, port, and protocol
	public NetworkTraffic(String sourceIP, int port, String protocol) {
		this.sourceIP = sourceIP;
		this.port = port;
		this.protocol = protocol;
	}

	// Default constructor for initializing with default values
	public NetworkTraffic() {
		this.sourceIP = "0.0.0.0";
		this.port = 0;
		this.protocol = "UNKNOWN";
	}

	// Checks if the IP string contains a dot to ensure it is valid
	public boolean validateIP(String ip) {
		return ip != null && ip.contains(".");
	}

	// Validates if the traffic can be logged based on the IP address
	public void logTraffic() {
		if (validateIP(this.sourceIP)) {
			System.out.println("Traffic logged successfully.");
		} else {
			System.out.println("Invalid IP, traffic not logged.");
		}
	}

	// Updates the source IP address
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	// Retrieves the source IP address
	public String getSourceIP() {
		return sourceIP;
	}

	// Updates the connection port number
	public void setPort(int port) {
		this.port = port;
	}

	// Retrieves the connection port number
	public int getPort() {
		return port;
	}

	// Updates the protocol type
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	// Retrieves the protocol type (e.g., TCP, UDP)
	public String getProtocol() {
		return protocol;
	}

	// Prints basic information about the network traffic
	public void displayNetworkTraffic() {
		System.out.println("Traffic from: " + sourceIP + " on port: " + port + "  [" + protocol + "]");
	}

}
