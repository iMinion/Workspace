package com.leda2.exam;

import java.util.Arrays;
import java.util.Scanner;

class HashTableLinkedList {
	
	private int value;
	private HashTableLinkedList next;
	
	public HashTableLinkedList() {
		next = null;
	}
	
	public HashTableLinkedList(int value, HashTableLinkedList next) {
		this.value = value;
		this.next = next;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public HashTableLinkedList getNext() {
		return next;
	}

	public void setNext(HashTableLinkedList next) {
		this.next = next;
	}
	
	public String toString() {
		String s = "";
		while(this.getNext() != null) {
			s = s + this.value + ",";
			this.setNext(getNext());
		}
		return s.toString().substring(0, s.length());
	}
}






public class MyHashTablePC2 {
	
	private HashTableLinkedList[] table;
	private int capacity = 10;
	
	public MyHashTablePC2(int capcacity) {
		this.capacity = capcacity;
		table = new HashTableLinkedList[this.capacity];
		Arrays.fill(table, new HashTableLinkedList());
	}
	
	public MyHashTablePC2() {
		table = new HashTableLinkedList[this.capacity];
		Arrays.fill(table, new HashTableLinkedList());
	}

	public int hashCode(int x){
		return x % table.length;
	}
	
	public void insert(int value){
		int hash = hashCode(value);
		HashTableLinkedList entry = table[hash];
		HashTableLinkedList prevEntry = null;
//		if(entry.getNext() == null) {
//			System.out.println("Inserting First time at " + hash);
//			entry.setValue(value);
//		}
//		else { 
			entry = table[hash];
			while (entry != null) {
				System.out.println("In insert\t Value: " + value+ "\thash: " + hash);
				System.out.println();
				if(entry.getValue() < value) {
					prevEntry = entry;
					entry = entry.getNext();
				}
				else break;
			}
			HashTableLinkedList entryNode = new HashTableLinkedList();
			entryNode.setValue(value);
			entryNode.setNext(entry);
			prevEntry.setNext(entryNode);
//		}
	}
	
	public void remove(int value){
		int hash = hashCode( value );
        if (table[hash] != null) 
        {
            HashTableLinkedList prevEntry = null;
            HashTableLinkedList entry = table[hash];
            while (entry != null) 
            {
                prevEntry = entry;
                entry = entry.getNext();
                if (entry.getValue() == value) break;
            }
            if(entry.getNext() != null) prevEntry.setNext(entry.getNext());
        }
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < table.length; ++i) {
			sb.append(i);
			sb.append(": ");
			sb.append(table[i].toString());
			sb.append("\n");
		}
		return sb.toString().substring(0, sb.length() -1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] str = s.split(",");
		MyHashTablePC2 hash = new MyHashTablePC2();
		for(String r: str) {
			char op = r.charAt(0);
			if(op == 'I' || op == 'i') {
				hash.insert(Integer.parseInt(r.substring(1, r.length())));
			}
			else if(op == 'R' || op == 'r') {
				hash.remove(Integer.parseInt(r.substring(1, r.length())));
			}
		}
		sc.close();
		System.out.println(hash);
	}
}