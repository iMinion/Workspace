public class LinkedLists {
	
}

class SingleLinkedList {
	SingleLinkedListNode list;
	SingleLinkedListNode temp;
	public SingleLinkedList(Object n) {
		list.setData(n);
		list.setNext(null);
		temp = list;
	}
	
	public void add(Object n) {
		
	}
	
}

class SingleLinkedListNode {
	Object data;
	SingleLinkedListNode next;
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setNext(SingleLinkedListNode node) {
		this.next = node;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public SingleLinkedListNode getNext() {
		return this.next;
	}
}