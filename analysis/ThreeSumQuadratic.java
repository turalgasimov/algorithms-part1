public class ThreeSumQuadratic {

    private static int threeSum(int[] arr, int target) {
        int sz = arr.length;
        mergeSort(arr, 0, sz - 1);

        int count = 0;
        for (int i = 0; i < sz - 2; i++) {
            int left = i + 1;
            int right = sz - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    count++;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int sz1 = mid - left + 1;
        int sz2 = right - mid;

        int L[] = new int[sz1];
        int R[] = new int[sz2];
        
        for (int i = 0; i < sz1; i++)
            L[i] = arr[left + i];
        for (int i = 0; i < sz2; i++)
            R[i] = arr[mid + 1 + i];

        int i = 0, j = 0;
        int k = left;
        while (i < sz1 && j < sz2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < sz1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < sz2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, -3, 4, -2, 5, -1};
        int target = 2;
        int count = threeSum(arr, target);
        System.out.println("Number of triplets that sum to " + target + ": " + count);
    }

}