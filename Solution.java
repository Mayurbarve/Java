
/**
 * Solution
 */
import java.util.*;

public class Solution {

    public static int largeSmallSum(int[] arr) {
        ArrayList<Integer> even = new ArrayList<Integer>();
        ArrayList<Integer> odd = new ArrayList<Integer>();
        even.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                even.add(arr[i]);
            } else {
                odd.add(arr[i]);
            }
        }

        Collections.sort(even);
        Collections.sort(odd);

        return even.get(even.size() - 2) + odd.get(1);

    }

    public static void main(String[] args) {
        System.out.println("Largest Small Summ");

        int[] arr = { 3,2,1,7,5,4 };

        System.out.println(largeSmallSum(arr));
    }
}