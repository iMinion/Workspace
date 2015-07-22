package com.Warmup;

import java.math.BigInteger;

public class ExtraLongFactorials {
	public static void main(String[] args) {
        factorial(7);
    }
    
    public static void factorial(int n) {
        BigInteger[] fact = new BigInteger[n + 1];
        fact[0] = new BigInteger("1");
        fact[1] = new BigInteger("1");
        for(int i = 2; i < fact.length; i++) {
            fact[i] = fact[i-1].multiply(new BigInteger(Integer.toString(i)));
        }
        System.out.println(fact[fact.length - 1]);
    }
}
