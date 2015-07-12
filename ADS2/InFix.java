
import java.util.*;
class PostFix{

	Hashtable<Character,Integer> precedence;
	Stack<Character> operators;

	public PostFix() {
		// TODO Auto-generated constructor stub
		precedence = new Hashtable<Character,Integer>();
		operators=new Stack<Character>();
	}

	public void setPriority(String[] st) {
		// TODO Auto-generated method stub
		int priority = 1;
		for(int i=0; i<st.length; i++, priority++)
			for(int j=0;j<st[i].length();j++) 
				precedence.put(st[i].charAt(j),priority);
		precedence.put('(', priority);
	}

	public String toPostFix(String s){
		String postfix="";
		Stack<Character> stk=new Stack<Character>();
		char symbol;
		for(int i=0;i<s.length();i++) {
			symbol = s.charAt(i);
			if(Character.isDigit(symbol)){
				postfix += symbol;
				if(i<s.length()-1&&!Character.isDigit(s.charAt(i+1)))
					postfix += " ";
				if(i==s.length()-1)
					postfix += " ";
			}
			else{
				if(stk.isEmpty()||symbol=='(')
					stk.push(symbol);
				else if(symbol==')'){
					while(!(stk.peek()=='('))
						postfix += stk.pop()+" ";
					stk.pop();
				}
				else{
					int p=prec(symbol);
					if(!stk.isEmpty())
						while(p<=prec(stk.peek())&&!(stk.peek()=='(')){
							postfix += stk.pop()+" ";
							if(stk.isEmpty())
								break;
						}
					stk.push(symbol);
				}
			}
		}
		while(!stk.isEmpty())
			postfix += stk.pop()+" ";
		return postfix;
	}

	private int prec(char symbol) {
		// TODO Auto-generated method stub
		return precedence.get(symbol);
	}

	public int evaluatePostFix(String s){
		LinkedList<String> expression = new LinkedList<String>(Arrays.asList(s.split(" ")));
		for(int i=0; i<expression.size(); i++) {
			//System.out.println(expression.toString());
			if(precedence.containsKey(expression.get(i).charAt(0))){
				//System.out.println(expression.get(i-2)+" "+expression.get(i-1)+" "+expression.get(i));
				int result=operation(Integer.parseInt(expression.get(i-2)), 
						Integer.parseInt(expression.get(i-1)), expression.get(i).charAt(0));
				expression.remove(i-1);
				expression.remove(i-2);
				i=i-2;
				expression.set(i, Integer.toString(result));
			}
		}
		return Integer.parseInt(expression.get(0));
	}

	public int operation(int num1,int num2,char c){
		switch(c){
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			return num1/num2;
		case '%':
			return num1%num2;
		case '^':
			return (int) Math.pow(num1, num2);
		default:
			return -1;
		}
	}
}

public class InFix{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		PostFix infi=new PostFix();
		String[] st = scan.nextLine().split(" ");
		String str = scan.nextLine();
		infi.setPriority(st);
		System.out.println(infi.toPostFix(str));
		System.out.println(infi.evaluatePostFix(infi.toPostFix(str)));
		scan.close();
	}
}