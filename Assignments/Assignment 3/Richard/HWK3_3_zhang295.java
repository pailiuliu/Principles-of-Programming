import java.util.ArrayList;
import java.util.Arrays;

public class HWK3_3_zhang295 {

	public static void main(String[] args) {
        int sum;
        int[] new_array;
        int actual_sum;
        ArrayList<Integer> ints = new ArrayList<Integer>();
        // convert args to ints
        int[] nums = new int[args.length];
        for (int i = 0; i < args.length; i++){
                nums[i] = Integer.parseInt(args[i]);
        }
        sum = nums[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
                actual_sum = 0;
                // only enter recursion if the current number is less than sum
                if (nums[i] <= sum) {
                        new_array = Arrays.copyOfRange(nums, i, nums.length);
                        ints = subsetSumProblem(new_array);
                        // sum up everything in ints
                        for (int j : ints) {
                                actual_sum += j;
                        }
                        if (actual_sum == sum) {
                                System.out.println(ints);
                        }
                }
        }
}

public static ArrayList<Integer> subsetSumProblem(int[] nums){
        int sum = nums[nums.length - 1];
        ArrayList<Integer> current_num = new ArrayList<Integer>();
        // you only have the sum number left, so you did not find a set of numbers
        if (nums.length == 1) {
                current_num.add(sum + 1);
                return current_num;
        } else {
                if (nums[0] == sum) {
                        current_num.add(nums[0]);
                        return current_num;
                } if(nums[0] < sum) {
                        // this current number is part of the sum
                        // create a new list with the sum changed to sum - current num
                        // then return a list containing this number + the rest of the numbers that creates the sum
                        int[] array_with_new_sum = Arrays.copyOfRange(nums, 1, nums.length);
                        int new_sum = sum - nums[0];
                        array_with_new_sum[array_with_new_sum.length - 1] = new_sum;
                        current_num.add(nums[0]);
                        current_num.addAll(subsetSumProblem(array_with_new_sum));
                        return current_num;
                } else if (nums[0] > sum) {
                        // this number is not part of the sum so move on to the next one
                        int[] array_with_new_sum = Arrays.copyOfRange(nums, 1, nums.length);
                        return subsetSumProblem(array_with_new_sum);
                }
        }
        // program will never reach here but java won't shut up so this needs to be here
        return current_num;
}

}
