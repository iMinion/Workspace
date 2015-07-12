import java.util.Scanner;

/**********
	 * This is the UNumber recognizer for the acceptor above.  The algorithm is the same.  The 
	 * only difference is that these methods do not produce a UNumber.  They just return true or 
	 * false based on what is found in the input.
	 * 
	 * This method processes the UNumber up to the exponent, States 0 through 5.  State 6 through
	 * 8 are called from so many places, so we have factored that portion out and it is below.
	 * 
	 * @param src
	 * @return
	 */

class FloatingPointRecognizer {
	public boolean hasUNumber(String src) {
		String s = src + ';';												// Add the sentinel at the end 
		int ndx = 0;															// Start at the beginning of the string
		// State 0																// We start in State 0
		if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {				// If the first character is a digit, 
			// State 1															// transition to State 1 and process
			ndx = 1;																// as many more digits as is there
			while (s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') ndx++;	// When we encounter a
			if (s.charAt(ndx) == '.') {									// non-digit, see if it was a decimal 
				// State 2														// point.  If so transition to State 2
				ndx++;															// Accept it and see if there are more
				if (s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') {// is another digit.  If so we
					// State 3													// transition to State 3
					ndx++;														// Accept the digit and then skip as 
					while (s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') ndx++;	// many as there are
																			// When the end of the digit sequence 
					if (s.charAt(ndx) == 'E' || s.charAt(ndx) == 'e')	// If there is an 'E' or 'e'
						return hasUNumberState6(s, ndx+1);				// use State 6 method and return result. 
					// End of State 3, but no exponent, so see if we are at the sentinel.  If so we
					else if (ndx + 1 == s.length()) return true;		// done and we return true
					else return false;										// otherwise, error and return false
					
				// State 2, but it is not a digit						// Therefore, we see if it might be an
				} else if (s.charAt(ndx) == 'E' || s.charAt(ndx) == 'e') {	// an exponent.  If so
					return hasUNumberState6(s, ndx+1);					// return was State6 method does
					
				// State 2, but no digit or exponent					// If it is the sentinel, then we return
				} else if (ndx + 1 == s.length()) return true;		// true
				
				// State 2, but not a digit, an exponent or the sentinel
				else return false;											// It is an error, so return false
				
			// At the end of State 1, but it was not a decimal point that ended it; an exponent?	
			} else if (s.charAt(ndx) == 'E' || s.charAt(ndx) == 'e') {	// Is it an 'E' or 'e'? If
				return hasUNumberState6(s, ndx+1);						// so, return the State6 method result
				
			// At the end of State 1, not a decimal point or an exponent? The sentinel?
			} else if (ndx + 1 == s.length())							// return, else check for the sentinel, 
				 return true;													// and return true if it is there,
			else 																	// else it is an error and 
				return false;													// we should return false
		}
		
		// We are at State 0 the next character was not a digit, see if it is a decimal point
		else if (s.charAt(0) >= '.') {									// If so, then we must transition to
			// State 4															// State 4 to process any digits after
			if (s.charAt(1) >= '0' && s.charAt(1) <= '9') {			// the decimal point.  Is there a digit?
				// state 5														// If so, we need to accept the digit
				ndx++;															// and set up to skip as many more 
				while (s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') ndx++;	// digits as can be found.  
																					// Must be exponent, sentinel, or error.
				if (s.charAt(ndx) == 'E' || s.charAt(ndx) == 'e') {// If 'E' or 'e', then process the
					return hasUNumberState6(s, ndx+1);					// exponent and return what it finds.
				} else if (ndx + 1 == s.length()) return true;		// If sentinel, return true.
				else return false;											// else error and return false.
			}
			// Coming here means that there is no digit before or after the period.
			else return false;												
		}
		// Coming here means the first character is not a digit or a decimal point.
		else return false;													
	}
	/**********
	 * This is the recognizer for the acceptor above.  The algorithm is the same.  The only 
	 * difference is that these methods do not produce a UNumber.  They just return true or false 
	 * based on what is found in the input. This method processes the exponent, States 6, 7, and 8.
	 * 
	 * @param s		The input string to be check to see if it is a UNumber
	 * 
	 * @param ndx	The index of the location within the string to start the process
	 * 
	 * @return		The Boolean value of whether or not the input is a valid UNumber
	 */
	private boolean hasUNumberState6(String s, int ndx) {
		if(s.charAt(ndx) == '+' || s.charAt(ndx) == '-') {// If + or - then process the symbol
															//If so we need to accept the symbol and
			ndx++;											// and set up to skip as many more
			while(s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') ndx++; 	//digits as can be found
			if(ndx + 1 == s.length()) return true;						// If sentinel, return true
			else return false;											// else error and return false
		}
		// coming here means only digits are found
		else if(s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') { //digits as can be found
			ndx++;												//check for the next coming positions
			while(s.charAt(ndx) >= '0' && s.charAt(ndx) <= '9') ndx++; //digits as can be found
			if(ndx + 1 == s.length()) return true;			// if sentinel return true
			else return false;								// else error and return false
		}
		else return false;						//else error and return false

		
	}	
	public static void main(String[] args) {
		FloatingPointRecognizer fp = new FloatingPointRecognizer();
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(fp.hasUNumber(sc.nextLine()));
		sc.close();
	}
}