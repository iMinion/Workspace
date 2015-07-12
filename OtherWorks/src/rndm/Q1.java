package rndm;

public class Q1 {

	public static void main(String[] args) {
		
		String s = "Gee ks for ge eks";
		
		for (; s.indexOf(" ") != -1; ) {
			s = s.substring(0, s.indexOf(" ")) + s.substring(s.indexOf(" ") + 1);					 
		}
		
		System.out.println(s);
	}	
}
