import java.util.Scanner;
import java.util.Stack;

public class myTryHanoi {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String temp = sc.nextLine();
		sc.close();
		String[] s = temp.substring(2, temp.length()-9).split(",");
		Stack<Integer> tower[] = new Stack[3];
		tower[0] = new Stack<Integer>();
		tower[1] = new Stack<Integer>();
		tower[2] = new Stack<Integer>();
		for(int i = 0; i < s.length; i++)
			tower[0].push(Integer.parseInt(s[i]));
		System.out.println(s.length);
		recursiveHanoi(s.length, tower[0], tower[2], tower[1]);
		display(tower);
	}
	public static <E> void recursiveHanoi(int n, Stack<E> source, Stack<E> destination, Stack<E> auxilary) {
		if(n == 0) return;
		else {
			recursiveHanoi(n-1, source, auxilary, destination);
			E item = source.pop();
			destination.push(item);
			recursiveHanoi(n-1, auxilary, destination, source);
		}
	}
	public static <E> void display(Stack<E> tower[]){   
		String s = "(<" + tower[0].toString() + ">,<" + tower[1].toString() + ">,<" + tower[2].toString() + ">)";
		System.out.println(s);
	}
}