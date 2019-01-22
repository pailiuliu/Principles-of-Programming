
public class HWK3_1_zhang295 {

	public static void main(String[] args) {
        // convert args to ints
        int[] nums = new int[args.length];
        for (int i = 0; i < args.length; i++){
                nums[i] = Integer.parseInt(args[i]);
        }
        System.out.println(binomialCoefficient(nums[0], nums[1]));
	}

	public static int binomialCoefficient(int n, int k) {
        if (n == k || k == 0) {
                return 1;
        } else {
                return binomialCoefficient(n-1, k-1) + binomialCoefficient(n-1, k);
        }
	}

}
