
public class HWK3_2_zhang295 {

	public static void main(String[] args) {
		nonContiguousSubstrings(args[0]);
	}
	
	public static void nonContiguousSubstrings(String s) {
		if (s.length() == 2) {
			// exit the program
		} else {
			// use a loop to print non contiguous strings
			for (int i = 1; i < s.length() - 1; i++) {
				System.out.println(s.substring(0, i) + "," + s.substring(i + 1, s.length()));
				if (s.substring(i + 1, s.length()).length() > 1) {
					System.out.println(s.substring(0, i) + "," + s.charAt(i+1));
				}
			}
			// print the first and last characters if that is a non contiguous string
			if (s.indexOf(s.charAt(s.length() - 1)) - s.indexOf(s.charAt(0)) > 2) {
				System.out.println(s.substring(0, 1) + "," + s.substring(s.length() - 1, s.length()));
			}
			nonContiguousSubstrings(s.substring(1, s.length()));
		}
	}

}
