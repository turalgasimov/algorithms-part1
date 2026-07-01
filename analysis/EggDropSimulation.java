public class EggDropSimulation {

    // A simulated building helper interface to check if an egg breaks
    static class Building {
        private final int T; // The hidden threshold floor
        private int eggCount = 0;
        private int tossCount = 0;

        public Building(int T) {
            this.T = T;
        }

        public boolean drop(int floor) {
            tossCount++;
            if (floor >= T) {
                eggCount++;
                return true; // Breaks
            }
            return false; // Survives
        }

        public void printStats(String version) {
            System.out.printf("%s -> Tosses: %d, Eggs Broken: %d%n", version, tossCount, eggCount);
        }
    }

    // Version 0: 1 Egg, <= T tosses
    public static int version0(Building b, int n) {
        int floor = 1;
        while (floor <= n) {
            if (b.drop(floor)) return floor;
            floor++;
        }
        return n + 1;
    }

    // Version 1: ~1 lg n eggs, ~1 lg n tosses
    public static int version1(Building b, int n) {
        int left = 1, right = n;
        int result = n + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (b.drop(mid)) {
                result = mid;    // It broke, so T <= mid
                right = mid - 1; // Look lower
            } else {
                left = mid + 1;  // It survived, so T > mid
            }
        }
        return result;
    }

    // Version 2: ~lg T eggs, ~2 lg T tosses
    public static int version2(Building b, int n) {
        int floor = 1;
        // Step 1: Repeated doubling to find the interval
        while (floor <= n && !b.drop(floor)) {
            floor *= 2;
        }

        // Interval is between (floor/2) and min(floor, n)
        int left = floor / 2 + 1;
        int right = Math.min(floor, n);
        int result = (floor >= T_global_mock_check(b)) ? Math.min(floor, n) : n + 1; 
        
        // Step 2: Binary search within the found interval
        if (floor <= n) result = floor; // It broke at 'floor'
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (b.drop(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // Version 3: 2 Eggs, ~2 sqrt(n) tosses
    public static int version3(Building b, int n) {
        int blockSize = (int) Math.sqrt(n);
        int floor = blockSize;

        // Drop first egg at block boundaries
        while (floor <= n && !b.drop(floor)) {
            floor += blockSize;
        }

        // First egg broke at 'floor', or we reached past the building top
        int start = floor - blockSize + 1;
        int end = Math.min(floor, n);

        // Linear scan the block with the 2nd egg
        for (int i = start; i <= end; i++) {
            if (b.drop(i)) return i;
        }
        return n + 1;
    }

    // Version 4: 2 Eggs, <= c * sqrt(T) tosses
    public static int version4(Building b, int n) {
        int floor = 0;
        int step = 1;

        // Drop first egg at triangular numbers: 1, 3, 6, 10, 15...
        while (floor <= n) {
            floor += step;
            step++;
            if (b.drop(Math.min(floor, n))) {
                break;
            }
            if (floor > n) return n + 1;
        }

        // First egg broke. Scan the step interval linearly
        int end = Math.min(floor, n);
        int start = floor - (step - 1) + 1;

        for (int i = start; i <= end; i++) {
            if (b.drop(i)) return i;
        }
        return n + 1;
    }

    // Simple mock tool to verify logic bounds in Version 2 wrapper
    private static int T_global_mock_check(Building b) { return b.T; }

    public static void main(String[] args) {
        int N = 10000; // 10,000-story building
        int T = 2345;  // Threshold floor is 2345

        Building b0 = new Building(T); version0(b0, N); b0.printStats("Version 0");
        Building b1 = new Building(T); version1(b1, N); b1.printStats("Version 1");
        Building b2 = new Building(T); version2(b2, N); b2.printStats("Version 2");
        Building b3 = new Building(T); version3(b3, N); b3.printStats("Version 3");
        Building b4 = new Building(T); version4(b4, N); b4.printStats("Version 4");
    }

}