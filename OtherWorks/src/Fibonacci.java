import java.util.Scanner;
public class Fibonacci {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str[] = new String[10];
		int i = 0;
		while(i < 10) {
			int n = Integer.parseInt(sc.nextLine());
			str[i] = "Case = " + (i + 1) +'\n';
			str[i] = str[i] + "input = " + (n + 2) + '\n';
			long a = 0, b = 1;
			long c = 1;
			str[i] = str[i] + "output = " + a + " " + b;
			int j = 0;
			while(j < n) {
				c = a + b;
				a = b;
				b = c;
				j++;
				str[i] = str[i] + " " + c;
			}
			i++;
		}
		sc.close();
		for(i = 0; i< 10; i++) {
			System.out.println(str[i]);
		}
	}
}
