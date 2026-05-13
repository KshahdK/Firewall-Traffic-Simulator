package firewallSimulator;

/**
 * Requirement: Enumeration as a class This enum defines the possible actions
 * the firewall can take.
 */
public enum ActionStatus {
	ALLOW, // Permission granted to pass
	BLOCK, // Traffic is stopped
	LOG_ONLY // Traffic is recorded but not stopped
}
