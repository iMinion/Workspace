import java.util.Scanner;
public class AddPrimes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int i = 2;
		int res = 0;
		while(n != 0) {
			int count = 0;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if(i % j == 0) count++;
			}
			if(count == 0) {
				res += i;
				--n;
			}
			i++;
		}
		System.out.println(res);
	}
}
