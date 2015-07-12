import java.util.Scanner;
import java.util.Stack;

public class PatternMatching {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String pattern = scan.nextLine();
		scan.close();
		if(MatchPattern(pattern))
		    System.out.println("True");
        else
            System.out.println("False");
	}

	private static boolean MatchPattern(String pattern) {
		// TODO Auto-generated method stub
		Stack<Character> temp = new Stack<Character>();
		for(int i=0; i<pattern.length(); i++){
			char c = pattern.charAt(i);
			if(c == '('){
				temp.push('(');
			}
			else if(c == ')'){
				if(temp.peek() == '(')
					temp.pop();
				else
					return false;
			}
			if(c == '{'){
				temp.push('{');
			}
			else if(c == '}'){
				if(temp.peek() == '{')
					temp.pop();
				else
					return false;
			}
			if(c == '['){
				temp.push('[');
			}
			else if(c == ']'){
				if(temp.peek() == '[')
					temp.pop();
				else
					return false;
			}
		}
		if(temp.isEmpty())
			return true;
		else
			return false;
	}
}
