import java.util.Scanner;
public class TestCases3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		String[] str = sc.nextLine().split(" ");
		String s = sc.nextLine();
		int i = 0;
		boolean arr[] = new boolean[100];
		while(!s.equalsIgnoreCase("output = ")) {
			arr[Integer.parseInt(s)] = true;
			s = sc.nextLine();
		}
		System.out.println(i);
		for(i = 0; i < 100; i++) {
			if(!arr[i]) System.out.println(i);
		}
		System.arraycopy(arr, 0, arr, i, 3);
//		int n = Integer.parseInt(sc.nextLine());
//		System.out.println(s.substring(0, n));
		sc.close();
//		System.out.println(str.length);
//		for(String s: str) System.out.println(s);
	}
}
