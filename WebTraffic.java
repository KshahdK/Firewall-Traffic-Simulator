package firewallSimulator;

/**
 * Description: This class represents a specific type of network traffic (Web
 * Traffic). It demonstrates the core OOP concepts of Inheritance by extending
 * the NetworkTraffic base class and Encapsulation by protecting data fields.
 */
public class WebTraffic extends NetworkTraffic {
	private String url;
	private String userAgent;
	private ActionStatus status;

	// Initializes a new web traffic object with both network and web-specific data
	public WebTraffic(String sourceIP, int port, String protocol, String url, String userAgent) {
		super(sourceIP, port, protocol);
		this.url = url;
		this.userAgent = userAgent;
	}

	// Updates the target URL with a new string value
	public void setUrl(String url) {
		this.url = url;
	}

	// Updates the browser or application identifier with a new value
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	// Retrieves the stored target URL address
	public String getUrl() {
		return url;
	}

	// Retrieves the browser or application identifier string
	public String getUserAgent() {
		return userAgent;
	}

	// Sets the firewall action status using the ActionStatus enum
	public void applyAction(ActionStatus action) {
		this.status = action;
		// System.out.println("Action applied: " + this.status);
	}

	// Compares two WebTraffic objects to see if they target the same URL
	public void compareTraffic(WebTraffic otherTraffic) {
		if (this.url.equals(otherTraffic.getUrl())) {
			System.out.println("Both traffic objects are targeting the same URL.");
		} else {
			System.out.println("Traffic targets are different.");
		}
	}

	// Prints an analysis of the traffic including parent data and web-specific
	// details
	@Override
	public void displayNetworkTraffic() {
		System.out.println(">>> Web Traffic Analysis <<<");

		super.displayNetworkTraffic();

		System.out.println("Target URL: " + this.url);
		System.out.println("User-Agent: " + this.userAgent);

		System.out.println("Security Status: [" + this.status + "]");
		System.out.println("----------------------------------------");
	}
}
