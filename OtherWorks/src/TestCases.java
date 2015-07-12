import java.util.Scanner;

public class TestCases {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s[] = new String[10];
		String op[] = new String[10];
		for(int i = 0; i < 10; i++) {
			s[i] = "Case = " + (i + 1) + "\n";
		}
		int i = 0;
		while(i < 10) {
			int j = 0;
			while(j < 10) {
				if(i != 0) {
					String r = sc.nextLine();
					s[j] = s[j] + r.substring(r.indexOf("= ") + 2) + "\n";
					r = sc.nextLine();
					op[j] = op[j] + r.substring(r.indexOf("= ") + 2) + "\n";
				}
				else {
					String r = sc.nextLine();
					s[j] = s[j] + "input = " + r.substring(r.indexOf("= ") + 2) + "\n";
					r = sc.nextLine();
					op[j] = op[j] + "output = " + r.substring(r.indexOf("= ") + 2) + "\n";
				}
				j++;
			}
			i++;
		}
		sc.close();
		for(i = 0; i < 10; i++) {
			s[i] = s[i] + op[i];
			System.out.println(s[i]);
		}
	}
}
