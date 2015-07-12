import java.util.Scanner;


public class Names {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String inp = sc.nextLine();
			String []s = inp.split(" ");
			String res = "";
			for(int i = 0; i < s.length; i++) {
				res = res + s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase() + " ";
			}
			System.out.println(inp + " = " +res.substring(0, res.length() - 1));
		}
		sc.close();
	}
}