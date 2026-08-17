public class SecurityRules {
    private static final int[] BLOCKED_PORTS = {21, 23, 135, 445, 3389, 4444};

    public static boolean isPortSuspicious(int port) {
        for (int blocked : BLOCKED_PORTS) {
            if (blocked == port) {
                return true;
            }
        }
        return false;
    }
}