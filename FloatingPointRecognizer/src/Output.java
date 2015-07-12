import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Output {
	public static void main(String[] args) throws FileNotFoundException {
		int i = 0;
		if(args.length == 2) {
			Scanner sc = new Scanner(System.in);
			PrintWriter pw = new PrintWriter(args[1]);
			int max = 0;
			pw.print('{');
			while(sc.hasNext()) {
				String s = sc.nextLine();
				if(s.length() > max) max = s.length();
				for(i = 0; i < s.length(); i++) {
					if(s.charAt(i) < 97 && s.charAt(i) >122) {
						System.out.println(s);
						break;
					}
				}
				pw.print('"');
				pw.print(s);
				pw.print('"');
				pw.print(',');
				i++;
			}
			System.out.println(max);
			pw.print('}');
			pw.close();
			sc.close();
		}
		System.out.println(i);
	}
}
