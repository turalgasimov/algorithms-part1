public class BitonicSearchStandard {

    // Step 1: Find the index of the peak element ~ 1 lg n
    private static int findPeak(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1; // Peak is to the right
            } else {
                right = mid;    // Peak is mid or to the left
            }
        }
        return left;
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
            if (arr[mid] < target) right = mid - 1; // Smaller elements are to the right
            else left = mid + 1;
        }
        return -1;
    }

    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        
        int peak = findPeak(arr);
        
        // Search left ascending side
        int leftRes = ascendingSearch(arr, 0, peak, target);
        if (leftRes != -1) return leftRes;
        
        // Search right descending side
        return descendingSearch(arr, peak + 1, arr.length - 1, target);
    }
}