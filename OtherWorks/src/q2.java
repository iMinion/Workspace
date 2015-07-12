import java.util.Scanner;
public class q2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = 0;
		while(k < 10) {
			String s = sc.nextLine();
			int off = Integer.parseInt(sc.nextLine()) % 26;
			String r = sc.nextLine();
			int coff = Integer.parseInt(sc.nextLine()) % 26;
			String sd = "";
			String rd = "";
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) !=  ' ') {
					int c = s.charAt(i);
					c = (c -97 + off) % 26 + 97;
					sd = sd + (char) c;
				}
				else sd = sd + ' ';
			}
			for(int i = 0; i < r.length(); i++) {
				if(r.charAt(i) != ' ') {
					int c = r.charAt(i);
					c = (c - 97 - coff + 26) % 26 + 97;
					rd = rd + (char) c;
				}
				else rd = rd + ' ';
			}
			System.out.println(sd);
			System.out.println(rd);
			k++;
		}
		sc.close();
	}
}
