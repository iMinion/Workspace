import java.util.Scanner;

public class myHanoi {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String temp = sc.nextLine();
		sc.close();
		String[] s = temp.substring(2, temp.length()-9).split(",");
		myStack<Integer> tower[] = new myStack[3];
		tower[0] = new myStack<Integer>();
		tower[1] = new myStack<Integer>();
		tower[2] = new myStack<Integer>();
		for(int i = 0; i < s.length; i++)
			tower[0].push(Integer.parseInt(s[i]));
		recursiveHanoi(s.length, tower[0], tower[2], tower[1]);
		display(tower);
	}
	public static <E> void recursiveHanoi(int n, myStack<E> source, myStack<E> destination, myStack<E> auxilary) {
		if(n == 0) return;
		else {
			recursiveHanoi(n-1, source, auxilary, destination);
			E item = source.pop();
			destination.push(item);
			recursiveHanoi(n-1, auxilary, destination, source);
		}
	}
	public static <E> void display(myStack<E> tower[]){   
		String s = "(<" + tower[0].toString() + ">,<" + tower[1].toString() + ">,<" + tower[2].toString() + ">)";
		System.out.println(s);
	}
}