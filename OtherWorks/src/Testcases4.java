import java.util.Scanner;
public class Testcases4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine().replaceAll("\t", " "));
		}
		sc.close();
	}
}
