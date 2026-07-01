public class BitonicSearch {

    private static boolean inBitArray(int[] bitArray, int target) {
        int sz = bitArray.length;

        int l = 0, r = sz-1;
        if (target % 2 == 0) {
            while (bitArray[r] % 2 != 1) {
                if (bitArray[r] == target) return true;
                r--;
            }
        } else {
            while (bitArray[l] % 2 != 0) {
                if (bitArray[l] == target) return true;
                l++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] bitArray = {1,2,3,4,5,6,7,8,9,11,13,151,719,8241,20002,1000,500,100,0};
        int target = 11;

        System.out.println(inBitArray(bitArray, target));
    }

}