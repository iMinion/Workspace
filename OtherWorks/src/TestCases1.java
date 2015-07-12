import java.util.Scanner;

public class TestCases1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println(s.length()-2);
		System.out.println(reverse(sc.nextLine()));
		sc.close();
	}
	
	static String reverse(String s) {
		String r = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			r = r + s.charAt(i);
		}
		return r;
	}
}
