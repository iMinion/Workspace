import java.util.Scanner;
public class TestCases2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String str[] = s.split(" ");
		s = s.replace(sc.nextLine(), sc.nextLine());
		int max = 0;
		int count = 0;
		String l = sc.nextLine();
		for(String r:str) {
			max = r.length() > max? r.length(): max;
			count += r.equals(l)? 1:0;
		}
		System.out.println(s);
		System.out.println(max);
		System.out.println(count);
		sc.close();
	}
}
