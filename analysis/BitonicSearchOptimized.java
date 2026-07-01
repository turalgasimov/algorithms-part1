public class BitonicSearchOptimized {

    public static int search(int[] arr, int target) {
        return bitonicSearch(arr, 0, arr.length - 1, target);
    }

    private static int bitonicSearch(int[] arr, int left, int right, int target) {
        if (left > right) return -1;
        if (left == right) return arr[left] == target ? left : -1;

        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;

        if (arr[mid] < target) {
            // Case A: 2 compares per step, continues bitonic recursion
            if (arr[mid] < arr[mid + 1]) {
                return bitonicSearch(arr, mid + 1, right, target); // Ascending slope
            } else {
                return bitonicSearch(arr, left, mid - 1, target);  // Descending slope
            }
        } else {
            // Case B: arr[mid] > target. Stop recursion, run two classic searches
            int leftResult = ascendingSearch(arr, left, mid - 1, target);
            if (leftResult != -1) return leftResult;
            
            return descendingSearch(arr, mid + 1, right, target);
        }
    }

    private static int ascendingSearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    private static int descendingSearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}