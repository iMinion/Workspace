import java.util.Scanner;


public class myPatternMatching {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		myStack<Character> stack = new myStack<Character>();
		for(int i = 0; i < s.length(); ++i) {
			if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
				stack.push(s.charAt(i));
			}
			else if(s.charAt(i) == '}') {
				char c = stack.peek();
				if(c != '{') {
					System.out.println("False");
					System.exit(0);
				}
				else {
					stack.pop();
				}
			}
			else if(s.charAt(i) == ')') {
				char c = stack.peek();
				if(c != '(') {
					System.out.println("False");
					System.exit(0);
				}
				else {
					stack.pop();
				}
			}
			else if(s.charAt(i) == ']') {
				char c = stack.peek();
				if(c != '[') {
					System.out.println("False");
					System.exit(0);
				}
				else {
					stack.pop();
				}
			}
		}
		System.out.println("True");
		sc.close();
	}
}